/*
 * **** UAS Praktek Pemrograman Visual ****
 * Nama  : Valencia Maharani Sonia Putri  *
 * NIM   : 062030701640                   *
 * Kelas : 4CB                            *
 ******************************************
 */

package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {
    public static Connection getConnection(){
        Connection conn = null;
        String url ="jdbc:mysql://localhost:3306/db_penjualan";
        String user = "root";
        String password = "";
        try{
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("Koneksi Sukses");
        } catch (Exception e){
            System.out.println(e);
        }  
        return conn;
    }
}