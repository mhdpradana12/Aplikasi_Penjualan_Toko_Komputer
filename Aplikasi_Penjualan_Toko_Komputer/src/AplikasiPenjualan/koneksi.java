/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AplikasiPenjualan;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Pradana
 */

public class koneksi {
     private static java.sql.Connection koneksi;
     
      public static java.sql.Connection getKoneksi(){
        if(koneksi == null){
          try{
              String url = "jdbc:mysql://localhost:3306/db_aplikasi";
              String user = "root";
              String password = "dana123";
              DriverManager.registerDriver(new com.mysql.jdbc.Driver());
              koneksi = DriverManager.getConnection(url, user, password);
          }catch(SQLException e){
              System.out.println(e.getMessage());
          }  
        }
        return koneksi;
    }
}
