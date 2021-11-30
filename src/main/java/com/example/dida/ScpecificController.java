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
public class ScpecificController implements Initializable{
	
	private String DATABASE = App.DATABASE;
	
	//NEW USER METHODSs
	@FXML TextField new_user_name;
	@FXML TextField new_user_surname;
	@FXML TextField new_user_mail;
	@FXML ComboBox<String> new_user_admin;
	@FXML TextField new_user_pass;
	@FXML Button select_profile_image;
	@FXML Button add_user;	
	
	private Users user = DatabaseController.selected_user;

	
	
	public void SAVE() {
		 String sql = String.format("UPDATE users SET Name = '%s', Surname= '%s', Mail = '%s' , pass = '%s' WHERE id = %s;",user.getName(),user.getSurname(),user.getMail(),user.getPass(),user.getId());
        try(Connection conexionDataBase = DriverManager.getConnection(DATABASE, "root","")){
            Statement statement = conexionDataBase.createStatement();
        	System.out.println("SQL:  " + sql);
            statement.executeUpdate(sql);

        }catch (Exception e) {
        	e.printStackTrace();
        	System.out.println("SQL:  " + sql);
        }
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		new_user_name.setText(user.getName());
		new_user_surname.setText(user.getSurname());
		new_user_mail.setText(user.getMail());
		new_user_pass.setText(user.getPass());
		
		//changing text byutton
		 add_user.setText("SAVE");;
		
	}
	
	
	


}
