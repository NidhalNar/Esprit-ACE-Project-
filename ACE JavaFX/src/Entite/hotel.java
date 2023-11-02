/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.sql.Date;

/**
 *
 * @author Lenovo
 */
public class hotel {
    private int id;
    private String nom,etoile,type;
    

    public hotel(int id , String nom, String etoile, String type) {
        this.id = id;
        this.nom = nom;
        this.etoile=etoile;
        this.type=type;
    }
   
      public hotel(String nom,   String etoile, String type) {
      
        this.nom = nom;
         this.etoile=etoile;
        this.type=type;
    }

    public String getEtoile() {
        return etoile;
    }

    public void setEtoile(String etoile) {
        this.etoile = etoile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public hotel() {
    }

   

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom+ "  "+etoile+" "+type; 
    }
     
    
    
    
}
