package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Product {
    VBox mainBox = new VBox();

    ImageView productImage = new ImageView();
    Image image;

    {
        try {
            image = new Image(new FileInputStream("default-product-image.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    Label lblPrice = new Label("$0.00");
    Label lblName = new Label();
    Button btnPurchase = new Button("Purchase");
    HBox  pricePurchase = new HBox(10,lblPrice,btnPurchase);
    ;

    Product() throws FileNotFoundException {
        lblName.setText("ItemNotFound");
        mainBox.getChildren().add(productImage);
        mainBox.getChildren().add(lblName);
        mainBox.getChildren().add(pricePurchase);
        productImage.setImage(image);
        productImage.setFitHeight(125);
        productImage.setFitWidth(125);

    }
    VBox getNode(){
        return mainBox;
    }
}
