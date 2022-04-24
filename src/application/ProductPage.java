package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	static Connection connection;
	static Statement statement;
	
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
	
	public ProductPage() throws SQLException{
		vbxButtons.setAlignment(Pos.CENTER);
		vbxMain.getChildren().add(hbxCatAndSearch);
		vbxMain.getChildren().add(hbxPrdctListAndBtns);
		cbxCategory.getItems().addAll("All Categories","Electronics","Home and Office");
//		hbxPrdctListAndBtns.setAlignment(Pos.CENTER);
//		hbxCatAndSearch.setAlignment(Pos.CENTER);
		cbxCategory.getSelectionModel().select(0);	
		propogateList("");
		initEventHandlers();
		}
	
	public VBox getNode() {
		return vbxMain;
	}
	public void propogateList(String extraQuery) throws SQLException{
		String url = "jdbc:mysql://localhost/rainforest";
		
		String username = "Rainforest";
		
		String password = "Rainforest123!";
		

		connection = DriverManager.getConnection(url,username,password);
		statement = connection.createStatement();
		lstProducts.getItems().clear();
		ResultSet result = statement.executeQuery("SELECT ProductName FROM Product_t" + extraQuery+";");
		
		while (result.next()) {
			lstProducts.getItems().add(result.getString("ProductName"));
		}
		connection.close();
	}
	
	private void initEventHandlers() {
		btnDetails.setOnAction(new DetailsBtnHandler());
		btnRemove.setOnAction(new RemoveBtnHandler());
		btnRefresh.setOnAction(new RefreshBtnHandler());
		btnAdd.setOnAction(new AddBtnHandler());
		cbxCategory.setOnAction(new CbxSelectionHandler());
	}
	
	
	
	class DetailsBtnHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			String strSelected  = lstProducts.getSelectionModel().getSelectedItem();
			ProductDetailsPage productDetails = null;
			try {
				productDetails = new ProductDetailsPage(strSelected);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
	class CbxSelectionHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			int index = cbxCategory.getSelectionModel().getSelectedIndex();
			String category = cbxCategory.getSelectionModel().getSelectedItem();
			try {
				if (index == 0) {
					propogateList("");
				}
				else {
					propogateList(", category_t\r\n" + 
						"Where product_t.categoryID = category_t.categoryID\r\n" + 
						"AND category_t.categoryName like '%"+ category +"';");
				}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
