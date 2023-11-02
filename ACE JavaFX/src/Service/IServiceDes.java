/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Lenovo
 * @param <T>
 */
public interface IServiceDes<T> {
    
        public void addDes(T t)throws SQLException;
        
        public void updateDes(T t)throws SQLException;
        
        public void DeleteDes(T t)throws SQLException;
        
        List<T> readAllDes()throws SQLException;
        
        T findDesById(int id)throws SQLException;


}
