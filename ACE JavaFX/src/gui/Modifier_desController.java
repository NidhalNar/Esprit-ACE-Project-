/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entite.Destination;
import Service.ServiceDestination;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class Modifier_desController implements Initializable {

    @FXML
    private TextField tfville2;
    @FXML
    private TextField tfpays2;
    @FXML
    private Button btnmodify;
    
    private Destination destination;
    @FXML
    private ImageView img1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Image myImage = new Image(getClass().getResourceAsStream("../Images/ace.jpg"));
         img1.setImage(myImage);
    }    
    public void setDestination(Destination dest) {
        this.destination = dest;
        
        // Set the destination data to the UI fields
        tfpays2.setText(destination.getPays());
        tfville2.setText(destination.getVille());
    }

    @FXML
    private void modify(ActionEvent event) throws SQLException {/*
         destination.setPays(tfpays2.getText());
        destination.setVille(tfville2.getText());
        ServiceDestination serviceDest = new ServiceDestination();
        serviceDest.updateDes(destination);
        
         Alert alert = new Alert (Alert.AlertType.INFORMATION);
         alert.setTitle("Modification d'une destination");
         alert.setHeaderText(null);
         alert.setContentText("La destination a éte modifier avec succés");
         alert.showAndWait();
        Stage stage = (Stage) tfpays2.getScene().getWindow();
        stage.close();
        */
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de modification");
    alert.setHeaderText(null);
    alert.setContentText("Voulez-vous vraiment modifier la destination ?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
        destination.setPays(tfpays2.getText());
        destination.setVille(tfville2.getText());
        ServiceDestination serviceDest = new ServiceDestination();
        serviceDest.updateDes(destination);

        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Modification d'une destination");
        successAlert.setHeaderText(null);
        successAlert.setContentText("La destination a été modifiée avec succès");
        successAlert.showAndWait();

        Stage stage = (Stage) tfpays2.getScene().getWindow();
        stage.close();
    }
        
        
    }
    
}
