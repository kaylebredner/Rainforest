package application;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Cart {
    private HBox mainBox = new HBox();
    private VBox itemBox = new VBox();
    private VBox checkoutBox = new VBox();
    private ScrollPane itemScroll = new ScrollPane(itemBox);
    private String messageString;
    private Button btnCheckout = new Button("Checkout");
    private Label lblPrice = new Label();
    private Label lblGreeting = new Label();
    private String priceString;

    public Cart(){
        messageString="UserID: not supplied, cart not shown";
        lblGreeting.setText(messageString);
        checkoutBox.getChildren().add(lblPrice);
        checkoutBox.getChildren().add(btnCheckout);
        mainBox.getChildren().add(itemScroll);
        mainBox.getChildren().add(checkoutBox);
        mainBox.setAlignment(Pos.CENTER);
    }
    public HBox getCart(){
        return mainBox;
    }
}
