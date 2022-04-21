package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


import java.io.FileNotFoundException;


public class Main extends Application {

	//controls
	Button btnRainforest = new Button("Rainforest");
	Button btnCart = new Button("Cart");
	Label lblSearch = new Label("Search");
	TextField txtSearch = new TextField();
	ComboBox<String> cbxCategory = new ComboBox<>();
	Button btnOrders = new Button("Orders");


	//Item Containers



	//Containers
	HBox hbxMenu = new HBox(10,btnRainforest,btnCart,btnOrders,lblSearch,cbxCategory,txtSearch);
	Product[][] product = new Product[3][3];

	GridPane itemGrid = new GridPane();
	VBox vbxItems = new VBox(10,hbxMenu);



	public void start(Stage primaryStage) {
		try {
			hbxMenu.setAlignment(Pos.CENTER);

			vbxItems.setAlignment(Pos.TOP_CENTER);
			itemGrid.setAlignment(Pos.CENTER);
			btnOrders.setPrefWidth(100);
			BuildProducts();
			btnRainforest.addEventHandler(MouseEvent.MOUSE_CLICKED,rainforestHandler);
			btnCart.addEventHandler(MouseEvent.MOUSE_CLICKED,cartHandler);
			cbxCategory.getItems().addAll("All Items","Electronics","Grocery");
			cbxCategory.getSelectionModel().select(0);
			BorderPane root = new BorderPane(vbxItems);
			Scene scene = new Scene(root,1200,700);
			primaryStage.setTitle("Rainforest");
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	public void BuildProducts() throws FileNotFoundException{
		if(!itemGrid.getChildren().isEmpty()){
			clearProducts();
		}
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				product[i][j]=new Product();
				itemGrid.add(product[i][j].getNode(),j,i);

			}
		}
		vbxItems.getChildren().add(itemGrid);
		itemGrid.setAlignment(Pos.CENTER);

	}
	public  void clearProducts(){
		itemGrid.getChildren().clear();
		vbxItems.getChildren().remove(itemGrid);
	}
	public void clearPage(){
		vbxItems.getChildren().clear();
		vbxItems.getChildren().add(hbxMenu);
	}
	EventHandler<MouseEvent> cartHandler = event -> {
		Cart newCart = new Cart();
		clearProducts();
		clearPage();
		vbxItems.getChildren().add(newCart.getCart());
	};
	EventHandler<MouseEvent> rainforestHandler = event -> {
		try {
			clearPage();
			BuildProducts();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	};
}
