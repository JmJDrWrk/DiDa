package com.example.dida;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class DatabaseController implements Initializable{
	
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
	private String DATABASE = "jdbc:h2:E:/DATABASES/database1.mv";
	
	
    @FXML Pane usersOptionPane;
    
	@FXML TableView<Users> table;
	
	@FXML Pane parent_table;
	

    
    
    public void GET_USERS() {
    	System.out.println("data: " + usersOptionPane.getScene().getRoot());
    	
        try(Connection conexionDB = DriverManager.getConnection(DATABASE, "root","")){
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
    
    public void add(MouseEvent mouseEvent) {
        System.out.println("Adding selected  -- ONLY IF A TABLE SELECTED\n\tDISPLAY ADD TO TABLE WINDOW");
		
		//CONSTANTES
		
		String DATABASE = "jdbc:h2:E:/DATABASES/database1.mv";
		
		//SQL
		
		String crear = "CREATE TABLE ADDRESS(FIRST_NAME VARCHAR,NAME VARCHAR, CITY VARCHAR, PHONE VARCHAR);";
		String insertar1 = "INSERT INTO ADDRESS VALUES('John', 'Miller', 'Berne', '123 456 789');";
		String insertar2 = "INSERT INTO ADDRESS VALUES('Philip', 'Jones', 'Berne', '123 012 345');";
		String obtener = "SELECT * FROM USERS;";

		//DEVOLVER 2
        try(Connection conexionDataBase = DriverManager.getConnection(DATABASE, "root","")){
            Statement statement = conexionDataBase.createStatement();
        	ResultSet rs = statement.executeQuery(obtener);
        	while(rs.next()) {
        		System.out.println(rs.getString("id"));
        	}
            System.out.println("devuelto");
        }catch (Exception e) {
        	e.printStackTrace();
        	System.out.println("Devolucion 2 fallida");
        }
    }

    public void edit(MouseEvent mouseEvent) {
        System.out.println("Delete selected  -- ONLY IF A TABLE REGISTRY SELECTED\n\tDISPLAY EDIT REG ON TABLE");
    }

    public void delete(MouseEvent mouseEvent) {
        System.out.println("Delete selected  -- ONLY IF A TABLE REGISTRY SELECTED");
    }

    public void database_settings(MouseEvent mouseEvent) {
        System.out.println("modify working mode");
    }

    public void refresh_database(MouseEvent mouseEvent) {
        System.out.println("Refreshing data on gui");
    }
    
    private void changeTable(String from, String to) {
        
        System.out.println("CREATING " + to + " TABLE");
        
        //hide by children
        ObservableList<Node> children = parent_table.getChildren();
        try{children.get(0).setVisible(false);}catch(Exception e) {System.out.println("No se pudo obtener el elemento hijo");}
        
        TableView<Users> oldTable = null;
        TableView<Users> newTable = null;
        
        try {oldTable = FXMLLoader.load(getClass().getResource(from + ".fxml")); 
        	oldTable.setVisible(false);}
        catch (IOException e1) {
        	System.out.println("table not found");} 
        
        try {
        	newTable = FXMLLoader.load(getClass().getResource(to + ".fxml"));
        	table = newTable;
        }
        catch (IOException e1) {e1.printStackTrace(); System.out.println("newTable error");}

       
        parent_table.getChildren().add(newTable);
    }
    
    public void users_ts(MouseEvent mouseEvent) {
    	//Cambiar a tabla usuarios
		changeTable("users_table","users_table");

        
    	//DELETE_USERS();
    	//CREATE(USERS_CREATE);
    	//INSERT();
    	GET_USERS();

        System.out.println("MOSTRANDO DATOS DE USERS");  
        
        try(Connection conexionDataBase = DriverManager.getConnection(DATABASE, "root","")){
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
		changeTable("guitars_table","guitars_table");
    }

    public void amps_ts(MouseEvent mouseEvent) {
    	//Cambiar a tabla amps
		changeTable("amps_table","amps_table");
    }

    public void comp_info(MouseEvent mouseEvent) {
        System.out.println("You are using version: 1.1.1.1");
        //returning to main menu
        App.changeFXMLto("login.fxml");
    }
    
    public void CREATE(String sql) {
        try(Connection conexionDataBase = DriverManager.getConnection(DATABASE, "root","")){
            Statement statement = conexionDataBase.createStatement();
        	statement.executeUpdate(sql);
            System.out.println("TABLA CREADA");
        }catch (Exception e) {
        	e.printStackTrace();
        	System.out.println("LA CREACION DE LA TABLA DE DATOS HA FALLADO");
        }
    
    }

    public void DELETE_USERS() {
    	String sql = "DROP TABLE users";
        try(Connection conexionDataBase = DriverManager.getConnection(DATABASE, "root","")){
            Statement statement = conexionDataBase.createStatement();
        	statement.executeUpdate(sql);
            System.out.println("TABLA CREADA");
        }catch (Exception e) {
        	e.printStackTrace();
        	System.out.println("LA ELIMINACION DE LA TABLA DE DATOS HA FALLADO");
        }
    }
    
    private void INSERT() {
    	try(Connection conexionDataBase = 
                DriverManager.getConnection(DATABASE, "root","")){
                Statement statement = conexionDataBase.createStatement();
                
                String i1 = "INSERT INTO USERS VALUES('100','admin', 'none', 'admin@', 'true', 'admin','0');";
                
                String i2 = "INSERT INTO USERS VALUES('1','Jaime','Roman Gil', 'jaime@g','false','0000','0');";
                String i3 = "INSERT INTO USERS VALUES('2','Gabi', 'Carnico', 'gabicarnico@', 'false', '4321','0');";
                String i4 = "INSERT INTO USERS VALUES('3','Damif', 'FCB', 'rompecorazones@', 'false', 'sopaGabi','0');";
                String i5 = "INSERT INTO USERS VALUES('4','Tekila', 'Javier Ceballos', 'AlMyLoco@', 'false', 'manin','0');";
                String i6 = "INSERT INTO USERS VALUES('5','Roo', 'C.R.', 'Rooooooooooooo@', 'true', 'levilovers','0');";
     
        		statement.executeUpdate(i1);
        		statement.executeUpdate(i2);
        		statement.executeUpdate(i3);
        		statement.executeUpdate(i4);
        		statement.executeUpdate(i5);
        		
        		System.out.println("DATOS INSERTADOS EXITOSAMENTE");
               
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("DATOS INSERTADOS FALLIDO");
            }
		
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

    
    
    
    
}
