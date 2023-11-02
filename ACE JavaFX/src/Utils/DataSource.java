package Utils;

import java.sql.*;

/**
 *
 * @author Lenovo
 */
public class DataSource {
     private static DataSource data;
    private Connection con;
      
    private  String url="jdbc:mysql://localhost:3306/ace";
    private  String login="root";
    private  String pwd="";
    private  Statement ste;
    private DataSource(){
        
    }
    public Connection getConnection(){
        try{
        con=DriverManager.getConnection(url, login, pwd);
          System.out.println("connexion établie");
        }catch(SQLException e){
            System.out.println(e);
        }
        
        return con;
    }
    public static DataSource getInstance(){
        if(data==null){
            data=new DataSource();
            
        }
        return data;
    }
    
    
}
