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
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Destination;
import com.mycompany.myapp.entities.reclamation;
import com.mycompany.myapp.services.ServiceDestination;
import com.mycompany.myapp.services.ServiceReclamation;

/**
 *
 * @author Lenovo
 */
public class modifierdest extends Form {

    public modifierdest(Form previous) {

        setTitle("Modifier Destination");
       //setLayout(BoxLayout.y());
       setLayout(BoxLayout.yCenter()); // Set the layout to BoxLayout.yCenter()


        TextField idField = new TextField("", "ID");
        TextField villeField = new TextField("", "Ville");
        villeField.setPreferredW(Display.getInstance().convertToPixels(40)); // Set preferred width to 40mm
       villeField.setPreferredH(Display.getInstance().convertToPixels(10)); // Set preferred height to 10mm

        TextField paysField = new TextField("", "Pays");

        Button btnValider = new Button("Update");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((villeField.getText().length() == 0) && (paysField.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        float id = Float.parseFloat(idField.getText().toString());
                        Destination dest = new Destination((int) id, paysField.getText().toString(), villeField.getText().toString());
                        if (ServiceDestination.getInstance().modifierDestination(dest)) {

                            Dialog.show("Success", "Destination updated successfully", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "ID must be a number", new Command("OK"));
                    }
                }
            }
        });

        addAll(idField, villeField, paysField, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        Style style = getStyle();
        style.setBgColor(0xCAECF8);
    }

}
