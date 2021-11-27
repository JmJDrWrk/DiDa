package com.example.dida;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {
	public static Users current_user;
	private String DATABASE = "jdbc:h2:E:/DATABASES/database1.mv";
	
    @FXML TextField user;
    @FXML TextField pass;
    @FXML TextField phone;

    @FXML Label debugger1;
    @FXML Label debugger;

    @FXML Button login;
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
    	
    	
    	
    }
    
    
    @FXML
    private void login(){

    	LoginController.current_user = null;
    	
    	//registered user and password
    	String db_user = user.getText().toString();
    	String db_pass = null;

    	
        try(Connection conexionDataBase = DriverManager.getConnection(DATABASE, "root","")){
            Statement statement = conexionDataBase.createStatement();
        	ResultSet rs = statement.executeQuery("SELECT * FROM USERS WHERE name = '" + user.getText().toString()+"';");
        	
        	while(rs.next()) {
        		System.out.println("id: " + rs.getInt("id") + "\n\tname: " + rs.getString("name"));
        		db_pass = rs.getString("pass");
        		System.out.println("The pass for user " + db_user + " is " + db_pass);
                Users user = new Users();
                
                user.setId_user(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setMail(rs.getString("mail"));
                user.setIsadmin(rs.getString("isadmin"));
                user.setPass(rs.getString("pass"));
                user.setId_historial(rs.getString("id_h"));
           
                LoginController.current_user = user;

        	}
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
    

    
}

