package application;

import java.math.BigDecimal;
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

public class AddProductPage {
	
	private Button okBtn = new Button("OK");
	private Button cancelBtn = new Button("Cancel");
	
	private Label vendorLbl = new Label("Vendor ID: ");
	private Label inventoryLbl = new Label("Inventory ID: ");
	private Label categoryLbl = new Label("Category ID: ");
	private Label nameLbl = new Label("Name: ");
	private Label priceLbl = new Label("Price: ");
	private Label weightLbl = new Label("Weight: ");
	
	private TextField vendorTxt = new TextField();
	private TextField inventoryTxt = new TextField();
	private TextField categoryTxt = new TextField();
	private TextField nameTxt = new TextField();
	private TextField priceTxt = new TextField();
	private TextField weightTxt = new TextField();
	
	private HBox btnHbox = new HBox(okBtn,cancelBtn);
	private HBox vendorHbox = new HBox(vendorLbl,vendorTxt);
	private HBox inventoryHbox = new HBox(inventoryLbl,inventoryTxt);
	private HBox categoryHbox = new HBox(categoryLbl,categoryTxt);
	private HBox nameHbox = new HBox(nameLbl,nameTxt);
	private HBox priceHbox = new HBox(priceLbl,priceTxt);
	private HBox weightHbox = new HBox(weightLbl,weightTxt);
	
	private VBox main = new VBox(nameHbox,priceHbox,categoryHbox,inventoryHbox,weightHbox,vendorHbox,btnHbox);
	
	
	Connection connection = null;
	Statement statement;
	
	public AddProductPage() throws SQLException{
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
			
			int vendorId = Integer.parseInt(vendorTxt.getText());
			int inventoryId = Integer.parseInt(inventoryTxt.getText());
			int categoryId = Integer.parseInt(categoryTxt.getText());
			String productName = nameTxt.getText();
			BigDecimal productPrice = BigDecimal.valueOf(Double.parseDouble(priceTxt.getText()));
			BigDecimal productWeight = BigDecimal.valueOf(Double.parseDouble(weightTxt.getText()));
			
			try {
				connection = DriverManager.getConnection(url,username,password);
				System.out.println("connected");
				statement = connection.createStatement();
				statement.executeUpdate("insert into product_t(VendorID, InventoryID, CategoryID, ProductName, ProductPrice, ProductWeight) values\r\n" + 
						"	("+vendorId+","+inventoryId+","+categoryId+", '"+productName+"' ,"+productPrice+","+productWeight+");");
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
