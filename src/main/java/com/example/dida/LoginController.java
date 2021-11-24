package com.example.dida;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;

public class LoginController {
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
    private void login(){
        if(user.getText().equals("admin") && pass.getText().toString().equals("1234")){
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
        debugger.setVisible(true);
        debugger.setText("loading");
    }

}

