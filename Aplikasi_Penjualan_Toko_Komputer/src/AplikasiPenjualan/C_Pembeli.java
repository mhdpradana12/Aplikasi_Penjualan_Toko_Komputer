package AplikasiPenjualan;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class C_Pembeli {
    public List<E_Pembeli> readData(){
        Connection conn = koneksi.koneksi.getConnection();
        List<E_Pembeli> hasil = new ArrayList<E_Pembeli>();
        try{
            java.sql.Statement stmt = conn.createStatement();
            String SQL = "SELECT * FROM tbl_pembeli";
            java.sql.ResultSet res = stmt.executeQuery(SQL);

            while (res.next()){
                E_Pembeli data = new E_Pembeli();
                data.idPembeli=res.getString("id_pembeli");
                data.namaPembeli=res.getString("nama_pembeli");
                data.noTelp=res.getString("no_telp");
                data.alaMat=res.getString("alamat");
                System.out.println(data);
                hasil.add(data);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } return hasil;
    }
}
