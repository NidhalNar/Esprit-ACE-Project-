/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entite.Destination;
import org.apache.poi.ss.usermodel.Sheet;
import API.openTripMapApi;
import Service.ServiceDestination;
import java.awt.Desktop;
import java.io.File;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class Gere_desController implements Initializable {

    @FXML
    private ListView<Destination> lvdes;
    private ObservableList<Destination> listdes = FXCollections.observableArrayList();
    @FXML
    private Button btndel;
    @FXML
    private Button btnmodify;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnrefresh;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img31;
    @FXML
    private Button map;
    @FXML
    private Button trville;
    @FXML
    private Button trpays;
    @FXML
    private Button btnexcel;
    
    
    
    
    
    
   
    
   
    

    /**
     * Initializes the controller class.
     */
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lvdes.setItems(listdes);
        display();
       
        
   
        
       Image myImage = new Image(getClass().getResourceAsStream("../Images/ace.jpg"));
         img3.setImage(myImage);
       
        
    }    

    @FXML
    private void delete(ActionEvent event) throws SQLException {/*
        
          ServiceDestination ser = new ServiceDestination();
        Destination selected = lvdes.getSelectionModel().getSelectedItem();
    try {
        ser.DeleteDes(selected);
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    
    // Remove the selected item from the listview
    lvdes.getItems().remove(selected);
    
      Alert alert = new Alert (Alert.AlertType.INFORMATION);
         alert.setTitle("Suppression d'une destination");
         alert.setHeaderText(null);
         alert.setContentText("La destination a éte supprimer avec succés");
         alert.showAndWait();
        */
        ServiceDestination ser = new ServiceDestination();
    Destination selected = lvdes.getSelectionModel().getSelectedItem();
    if(selected != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
    alert.setContentText("Voulez-vous vraiment supprimer la destination " + selected.getPays() + " - " + selected.getVille() + " ?");
       // alert.setTitle("Confirmation de suppression");
       // alert.setHeaderText("Voulez-vous vraiment supprimer la destination " + selected.getPays() + " - " + selected.getVille() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            ser.DeleteDes(selected);
            lvdes.getItems().remove(selected);
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Suppression d'une destination");
            successAlert.setHeaderText(null);
            successAlert.setContentText("La destination a été supprimée avec succès");
            successAlert.showAndWait();
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Sélectionner une destination");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une destination à supprimer");
        alert.showAndWait();
    }
    }
    
    

    @FXML
    private void modify2(ActionEvent event) throws IOException {/*
        Destination selectedDest = lvdes.getSelectionModel().getSelectedItem();
        if (selectedDest != null) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/modifier_des.fxml"));
           Parent root = loader.load();
            Modifier_desController mod = loader.getController();
             mod.setDestination(selectedDest);
           
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }*/
         Destination selectedDest = lvdes.getSelectionModel().getSelectedItem();
    if (selectedDest == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de modification");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une destination à modifier.");
        alert.showAndWait();
    } else {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/modifier_des.fxml"));
        Parent root = loader.load();
        Modifier_desController mod = loader.getController();
        mod.setDestination(selectedDest);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    }

    @FXML
    private void ajoute2(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ajoute_des.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }

    @FXML
    private void refresh(ActionEvent event) throws SQLException {
         try {
            // retrieve the updated list of data from the database
            ServiceDestination service = new ServiceDestination();
            List<Destination> dataList = service.readAllDes();

            // update the listview with the new data
            lvdes.getItems().clear();
            lvdes.getItems().addAll(dataList);

           
        } catch (SQLException e) {
            // handle the exception
        }
    }
        
     
     
    
    public void display() {
        try {
             ServiceDestination ser = new ServiceDestination();
        List<Destination> destinations =  ser.readAllDes();
        listdes.clear();
        listdes.addAll(destinations);
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
        
    }

    @FXML
    private void map(ActionEvent event) throws IOException {
        
         
           FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/gmap.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    
    }

    @FXML
    private void trierville(ActionEvent event) {
         // Get the items in the ListView
    ObservableList<Destination> items = lvdes.getItems();
    
    // Sort the items by ville
    items.sort(new Comparator<Destination>() {
        public int compare(Destination d1, Destination d2) {
            return d1.getVille().compareTo(d2.getVille());
        }

    });
    
    // Set the sorted items back to the ListView
    lvdes.setItems(items);
    }

    @FXML
    private void trierpays(ActionEvent event) {
          // Get the items in the ListView
    ObservableList<Destination> items = lvdes.getItems();
    
    // Sort the items by ville
    items.sort(new Comparator<Destination>() {
        public int compare(Destination d1, Destination d2) {
            return d1.getPays().compareTo(d2.getPays());
        }

    });
    
    // Set the sorted items back to the ListView
    lvdes.setItems(items);
    }

    @FXML
    private void exel(ActionEvent event) throws IOException {
        
    Workbook workbook = new XSSFWorkbook(); 
    Sheet sheet = workbook.createSheet("ListView Data");

    // Get the data from the ListView
    ObservableList<Destination> data = lvdes.getItems();

    // Create a font for the headers
    Font headerFont = workbook.createFont();
    headerFont.setBold(true);

    // Create a cell style for the headers
    CellStyle headerStyle = workbook.createCellStyle();
    headerStyle.setFont(headerFont);

    // Write the headers to the sheet
    Row headerRow = sheet.createRow(0);
    Cell headerCell1 = headerRow.createCell(0);
    headerCell1.setCellValue("Pays");
    headerCell1.setCellStyle(headerStyle);
    Cell headerCell2 = headerRow.createCell(1);
    headerCell2.setCellValue("Ville");
    headerCell2.setCellStyle(headerStyle);

    // Write the data to the sheet
    for (int i = 0; i < data.size(); i++) {
        Row row = sheet.createRow(i + 1);
        Cell cell1 = row.createCell(0);
        cell1.setCellValue(data.get(i).getPays());
        Cell cell2 = row.createCell(1);
        cell2.setCellValue(data.get(i).getVille());
    }

    // Save the workbook to a file
    FileOutputStream fileOut = new FileOutputStream("ListViewDestination.xlsx");
    workbook.write(fileOut);
    fileOut.close();

    // Open the file
    Desktop.getDesktop().open(new File("ListViewDestination.xlsx"));
    }

    

   


    
    
    }
    

