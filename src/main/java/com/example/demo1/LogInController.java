package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInController {
    public TextField userField;
    public TextField passFiled;

    public void loginAction(ActionEvent e) throws IOException {
        String userName = userField.getText();
        String password = passFiled.getText();
        if ( password.equals("admin")) {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("HomePage.fxml"));
            Parent root=loader.load();
            HomePageController hm=loader.getController();
            hm.displayName(userName);
            Stage stage =(Stage) ((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root,600,400);
            stage.setScene(scene);
            stage.setTitle("This is HomePage");
            stage.show();
        }
        else {
            Alert alert=new Alert(Alert.AlertType.WARNING,"Wrong Password");
            alert.show();
        }

    }
}
