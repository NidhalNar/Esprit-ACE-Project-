/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.Destination;
import com.mycompany.myapp.entities.reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import com.mycompany.myapp.gui.recSearch;
import com.mycompany.myapp.services.ServiceDestination;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class ListDestination extends Form {

    public ListDestination(Form previous) {
        ServiceDestination sp = new ServiceDestination();
        add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(() -> {

            Display.getInstance().callSerially(() -> {

                removeAll();
                setLayout(BoxLayout.y());
                Button searchButton = new Button();
                FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, "Search Icon", 4);
                searchButton.setIcon(searchIcon);

                // back = Image.createImage("/logo.png");
                //back.scaled(1000, 1000);
                add(searchButton);
                Style s = UIManager.getInstance().getComponentStyle("Contenu");

                Form hi = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
                searchButton.addActionListener(e -> {
                    hi.show();
                });
                Button gui_Button_12 = new Button();
                gui_Button_12.setText("search");
                gui_Button_12.setUIID("Label");
                gui_Button_12.setName("Button_12");
                FontImage.setMaterialIcon(gui_Button_12, FontImage.MATERIAL_CIRCLE);
                TextField searchField = new TextField("", "Toolbar Search"); // <1>
                searchField.getHintLabel().setUIID("Title");
                searchField.setUIID("Title");
                searchField.getAllStyles().setAlignment(Component.LEFT);
                hi.getToolbar().setTitleComponent(searchField);
                // FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
                ArrayList<Destination> list1;
                list1 = ServiceDestination.getInstance().affichageDestination();
                //hi.add(gui_Button_12);
                searchField.addDataChangeListener((i1, i2) -> { // <2>
                    String t = searchField.getText();

                    if (t.length() < 1) {
                        for (Component cmp : hi.getContentPane()) {
                            cmp.setHidden(false);
                            cmp.setVisible(true);
                        }
                    } else {
                        t = t.toLowerCase();
                        for (Component cmp : hi.getContentPane()) {
                            String val = null;
                            //hi.add(gui_Button_12);
                            if (cmp instanceof Label) {
                                val = ((Label) cmp).getText();
                            } else {
                                if (cmp instanceof TextArea) {
                                    val = ((TextArea) cmp).getText();
                                } else {
                                    val = (String) cmp.getPropertyValue("text");
                                }
                            }
                            boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
                            cmp.setHidden(!show); // <3>
                            cmp.setVisible(show);
                            //hi.add(gui_Button_12);
                        }
                    }
                    hi.getContentPane().animateLayout(250);
                    //  hi.add(gui_Button_12);
                });
                hi.getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
                    searchField.startEditingAsync(); // <4>
                    //    hi.add(gui_Button_12);
                });

                for (Destination rec : list1) {
                    Label b = new Label(rec.getVille());

                    hi.add(b);

                    b.addPointerPressedListener(e -> {

                        if (rec.getVille() == b.getText()) {
                            new destSearch(previous).show();
                        }
                    });
                }/*
Button mapButton = new Button("Afficher la carte");
mapButton.addActionListener(e -> {
   String url = "https://www.google.com/maps"; // replace with your desired URL
    BrowserComponent browser = new BrowserComponent();
    browser.setURL(url);
    Form browserForm = new Form("Browser");
    browserForm.setLayout(new BorderLayout());
    browserForm.add(BorderLayout.CENTER, browser);
    browserForm.show();
});
add(mapButton);*/
                Button refreshButton = new Button();
                FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_REFRESH, UIManager.getInstance().getComponentStyle("Button"));
                refreshButton.setIcon(icon1);

                refreshButton.addActionListener(e -> new ListDestination(previous).show());
                add(refreshButton);

                List<Destination> listerec = sp.affichageDestination();
                for (Destination p : listerec) {

                    MultiButton m = new MultiButton();

                    m.setTextLine1("Pays:" + p.getPays());
                    m.setTextLine2("Ville:" + p.getVille());
                    // m.setTextLine3("Email:"+p.getEmail());
                    // m.setTextLine4("Prenom:"+p.getPrenom());

                    add(m);
                    Button btnModifier = new Button();

                    Button mapsButton = new Button("Map");
                    mapsButton.addActionListener(e -> {
                        String url = "https://www.google.com/maps/place/" + p.getVille(); // replace with your desired URL
                        BrowserComponent browser = new BrowserComponent();
                        browser.setURL(url);
                        Form browserForm = new Form("Browser");
                        browserForm.setLayout(new BorderLayout());
                        browserForm.add(BorderLayout.CENTER, browser);
                        browserForm.show();

                    });
                    add(mapsButton);
                    FontImage.setMaterialIcon(btnModifier, FontImage.MATERIAL_EDIT);
                    m.addComponent(BorderLayout.SOUTH, btnModifier);
                    btnModifier.addActionListener(e -> {
                        new modifierdest(this).show();
                    });

                    Button btnSupprimer = new Button();
                    Image icon = FontImage.createMaterial(FontImage.MATERIAL_DELETE, "ButtonIcon", 5);
                    btnSupprimer.setIcon(icon);
                    m.addComponent(BorderLayout.WEST, btnSupprimer);
                    btnSupprimer.addActionListener(e -> {
                        Dialog dig = new Dialog("Suppression");
                        if (dig.show("Suppression", "Êtes-vous sûr de vouloir supprimer cet élément ?", "Annuler", "Oui")) {
                            dig.dispose();
                        } else {
                            dig.dispose();
                            if (ServiceDestination.getInstance().deleteDestination(p.getId())) {
                                Dialog.show("Success", "Element supprimé avec succès", "OK", null);

                            }
                        }
                    });
                }

                revalidate();
            });
        });

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        Style style = getStyle();
        style.setBgColor(0xCAECF8);

    }

}
