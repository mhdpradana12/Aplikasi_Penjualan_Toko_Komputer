package AplikasiPenjualan;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class C_Transaksi {
    public List<E_Transaksi> readData(){
        Connection conn = koneksi.koneksi.getConnection();
        List<E_Transaksi> hasil = new ArrayList<E_Transaksi>();
        try{
            java.sql.Statement stmt = conn.createStatement();
            String SQL = "SELECT * FROM tbl_detail_transaksi";
            java.sql.ResultSet res = stmt.executeQuery(SQL);

            while (res.next()){
                E_Transaksi data = new E_Transaksi();
                data.idTransaksi=res.getString("id_transaksi");
                data.kodeBarang=res.getString("kode_barang");
                data.harga=res.getInt("harga");
                data.banyakBarang=res.getInt("banyak_barang");
                data.subTotal=res.getInt("sub_total");
                System.out.println(data);
                hasil.add(data);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } return hasil;
    }
}
