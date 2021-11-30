package com.example.dida;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
public class ScpecificController implements Initializable{
	
	
	@FXML Pane father;
	//NEW USER METHODSs
	@FXML TextField new_user_name;
	@FXML TextField new_user_surname;
	@FXML TextField new_user_mail;
	@FXML TextField new_user_isadmin;
	@FXML TextField new_user_pass;
	@FXML Button select_profile_image;
	@FXML Button add_user;	
	
	private Users user = DatabaseController.selected_user;

	
	public void SAVE() {
		
		if(DatabaseController.is_new) {
			System.out.println("new user mode");
			 String sql =  String.format("INSERT INTO USERS(name,surname,mail,isadmin,pass,id_h) VALUES('%s', '%s', '%s', '%s', '%s','0');",
					 new_user_name.getText(),
					 new_user_surname.getText(),
					 new_user_mail.getText(),
					 new_user_isadmin.getText(),
					 new_user_pass.getText());
			 
		        try(Connection conexionDataBase = DriverManager.getConnection(App.DATABASE, "root","")){
		            Statement statement = conexionDataBase.createStatement();
		            System.out.println("profile edited");
		        	System.out.println("SQL:  " + sql);
		            statement.executeUpdate(sql);

		        }catch (Exception e) {
		        	e.printStackTrace();
		        	System.out.println("SQL:  " + sql);
		        }
		}else {
		
		String isadmin = "";
		//SET DEFAULT VALUE FOR ISADMIN
		if(new_user_isadmin.getText().equals("")) {System.out.println("setting is admin to default value true");isadmin = "true";}
		//CONTROL IF ISADMIN IS TRUE OR FALSE
		if(new_user_isadmin.getText().equals("true") || new_user_isadmin.getText().equals("false")) {
			 String sql = String.format("UPDATE users SET name = '%s', surname= '%s', mail = '%s' , isadmin = '%s' ,pass = '%s' WHERE id = %s;",
					 new_user_name.getText(),
					 new_user_surname.getText(),
					 new_user_mail.getText(),
					 isadmin+new_user_isadmin.getText(),
					 new_user_pass.getText(),
					 user.getId());
			 
		        try(Connection conexionDataBase = DriverManager.getConnection(App.DATABASE, "root","")){
		            Statement statement = conexionDataBase.createStatement();
		            System.out.println("profile edited");
		        	System.out.println("SQL:  " + sql);
		            statement.executeUpdate(sql);

		        }catch (Exception e) {
		        	e.printStackTrace();
		        	System.out.println("SQL:  " + sql);
		        }
		}else {
			System.out.println("isadmin field MUST BE 'true' or 'false'");
		}
		}
		
		father.setVisible(false);
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		father.setVisible(true);//overdone
		new_user_name.setText(user.getName());
		new_user_surname.setText(user.getSurname());
		new_user_mail.setText(user.getMail());
		new_user_pass.setText(user.getPass());
		new_user_isadmin.setText(user.isIsadmin());
		
		//changing text byutton
		 add_user.setText("SAVE");;
		
	}
	
	
	


}
