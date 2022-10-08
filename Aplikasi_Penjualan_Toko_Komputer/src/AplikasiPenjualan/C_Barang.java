package AplikasiPenjualan;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class C_Barang {
    public List<E_Barang> readData(){
        Connection conn = koneksi.koneksi.getConnection();
        List<E_Barang> hasil = new ArrayList<E_Barang>();
        try{
            java.sql.Statement stmt = conn.createStatement();
            String SQL = "SELECT * FROM tbl_barang";
            java.sql.ResultSet res = stmt.executeQuery(SQL);

            while (res.next()){
                E_Barang data = new E_Barang();
                data.kodeBarang=res.getString("kode_barang");
                data.namaBarang=res.getString("nama_barang");
                data.vendorBarang=res.getString("vendor_barang");
                data.stokBarang=res.getInt("stok_barang");
                data.hargaBarang=res.getInt("harga_barang");
                System.out.println(data);
                hasil.add(data);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } return hasil;
    }
}
