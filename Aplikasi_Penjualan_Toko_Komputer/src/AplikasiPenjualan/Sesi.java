/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AplikasiPenjualan;

/**
 *
 * @author Pradana
 */
public class Sesi {
    public static String id_user_log;
    public static String username_log;
    public static String password_log;
    
    public static String getId_user_log(){
        return id_user_log;
    }
    
    public static void setId_user_log(String user_id_l){
        Sesi.id_user_log = user_id_l;
    }
    
    public static String getUsername_log(){
        return username_log;
    }
    
    public static void setUsername_log(String username_l){
        Sesi.username_log = username_l;
    }
    
    public static String getPassword_log(){
        return password_log;
    }
    
    public static void setPassword_log(String password_l){
        Sesi.password_log = password_l;
    }
}
