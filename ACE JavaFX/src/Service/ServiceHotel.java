/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Destination;
import Entite.hotel;
import Utils.DataSource;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class ServiceHotel implements IServiceHotel<hotel>{
    
         private Connection con= DataSource.getInstance().getConnection();
         private Statement ste;
         
          public ServiceHotel() {
         try {
           ste=con.createStatement();
       } catch (SQLException ex) {
          System.out.print(ex);
       }
    }

    @Override
    public void addhotel(hotel t) throws SQLException {
      String req = "INSERT INTO hotel (nom,etoile,type) VALUES(?,?,?)";  
        PreparedStatement pre=con.prepareStatement(req); 
        pre.setString(1,t.getNom()); 
        pre.setString(2,t.getEtoile()); 
        pre.setString(3,t.getType()); 
       
       
         
         
         int rowsInserted = pre.executeUpdate();
         if (rowsInserted > 0) {
             System.out.println("A new destination was inserted successfully!");
         }
       
    }

    @Override
    public void updatehotel(hotel t) throws SQLException {
        String req = "UPDATE hotel SET nom=?, etoile=?,type=?  WHERE id=?";
 
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1,t.getNom() );
         pre.setString(2,t.getEtoile()); 
        pre.setString(3,t.getType()); 
        
        pre.setInt(4, t.getId());

        int rowsUpdated = pre.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing destination was updated successfully!");
        }    }

    @Override
    public void Deletehotel(hotel t) throws SQLException {
        String req = "DELETE FROM hotel WHERE id=?";
 
    PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, t.getId());
 
    
    int rowsDeleted = pre.executeUpdate();
    if (rowsDeleted > 0) {
        System.out.println("A user was deleted successfully!");
    }    }

    @Override
    public List<hotel> readAllhotels() throws SQLException {
        
        ArrayList<hotel> listper=new ArrayList<>();
        String req="select * from hotel";
        ResultSet res=ste.executeQuery(req);
        
        while (res.next()) {            
            int id=res.getInt("id");
            String nom=res.getString("nom");
           String etoile=res.getString("etoile");
           String type=res.getString("type");

           

            
          
            
            hotel d=new hotel(id, nom, etoile,type);
           // System.out.println(p);
            listper.add(d);
        }
        return listper;    }

    @Override
    public hotel findhotelById(int id) throws SQLException {
  String req = "SELECT * FROM hotel WHERE id = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, id);
        ResultSet res = pre.executeQuery();
        if (res.next()) {
            String nom = res.getString("nom");
            String etoile=res.getString("etoile");
           String type=res.getString("type");

            
            
            hotel h = new hotel(id, nom, etoile,type);
            return h;
        }
    
    return null;    }
    
    public hotel getho(String nom, String etoile,String type ) throws SQLException {
    String query = "SELECT * FROM hotel WHERE nom = ? AND etoile = ? AND type = ?";
    try (PreparedStatement statement = con.prepareStatement(query)) {
        statement.setString(1, nom);
        statement.setString(2, etoile);
          statement.setString(3, type);
        try (ResultSet result = statement.executeQuery()) {
            if (result.next()) {
                hotel d = new hotel();
                d.setId(result.getInt("id"));
                d.setNom(result.getString("nom"));
                d.setEtoile(result.getString("etoile"));
                d.setType(result.getString("type"));
                return d;
            }
        }
    }
    return null;
}
    
}
