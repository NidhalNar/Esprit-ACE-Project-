/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entite.Destination;
import Entite.hotel;
import Service.ServiceDestination;
import Service.ServiceHotel;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class Modifier_hotController implements Initializable {

    @FXML
    private TextField tfetoile;
    @FXML
    private Button btnmodify;
    @FXML
    private TextField tftype;
    @FXML
    private ImageView img1;
    @FXML
    private TextField tfnom;
     private hotel hotels;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modify(ActionEvent event) throws SQLException {/*
         hotels.setNom(tfnom.getText());
        hotels.setEtoile(tfetoile.getText());
         hotels.setType(tftype.getText());
        ServiceHotel serviceDest = new ServiceHotel();
        serviceDest.updatehotel(hotels);
        
         Alert alert = new Alert (Alert.AlertType.INFORMATION);
         alert.setTitle("Modification d'une destination");
         alert.setHeaderText(null);
         alert.setContentText("La destination a éte modifier avec succés");
         alert.showAndWait();
        Stage stage = (Stage) tfnom.getScene().getWindow();
        stage.close();
        
        
   */ 
     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de modification");
    alert.setHeaderText(null);
    alert.setContentText("Voulez-vous vraiment modifier l'hotel ?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
        hotels.setNom(tfnom.getText());
        hotels.setEtoile(tfetoile.getText());
         hotels.setType(tftype.getText());
        ServiceHotel serviceDest = new ServiceHotel();
        serviceDest.updatehotel(hotels);

        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Modification d'un hotel");
        successAlert.setHeaderText(null);
        successAlert.setContentText("L'hotel a été modifiée avec succès");
        successAlert.showAndWait();

        Stage stage = (Stage) tfnom.getScene().getWindow();
        stage.close();
    }
    
    }
    
      public void sethotel(hotel hot) {
        this.hotels = hot;
        
        // Set the destination data to the UI fields
        tfnom.setText(hotels.getNom());
        tfetoile.setText(hotels.getEtoile());
        tftype.setText(hotels.getType());
    }
    
}
