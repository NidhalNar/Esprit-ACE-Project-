/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.reclamation;
import com.mycompany.myapp.services.ServiceReclamation;

/**
 *
 * @author Sondes
 */
public class AddReclamationForm extends Form {
    
    public AddReclamationForm(Form previous) {
        setTitle("Ajoute une nouvelle reclamation");
        setLayout(BoxLayout.y());
        
        // Create input fields and button
        TextField tfContenu = new TextField("", "Contenu");
        TextField tfNom = new TextField("", "Nom");
        TextField tfEmail = new TextField("", "Email");
        TextField tfPrenom = new TextField("", "Prenom");
        Button btnAdd = new Button("Add");
        
        // Add action listener to button
        btnAdd.addActionListener(evt -> {
            if (tfContenu.getText().length() == 0 || tfNom.getText().length() == 0 ||
                    tfEmail.getText().length() == 0 || tfPrenom.getText().length() == 0) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {
                    // Create new reclamation object and add it to the server
                    reclamation reclamation = new reclamation(tfContenu.getText(), tfNom.getText(),
                            tfEmail.getText(), tfPrenom.getText());
                    if (ServiceReclamation.getInstance().addReclamation(reclamation)) {
                        Dialog.show("Success", "Congrats", new Command("OK"));
                    } else {
                        Dialog.show("Error", "Server error", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("Error", "Invalid input", new Command("OK"));
                }
            }
        });
        
        // Add input fields and button to the form
        addAll(tfContenu, tfNom, tfEmail, tfPrenom, btnAdd);
        
        // Add back button to the toolbar
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> previous.showBack());
    }
}
   

    

