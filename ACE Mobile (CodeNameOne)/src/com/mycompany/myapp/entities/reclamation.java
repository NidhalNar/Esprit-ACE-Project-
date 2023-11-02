/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Sondes
 */
public class reclamation {
    String contenu;
    String email,nom,prenom;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public reclamation() {
    }
public reclamation(int id,String contenu, String nom, String email,String prenom) {
        this.contenu = contenu;
        this.nom = nom;
        this.email = email;
        this.prenom = prenom;
        this.id = id;
    }
    public reclamation(String contenu, String nom, String email,String prenom) {
        this.contenu = contenu;
        this.nom = nom;
        this.email = email;
        this.prenom = prenom;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
