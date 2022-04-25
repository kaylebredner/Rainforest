package application;


import java.sql.Connection;
import java.sql.DriverManager;
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
	
	private Label vendorLbl = new Label("Address: ");
	private Label inventoryLbl = new Label("Customer Premium: ");
	private Label nameLbl = new Label("Name: ");
	
	
	private TextField vendorTxt = new TextField();
	private TextField inventoryTxt = new TextField();
	
	private TextField nameTxt = new TextField();
	
	private HBox btnHbox = new HBox(okBtn,cancelBtn);
	private HBox vendorHbox = new HBox(vendorLbl,vendorTxt);
	private HBox inventoryHbox = new HBox(inventoryLbl,inventoryTxt);
	private HBox nameHbox = new HBox(nameLbl,nameTxt);
	
	private VBox main = new VBox(nameHbox,inventoryHbox,vendorHbox,btnHbox);
	
	
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
			
			try {
				connection = DriverManager.getConnection(url,username,password);
				System.out.println("connected");
				statement = connection.createStatement();
				statement.executeUpdate("insert into customer_t(CustomerName,CustomerAddress,CustomerPremium /*has premium if value is 1, if 0 does not*/) values" +
				"('"+productName+"', '"+customerAddress+"',"+customerPrem+");");
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
