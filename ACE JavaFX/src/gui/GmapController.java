/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entite.Destination;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class GmapController implements Initializable {

    @FXML
    private WebView mapi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
       mapi.getEngine().load("https://opentripmap.com/en/#15/36.8001/10.1784"); 

         StackPane root = new StackPane();
         root.getChildren().add(mapi);

    Scene scene = new Scene(root, 900, 500);
    Stage stage = new Stage();
    stage.setTitle("Open trip map");
    stage.setScene(scene);
    stage.show();

    // Get the current window after showing the stage
   Stage currentStage = (Stage) mapi.getScene().getWindow();
    currentStage.setTitle("Open trip map");
       
      
        
    }    
    
}

