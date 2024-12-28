package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("LogIn.fxml"));
        Parent root=loader.load();

        Scene scene = new Scene(root,600,400);
        stage.setScene(scene);
        stage.setTitle("My New Application");
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}


