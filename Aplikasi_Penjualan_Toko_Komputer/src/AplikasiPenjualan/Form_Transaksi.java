/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AplikasiPenjualan;
import java.awt.Desktop;
import java.net.URL;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;

/**
 *
 * @author Pradana
 */

public class Form_Transaksi extends javax.swing.JFrame {
    // Set Variable
    private DefaultTableModel model;
    public long total;
    public long bayar;
    public long kembali;
    public Statement st;
    // Variable Koneksi Ke Database
    Connection cn = koneksi.getKoneksi();
            
    
    /**
     * Creates new form Form_Transaksi
     */
    public Form_Transaksi() {
        initComponents();
        tfIdTransaksi.setEnabled(false);
        tfKodeBarang.setEnabled(false);
        tfHargaSatuan.setEnabled(false);
        model = new DefaultTableModel();
        // Set Title Table
        tblTransaksi.setModel(model);
        model.addColumn("ID Transaksi");
        model.addColumn("Nama Barang");
        model.addColumn("Jumlah Beli");
        model.addColumn("Total");
        model.addColumn("Tanggal Transaksi");
        // Load Method
        loadData();
        noTransaksi();
        tampilpilih();
        
    }
    
    // Method Blokir Huruf Pada Twxt Field
    public void FilterAngka(KeyEvent a){
       if(Character.isAlphabetic(a.getKeyChar())){
           a.consume();
           JOptionPane.showMessageDialog(null, "masukan angka saja!", "peringatan", JOptionPane.WARNING_MESSAGE);
       }
    }
    
    // Method Untuk Membersihkan Form
    private void Clear(){
        noTransaksi();
        btnHapus.setEnabled(false);
        tfKodeBarang.setText("");
        cbNamaBarang.getSelectedItem().equals("Pilih Barang");
        tfHargaSatuan.setText("");
        tfJumlahBarang.setText("");
        tfHitung.setText("");
        tfBayar.setText("");
        tfKembalian.setText("");
    }
    
