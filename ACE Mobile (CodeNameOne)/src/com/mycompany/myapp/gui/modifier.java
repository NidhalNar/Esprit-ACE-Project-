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
 * @author HP
 */
public class modifier extends Form {
    public modifier(Form previous) {
        setTitle("Modifier Reclamation");
        setLayout(BoxLayout.y());
        
     TextField ID = new TextField("","ID");
        TextField tcontenu = new TextField("","Contenu");
        TextField tnom = new TextField("","Nom");
        TextField temail = new TextField("","Email");
        TextField tprenom = new TextField("","Prenom");
        
        Button btnValider = new Button("Update ");
          
         
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tcontenu.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                   try {
                       float id = Float.parseFloat(ID.getText().toString());
                        reclamation t;
                        t = new reclamation((int) id,tcontenu.getText().toString(),tnom.getText().toString(),temail.getText().toString(),tprenom.getText().toString());
                        if( ServiceReclamation.getInstance().modifierReclamation(t))
                        {
                           Dialog.show("Success","Congrats!!",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
           }
        });
        
        addAll(ID,tcontenu,tnom,temail,tprenom,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
             
    }
    
}
