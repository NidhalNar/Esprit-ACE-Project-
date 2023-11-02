/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;
import java.sql.*;
/**
 *
 * @author sanabenfadhel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
//        DataSource dat=DataSource.getInstance();
//        System.out.println(dat);
//        DataSource dat1=DataSource.getInstance();
//        System.out.println(dat1);
//        
//        DataSource dat2=DataSource.getInstance();
//        System.out.println(dat2);
//        
      Connection con=DataSource.getInstance().getConnection();
      
      Connection con1=DataSource.getInstance().getConnection();
        System.out.println(con);
        System.out.println(con1);
        
    }
    
}