    // Method Menampilkan Data Ke Table
    public final void loadData() {
        btnHapus.setEnabled(false);
        // Mendapatkan Data
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        try {
            //Koneksi Ke Database
            Connection c = koneksi.getKoneksi();
            // Kueri Menampilkan Table Transaksi
            Statement s = c.createStatement();
            String sql = "SELECT * FROM tbl_transaksi";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                // Menampilkan Data Table Ke Baris Kolom
                Object[] o = new Object[5];
                o[0] = r.getString("id_transaksi");
                o[1] = r.getString("nama_barang");
                o[2] = r.getString("jumlah_beli");
                o[3] = r.getString("total_bayar");
                o[4] = r.getString("tanggal_transaksi");
                model.addRow(o);
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    // Method Untuk Menampilkan Data Nama Barang dari Table Barang Ke Combo Box Nama Barang
    private void tampilpilih() {
        try {
            // Koneksi Ke Database
            Connection c = koneksi.getKoneksi();
            // Kueri Menampilkan Table Barang
            Statement s = c.createStatement();
            String sql = "SELECT nama_barang FROM tbl_barang WHERE stok_barang !='0'";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                // Menampilkan Data Nama Barang Ke Combo Box Nama Barang
                cbNamaBarang.addItem(r.getString("nama_barang"));
            }
            r.last();
            int jumlahdata = r.getRow();
            r.first();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    // Method Membuat No Transaksi Secara Beraturan
    private void noTransaksi() {
        try {
            // Koneksi Ke Database
            Connection c = koneksi.getKoneksi();
            // Kueri Menampilkan Kode Transaksi Di Table Transaksi
            Statement s = c.createStatement();
            String sql = "SELECT * FROM tbl_transaksi ORDER by id_transaksi desc";
            ResultSet r = s.executeQuery(sql);
            // Validasi Cek Nomor Secara Beraturan
            if (r.next()) {
                String notransaksi = r.getString("id_transaksi");
                String AN = "" + (Integer.parseInt(notransaksi) + 1);
                tfIdTransaksi.setText(AN);
            } else {
                tfIdTransaksi.setText("1");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbIdFaktur = new javax.swing.JLabel();
        lbNamaBarang = new javax.swing.JLabel();
        lbKodeBarang = new javax.swing.JLabel();
        lbHargaSatuan = new javax.swing.JLabel();
        tfIdTransaksi = new javax.swing.JTextField();
        cbNamaBarang = new javax.swing.JComboBox<>();
        tfKodeBarang = new javax.swing.JTextField();
        tfHargaSatuan = new javax.swing.JTextField();
        tfJumlahBarang = new javax.swing.JTextField();
        tfHitung = new javax.swing.JTextField();
        btnHitung = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        lbJumlahBeli = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbTitle = new javax.swing.JLabel();
        btnKembali = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnTotal = new javax.swing.JButton();
        tfBayar = new javax.swing.JTextField();
        tfKembalian = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        btnSelesai = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        tfStok = new javax.swing.JTextField();
        btnHapus = new javax.swing.JButton();
        tfId = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setForeground(new java.awt.Color(0, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbIdFaktur.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbIdFaktur.setText("ID Transaksi");
        jPanel1.add(lbIdFaktur, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        lbNamaBarang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbNamaBarang.setText("Nama barang  ");
        jPanel1.add(lbNamaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        lbKodeBarang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbKodeBarang.setText("Kode Barang  ");
        jPanel1.add(lbKodeBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        lbHargaSatuan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbHargaSatuan.setText("Harga Satuan");
        jPanel1.add(lbHargaSatuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        tfIdTransaksi.setEditable(false);
        jPanel1.add(tfIdTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 260, 30));

        cbNamaBarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Barang" }));
        cbNamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNamaBarangActionPerformed(evt);
            }
        });
        jPanel1.add(cbNamaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 260, 30));

        tfKodeBarang.setEditable(false);
        jPanel1.add(tfKodeBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 260, 30));

        tfHargaSatuan.setEditable(false);
        tfHargaSatuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfHargaSatuanActionPerformed(evt);
            }
        });
        jPanel1.add(tfHargaSatuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 260, 30));

        tfJumlahBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfJumlahBarangKeyTyped(evt);
            }
        });
        jPanel1.add(tfJumlahBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 260, 30));

        tfHitung.setEditable(false);
        tfHitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfHitungActionPerformed(evt);
            }
        });
        jPanel1.add(tfHitung, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 260, 30));

        btnHitung.setText("Hitung");
        btnHitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHitungActionPerformed(evt);
            }
        });
        jPanel1.add(btnHitung, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 290, 80, 30));

        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        jPanel1.add(btnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, 260, 30));

        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        tblTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTransaksi);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 540, 130));

        lbJumlahBeli.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbJumlahBeli.setText("Jumlah Beli");
        jPanel1.add(lbJumlahBeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));

        lbTitle.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(255, 255, 255));
        lbTitle.setText("APLIKASI PENJUALAN TOKO KOMPUTER");

        btnKembali.setText("Kembali");
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(lbTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 345, Short.MAX_VALUE)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKembali)
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTitle)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnClear)
                        .addComponent(btnKembali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 50));

        jPanel3.setBackground(new java.awt.Color(0, 153, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTotal.setText("Total");
        btnTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTotalActionPerformed(evt);
            }
        });
        jPanel3.add(btnTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 107, 194, 30));

        tfBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfBayarActionPerformed(evt);
            }
        });
        tfBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfBayarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfBayarKeyTyped(evt);
            }
        });
        jPanel3.add(tfBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 160, 40));

        tfKembalian.setEditable(false);
        jPanel3.add(tfKembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 160, 40));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Bayar");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Kembalian");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        lbTotal.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbTotal.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 150, 30));

        btnSelesai.setText("Selesai Transaksi");
        btnSelesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelesaiActionPerformed(evt);
            }
        });
        jPanel3.add(btnSelesai, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 160, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Rp.");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 50, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 60, 320, 440));

        tfStok.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfStok.setEnabled(false);
        jPanel1.add(tfStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, 40, 30));

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        jPanel1.add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 330, 80, 30));

        tfId.setEditable(false);
        tfId.setText("jTextField2");
        tfId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfIdActionPerformed(evt);
            }
        });
        jPanel1.add(tfId, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 460, -1, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 935, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfHargaSatuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfHargaSatuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfHargaSatuanActionPerformed

    // Method Untuk Menampilkan Data Barang ke Table Barang
    private void cbNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNamaBarangActionPerformed
        // TODO add your handling code here:
        if (cbNamaBarang.getSelectedItem().equals("Pilih Barang")){
            tfKodeBarang.setText("");
            tfHargaSatuan.setText("");
        } else {
        try {
            // Koenksi ke Database
            Connection c = koneksi.getKoneksi();
            // Kueri Menampilkan Nama Barang Di Table Barang
            Statement s = c.createStatement();
            String sql = "SELECT kode_barang, stok_barang FROM tbl_barang WHERE nama_barang ='" + cbNamaBarang.getSelectedItem() + "'";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                // Menampilkan Data Table Ke Combo Box Nama Barang
                tfKodeBarang.setText(r.getString("kode_barang"));
                tfStok.setText(r.getString("stok_barang"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        try {
            // Koenksi ke Database
            Connection c = koneksi.getKoneksi();
            // Kueri Menampilkan Harga Barang Di Table Barang
            Statement s = c.createStatement();
            String sql = "SELECT harga_barang FROM tbl_barang WHERE nama_barang ='" + cbNamaBarang.getSelectedItem() + "'";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                // Menambilkan Data Table Ke Text Field Harga Barang
                tfHargaSatuan.setText(r.getString("harga_barang"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }
    }//GEN-LAST:event_cbNamaBarangActionPerformed

    private void btnHitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHitungActionPerformed
        // TODO add your handling code here:
         if(tfIdTransaksi.getText().equals("") ||tfKodeBarang.getText().equals("") || cbNamaBarang.getSelectedItem().equals("")|| tfHargaSatuan.getText().equals("")|| tfJumlahBarang.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "elektronik berkah", JOptionPane.INFORMATION_MESSAGE);
        
        }else{
        String a = tfJumlahBarang.getText();
        int aa = Integer.parseInt(a);
        
        String b = tfStok.getText();
        int bb = Integer.parseInt(b);
        if(aa > bb){
             JOptionPane.showMessageDialog(null, "Jumlah Melebihi Stok!!!", "Pesan", JOptionPane.INFORMATION_MESSAGE);
             tfJumlahBarang.setText("");
        }else{
       
        if(tfJumlahBarang.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Isi Jumlah Beli!!!");
        }else {
        int jumlah, harga, total;
       
        jumlah = Integer.parseInt(tfJumlahBarang.getText().toString());
        harga = Integer.parseInt(tfHargaSatuan.getText().toString());
        total = jumlah * harga;
        tfHitung.setText(Integer.toString(total)); 
        }
        }
        }  
    }//GEN-LAST:event_btnHitungActionPerformed

    // Method Menambah Data Ke Transaksi
    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        // Validasi Data Biar Tidak Kosong
        if(tfIdTransaksi.getText().equals("") ||tfKodeBarang.getText().equals("") || cbNamaBarang.getSelectedItem().equals("")|| tfHargaSatuan.getText().equals("")|| tfJumlahBarang.getText().equals("")|| tfHitung.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "elektronik berkah", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Menyimpan String Dari Text Field
            String kdbarangg = tfKodeBarang.getText();
            String pilihbarangg = (String)cbNamaBarang.getSelectedItem();
            String hsatuann = tfHargaSatuan.getText();
            String tjumlahh = tfJumlahBarang.getText();
            String totall = tfHitung.getText();
           
            try {
                // Mengambil Data Waktu Sekarang
                long millis=System.currentTimeMillis();  
                java.sql.Date date=new java.sql.Date(millis);  
                System.out.println(date); 
                String tgl = date.toString();
                // Koneksi Ke Data
                Connection c = koneksi.getKoneksi();
                // Kueri Untuk Menambahkan Data
                String sql = "INSERT INTO tbl_transaksi VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, tfIdTransaksi.getText());
                p.setString(2, pilihbarangg);
                p.setString(3, kdbarangg);
                p.setString(4, hsatuann);
                p.setString(5, tjumlahh);
                p.setString(6, totall);
                p.setString(7, tgl);
                p.executeUpdate();
                p.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                noTransaksi();
                Clear();
                JOptionPane.showMessageDialog(null, "Transaksi Berhasil Disimpan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                loadData();    
            }
        }
    }//GEN-LAST:event_btnTambahActionPerformed

    // Event Table Jika Di Klik
    private void tblTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTransaksiMouseClicked
        // TODO add your handling code here:
        // Enable Button Hapus
        btnHapus.setEnabled(true);
        // Seleksi Data Berdasarkan Kolom Table
        int i = tblTransaksi.getSelectedRow();
        if (i == -1) {
            return;
        }
        // Menambahkan Text Ke ID String Text Field
        String id_transaksi = (String) model.getValueAt(i, 0);
        tfIdTransaksi.setText(id_transaksi);
    }//GEN-LAST:event_tblTransaksiMouseClicked

    // Method Untuk Kembali Ke Form Menu
    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        // TODO add your handling code here:
        Form_Menu fb = new Form_Menu();
        fb.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnKembaliActionPerformed

    // Method Selesai Bayar
    private void btnSelesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelesaiActionPerformed
        // TODO add your handling code here:
        // Validasi Data Biar Tidak Kosong
        if(tfBayar.getText().equals("") ||tfKembalian.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lengkapi Data||!", "Pesan", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Mendapatkan String dan Integer
            String a = tfKembalian.getText();
            int ab = Integer.parseInt(String.valueOf(tfKembalian.getText()));
              if(ab < 0){
                JOptionPane.showMessageDialog(null, "Uang Anda kurang", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                tfBayar.setText("");
                tfKembalian.setText("");
              } else {
            try {
            // Koneksi Ke Database   
            Connection c = koneksi.getKoneksi();
            // // Kueri Menampilkan Total Bayar Di Table Transaksi
            Statement s = c.createStatement();
            String sql = "SELECT * FROM tbl_transaksi where total_bayar ='" + lbTotal.getText() + "'";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                // Kueri Untuk Menambahkan Data
                String sqla = "INSERT INTO tbl_detail_transaksi VALUES (?, ?, ?, ?)";
                PreparedStatement p = c.prepareStatement(sqla);
                p.setString(1, r.getString("id_transaksi"));
                p.setString(2, r.getString("kode_barang"));
                p.setString(3, tfBayar.getText());
                p.setString(4, tfKembalian.getText());
                p.executeUpdate();
                p.close();
            }
            r.close();
            s.close();
            JOptionPane.showMessageDialog(null, "Transaksi Selesai", "Pesan", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            loadData();
            Clear();
            noTransaksi();
        }
              }
        }
    }//GEN-LAST:event_btnSelesaiActionPerformed

    // Method Menghitung Total Pembayaran
    private void btnTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTotalActionPerformed
        // TODO add your handling code here:
        //
        try {
            // Koneksi Ke Database
            Connection c = koneksi.getKoneksi();
            // Kueri Data Ttotal bayat Dari Tabel Transaksi
            Statement s = c.createStatement();
            String sql = "SELECT total_bayar FROM tbl_transaksi where id_transaksi = '"+ tfIdTransaksi.getText() +"'";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                // Menberikan String Label Dari Total Bayar
                lbTotal.setText(r.getString("total_bayar"));
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }   
    }//GEN-LAST:event_btnTotalActionPerformed

    // Event Menghitung Pembayaran
    private void tfBayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBayarKeyReleased
        // TODO add your handling code here:
        // Menghitung Pembayaran
         bayar = Integer.parseInt(String.valueOf(tfBayar.getText()));
            total = Integer.parseInt(String.valueOf(lbTotal.getText()));
            kembali = bayar - total;
            tfKembalian.setText(Long.toString(kembali));
    }//GEN-LAST:event_tfBayarKeyReleased

    private void tfBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfBayarActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tfBayarActionPerformed

    private void tfBayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBayarKeyTyped
        // TODO add your handling code here:
       FilterAngka(evt);
    }//GEN-LAST:event_tfBayarKeyTyped

    private void tfHitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfHitungActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfHitungActionPerformed

    private void tfJumlahBarangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfJumlahBarangKeyTyped
        // TODO add your handling code here:
        FilterAngka(evt);
    }//GEN-LAST:event_tfJumlahBarangKeyTyped

    private void tfIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfIdActionPerformed

    // Menghapus Info Transaksi
    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        // Konfirmasi Sebelum Hapus
        int jawaban;
        if ((jawaban = JOptionPane.showConfirmDialog(null,"Yakin Hapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION)) == 0) {
        try{
        // Kueri Menghapus Data
        st = cn.createStatement();
        st.executeUpdate("DELETE FROM tbl_transaksi where id_transaksi = '"+ tfIdTransaksi.getText() +"'");
        noTransaksi();
        Clear();
        loadData();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        Clear();
    }//GEN-LAST:event_btnClearActionPerformed

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
            java.util.logging.Logger.getLogger(Form_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnHitung;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnSelesai;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnTotal;
    private javax.swing.JComboBox<String> cbNamaBarang;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbHargaSatuan;
    private javax.swing.JLabel lbIdFaktur;
    private javax.swing.JLabel lbJumlahBeli;
    private javax.swing.JLabel lbKodeBarang;
    private javax.swing.JLabel lbNamaBarang;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JTable tblTransaksi;
    private javax.swing.JTextField tfBayar;
    private javax.swing.JTextField tfHargaSatuan;
    private javax.swing.JTextField tfHitung;
    private javax.swing.JTextField tfId;
    private javax.swing.JTextField tfIdTransaksi;
    private javax.swing.JTextField tfJumlahBarang;
    private javax.swing.JTextField tfKembalian;
    private javax.swing.JTextField tfKodeBarang;
    private javax.swing.JTextField tfStok;
    // End of variables declaration//GEN-END:variables
}
