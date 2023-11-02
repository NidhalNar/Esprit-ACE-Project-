/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Destination;
import com.mycompany.myapp.entities.reclamation;
import com.mycompany.myapp.services.ServiceDestination;

/**
 *
 * @author Lenovo
 */
public class addDestinationForm   extends Form{
     public addDestinationForm(Form previous) {
        setTitle("Ajout destination");
       // setLayout(BoxLayout.y());
                   setLayout(BoxLayout.yCenter()); // Set the layout to BoxLayout.yCenter()

        
        // Create input fields and button
       // TextField tfville = new TextField("", "ville");
       // TextField tfpays = new TextField("", "pays");
       
       TextField tfville = new TextField("", "ville");
tfville.setPreferredW(Display.getInstance().convertToPixels(40)); // Set preferred width to 40mm
tfville.setPreferredH(Display.getInstance().convertToPixels(10)); // Set preferred height to 10mm

TextField tfpays = new TextField("", "pays");
tfpays.setPreferredW(Display.getInstance().convertToPixels(40)); // Set preferred width to 40mm
tfpays.setPreferredH(Display.getInstance().convertToPixels(10)); // Set preferred height to 10mm

       
         //  Button btnAdd = new Button("Add");
         Button btnAdd = new Button("Add");
         Style btnStyle = btnAdd.getAllStyles();
         btnStyle.setPaddingTop(5);
         btnStyle.setPaddingBottom(10);
         btnStyle.setPaddingLeft(20);
         btnStyle.setPaddingRight(20);
         btnStyle.setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE)); // Set the font to bold and large


        

        
        // Add action listener to button
        btnAdd.addActionListener(evt -> {
            if (tfville.getText().length() == 0 || tfpays.getText().length() == 0 ) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {
                    // Create new reclamation object and add it to the server
                    Destination destination = new Destination(tfpays.getText(), tfville.getText());
                    if (ServiceDestination.getInstance().addDestination(destination)) { // to fix
                        Dialog.show("Success", "Congrats", new Command("OK"));
                            new ListDestination(previous).show();
                        
                        
                    } else {
                        Dialog.show("Error", "Server error", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("Error", "Invalid input", new Command("OK"));
                }
            }
        });
        
        // Add input fields and button to the form
        addAll(tfville, tfpays, btnAdd);
        
        // Add back button to the toolbar
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> previous.showBack());
        
         Style style = getStyle();
        style.setBgColor(0xCAECF8);
    }
    
}
