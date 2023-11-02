/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ace;

import Entite.Destination;
import Entite.hotel;
import Utils.DataSource;
import Service.ServiceDestination;
import Service.ServiceHotel;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Lenovo
 */
public class Ace {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
            hotel h = new hotel("esprit","**","travel");
            
            ServiceHotel ser = new ServiceHotel();

        try {
           ser.addhotel(h);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    
 


        /*Destination p1 = new Destination("bizerte ", "rasjebel");

        ServiceDestination ser = new ServiceDestination();

        try {
            ser.addDes(p1);
        } catch (SQLException ex) {
            System.out.println(ex);
        }*/

//
//         Destination p2 = new Destination("bizerte ", "alia");
//
//        
//        try {
//            ser.add(p2);
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
        
     
       

        List<hotel> l1 = null;

        try {
            l1 = ser.readAllhotels();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        l1.forEach(e -> System.out.println(e));
        
        
       
        try {
        
        System.out.println("Veuillez saisir l'ID de l'hotel à mettre à jour :");
        int id = sc.nextInt();
        sc.nextLine();
        hotel destinationToUpdate=  ser.findhotelById(id);
        if (destinationToUpdate != null) {
            System.out.println("Veuillez saisir le nouveau nom de l'hotel :");
            String nom = sc.nextLine();
           System.out.println("Veuillez saisir le nouveau etoile de l'hotel :");
            String etoile = sc.nextLine();
            System.out.println("Veuillez saisir le nouveau type de l'hotel :");
            String type = sc.nextLine();
            destinationToUpdate.setNom(nom);
             destinationToUpdate.setEtoile(etoile);
              destinationToUpdate.setType(type);
            ser.updatehotel(destinationToUpdate);       
            } else {
            System.out.println("L'hotel avec l'ID " + id + " n'existe pas !");
        }
 
    } catch (SQLException e) {
        e.printStackTrace();
    }
        
        
        try {
        System.out.println("Veuillez saisir l'ID de l'hotel à supprimer :");
        int id = sc.nextInt();
        sc.nextLine();
        hotel destinationToDelete = ser.findhotelById(id);
        if (destinationToDelete != null) {
        	ser.Deletehotel(destinationToDelete);       
        	} else {
            System.out.println("L'hotel avec l'ID " + id + " n'existe pas !");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        
        

        try {
            l1 = ser.readAllhotels();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        l1.forEach(e -> System.out.println(e));
        
    }
    
    
    
    
    
    
            
            
            
            
            
            
            
            
            
            
    
}
