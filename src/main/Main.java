/*
 * Copyright (C) 2018 Szysz
 */
package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Szysz
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader mainLoader = new FXMLLoader();
        mainLoader.setLocation(this.getClass().getResource("/gui/GoAFXML.fxml"));
        Parent mainNode = mainLoader.load();
        Scene scene = new Scene(mainNode);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Game of Life");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        launch(args); 
    }
}
