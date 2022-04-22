package application;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProductList {
	private VBox vbxMain = new VBox();
	//product list class is incomplete
	
	
	private ListView<String> lstProducts = new ListView<String>();
	private Button btnAdd = new Button("Add Product");
	private Button btnRemove = new Button("Remove Selected Product");
	private Button btnDetails = new Button("Product Details");
	private Button btnRefresh = new Button("Refresh");
	private Label lblSearch = new Label("Search");
	private TextField txtSearch = new TextField();
	private ComboBox<String> cbxCategory = new ComboBox<String>();
	private HBox hbxSearch = new HBox(50,lblSearch,txtSearch);
	private VBox vbxProductList = new VBox(cbxCategory,lstProducts);
	private VBox vbxButtons = new VBox(20,btnAdd,btnRemove,btnDetails,btnRefresh);
	private HBox hbxContainer = new HBox(50,vbxProductList,vbxButtons);
	
	public ProductList(){
		vbxMain.getChildren().add(hbxSearch);
		vbxMain.getChildren().add(hbxContainer);
		cbxCategory.getItems().add("All Departments");
		hbxContainer.setAlignment(Pos.CENTER);
		hbxSearch.setAlignment(Pos.CENTER);
		cbxCategory.getSelectionModel().select(0);	
		}
	public VBox getNode() {
		return vbxMain;
	}
	public void propogateList() {
		
	}
	}
