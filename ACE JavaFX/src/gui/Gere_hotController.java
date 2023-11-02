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
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class Gere_hotController implements Initializable {

    @FXML
    private ListView<hotel> lvdes;
     private ObservableList<hotel> listdes = FXCollections.observableArrayList();
    @FXML
    private Button btndel;
    @FXML
    private Button btnmodify;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnrefresh;
    @FXML
    private Button btnhot;
    @FXML
    private Button btnex;
    @FXML
    private Button btnet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lvdes.setItems(listdes);
        display();
        
       //Image myImage = new Image(getClass().getResourceAsStream("../Images/ace.jpg"));
      //   img3.setImage(myImage);
    }    

    @FXML
    private void delete(ActionEvent event) throws SQLException{/*
         ServiceHotel ser = new ServiceHotel();
        hotel selected = lvdes.getSelectionModel().getSelectedItem();
    try {
        ser.Deletehotel(selected);
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    
    // Remove the selected item from the listview
    lvdes.getItems().remove(selected);
    
      Alert alert = new Alert (Alert.AlertType.INFORMATION);
         alert.setTitle("Suppression d'une destination");
         alert.setHeaderText(null);
         alert.setContentText("La destination a éte supprimer avec succés");
         alert.showAndWait();*/
              ServiceHotel ser = new ServiceHotel();
    hotel selected = lvdes.getSelectionModel().getSelectedItem();
    if(selected != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
    alert.setContentText("Voulez-vous vraiment supprimer l'hotel " + selected.getNom() + " ?");
       // alert.setTitle("Confirmation de suppression");
       // alert.setHeaderText("Voulez-vous vraiment supprimer la destination " + selected.getPays() + " - " + selected.getVille() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            ser.Deletehotel(selected);
            lvdes.getItems().remove(selected);
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Suppression d'un hotel");
            successAlert.setHeaderText(null);
            successAlert.setContentText("L'hotel a été supprimée avec succès");
            successAlert.showAndWait();
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Sélectionner un hotel");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un hotel à supprimer");
        alert.showAndWait();
    }
    }

    @FXML
    private void modify2(ActionEvent event) throws IOException {/*
         hotel selectedDest = lvdes.getSelectionModel().getSelectedItem();
        if (selectedDest != null) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/modifier_hot.fxml"));
           Parent root = loader.load();
            Modifier_hotController mod = loader.getController();
             mod.sethotel(selectedDest);
           
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }*/
         hotel selectedDest = lvdes.getSelectionModel().getSelectedItem();
    if (selectedDest == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de modification");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un hotel à modifier.");
        alert.showAndWait();
    } else {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/modifier_hot.fxml"));
        Parent root = loader.load();
        Modifier_hotController mod = loader.getController();
         mod.sethotel(selectedDest);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    }

    @FXML
    private void ajoute2(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ajoute_hot.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }

    @FXML
    private void refresh(ActionEvent event) {
          try {
            // retrieve the updated list of data from the database
            ServiceHotel service = new ServiceHotel();
            List<hotel> dataList = service.readAllhotels();

            // update the listview with the new data
            lvdes.getItems().clear();
            lvdes.getItems().addAll(dataList);

           
        } catch (SQLException e) {
            // handle the exception
        }
    }
    
     public void display() {
        try {
             ServiceHotel ser = new ServiceHotel();
        List<hotel> hotels =  ser.readAllhotels();
        listdes.clear();
        listdes.addAll(hotels);
    } catch (SQLException e) {
        e.printStackTrace();
    }
        
    }

    @FXML
    private void gohot(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/gmap.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }

    @FXML
    private void excel(ActionEvent event) throws FileNotFoundException, IOException {
        // Create a workbook and a sheet
    Workbook workbook = new XSSFWorkbook(); 
    Sheet sheet = workbook.createSheet("ListView Data");

    // Get the data from the ListView
    ObservableList<hotel> data = lvdes.getItems();

    // Create a font for the headers
    Font headerFont = workbook.createFont();
    headerFont.setBold(true);

    // Create a cell style for the headers
    CellStyle headerStyle = workbook.createCellStyle();
    headerStyle.setFont(headerFont);

    // Write the headers to the sheet
    Row headerRow = sheet.createRow(0);
    Cell headerCell1 = headerRow.createCell(0);
    headerCell1.setCellValue("nom");
    headerCell1.setCellStyle(headerStyle);
    Cell headerCell2 = headerRow.createCell(1);
    headerCell2.setCellValue("etoile");
    headerCell2.setCellStyle(headerStyle);
     Cell headerCell3 = headerRow.createCell(2);
    headerCell3.setCellValue("type");
    headerCell3.setCellStyle(headerStyle);

    // Write the data to the sheet
    for (int i = 0; i < data.size(); i++) {
        Row row = sheet.createRow(i + 1);
        Cell cell1 = row.createCell(0);
        cell1.setCellValue(data.get(i).getNom());
        Cell cell2 = row.createCell(1);
        cell2.setCellValue(data.get(i).getEtoile());
        Cell cell3 = row.createCell(2);
        cell3.setCellValue(data.get(i).getType());
    }

    // Save the workbook to a file
    FileOutputStream fileOut = new FileOutputStream("ListViewHotel.xlsx");
    workbook.write(fileOut);
    fileOut.close();

    // Open the file
    Desktop.getDesktop().open(new File("ListViewHotel.xlsx"));
    }

    @FXML
    private void trietoile(ActionEvent event) {
        
        
          // Get the items in the ListView
    ObservableList<hotel> items = lvdes.getItems();
    
    // Sort the items by ville
    items.sort(new Comparator<hotel>() {
        public int compare(hotel d1, hotel d2) {
            return d2.getEtoile().compareTo(d1.getEtoile());
        }

    });
    
    // Set the sorted items back to the ListView
    lvdes.setItems(items);
        
    }
    
}
