package application;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddCustomerPage {
	
	private Button okBtn = new Button("OK");
	private Button cancelBtn = new Button("Cancel");
	
	private Label addressLbl = new Label("Address: ");
	private Label premiumLbl = new Label("Customer Premium: ");
	private Label nameLbl = new Label("Name: ");
	private Label locationNameLbl = new Label("Location Name: ");
	private Label addr1Lbl = new Label("Address 1: ");
	private Label addr2Lbl = new Label("Address 2 (optional): ");
	private Label cityLbl = new Label("City: ");
	private Label stateLbl = new Label("State: ");
	private Label zipLbl = new Label("Zip: ");
	
	private TextField vendorTxt = new TextField();
	private TextField inventoryTxt = new TextField();
	private TextField nameTxt = new TextField();
	private TextField locationNameTxt = new TextField();
	private TextField addr1Txt = new TextField();
	private TextField addr2Txt = new TextField();
	private TextField cityTxt = new TextField();
	private TextField stateTxt = new TextField();
	private TextField zipTxt = new TextField();
	
	
	private HBox btnHbox = new HBox(okBtn,cancelBtn);
	private HBox addressHbox = new HBox(addressLbl,vendorTxt);
	private HBox premiumHbox = new HBox(premiumLbl,inventoryTxt);
	private HBox nameHbox = new HBox(nameLbl,nameTxt);
	private HBox locationHbox = new HBox(locationNameLbl,locationNameTxt);
	private HBox addr1Hbox = new HBox(addr1Lbl,addr1Txt);
	private HBox addr2Hbox = new HBox(addr2Lbl,addr2Txt);
	private HBox cityHbox = new HBox(cityLbl,cityTxt);
	private HBox stateHbox = new HBox(stateLbl,stateTxt);
	private HBox zipHbox = new HBox(zipLbl,zipTxt);
	
	private VBox main = new VBox(nameHbox,premiumHbox,addressHbox,locationHbox,addr1Hbox,addr2Hbox,cityHbox,stateHbox,zipHbox,btnHbox);
	
	
	Connection connection = null;
	Statement statement;
	
	public AddCustomerPage() throws SQLException{
		okBtn.setOnAction(new OkBtnHandler());
		cancelBtn.setOnAction(new CancelBtnHandler());
	}
	
	public VBox getNode() {
		return main;
	}
	
	class OkBtnHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			String url = "jdbc:mysql://localhost/rainforest";
			String username = "Rainforest";
			String password = "Rainforest123!";
			
			int customerPrem = Integer.parseInt(inventoryTxt.getText());
			String productName = nameTxt.getText();
			String customerAddress = vendorTxt.getText();
			String addr1 = addr1Txt.getText();
			String addr2 = addr2Txt.getText();
			String city = cityTxt.getText();
			String state = stateTxt.getText();
			String zip = zipTxt.getText();
			
			try {
				connection = DriverManager.getConnection(url,username,password);
				System.out.println("connected");
				statement = connection.createStatement();
				statement.executeUpdate("insert into shipmentLocation_t(LocationName,address1,address2,city,state,zipcode) values"
						+ "('"+customerAddress+"','"+addr1+"','"+addr2+"','"+city+"','"+state+"',"+zip+");");
				int locationID;
				ResultSet result = statement.executeQuery("Select locationID from ShipmentLocation_t order by LocationID desc;");
				result.next();
				locationID = result.getInt("LocationID");
				statement.executeUpdate("insert into customer_t(CustomerName,locationID,CustomerPremium /*has premium if value is 1, if 0 does not*/) values" +
				"('"+productName+"', '"+locationID+"',"+customerPrem+");");
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	class CancelBtnHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
	
		}
	}
}
