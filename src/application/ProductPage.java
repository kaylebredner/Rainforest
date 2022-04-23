package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProductPage {
	private VBox vbxMain = new VBox();
	//product list class is incomplete
	
	
	private ListView<String> lstProducts = new ListView<String>();
	private Button btnAdd = new Button("Add");
	private Button btnRemove = new Button("Remove");
	private Button btnDetails = new Button("Details");
	private Button btnRefresh = new Button("Refresh");
	private Label lblSearch = new Label("Search");
	private TextField txtSearch = new TextField();
	private ComboBox<String> cbxCategory = new ComboBox<String>();
	private HBox hbxCatAndSearch = new HBox(25,cbxCategory,lblSearch,txtSearch);
//	private VBox vbxProductList = new VBox(lstProducts);
	private VBox vbxButtons = new VBox(20,btnAdd,btnRemove,btnDetails,btnRefresh);
	private HBox hbxPrdctListAndBtns = new HBox(50,lstProducts,vbxButtons);
	
	public ProductPage(){
		vbxButtons.setAlignment(Pos.CENTER);
		vbxMain.getChildren().add(hbxCatAndSearch);
		vbxMain.getChildren().add(hbxPrdctListAndBtns);
		cbxCategory.getItems().add("All Departments");
//		hbxPrdctListAndBtns.setAlignment(Pos.CENTER);
//		hbxCatAndSearch.setAlignment(Pos.CENTER);
		cbxCategory.getSelectionModel().select(0);	
		
		initEventHandlers();
		}
	
	public VBox getNode() {
		return vbxMain;
	}
	public void propogateList() {
		
	}
	
	private void initEventHandlers() {
		btnDetails.setOnAction(new DetailsBtnHandler());
		btnRemove.setOnAction(new RemoveBtnHandler());
		btnRefresh.setOnAction(new RefreshBtnHandler());
		btnAdd.setOnAction(new AddBtnHandler());
	}
	
	
	
	class DetailsBtnHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			ProductDetailsPage productDetails = new ProductDetailsPage();
			VBox main = productDetails.getNode();
			Scene productDetailsScene = new Scene(main,450,700);
			Stage productDetailsStage = new Stage();
			productDetailsStage.setTitle("Product Details");
			productDetailsStage.setScene(productDetailsScene);
			productDetailsStage.show();
		}
	}
	
	class AddBtnHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			
		}
	}
	
	class RemoveBtnHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			
		}
	}
	
	class RefreshBtnHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			
		}
	}
}
