package application;
//todo: see sketch on discord
//Pop up page for selected customer from the list, include relevant details
//a text box or something to display the shipping lists, payment options, and past orders
//and 3 buttons: shipping, payment, and orders to swap views

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
	
	public CustomerDetailsPage() {
		
	}
	public VBox getNode() {
		return vbxMain;
	}
	
}
