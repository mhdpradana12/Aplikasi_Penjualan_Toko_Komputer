package AplikasiPenjualan;

public class E_Transaksi {
    public String idTransaksi, kodeBarang;
    public int harga,banyakBarang,subTotal;
    
    public void setIdTransaksi(String IdTransaksi){
        this.idTransaksi=idTransaksi;
    }
    
    public String getIDTransaksi(){
        return this.idTransaksi;
    }
    
    public void setKodeBarang(String kodeBarang){
        this.kodeBarang=kodeBarang;
    }
    
    public String getKodeBarang(){
        return this.kodeBarang;
    }
    
    public void setHarga(int harga){
        this.harga=harga;
    }
    
    public int getHarga(){
        return this.harga;
    }
    
    public void setBanyakBarang(int banyakBarang){
        this.banyakBarang=banyakBarang;
    }
    
    public int getBanyakBarang(){
        return this.banyakBarang;
    }
    
    public void setSubTotal(int subTotal){
        this.subTotal=subTotal;
    }
    
    public int getSubTotal(){
        return this.subTotal;
    }
}
