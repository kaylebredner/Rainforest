package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.ProductPage.DetailsBtnHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//todo: see sketch on discord
//pop up page for selected product from the list with relevant details from DB
//and an edit button
public class ProductDetailsPage {

	
	private ImageView imageView = new ImageView();
	private Label priceLbl = new Label("Price: ");
	private Label nameLbl = new Label("Name: ");
	private Label vendorLbl = new Label("Vendor: ");
	private Label categoryLbl = new Label("Category: ");
	private Label weightLbl = new Label("Weight: ");
	private Label quantityLbl = new Label("Quantity: ");
//	private Label positionLbl = new Label("Position: ");
	
//	private HBox nameAndPrice = new HBox(nameLbl, priceLbl);
//	private HBox weightAndAmount = new HBox(weightLbl, amountLbl);
//	private HBox catAndVendor = new HBox(categoryLbl, vendorLbl);
//	private HBox location = new HBox(locationLbl);
	
	private VBox details = new VBox(nameLbl,priceLbl,categoryLbl,vendorLbl,weightLbl,quantityLbl);
	
	//add all the hbox's to this
	private VBox mainVbox = new VBox();
//	private Scene productDetailsScene = new Scene(mainVbox);
	
	//SQL
	Connection connection = null;
	Statement statement;
	
	public ProductDetailsPage(String productName) throws SQLException, FileNotFoundException {
		String url = "jdbc:mysql://localhost/rainforest";
		
		String username = "Rainforest";
		
		String password = "Rainforest123!";
//		try {
//			imageView.setImage(new Image(new FileInputStream("default-product-image.png")));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		connection = DriverManager.getConnection(url,username,password);
		System.out.println("connected");
		mainVbox.getChildren().addAll(imageView,details);
		initDetails(productName);
	}
	
	private void initDetails(String productName) throws SQLException, FileNotFoundException {
		statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * From Product_t where ProductName = '"+productName+"';");
		result.next();
		nameLbl.setText("Name: " + result.getString("ProductName"));
		BigDecimal price = result.getBigDecimal("ProductPrice");
		priceLbl.setText("Price: $" + price);
		BigDecimal weight = result.getBigDecimal("ProductWeight");
		weightLbl.setText("Weight: " + weight + " lbs");
		String imageUrl = result.getString("ImageURL");
		if (imageUrl != null) {
			imageView.setImage(new Image(imageUrl));
		}
		else {
			imageView.setImage(new Image(new FileInputStream("default-product-image.png")));
		}
		System.out.println(imageUrl);
		imageView.setFitHeight(550);
		imageView.setFitWidth(450);
		imageView.setPreserveRatio(true);
//		imageView.setImage(new Image(new FileInputStream(imageUrl)));
		
		//add position in select here once it's inserted into the DB
		result = statement.executeQuery("select quantity\r\n" + 
				"from inventory_t, product_t\r\n" + 
				"where product_t.inventoryID = inventory_t.inventoryID\r\n" + 
				"And product_t.productName like '%"+ productName +"';");
		result.next();
		//todo: pull position like normal and set the label once it's inserted
		int quantity = result.getInt("quantity");
		quantityLbl.setText("Quantity: " + quantity);
		
		result = statement.executeQuery("select vendorName\r\n" + 
				"from vendor_t, product_t\r\n" + 
				"where product_t.vendorID = vendor_t.vendorID\r\n" + 
				"AND product_t.productName like '%" + productName+"';");
		result.next();
		vendorLbl.setText("Vendor: " + result.getString("vendorName"));
		
		result = statement.executeQuery("select categoryName\r\n" + 
				"from category_t, product_t\r\n" + 
				"where product_t.categoryID = category_T.categoryID\r\n" + 
				"AND product_t.productName like '%"+productName+"';");
		result.next();
		categoryLbl.setText("Category: " + result.getString("categoryName"));
		
//		result = statement.executeQuery("select imageURL\r\n" + 
//				"from product_t\r\n" + 
//				"where productName = '+"+productName+"';");
		
		
//		categoryLbl.setText("Category: "+result.getString("));
	}
	
	public VBox getNode() {
		return mainVbox;
	}
}
