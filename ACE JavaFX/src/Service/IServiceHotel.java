/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface IServiceHotel<T> {
    
     public void addhotel(T t)throws SQLException;
        
        public void updatehotel(T t)throws SQLException;
        
        public void Deletehotel(T t)throws SQLException;
        
        List<T> readAllhotels()throws SQLException;
        
        T findhotelById(int id)throws SQLException;
    
}
