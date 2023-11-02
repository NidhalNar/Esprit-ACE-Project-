/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Entite.Personne;
import Services.ServicePersonne;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sanabenfadhel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Personne p1 = new Personne("Benn ", "ssss", 10);

        ServicePersonne ser = new ServicePersonne();

        try {
            ser.ajouter(p1);
        } catch (SQLException ex) {
            System.out.println(ex);
        }


         Personne p2 = new Personne("dd ", "serr", 15);

        
        try {
            ser.ajouterPST(p2);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
        List<Personne> l1 = null;

        try {
            l1 = ser.readAll();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        l1.forEach(e -> System.out.println(e));
    }

}
