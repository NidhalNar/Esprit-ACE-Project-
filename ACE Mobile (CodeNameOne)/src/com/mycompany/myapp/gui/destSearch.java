/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.Destination;
import com.mycompany.myapp.entities.reclamation;
import com.mycompany.myapp.services.ServiceDestination;
import com.mycompany.myapp.services.ServiceReclamation;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class destSearch extends Form {

    Form current;

    public destSearch(Form previous) {
        current = this;

        ServiceDestination sp = new ServiceDestination();
        add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(() -> {

            Display.getInstance().callSerially(() -> {

                removeAll();
                setLayout(BoxLayout.y());
                Button searchButton = new Button();
                FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, "Search Icon", 4);
                searchButton.setIcon(searchIcon);

                add(searchButton);

                Button refreshButton = new Button();
                FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_REFRESH, UIManager.getInstance().getComponentStyle("Button"));
                refreshButton.setIcon(icon1);

                refreshButton.addActionListener(e -> new ListReclamation(previous).show());
                add(refreshButton);

                List<Destination> listerec = sp.affichageDestination();

                for (Destination c : listerec) {
                    MultiButton m = new MultiButton();

                    m.setTextLine1("Pays: " + c.getVille());
                    m.setTextLine2("Ville: " + c.getPays());
                    //m.setTextLine3("Email: " + c.getEmail());
                    // m.setTextLine4("Prenom: " + c.getPrenom());

                    add(m);
                }

                revalidate();
            });
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        Style style = getStyle();
        style.setBgColor(0xCAECF8);

    }

}
