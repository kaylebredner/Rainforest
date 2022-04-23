package application;
	
import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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

	private BorderPane root = null;
	private ProductPage productPage = null;
	private CustomerPage customerPage = null;

	public void start(Stage primaryStage) {
		try {
			productPage = new ProductPage();
			root = new BorderPane(productPage.getNode());
			Scene scene = new Scene(root,450,475);
			primaryStage.setTitle("Rainforest");
			
			customerPage = new CustomerPage();
			
			//Create Menu bars and attach them
			initMenuBar();
			
			
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

	//creates all the menu bar buttons and attaches event handlers to them
	private void initMenuBar() {
		Menu fileMenu = new Menu("File");
		MenuItem exit = new MenuItem("Exit");
		fileMenu.getItems().add(exit);
		
		Menu viewMenu = new Menu("View");
		MenuItem productView = new MenuItem("Products");
		MenuItem customerView = new MenuItem("Customers");
		viewMenu.getItems().addAll(productView,customerView);
		
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(fileMenu);
		menuBar.getMenus().add(viewMenu);
		
		exit.setOnAction(new ExitHandler());
		productView.setOnAction(new ProductPageViewHandler());
		customerView.setOnAction(new CustomerPageViewHandler());
		
		//attach menu element to the scene
		root.setTop(menuBar);
	}
	
	//BELOW ARE EVENT HANDLERS - being implemented as anonymous inner classes for readability.
	
	class ExitHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			System.exit(0);
		}
	}

	class ProductPageViewHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			root.setCenter(productPage.getNode());
		}
	}

	class CustomerPageViewHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			root.setCenter(customerPage.getNode());
		}
	}
}
