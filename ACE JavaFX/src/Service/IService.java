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
public interface IService<T> {
    
        public void add(T t)throws SQLException;
        
        public void update(T t)throws SQLException;
        
        public void Delete(T t)throws SQLException;
        
        List<T> readAll()throws SQLException;
        
        T findById(int id)throws SQLException;


}
