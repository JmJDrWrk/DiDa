package com.example.dida;

import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class DatabaseController implements Initializable{
	public static Users selected_user;
    private String USERS_CREATE = 
    		"CREATE TABLE USERS"
    		+"(id INTEGER,"
    		+"name VARCHAR,"
    		+"surname VARCHAR,"
    		+"mail VARCHAR,"
    		+"isadmin VARCHAR,"
    		+"pass VARCHAR,"
    		+"id_h VARCHAR)";
    
	private String USERS_SELECT = "SELECT * FROM users";
	//private String DATABASE = "jdbc:h2:E:/DATABASES/database1.mv";
	
	
    @FXML Pane usersOptionPane;
    
	@FXML TableView<Users> table;
	
	@FXML Pane children_pane;
	
	@FXML Pane parent_table;
	
	@FXML Button new_user_view;


	public void saludar() {
		System.out.println("hola!");
	}
    
    public void GET_USERS() {
    	System.out.println("data: " + usersOptionPane.getScene().getRoot());
    	
        try(Connection conexionDB = DriverManager.getConnection(App.DATABASE, "root","")){
            Statement statement = conexionDB.createStatement();
            String sql = "SELECT * FROM users ORDER BY id";
            ResultSet resultSet = statement.executeQuery(sql);
            ObservableList<Users> users_list = FXCollections.observableArrayList();
            
            while(resultSet.next()){
            	
                Users user = new Users();
                
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setMail(resultSet.getString("mail"));
                user.setIsadmin(resultSet.getString("isadmin"));
                user.setPass(resultSet.getString("pass"));
                user.setId_h(resultSet.getString("id_h"));
           
                users_list.add(user);

                System.out.println("User: " + user.getId() + " added");
            }
            table.setEditable(true);
            //table.set
            //configurarTamanhoColumnas();
            table.setItems(users_list);
            //table.refresh();
            
            //table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("DATOS INSERTADOS FALLIDO");
        }
    }
    

	public void display_new_user_view() {
        System.out.println("new user view");
        changePane("new_test");

//		
//		//CONSTANTES
//		
//		String DATABASE = "jdbc:h2:E:/DATABASES/database1.mv";
//		
//		//SQL
//		
//		String obtener = "SELECT * FROM USERS;";
//
//		//DEVOLVER 2
//        try(Connection conexionDataBase = DriverManager.getConnection(DATABASE, "root","")){
//            Statement statement = conexionDataBase.createStatement();
//        	ResultSet rs = statement.executeQuery(obtener);
//        	while(rs.next()) {
//        		System.out.println(rs.getString("id"));
//        	}
//            System.out.println("devuelto");
//        }catch (Exception e) {
//        	e.printStackTrace();
//        	System.out.println("Devolucion 2 fallida");
//        }
    }
	
	public void exit() {
		System.exit(0);
	}

    

    public void edit(MouseEvent mouseEvent) {
       System.out.println("Edit");
       DatabaseController.selected_user = table.getSelectionModel().getSelectedItem();
       changePane("new_test");
       	
    }

    public void delete(MouseEvent mouseEvent) {
        System.out.println("Delete selected  -- ONLY IF A TABLE REGISTRY SELECTED");
    }

    public void database_settings(MouseEvent mouseEvent) {
        System.out.println("modify working mode");
    }

    public void refresh_database(MouseEvent mouseEvent) {
        System.out.println("Refreshing data on gui");
    	try(Connection conexionDataBase = 
                DriverManager.getConnection(App.DATABASE, "root","")){
                Statement statement = conexionDataBase.createStatement();
                String i1 = "delete from users where id <10000";

        		statement.executeUpdate(i1);
 
        		System.out.println("SQL: " + i1);
        		System.out.println("DATOS INSERTADOS EXITOSAMENTE");
               
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("DATOS INSERTADOS FALLIDO");
            }
    	

    	
    }
    private void changePane(String fxml) {
        
        System.out.println("CREATING " + fxml + " TABLE");
        
        System.out.println(parent_table.getChildren());
        
        try{table.setVisible(false);
        	table.refresh();
        	}catch(Exception e) {System.out.println("table to false failed");}
        ObservableList<Node> children = null;
        try {
        	children = parent_table.getChildren();
        	System.out.println("Elemento hijo obtenido");
        }catch(Exception e) {
        	System.out.println("failed to obtain children from parent_table");
        }
        try{children.get(0).setVisible(false);table.refresh();}catch(Exception e) {System.out.println("No se pudo settear la visibilidad del elemento hijo");}
        
        Pane oldPane = null;
        Pane newPane = null;
        
        try {oldPane = FXMLLoader.load(getClass().getResource(fxml + ".fxml")); 
        oldPane.setVisible(false);
        System.out.println("old pane obtenido");}
        catch (IOException e1) {
        	System.out.println("pane not found");} 
        
        try {
        	newPane = FXMLLoader.load(getClass().getResource(fxml + ".fxml"));
        	children_pane = newPane;
        }
        catch (IOException e1) {e1.printStackTrace(); System.out.println("newPane error");}
        
        try {
    		parent_table.getChildren().add(newPane);
    	} catch (Exception e) {
    		System.out.println("failed to add new pane to parent_table");
    	}
        
    } 
    private void changeTable(String fxml) {
        
        try{table.setVisible(false);
        	table.refresh();
        	}catch(Exception e) {System.out.println("table to false failed");}
        System.out.println("CREATING " + fxml + " TABLE");
        
        //hide by children
        ObservableList<Node> children = parent_table.getChildren();
        try{children.get(0).setVisible(false);}catch(Exception e) {System.out.println("No se pudo obtener el elemento hijo");}
        
        TableView<Users> oldTable = null;
        TableView<Users> newTable = null;
        
        try {oldTable = FXMLLoader.load(getClass().getResource(fxml + ".fxml")); 
        	oldTable.setVisible(false);}
        catch (IOException e1) {
        	System.out.println("table not found");} 
        
        try {
        	newTable = FXMLLoader.load(getClass().getResource(fxml + ".fxml"));
        	table = newTable;
        }
        catch (IOException e1) {e1.printStackTrace(); System.out.println("newTable error");}

       
        parent_table.getChildren().add(newTable);
    }
    
    public void users_ts(MouseEvent mouseEvent) {
    	//Cambiar a tabla usuarios
		changeTable("users_table");

        
    	//DELETE_USERS();
    	//CREATE(USERS_CREATE);
    	//INSERT();
    	GET_USERS();

        System.out.println("MOSTRANDO DATOS DE USERS");  
        
        try(Connection conexionDataBase = DriverManager.getConnection(App.DATABASE, "root","")){
            Statement statement = conexionDataBase.createStatement();
        	ResultSet rs = statement.executeQuery("SELECT * FROM USERS");
        	while(rs.next()) {
        		System.out.println("id: " + rs.getInt("id") + "\n\tname: " + rs.getString("name"));
        	}
            System.out.println("devuelto");
        }catch (Exception e) {
        	e.printStackTrace();
        	System.out.println("Devolucion 2 fallida");
        }
    }

	public void guitars_ts(MouseEvent mouseEvent) {
		//Cambiar a tabla guitarras
		changeTable("guitars_table");
    }

    public void amps_ts(MouseEvent mouseEvent) {
    	//Cambiar a tabla amps
		changeTable("amps_table");
    }

    public void comp_info(MouseEvent mouseEvent) {
        System.out.println("You are using version: 1.1.1.1");
        //returning to main menu
        App.changeFXMLto("login.fxml");
    }
    



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//CHECK IF USER IS ADMIN
		if(LoginController.current_user.isIsadmin().equals("true")) {
			System.out.println("is admin");
			usersOptionPane.setVisible(true);
		}else {
			System.out.println("is admin was: " + LoginController.current_user.isIsadmin());
		}
		
		//NO LOAD ANY TABLE
		
		
		
		
	}
    
private void configurarTamanhoColumnas() {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<TableColumn<Users, ?>> columnas = table.getColumns();
        columnas.get(0).setMaxWidth(1f * Integer.MAX_VALUE * 12.5);
        columnas.get(1).setMaxWidth(1f * Integer.MAX_VALUE * 12.5);
        columnas.get(2).setMaxWidth(1f * Integer.MAX_VALUE * 12.5);
        columnas.get(3).setMaxWidth(1f * Integer.MAX_VALUE * 12.5);
        columnas.get(4).setMaxWidth(1f * Integer.MAX_VALUE * 12.5);
        columnas.get(5).setMaxWidth(1f * Integer.MAX_VALUE * 12.5);
        columnas.get(6).setMaxWidth(1f * Integer.MAX_VALUE * 12.5);
        //columnas.get(7).setMaxWidth(1f * Integer.MAX_VALUE * 12.5);
        
        
    }

    

public void select_profile_image() {
	System.out.println("select profile image");
}

public void add_user() {
	System.out.println("add user");
}
    
}
