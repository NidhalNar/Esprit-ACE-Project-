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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class Ajoute_hotController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfetoile;
    @FXML
    private Button btnajout;
    @FXML
    private TextField tftype;
    @FXML
    private ImageView img1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {/*
          hotel d = new hotel();
         d.setNom(tfnom.getText());
         d.setEtoile(tfetoile.getText());
         d.setType(tftype.getText());
        
      ServiceHotel ser = new ServiceHotel();
   
   ser.addhotel(d);
   
   Alert alert = new Alert (Alert.AlertType.INFORMATION);
   alert.setTitle("Ajout e'une destination");
   alert.setHeaderText(null);
   alert.setContentText("La destination a éte ajouter avec succés");
   alert.showAndWait();
   
   Stage stage = (Stage) tfnom.getScene().getWindow();
        stage.close();
        
    */ //vide
         if (tfnom.getText().isEmpty() || tfetoile.getText().isEmpty()|| tftype.getText().isEmpty()) {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
    }
        hotel d = new hotel();
        d.setNom(tfnom.getText());
         d.setEtoile(tfetoile.getText());
         d.setType(tftype.getText());
        
      ServiceHotel ser = new ServiceHotel();
      // meme input
        if (ser.getho(d.getNom(), d.getEtoile(),d.getType()) != null) {
        Alert alert = new Alert (Alert.AlertType.ERROR);
        alert.setTitle("Erreur lors de l'ajout d'un hotel");
        alert.setHeaderText(null);
        alert.setContentText("Un hotel avec le même données existe déjà.");
        alert.showAndWait();
        return;
    }
   
   ser.addhotel(d);
   
   Alert alert = new Alert (Alert.AlertType.INFORMATION);
   alert.setTitle("Ajout d'un hotel");
   alert.setHeaderText(null);
   alert.setContentText("L'hotel a éte ajouter avec succés");
   alert.showAndWait();
   
   Stage stage = (Stage) tfnom.getScene().getWindow();
        stage.close();
    
    
    }   
}
