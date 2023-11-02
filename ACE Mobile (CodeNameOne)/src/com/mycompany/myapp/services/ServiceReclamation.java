/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;

import com.codename1.ui.Form;
import java.io.IOException;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.html.DocumentInfo;
import com.mycompany.myapp.entities.reclamation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * HP
 */
public class ServiceReclamation {
    public static ServiceReclamation instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<reclamation> listCategory=new ArrayList<>();

    public ServiceReclamation() {
        req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }
//ajout
    public boolean addReclamation(reclamation t) {

        String contenu = t.getContenu();
      String nom=t.getNom();
      String email=t.getEmail();
      String prenom=t.getPrenom();
        
       
    
String url= "http://127.0.0.1:8000/"+"reclamation/ajouteM?contenu="+t.getContenu()+" &nom="+t.getNom()+" &email="+t.getEmail()+" &prenom=" +t.getPrenom();
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    /********** Affichage********************/
      
     public ArrayList<reclamation> affichageReclamation()
    {

        ArrayList<reclamation> result = new ArrayList<>();
        String url ="http://127.0.0.1:8000/"+"reclamation/afficheM";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try 
                {
                    Map<String,Object>mapR = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> ListOfMaps = (List<Map<String,Object>>) mapR.get("root");
                    System.out.println(mapR);
                    for(Map<String, Object> obj : ListOfMaps)
                    {
                        System.out.println(obj);
                       reclamation c = new reclamation();
                       
                        String contenu = obj.get("contenu").toString();
                         float id = Float.parseFloat(obj.get("id").toString());
                         String nom = obj.get("nom").toString();
                         String email = obj.get("email").toString();
                         String prenom = obj.get("prenom").toString();
                       

                       c.setId((int)id);
                        c.setContenu(contenu);
                        
                        c.setNom(nom); 
                        c.setEmail(email);
                         c.setPrenom(prenom);
                        
                        result.add(c);
                        System.out.println(c.getContenu()+c.getNom()+c.getEmail()+c.getPrenom());
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;
    
    }    

    
     /*********************************************update***************************************************/    
    public boolean modifierReclamation(reclamation t) {
        String contenu = t.getContenu();
        String url= "http://127.0.0.1:8000/"+"reclamation/modifierM?id="+t.getId()+"&contenu="+t.getContenu()+"&nom="+t.getNom()+"&email="+t.getEmail()+"&prenom="+t.getPrenom();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy 
    return resultOK;
        
    }
    //////////////////////////////////delete///////////////////////////////////////
    public boolean deleteReclamation(int id ) {
        String url = "http://127.0.0.1:8000/" +"reclamation/deleteM?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOK;
    }
   
}

    
   

