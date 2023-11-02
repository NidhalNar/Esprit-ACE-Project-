/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Lenovo
 */
public class Destination {
    private String ville,pays;
     private int id;
     
      public Destination() {
       
    }

    public Destination(String pays, String ville) {
        this.ville = ville;
        this.pays = pays;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Destination( int id,String pays, String ville) {
        this.ville = ville;
        this.pays = pays;
        this.id = id;
    }
    

    public String getPays() {
        return pays;
    }

    public String getVille() {
        return ville;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }
    public void setDestination(String pays, String ville) {
        
        this.pays = pays;
        this.ville = ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return ville+ "  "+pays;
    }
    
    
}
