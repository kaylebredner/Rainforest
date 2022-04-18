package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;



public class Main extends Application {
	
	//controls
	Button btnRainforest = new Button("Rainforest");
	Button btnCart = new Button("Cart");
	Label lblSearch = new Label("Search");
	TextField txtSearch = new TextField();
	ComboBox<String> cbxCategory = new ComboBox<>();
	
	//Containers
	HBox hbxMenu = new HBox(10,btnRainforest,btnCart,lblSearch,cbxCategory,txtSearch);
	public void start(Stage primaryStage) {
		try {
			cbxCategory.getItems().addAll("All Items");
			cbxCategory.getSelectionModel().select(0);
			BorderPane root = new BorderPane(hbxMenu);
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
}
