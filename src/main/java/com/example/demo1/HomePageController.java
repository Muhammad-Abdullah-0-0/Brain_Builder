package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageController {
    public Label shwUsrName;
    public void  displayName(String name){
        shwUsrName.setText("Welcome "+name+"!");
    }
    public void logoutAction(ActionEvent e) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("LogIn.fxml"));
        Parent root=loader.load();

        Stage stage =(Stage) ((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root,600,400);
        stage.setScene(scene);
        stage.setTitle("Back to LogIn");
        stage.show();
    }
}
