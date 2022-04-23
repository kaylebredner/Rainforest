package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
	
	public ProductDetailsPage() {
		try {
			imageView.setImage(new Image(new FileInputStream("default-product-image.png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		nameAndPrice.setSpacing(10);
		mainVbox.getChildren().addAll(imageView,nameAndPrice,weightAndAmount,catAndVendor,location);
	}
	
	public VBox getNode() {
		return mainVbox;
	}
}
