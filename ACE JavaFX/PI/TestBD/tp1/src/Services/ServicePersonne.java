/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entite.Personne;
import Utils.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sanabenfadhel
 */
public class ServicePersonne implements IService<Personne>{
 
    Connection con=DataSource.getInstance().getConnection();
    
    private Statement ste;

    public ServicePersonne() {
        
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
    
    @Override
    public void ajouter(Personne t) throws SQLException {
            String req = "INSERT INTO `personne` ( `nom`, `prenom`, `age`) VALUES "
                    + "( '"+t.getNom()+"', '"+t.getPrenom()+"', '"+t.getAge()+"');";

            
            ste.executeUpdate(req);
    }
    
    public void ajouterPST(Personne p) throws SQLException
    {
    String req = "INSERT INTO `personne` ( `nom`, `prenom`, `age`) VALUES ( ?,?,?);";

     PreparedStatement pre=con.prepareStatement(req);
        
     
     pre.setString(1,p.getNom() );
     pre.setString(2, p.getPrenom());
     pre.setInt(3, p.getAge());
     
     pre.executeUpdate();
    }

    @Override
    public void update(Personne t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprime(Personne t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Personne> readAll() throws SQLException{
        ArrayList<Personne> listper=new ArrayList<>();
        
        String req="select * from personne";
        
        ResultSet res=ste.executeQuery(req);
        
        
        while (res.next()) {            
            int id=res.getInt(1);
            String nom=res.getString(2);
            String prenom=res.getString("prenom");
            int age=res.getInt(4);
            Personne p=new Personne(id, nom, prenom, age);
           // System.out.println(p);
            listper.add(p);
        }
        return listper;
    }

    @Override
    public Personne findById(int id)  throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
