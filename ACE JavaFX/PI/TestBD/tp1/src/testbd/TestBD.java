/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testbd;

import java.sql.*;

/**
 *
 * @author sanabenfadhel
 */
public class TestBD {

    private static Connection con;
    private static String url = "jdbc:mysql://localhost:3306/esprit";
    private static String login = "root";
    private static String pwd = "";
    private static Statement ste;

    public static void main(String[] args) {
        // TODO code application logic here
        try {
            con = DriverManager.getConnection(url, login, pwd);
            System.out.println("connexion établie");
        } catch (SQLException e) {
            System.out.println(e);
        }

        try {
            ste = con.createStatement();

            String req = "INSERT INTO `personne` ( `nom`, `prenom`, `age`) VALUES ( 'Ben FADHEL', 'ons', '12');";

            int res = ste.executeUpdate(req);
            System.out.println(res);
            
            ResultSet rest=ste.executeQuery("select * from personne");
            while (rest.next()) {                
                int id=rest.getInt(1);
                String nom=rest.getString("nom");
                String prenom=rest.getString("prenom");
                int age=rest.getInt("age");
                
                System.out.println("id :"+id+" nom :"+nom+
                        " prenom :"+prenom+" age :"+age);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
