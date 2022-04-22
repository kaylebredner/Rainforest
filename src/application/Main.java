package application;
	
import javafx.application.Application;
<<<<<<< Updated upstream
import javafx.scene.image.ImageView;
=======
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
	
	//controls
	Button btnRainforest = new Button("Rainforest");
	Button btnCart = new Button("Cart");
	Label lblSearch = new Label("Search");
	TextField txtSearch = new TextField();
	ComboBox<String> cbxCategory = new ComboBox<>();

	//Item Containers

=======
	private BorderPane root = null;
	private ProductPage productPage = null;
	private CustomerPage customerPage = null;
>>>>>>> Stashed changes


	//Containers
	HBox hbxMenu = new HBox(10,btnRainforest,btnCart,lblSearch,cbxCategory,txtSearch);
	Product product[][] = new Product[3][3];
	

	GridPane itemGrid = new GridPane();
	VBox vbxItems = new VBox(10,hbxMenu);
	HBox hbxItem = new HBox();
	
	public void start(Stage primaryStage) {
		try {
<<<<<<< Updated upstream
			BuildProducts();
			cbxCategory.getItems().addAll("All Items","Electronics","Grocery");
			cbxCategory.getSelectionModel().select(0);
			BorderPane root = new BorderPane(vbxItems);
			Scene scene = new Scene(root,1200,700);
=======
			productPage = new ProductPage();
			root = new BorderPane(productPage.getNode());
			Scene scene = new Scene(root,450,475);
>>>>>>> Stashed changes
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
	
<<<<<<< Updated upstream
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
=======
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

	public static void main(String[] args) {
		launch(args);
	}
	
	


	class ExitHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			System.exit(0);
		}
	}

	//todo: when pressed swaps root to productPage
	class ProductPageViewHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			root.setCenter(productPage.getNode());
		}
	}

	//todo: when pressed swaps root to customerPage
	class CustomerPageViewHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			root.setCenter(customerPage.getNode());
		}
	}
}
>>>>>>> Stashed changes
