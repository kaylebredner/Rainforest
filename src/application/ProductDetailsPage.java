package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

//todo: see sketch on discord
//pop up page for selected product from the list with relevant details from DB
//and an edit button
public class ProductDetailsPage {

	private Button editBtn = new Button("Edit");
	private ImageView imageView = new ImageView();
	private Label priceLbl = new Label("Price: ");
	private Label nameLbl = new Label("Name: ");
	private Label vendorLbl = new Label("Vendor: ");
	private Label categoryLbl = new Label("Category: ");
	private Label weightLbl = new Label("Weight: ");
	private Label amountLbl = new Label("Amount: ");
	private Label locationLbl = new Label("Location: ");
	
	private HBox nameAndPrice = new HBox(nameLbl, priceLbl);
	private HBox weightAndAmount = new HBox(weightLbl, amountLbl);
	private HBox catAndVendor = new HBox(categoryLbl, vendorLbl);
	private HBox location = new HBox(locationLbl);
	
	//add all the hbox's to this
	private VBox mainVbox = new VBox();
//	private Scene productDetailsScene = new Scene(mainVbox);
	
	//SQL
	Connection connection = null;
	Statement statement;
	
	public ProductDetailsPage(String productName) throws SQLException {
		String url = "jdbc:mysql://localhost/rainforest";
		
		String username = "Rainforest";
		
		String password = "Rainforest123!";
		try {
			imageView.setImage(new Image(new FileInputStream("default-product-image.png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		connection = DriverManager.getConnection(url,username,password);
		System.out.println("connected");
		mainVbox.getChildren().addAll(imageView,nameAndPrice,weightAndAmount,catAndVendor,location);
		initDetails(productName);
	}
	
	private void initDetails(String productName) throws SQLException {
		statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * From Product_t where ProductName = '"+productName+"';");
		result.next();
		nameLbl.setText("Name: " + result.getString("ProductName"));
		BigDecimal price = result.getBigDecimal("ProductPrice");
		priceLbl.setText("Price: " + price);
	}
	
	public VBox getNode() {
		return mainVbox;
	}
}
