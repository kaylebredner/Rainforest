package application;

import application.ProductPage.AddBtnHandler;
import application.ProductPage.DetailsBtnHandler;
import application.ProductPage.RefreshBtnHandler;
import application.ProductPage.RemoveBtnHandler;
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerPage {
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
	
	public CustomerPage() throws SQLException{
		openConnection();
		propogateList();
		vbxButtons.setAlignment(Pos.CENTER);
		vbxMain.getChildren().add(hbxCatAndSearch);
		vbxMain.getChildren().add(hbxPrdctListAndBtns);
		cbxCategory.getItems().add("Premium");
//		hbxPrdctListAndBtns.setAlignment(Pos.CENTER);
//		hbxCatAndSearch.setAlignment(Pos.CENTER);
		cbxCategory.getSelectionModel().select(0);	
		
		initEventHandlers();
		}
	public VBox getNode() {
		return vbxMain;
	}
	public void propogateList() throws SQLException {

		String url = "jdbc:mysql://localhost/rainforest";
		
		String username = "Rainforest";
		
		String password = "Rainforest123!";
		
		try {
			connection = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		statement = connection.createStatement();

		openConnection();

		clearList();
		ResultSet result = statement.executeQuery("SELECT CustomerName FROM Customer_t;");
		
		while (result.next()) {
			lstProducts.getItems().add(result.getString("CustomerName"));
		}
		closeConnection();
		
	}
	public void clearList() {
		if(!lstProducts.getItems().isEmpty()) {
			lstProducts.getItems().clear();
		}
	}
	public String getSelected() {
		String strSelected  = lstProducts.getSelectionModel().getSelectedItem();
		return strSelected;
	}
	public void openConnection() throws SQLException {
		String url = "jdbc:mysql://localhost/rainforest";
		
		String username = "Rainforest";
		
		String password = "Rainforest123!";
		
		try {
			connection = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		statement = connection.createStatement();
	}
	public void closeConnection() throws SQLException {
		connection.close();
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
			
			CustomerDetailsPage customer = null;
			try {
				customer = new CustomerDetailsPage(getSelected());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			VBox main = customer.getNode();
			Scene customerDetailsScene = new Scene(main,450,700);
			Stage customerDetailsStage = new Stage();
			customerDetailsStage.setTitle("Customer Details");
			customerDetailsStage.setScene(customerDetailsScene);
			customerDetailsStage.show();
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
			try {
				openConnection();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				statement.executeQuery("delete from customer_t where customerName ="+getSelected());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				propogateList();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	class RefreshBtnHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			try {
				propogateList();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
