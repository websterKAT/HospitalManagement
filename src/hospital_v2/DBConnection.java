/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital_v2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lakshan
 */
public class DBConnection {
    private Connection DBConnection;    
    public Connection connect(){
         try {
             Class.forName("com.mysql.jdbc.Driver");
             System.out.println("Connection Success!!");
         }
         catch(ClassNotFoundException cnfe){
             System.out.println("Connection Failed"+cnfe);
         }
         String url = "jdbc:mysql://localhost:3306/hospital_info";
         try {
             DBConnection = (Connection) DriverManager.getConnection(url,"root","Ieeew3");
             System.out.println("Database Connected");
         }
         catch(SQLException se) {
             System.out.println("No database"+se);
         }
         return DBConnection;
    }
}
