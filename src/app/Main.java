/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package app;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author AL
 */
public class Main extends Application{
    public static void main(String [] args){
        
        launch(args);
        
    }

    
    public void start(Stage stage) throws Exception {
    URL location = getClass().getResource("/view/mainApp.fxml");
    Parent p = FXMLLoader.load(location);
   
    Scene s = new Scene(p);
    stage.setScene(s);
    stage.setTitle("Task Management System");
    stage.show();
}
    
}
