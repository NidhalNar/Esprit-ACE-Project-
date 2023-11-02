/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Display;

import com.codename1.ui.Form;
import java.io.IOException;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.html.DocumentInfo;
import com.mycompany.myapp.entities.Destination;
import com.mycompany.myapp.entities.reclamation;
import com.mycompany.myapp.gui.ListDestination;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public class ServiceDestination {
    
    

    public static ServiceDestination instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<Destination> listCategory=new ArrayList<>();

    public ServiceDestination() {
        req = new ConnectionRequest();
    }

    public static ServiceDestination getInstance() {
        if (instance == null) {
            instance = new ServiceDestination();
        }
        return instance;
    }
//ajout
    public boolean addDestination(Destination t) {
        
        String ville = t.getVille();
        String pays = t.getPays();
        
             

    
        String url= "http://127.0.0.1:8000/"+"destination/newdestjson?ville="+t.getVille()+"&pays="+t.getPays();
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
      
     public ArrayList<Destination> affichageDestination()
    {

        ArrayList<Destination> result = new ArrayList<>();
        String url ="http://127.0.0.1:8000/"+"destination/displaydestinationjson";
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
                       Destination c = new Destination();
                       
                         float id = Float.parseFloat(obj.get("id").toString());
                         String ville = obj.get("ville").toString();
                         String pays = obj.get("pays").toString();
                       

                         c.setId((int)id);
                        c.setVille(ville);
                        
                        c.setPays(pays); 
                       
                        
                        result.add(c);
                         System.out.println(c.getPays()+c.getVille());
                        
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
    public boolean modifierDestination(Destination t) {
        String ville = t.getVille();
        String pays = t.getPays();
        int id = t.getId();
        
//"http://127.0.0.1:8000/"+"destination/updatedestjson/id="+t.getId()+"?ville="+t.getVille()+"&pays="+t.getPays();
        String url= "http://127.0.0.1:8000/"+"destination/updatedestjson/"+t.getId()+"?ville="+t.getVille()+"&pays="+t.getPays();
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
    public boolean deleteDestination(int id ) {
        
        //"http://127.0.0.1:8000/"+"destination/deletedestjson/id"+id;
        String url = "http://127.0.0.1:8000/"+"destination/deletedestjson/"+id;
        
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
