package AplikasiPenjualan;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import javax.swing.JOptionPane;

public class F_Transaksi extends javax.swing.JFrame {
    DefaultTableModel dtTransaksi;
    C_Transaksi cTransaksi;

    public F_Transaksi() {
        initComponents();
        Tampil_ID();
        Connection conn = koneksi.koneksi.getConnection();
        cTransaksi = new C_Transaksi();
        setTabel();
        loadData();
    }
    
    public void setTabel(){
        dtTransaksi = new DefaultTableModel();
        dtTransaksi.addColumn("KODE BARANG");
        dtTransaksi.addColumn("NAMA BARANG");
        dtTransaksi.addColumn("HARGA BARANG");
        dtTransaksi.addColumn("BANYAK BARANG");
        dtTransaksi.addColumn("SUB TOTAL");
        jtblTransaksi.setModel(dtTransaksi);
    }
    
    public void showData(){
        try{
        Connection conn = koneksi.koneksi.getConnection();
        String sql = "SELECT * FROM tbl_barang WHERE kode_barang='"+tfKodeBarang.getText()+"'";
        Statement st = koneksi.koneksi.getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            tfNamaBarang.setText(rs.getString("nama_barang"));
            tfVendorBarang.setText(rs.getString("vendor_barang"));
            tfStokBarang.setText(rs.getString("stok_barang"));
            tfHargaBarang.setText(rs.getString("harga_barang"));
        }rs.close(); st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"GAGAL");
        }
    }
    
    public void showDataPembeli(){
        try{
        Connection conn = koneksi.koneksi.getConnection();
        String sql = "SELECT * FROM tbl_pembeli WHERE id_pembeli='"+tfIdPembeli.getText()+"'";
        Statement st = koneksi.koneksi.getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            tfNamaPembeli.setText(rs.getString("nama_pembeli"));
        }rs.close(); st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"GAGAL");
        }
    }
    
    public void showDataTransaksi(){
        try{
        Connection conn = koneksi.koneksi.getConnection();
        String sql = "SELECT * FROM tbl_transaksi WHERE id_transaksi='"+tfIdTransaksi.getText()+"'";
        Statement st = koneksi.koneksi.getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        while(rs.next()){
            tfIdPembeli.setText(rs.getString("id_pembeli"));
            jBeli.setDate(rs.getDate("tgl_beli"));
            tfTotal.setText(String.valueOf(rs.getInt("total")));
            tfCash.setText(String.valueOf(rs.getInt("cash")));
            tfKembali.setText(String.valueOf(rs.getInt("kembali")));
        }rs.close(); st.close(); loadData();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"GAGAL");
        }
    }
    
    public void TambahDetail(){
       String idTransaksi = tfIdTransaksi.getText();
       String kodeBarang = tfKodeBarang.getText();
       String harga = tfHargaBarang.getText();
       String banyakBarang = tfBanyak.getText();
       String subTotal = tfSubTotal.getText();
       
       try{
           Connection conn = koneksi.koneksi.getConnection();
           String sql = "INSERT INTO tbl_detail_transaksi values (?,?,?,?,?)";
           PreparedStatement p = conn.prepareStatement(sql);
           p.setString(1, idTransaksi);
           p.setString(2, kodeBarang);
           p.setString(3, harga);
           p.setString(4, banyakBarang);
           p.setString(5, subTotal);
           p.executeUpdate();
           p.close();
           JOptionPane.showMessageDialog(null, "Barang Telah Ditambahkan");
       } catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Terjadi Kesalahan pada " +e);
       }
    }
    
    public final void loadData(){
        dtTransaksi.getDataVector().removeAllElements();
        dtTransaksi.fireTableDataChanged();
        try{
            Connection conn = koneksi.koneksi.getConnection();
            Statement s = conn.createStatement();
            String sql = "SELECT * FROM tbl_detail_transaksi, tbl_barang WHERE tbl_detail_transaksi.kode_barang = tbl_barang.kode_barang AND tbl_detail_transaksi.id_transaksi='"+tfIdTransaksi.getText()+"'";
            ResultSet r = s.executeQuery(sql);
            while(r.next()){
                Object[]o=new Object[6];
                o[0]=r.getString("kode_barang");
                o[1]=r.getString("nama_barang");
                o[2]=r.getString("harga");
                o[3]=r.getString("banyak_barang");
                o[4]=r.getString("sub_total");
                o[5]=r.getString("stok_barang");
                dtTransaksi.addRow(o);
            }
            r.close();
            s.close();
        } catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Terjadi Kesalahan pada " +e);
        }
        
        //menjumlahkan isi kolom ke 4 ke tabel
        int total = 0;
        for (int i=0; i<jtblTransaksi.getRowCount();i++){
            int amount = Integer.parseInt((String)jtblTransaksi.getValueAt(i,4));
            total += amount;
        }tfTotal.setText(""+total);
    }
    
    public void UpdateStock(){
        int x,y,z;
        x = Integer.parseInt(tfStokBarang.getText());
        y = Integer.parseInt(tfBanyak.getText());
        z = x-y;
        
        String kodeBarang = tfKodeBarang.getText();
        try{
            Connection conn = koneksi.koneksi.getConnection();
            String sql = "UPDATE tbl_barang SET stok_barang=? WHERE kode_barang=?";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setInt(1,z);
            p.setString(2,kodeBarang);
            p.executeUpdate();
            p.close();
        }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Terjadi Kesalahan pada " +e);
        }
    }
    
    public void Batal(){
        int x,y,z;
        x = Integer.parseInt(tfStokBarang.getText());
        y = Integer.parseInt(tfBanyak.getText());
        z = x+y;
        
        String kodeBarang = tfKodeBarang.getText();
        try{
            Connection conn = koneksi.koneksi.getConnection();
            String sql = "UPDATE tbl_barang SET stok_barang=? WHERE kode_barang=?";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setInt(1,z);
            p.setString(2,kodeBarang);
            p.executeUpdate();
            p.close();
        }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Terjadi Kesalahan pada " +e);
        }
        
        try {
            Connection conn = koneksi.koneksi.getConnection();
            String sql = "DELETE FROM tbl_detail_transaksi WHERE id_transaksi='"+tfIdTransaksi.getText()+"' AND kode_barang='"+tfKodeBarang.getText()+"'";
            PreparedStatement p = conn.prepareStatement(sql);
            p.executeUpdate();
            p.close();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan pada " +e);
        } finally{
            loadData();
            JOptionPane.showMessageDialog(null, "Barang Berhasil Dihapus");
        }
    }
    
    public void Daftar(){
        String tanggal = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tanggal);
        
        String idTransaksi = tfIdTransaksi.getText();
        String idPembeli = tfIdPembeli.getText();
        String total = tfTotal.getText();
        String cash = tfCash.getText();
        String kembali = tfKembali.getText();
        String tanggalbeli = String.valueOf(fm.format(jBeli.getDate()));
        
        try{
            Connection conn = koneksi.koneksi.getConnection();
            String sql = "INSERT INTO tbl_transaksi values(?,?,?,?,?,?)";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(1,idTransaksi);
            p.setString(2,idPembeli);
            p.setString(3,tanggalbeli);
            p.setString(4,total);
            p.setString(5,cash);
            p.setString(6,kembali);
            p.executeUpdate();
            p.close();
            loadData();
            JOptionPane.showMessageDialog(null, "Transaksi Telah Terdaftar");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan pada " +e);
        }
    }
    
    public void Selesai(){
        String tanggal = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tanggal);
        
        String idTransaksi = tfIdTransaksi.getText();
        String idPembeli = tfIdPembeli.getText();
        String total = tfTotal.getText();
        String cash = tfCash.getText();
        String kembali = tfKembali.getText();
        String tanggalbeli = String.valueOf(fm.format(jBeli.getDate()));
        
        try{
            Connection conn = koneksi.koneksi.getConnection();
            String sql = "UPDATE tbl_transaksi SET id_pembeli=?, tgl_beli=?, total=?, cash=?, kembali=? WHERE id_transaksi=?";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(6,idTransaksi);
            p.setString(1,idPembeli);
            p.setString(2,tanggalbeli);
            p.setString(3,total);
            p.setString(4,cash);
            p.setString(5,kembali);
            p.executeUpdate();
            p.close();
            loadData();
            JOptionPane.showMessageDialog(null, "Transaksi Berhasil Disimpan");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan pada " +e);
        }
    }
    
    public void Tampil_ID() {
    try {
        Connection conn = koneksi.koneksi.getConnection();
        String sql = "SELECT MAX(RIGHT(id_transaksi,6)) AS NO FROM tbl_transaksi";
        Statement st = conn.createStatement();
        ResultSet rsjual = st.executeQuery(sql);
        while (rsjual.next()) {
            if (rsjual.first() == false) {
                 tfIdTransaksi.setText("IT000001");
            } else {
                rsjual.last();
                int auto_id = rsjual.getInt(1) + 1;
                String no = String.valueOf(auto_id);
                int NomorJual = no.length();
                for (int j = 0; j < 6 - NomorJual; j++) {
                    no = "0" + no;
                }
                tfIdTransaksi.setText("IT" + no);
            }
        }
        rsjual.close();
        st.close();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan pada " +e);
        }
    }
    
    public void clearBarang(){
        tfKodeBarang.setText("");
        tfNamaBarang.setText("");
        tfHargaBarang.setText("");
        tfVendorBarang.setText("");
        tfBanyak.setText("");
        tfSubTotal.setText("");
        tfStokBarang.setText("");
    }
    
    public void clearTransaksi(){
        tfIdTransaksi.setText("");
        tfIdPembeli.setText("");
        tfNamaPembeli.setText("");
        tfCash.setText("0");
        tfKembali.setText("0");
        tfTotal.setText("0");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitle = new javax.swing.JLabel();
        lbIdTransaksi = new javax.swing.JLabel();
        lbIdPembeli = new javax.swing.JLabel();
        lbNamaPembeli = new javax.swing.JLabel();
        lbKodeBarang = new javax.swing.JLabel();
        lbNamaBarang = new javax.swing.JLabel();
        lbVendorBarang = new javax.swing.JLabel();
        lbHargaBarang = new javax.swing.JLabel();
        lbStokBarang = new javax.swing.JLabel();
        lbJumlah = new javax.swing.JLabel();
        lbSubTotal = new javax.swing.JLabel();
        lbTanggal = new javax.swing.JLabel();
        tfIdTransaksi = new javax.swing.JTextField();
        tfIdPembeli = new javax.swing.JTextField();
        tfNamaPembeli = new javax.swing.JTextField();
        tfNamaBarang = new javax.swing.JTextField();
        tfVendorBarang = new javax.swing.JTextField();
        tfHargaBarang = new javax.swing.JTextField();
        tfStokBarang = new javax.swing.JTextField();
        tfBanyak = new javax.swing.JTextField();
        tfSubTotal = new javax.swing.JTextField();
        tfTotal = new javax.swing.JTextField();
        jBeli = new com.toedter.calendar.JDateChooser();
        btnTambah = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblTransaksi = new javax.swing.JTable();
        lbTotalHarga = new javax.swing.JLabel();
        lbCash = new javax.swing.JLabel();
        lbKembalian = new javax.swing.JLabel();
        tfCash = new javax.swing.JTextField();
        tfKembali = new javax.swing.JTextField();
        tfKodeBarang = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnDaftar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbTitle.setText("APLIKASI PENJUALAN TOKO KOMPUTER");
        getContentPane().add(lbTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 380, -1));

        lbIdTransaksi.setBackground(new java.awt.Color(0, 0, 0));
        lbIdTransaksi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbIdTransaksi.setText("ID Transaksi");
        getContentPane().add(lbIdTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 95, -1));

        lbIdPembeli.setBackground(new java.awt.Color(0, 0, 0));
        lbIdPembeli.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbIdPembeli.setText("ID Pembeli");
        getContentPane().add(lbIdPembeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 95, 23));

        lbNamaPembeli.setBackground(new java.awt.Color(0, 0, 0));
        lbNamaPembeli.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbNamaPembeli.setText("Nama Pembeli");
        getContentPane().add(lbNamaPembeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 118, -1));

        lbKodeBarang.setBackground(new java.awt.Color(0, 0, 0));
        lbKodeBarang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbKodeBarang.setText("Kode Barang");
        getContentPane().add(lbKodeBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 118, -1));

        lbNamaBarang.setBackground(new java.awt.Color(0, 0, 0));
        lbNamaBarang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbNamaBarang.setText("Nama Barang");
        getContentPane().add(lbNamaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 118, -1));

        lbVendorBarang.setBackground(new java.awt.Color(0, 0, 0));
        lbVendorBarang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbVendorBarang.setText("Vendor Barang");
        getContentPane().add(lbVendorBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 110, -1));

        lbHargaBarang.setBackground(new java.awt.Color(0, 0, 0));
        lbHargaBarang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbHargaBarang.setText("Harga Barang");
        getContentPane().add(lbHargaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, 118, -1));

        lbStokBarang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbStokBarang.setText("Stok Barang");
        getContentPane().add(lbStokBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 200, 70, -1));

        lbJumlah.setBackground(new java.awt.Color(0, 0, 0));
        lbJumlah.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbJumlah.setText("Jumlah Beli");
        getContentPane().add(lbJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 250, 120, -1));

        lbSubTotal.setBackground(new java.awt.Color(0, 0, 0));
        lbSubTotal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbSubTotal.setText("Sub Total");
        getContentPane().add(lbSubTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, 118, -1));

        lbTanggal.setBackground(new java.awt.Color(0, 0, 0));
        lbTanggal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbTanggal.setText("Tanggal Pembelian");
        getContentPane().add(lbTanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 118, -1));

        tfIdTransaksi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tfIdTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfIdTransaksiActionPerformed(evt);
            }
        });
        getContentPane().add(tfIdTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 270, 30));

        tfIdPembeli.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tfIdPembeli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfIdPembeliKeyReleased(evt);
            }
        });
        getContentPane().add(tfIdPembeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 270, 30));

        tfNamaPembeli.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(tfNamaPembeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 270, 30));

        tfNamaBarang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(tfNamaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 270, 30));

        tfVendorBarang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(tfVendorBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, 270, 30));

        tfHargaBarang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(tfHargaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 150, 270, 30));

        tfStokBarang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(tfStokBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, 270, 30));

        tfBanyak.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tfBanyak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfBanyakKeyReleased(evt);
            }
        });
        getContentPane().add(tfBanyak, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 250, 270, 30));

        tfSubTotal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(tfSubTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 300, 270, 30));

        tfTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(tfTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 580, 390, -1));

        jBeli.setDateFormatString("yyyy-MM-dd");
        getContentPane().add(jBeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 270, 30));

        btnTambah.setText("Tambah Barang");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        getContentPane().add(btnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, 130, 30));

        btnBatal.setText("Hapus Barang");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        getContentPane().add(btnBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 350, 130, 30));

        btnClear.setText("Bersihkan");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        getContentPane().add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 350, 120, 30));

        jtblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtblTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblTransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblTransaksi);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 870, 130));

        lbTotalHarga.setBackground(new java.awt.Color(0, 0, 0));
        lbTotalHarga.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTotalHarga.setText("Total Harga (Rp.)");
        getContentPane().add(lbTotalHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 540, 160, -1));

        lbCash.setBackground(new java.awt.Color(0, 0, 0));
        lbCash.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbCash.setText("Cash");
        getContentPane().add(lbCash, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 118, -1));

        lbKembalian.setBackground(new java.awt.Color(0, 0, 0));
        lbKembalian.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbKembalian.setText("Kembalian");
        getContentPane().add(lbKembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, 118, -1));

        tfCash.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tfCash.setText("0");
        tfCash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfCashKeyReleased(evt);
            }
        });
        getContentPane().add(tfCash, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 540, 225, 30));

        tfKembali.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tfKembali.setText("0");
        getContentPane().add(tfKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 590, 225, 30));

        tfKodeBarang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tfKodeBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfKodeBarangKeyReleased(evt);
            }
        });
        getContentPane().add(tfKodeBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 270, 30));

        btnSimpan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSimpan.setText("SELESAI");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        getContentPane().add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 580, 120, 60));

        btnDaftar.setText("Daftarkan Transaksi");
        btnDaftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDaftarActionPerformed(evt);
            }
        });
        getContentPane().add(btnDaftar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 150, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfIdTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfIdTransaksiActionPerformed
        showDataTransaksi();
        showDataPembeli();
    }//GEN-LAST:event_tfIdTransaksiActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        TambahDetail();
        UpdateStock();
        loadData();
        clearBarang();
        
        //kosongkan tf cash dan kembalian
        tfCash.setText("");
        tfKembali.setText("");
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        Selesai();
        clearTransaksi();
        Tampil_ID();
        loadData();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnDaftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDaftarActionPerformed
        // TODO add your handling code here:
        Daftar();
    }//GEN-LAST:event_btnDaftarActionPerformed

    private void jtblTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblTransaksiMouseClicked
        // TODO add your handling code here:
        int i = jtblTransaksi.getSelectedRow();
        if (i == -1){
            return; //jika tidak ada baris yang terseleksi
        }
        
        showData();
        String kodeBarang = (String) dtTransaksi.getValueAt(i,0);
        tfKodeBarang.setText(kodeBarang);
        String namaBarang = (String) dtTransaksi.getValueAt(i,1);
        tfNamaBarang.setText(namaBarang);
        String harga = (String) dtTransaksi.getValueAt(i,2);
        tfHargaBarang.setText(harga);
        String banyakBarang = (String) dtTransaksi.getValueAt(i,3);
        tfBanyak.setText(banyakBarang);
        String subTotal = (String) dtTransaksi.getValueAt(i,4);
        tfSubTotal.setText(subTotal);
    }//GEN-LAST:event_jtblTransaksiMouseClicked

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        Batal();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clearBarang();
    }//GEN-LAST:event_btnClearActionPerformed

    private void tfKodeBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKodeBarangKeyReleased
        // TODO add your handling code here:
        showData();
    }//GEN-LAST:event_tfKodeBarangKeyReleased

    private void tfBanyakKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBanyakKeyReleased
        // TODO add your handling code here:
        Integer harga = Integer.valueOf(tfHargaBarang.getText());
        tfHargaBarang.setText(String.valueOf(harga));
        Integer banyakBarang = Integer.valueOf(tfBanyak.getText());
        tfBanyak.setText(String.valueOf(banyakBarang));

        Integer subTotal = harga*banyakBarang;
        tfSubTotal.setText(String.valueOf(subTotal));
    }//GEN-LAST:event_tfBanyakKeyReleased

    private void tfCashKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCashKeyReleased
        // TODO add your handling code here:
        Integer total = Integer.valueOf(tfTotal.getText());
        tfTotal.setText(String.valueOf(total));
        Integer cash = Integer.valueOf(tfCash.getText());
        tfCash.setText(String.valueOf(cash));
        
        Integer kembali = cash-total;
        tfKembali.setText(String.valueOf(kembali));
    }//GEN-LAST:event_tfCashKeyReleased

    private void tfIdPembeliKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfIdPembeliKeyReleased
        // TODO add your handling code here:
        showDataPembeli();
    }//GEN-LAST:event_tfIdPembeliKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(F_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(F_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(F_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(F_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new F_Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDaftar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private com.toedter.calendar.JDateChooser jBeli;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtblTransaksi;
    private javax.swing.JLabel lbCash;
    private javax.swing.JLabel lbHargaBarang;
    private javax.swing.JLabel lbIdPembeli;
    private javax.swing.JLabel lbIdTransaksi;
    private javax.swing.JLabel lbJumlah;
    private javax.swing.JLabel lbKembalian;
    private javax.swing.JLabel lbKodeBarang;
    private javax.swing.JLabel lbNamaBarang;
    private javax.swing.JLabel lbNamaPembeli;
    private javax.swing.JLabel lbStokBarang;
    private javax.swing.JLabel lbSubTotal;
    private javax.swing.JLabel lbTanggal;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbTotalHarga;
    private javax.swing.JLabel lbVendorBarang;
    private javax.swing.JTextField tfBanyak;
    private javax.swing.JTextField tfCash;
    private javax.swing.JTextField tfHargaBarang;
    private javax.swing.JTextField tfIdPembeli;
    private javax.swing.JTextField tfIdTransaksi;
    private javax.swing.JTextField tfKembali;
    private javax.swing.JTextField tfKodeBarang;
    private javax.swing.JTextField tfNamaBarang;
    private javax.swing.JTextField tfNamaPembeli;
    private javax.swing.JTextField tfStokBarang;
    private javax.swing.JTextField tfSubTotal;
    private javax.swing.JTextField tfTotal;
    private javax.swing.JTextField tfVendorBarang;
    // End of variables declaration//GEN-END:variables
}
