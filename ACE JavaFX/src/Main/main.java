/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Entite.Destination;
import Utils.DataSource;
import Service.ServiceDestination;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Lenovo
 */
public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        Destination p1 = new Destination("bizerte ", "rasjebel");

        ServiceDestination ser = new ServiceDestination();

        try {
            ser.add(p1);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

//
//         Destination p2 = new Destination("bizerte ", "alia");
//
//        
//        try {
//            ser.add(p2);
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
        
     
       

        List<Destination> l1 = null;

        try {
            l1 = ser.readAll();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        l1.forEach(e -> System.out.println(e));
        
        
        
        try {
        
        System.out.println("Veuillez saisir l'ID de la destination à mettre à jour :");
        int id = sc.nextInt();
        sc.nextLine();
        Destination destinationToUpdate=  ser.findById(id);
        if (destinationToUpdate != null) {
            System.out.println("Veuillez saisir le nouveau pays de la destination :");
            String pays = sc.nextLine();
            System.out.println("Veuillez saisir la nouvelle ville de la destination :");
            String ville = sc.nextLine();
            destinationToUpdate.setPays(pays);
            destinationToUpdate.setVille(ville);
            ser.update(destinationToUpdate);       
            } else {
            System.out.println("La destination avec l'ID " + id + " n'existe pas !");
        }
 
    } catch (SQLException e) {
        e.printStackTrace();
    }
        
        
        try {
        System.out.println("Veuillez saisir l'ID de la destination à supprimer :");
        int id = sc.nextInt();
        sc.nextLine();
        Destination destinationToDelete = ser.findById(id);
        if (destinationToDelete != null) {
        	ser.Delete(destinationToDelete);       
        	} else {
            System.out.println("La destination avec l'ID " + id + " n'existe pas !");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        
        

        try {
            l1 = ser.readAll();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        l1.forEach(e -> System.out.println(e));
        
    }
    
    
    
    
    
    
            
            
            
            
            
            
            
            
            
            
    
}
