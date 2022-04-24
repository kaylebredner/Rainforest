package application;
//todo: see sketch on discord
//Pop up page for selected customer from the list, include relevant details
//a text box or something to display the shipping lists, payment options, and past orders
//and 3 buttons: shipping, payment, and orders to swap views

import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//this is the least concrete and most changeable page imo. Feel free to throw ideas on discord if you think something is easier.
public class CustomerDetailsPage {
	//controls
	private ListView<String> lstSelected = new ListView<String>();
	private Button btnShipping = new Button("Shipping");
	private Button btnPayment = new Button("Payment");
	private Button btnOrders = new Button("Orders");
	private Label lblName = new Label("Name");
	private Label lblAddress = new Label("Address");
	private CheckBox cbxPremium = new CheckBox();
	private Label lblPremium = new Label("Premium");
	
	//containers
	private HBox hbxNameAddress = new HBox(10,lblName,lblAddress);
	private HBox hbxPremium = new HBox(10,cbxPremium,lblPremium);
	private VBox vbxPremium = new VBox(20,hbxNameAddress,hbxPremium);
	private VBox vbxButtons  = new VBox(20,btnShipping,btnPayment,btnOrders);
	private HBox hbxListButtons = new HBox(20,lstSelected,vbxButtons);
	private VBox vbxMain = new VBox(20,vbxPremium,hbxListButtons);
	//other variables
	private int selectedButton=2;
	//sql
	Connection connection = null;
	Statement statement;
	public CustomerDetailsPage() throws SQLException {
		
	}
	public CustomerDetailsPage(String name) throws SQLException {
		
		String url = "jdbc:mysql://localhost/rainforest";
		
		String username = "Rainforest";
		
		String password = "Rainforest123!";
		
		try {
			connection = DriverManager.getConnection(url,username,password);
			System.out.println("connected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getCustomerDetails(name);
	}
	public VBox getNode() {
		return vbxMain;
	}
	public Label getName() {
		return lblName;
	}
	public Label getAddress() {
		return lblAddress;
	}
	public void setSelectedButton(int i) {
		selectedButton=i;
	}
	public int getSelectedButton() {
		return selectedButton;
	}
	public void getCustomerDetails(String name) throws SQLException {
		statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * From Customer_t where CustomerName = '"+name+"';");
		result.next();
		getName().setText("Name: " + result.getString("CustomerName"));
		getAddress().setText("Address: " + result.getString("CustomerAddress"));
		if(result.getInt("CustomerPremium")==1) {
			cbxPremium.selectedProperty().set(true);
		}
		else {
			cbxPremium.selectedProperty().set(false);
		}
		
	}
	
}
