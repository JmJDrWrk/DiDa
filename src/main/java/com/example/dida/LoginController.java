package com.example.dida;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable{
	String ci = "\u001b[1;96m";
	String cf = "\u001b[0m";
	String ri = "\u001b[1;91m";
	String rf = "\u001b[0m";
	
	private boolean show_regs_on_start = true;
	private boolean reset_table_users_regs = false;
	private boolean reset_table = false;
	private boolean create_table_users = false;
	private boolean reinsert_database = false;
	
	
	public static Users current_user;
	//C:/Users/Jaime/3D Objects/ASIGNATURAS/DISEÑO INTERFACES/DiDa/src/database/didadatabase

	
    @FXML TextField user;
    @FXML TextField pass;
    @FXML TextField phone;

    @FXML Label debugger1;
    @FXML Label debugger;

    @FXML Button login;
    @FXML Button sigin;
    @FXML Button join;
    @FXML Button account;
    @FXML Button settings;

    @FXML Pane profileimage;
    @FXML Pane leftPane;
    @FXML Pane accountSet;
    @FXML Pane settingsSet;

    @FXML
    ProgressIndicator progressIndicator;

    //head by brain
    ObservableList<Node> children;


    @FXML 
    private void signin() {
    	System.out.println("Sign in");
    	boolean regExists = true;
    	
    	//Check if reg yet exists
        try(Connection conexionDataBase = DriverManager.getConnection(App.DATABASE, "root","")){
            Statement statement = conexionDataBase.createStatement();
        	ResultSet rs = statement.executeQuery(String.format("SELECT * FROM USERS WHERE name = '%s'",user.getText()));
        	if(!rs.next()) {
        		//System.out.println("id: " + rs.getInt("id") + "\n\tname: " + rs.getString("name"));
        		System.out.println("empty set\n\t regExists changed to false");
        		regExists = false;
        	}
            
        }catch (Exception e) {
        	e.printStackTrace();
        	System.out.println("\tsearching for regs failed in sig in");
        }
    	//do if true
        if(!regExists) {
        	try(Connection conexionDataBase = 
                    DriverManager.getConnection(App.DATABASE, "root","")){
                    Statement statement = conexionDataBase.createStatement();
                    
                    
                    String i1 = String.format("INSERT INTO USERS(name,surname,mail,isadmin,pass,id_h) VALUES('%s', 'none', 'admin@', 'true', '%s','0');",user.getText(), pass.getText());

            		statement.executeUpdate(i1);
     
            		System.out.println("\tSQL: " + i1);
            		System.out.println("\tdata inserted successfully");
                   
                }catch(Exception e){
                    e.printStackTrace();
                    System.out.println("\tdata insert failed");
                }
        }else {
        	System.out.println("\tSign In skipped due to a yet existing reg");
        }
    	
    }
    
    
    @FXML
    private void login(){

    	LoginController.current_user = null;
    	
    	//registered user and password
    	String db_user = user.getText().toString();
    	String db_pass = null;

    	
        try(Connection conexionDataBase = DriverManager.getConnection(App.DATABASE, "root","")){
            Statement statement = conexionDataBase.createStatement();
        	ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE name = '" +db_user+"'");
        	if(rs.next() == false) {System.out.println("SELECT * FROM users WHERE name = '" +db_user+"'");System.out.println("NULL RESULTSET");System.exit(0);}
        	rs = statement.executeQuery("SELECT * FROM users WHERE name = '" +db_user+"'");
        	while(rs.next()) {
        		System.out.println(ci+"id: " + rs.getInt("id") + "\n\tname: " + rs.getString("name")+ci);
        		db_pass = rs.getString("pass");
        		System.out.println("The pass for user " + db_user + " is " + db_pass);
                Users user = new Users();
                
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setMail(rs.getString("mail"));
                user.setIsadmin(rs.getString("isadmin"));
                user.setPass(rs.getString("pass"));
                user.setId_h(rs.getString("id_h"));
           
                LoginController.current_user = user;
                
        	}
        	
        	System.out.println("user acepted");
            System.out.println("devuelto");
            System.out.println("user: " + LoginController.current_user + " name: " + LoginController.current_user.getName() + " isadmin: " + LoginController.current_user.isIsadmin());
        }catch (Exception e) {e.printStackTrace();System.out.println("Devolucion 2 fallida");}
    	
    	
        if(pass.getText().toString().equals(db_pass)){
            //Show logged part
            join.setVisible     (true);
            account.setVisible  (true);
            settings.setVisible (true);
            profileimage.setVisible(true);

            //Hide login part
            user.setVisible(false);
            pass.setVisible(false);
            phone.setVisible(false);
            debugger1.setVisible(false);
            login.setVisible(false);
            
            //Unlocking if admin
            
        }else{
            debugger1.setText("User and password invalid");
        }
    }
    
   
    
    @FXML
    private void account(){
        System.out.println("account button clicked");
        Pane accountPane = null;
        FXMLLoader loader = null;
        try {
            accountPane = FXMLLoader.load(getClass().getResource("account.fxml"));
            //loader.setLocation(getClass().getResource("account.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //FXMLLoader.load(getClass().getResource("/application/fxml2.fxml"));

        //LINEA QUE HE SACADO YO SOLO
        children = leftPane.getChildren();
        //System.out.println(children); -->Devuelve una lista de los elementos hijos
        System.out.println("Contained in Pane: ");
        for(int i = 0; i<children.size(); i++){
            System.out.println("\tid: " + children.get(i).getId());
            children.get(i).setVisible(false);
        }
        children.clear();
        System.out.println("(Updated) Contained in Pane: " + accountPane.getChildren().size() + " items");
        for(int i=0; i<accountPane.getChildren().size(); i++){
            try {
                children.add(accountPane.getChildren().get(i));
                System.out.println("\ttype: " + accountPane.getChildren().get(i).getClass() );//+ " id: " + accountPane.getChildren().get(i).getId());
            }catch (Exception e){
                System.out.println("list get failed");
            }

        }
        System.out.println("The final panel has " + children.size() + " elements");
        leftPane.getChildren().add(accountPane);
    }

    //ADD A TRY CATCH METHOD
    @FXML
    private void settings() {
        System.out.println("settings button clicked");
        Pane accountPane = null;
        FXMLLoader loader = null;
        try {
            accountPane = FXMLLoader.load(getClass().getResource("settings.fxml"));
            //loader.setLocation(getClass().getResource("account.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //FXMLLoader.load(getClass().getResource("/application/fxml2.fxml"));

        //LINEA QUE HE SACADO YO SOLO
        children = leftPane.getChildren();
        //System.out.println(children); -->Devuelve una lista de los elementos hijos
        System.out.println("Contained in Pane: ");
        for (int i = 0; i < children.size(); i++) {
            System.out.println("\tid: " + children.get(i).getId());
            children.get(i).setVisible(false);
        }
        children.clear();
        System.out.println("(Updated) Contained in Pane: " + accountPane.getChildren().size() + " items");
        for (int i = 0; i < accountPane.getChildren().size(); i++) {
            children.add(accountPane.getChildren().get(i));
            System.out.println("\ttype: " + accountPane.getChildren().get(i).getClass() + " id: " + accountPane.getChildren().get(i).getId());
        }
        System.out.println("The final panel has " + children.size() + " elements");
        leftPane.getChildren().add(accountPane);
    }



    //JOIN DATABASE
    @FXML
    private void join() {
        progressIndicator.setVisible(true);
        //debugger.setVisible(true);
        //debugger.setText("loading");
        App.changeFXMLto("DatabaseView.fxml");
    }

    public void hacer(MouseEvent mouseEvent) {
        System.out.println("hecho");
    }
    
    
    private void insert_sample_users() {
    	try(Connection conexionDataBase = 
                DriverManager.getConnection(App.DATABASE, "root","")){
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
        		statement.executeUpdate(i6);
        		
        		System.out.println("DATOS INSERTADOS EXITOSAMENTE");
               
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("DATOS INSERTADOS FALLIDO");
            }
		
    }
    
    private void create_table_users() {
    	System.out.println("CREATING TABLE USERS");
    	 try(Connection conexionDataBase = DriverManager.getConnection(App.DATABASE, "root","")){
             Statement statement = conexionDataBase.createStatement();
             String CREATE_TABLE_USERS = "CREATE TABLE USERS"
            			+"(id INTEGER,"
            			+"name VARCHAR,"
            			+"surname VARCHAR,"
            			+"mail VARCHAR,"
            			+"isadmin VARCHAR,"
            			+"pass VARCHAR,"
            			+"id_h VARCHAR)";
            	    
             statement.executeUpdate(CREATE_TABLE_USERS);
         	
  
         	System.out.println("TABLE USERS CREATED");
             
         }catch (Exception e) {e.printStackTrace();System.out.println("TABLE USERS CREATION FAILED");}
     	
     	
    }

    private void reset_table_users_regs() {
    	System.out.println("reseting data in table users");
   	 try(Connection conexionDataBase = DriverManager.getConnection(App.DATABASE, "root","")){
            Statement statement = conexionDataBase.createStatement();
           	    
            statement.executeQuery("DELETE FROM TABLE USERS WHERE 'id' < 1000");

        	System.out.println("data on table users deleted");
            
        }catch (Exception e) {e.printStackTrace();System.out.println("data on table users could not be deleted");}   	
    }
    
    private void show_regs_on_start() {
    	System.out.println("showing all data in table users");
   	 try(Connection conexionDataBase = DriverManager.getConnection(App.DATABASE, "root","")){
            Statement statement = conexionDataBase.createStatement();
           	    
            ResultSet rs = statement.executeQuery("SELECT * FROM users");
            
            System.out.println("-----------DATA----------");
            while(rs.next()) {
            	System.out.println(String.format("\tid: %s \n\tname: %s\n", rs.getInt("id"),rs.getString("name")));
            }
            

        	System.out.println("data on table users deleted");
            
        }catch (Exception e) {e.printStackTrace();System.out.println("data on table users could not be deleted");}
    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		if(create_table_users) {create_table_users();}
		if(reset_table_users_regs) {reset_table_users_regs();}
		if(reinsert_database) {insert_sample_users();}
		if(show_regs_on_start) {show_regs_on_start();}
		
	        try(Connection conexionDataBase = DriverManager.getConnection(App.DATABASE, "root","")){
	            Statement statement = conexionDataBase.createStatement();
	        	try {
	        		//statement.executeUpdate(SQL_SENTENCE.DROP_TABLE_USERS);
				} catch (Exception e) {
					System.out.println("DELETE FAILED");
				}
	        	 try {
	        		 //statement.executeUpdate(SQL_SENTENCE.CREATE_TABLE_USERS);
				} catch (Exception e) {
					System.out.println("CREATE FAILED");
				}
	             

	        }catch (Exception e) {
	        	e.printStackTrace();
	        	
	        }
		}
      
		
	
    
    

    
}

