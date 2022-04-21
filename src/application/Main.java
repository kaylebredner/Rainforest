package application;
	
import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

	//Item Containers



	//Containers
	HBox hbxMenu = new HBox(10,btnRainforest,btnCart,lblSearch,cbxCategory,txtSearch);
	Product product[][] = new Product[3][3];
	

	GridPane itemGrid = new GridPane();
	VBox vbxItems = new VBox(10,hbxMenu);
	HBox hbxItem = new HBox();
	
	public void start(Stage primaryStage) {
		try {
			BuildProducts();
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
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				product[i][j]=new Product();
				itemGrid.add(product[i][j].mainBox,j,i);
				
			}
		}
		vbxItems.getChildren().clear();
		vbxItems.getChildren().add(hbxMenu);
		vbxItems.getChildren().add(itemGrid);
		
		
		
		

	}
}
