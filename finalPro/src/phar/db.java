/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana
 */
public class db {
   static Connection con;
public static Connection connect(){
      
            try {   
              con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopmanagement1","root","");
              System.out.print("ccooon");
            } catch (SQLException ex) {
                Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
            }
      return con;
    }
    
}
