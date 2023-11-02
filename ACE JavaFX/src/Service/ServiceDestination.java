
package Service;

import Entite.Destination;
import Utils.DataSource;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
    public class ServiceDestination implements IService<Destination>{
        
         private Connection con= DataSource.getInstance().getConnection();
         private Statement ste;

    public ServiceDestination() {
         try {
           ste=con.createStatement();
       } catch (SQLException ex) {
          System.out.print(ex);
       }
    }
   
   

    @Override
    public void add(Destination t) throws SQLException {
       String req = "INSERT INTO destination (pays,ville) VALUES(?,?)";  
        PreparedStatement pre=con.prepareStatement(req); 
        pre.setString(1,t.getPays());
        pre.setString(2,t.getVille());
         
         
         int rowsInserted = pre.executeUpdate();
         if (rowsInserted > 0) {
             System.out.println("A new destination was inserted successfully!");
         }
       
     
     
    
    }
    

    @Override
    public void update(Destination t) throws SQLException {
       String req = "UPDATE destination SET pays=?, ville=? WHERE id=?";
 
    PreparedStatement pre = con.prepareStatement(req);
    pre.setString(1,t.getPays() );
    pre.setString(2,t.getVille() );
    pre.setInt(3, t.getId());
    int rowsUpdated = pre.executeUpdate();
    if (rowsUpdated > 0) {
        System.out.println("An existing destination was updated successfully!");
    }
       
    }

    @Override
    public void Delete(Destination t) throws SQLException {
    String req = "DELETE FROM destination WHERE id=?";
 
    PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, t.getId());
 
    
    int rowsDeleted = pre.executeUpdate();
    if (rowsDeleted > 0) {
        System.out.println("A user was deleted successfully!");
    }
       
        
    
 }

    @Override
    public List<Destination> readAll() throws SQLException {
         ArrayList<Destination> listper=new ArrayList<>();
        String req="select * from destination";
        ResultSet res=ste.executeQuery(req);
        
        while (res.next()) {            
            int id=res.getInt("id");
              String pays=res.getString("pays");
            String ville=res.getString("ville");
          
            
            Destination d=new Destination(id, pays, ville);
           // System.out.println(p);
            listper.add(d);
        }
        return listper;
    }

    @Override
    public Destination findById(int id) throws SQLException {
        
        String req = "SELECT * FROM destination WHERE id = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, id);
        ResultSet res = pre.executeQuery();
        if (res.next()) {
            String ville = res.getString("ville");
            String pays = res.getString("pays");
            Destination d = new Destination(id, pays, ville);
            return d;
        }
    
    return null;
    }
    
}
