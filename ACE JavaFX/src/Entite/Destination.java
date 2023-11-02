/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entite;

/**
 *
 * @author Lenovo
 */
public class Destination {
    private String ville,pays;
     private int id;

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

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "ville:"+ville+" || pays: "+pays;
    }
    
    
}
