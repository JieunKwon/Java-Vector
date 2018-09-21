////////////////////////////////////////////////////////////////////////////////////////////
//      Java 2 : Assignment 3
//      Task :  Line Intersection
////////////////////////////////////////////////////////////////////////////////////////////
//     - current src file : Assignment3_jieun.java
//     - current src task : Start Project 
//     - created by : Jieun Kwon
//     - created date : April 12, 2018
//     - modified date : April 15, 2018
////////////////////////////////////////////////////////////////////////////////////////////

package jieun;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Assignment3_jieun extends Application
{
    ///////////////////////////////////////////////////////////////////////////
    // entry-point of JavaFX application
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
        // Stage Title
        stage.setTitle("Jieun's Line Intersection");
        
        // Stage Icon
        stage.getIcons().add(new Image(getClass().getResourceAsStream("logo.png")));
        
        stage.show();
    }



    ///////////////////////////////////////////////////////////////////////////
    public static void main(String[] args)
    {
        launch(args);
    }
}