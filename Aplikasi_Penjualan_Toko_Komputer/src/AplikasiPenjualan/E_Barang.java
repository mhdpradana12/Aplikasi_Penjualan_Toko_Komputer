package AplikasiPenjualan;

public class E_Barang {
    public String kodeBarang, namaBarang, vendorBarang;
    public int stokBarang, hargaBarang;
    
    public void setKodeBarang(String kodeBarang){
        this.kodeBarang=kodeBarang;
    }
    
    public String getKodeBarang(){
        return this.kodeBarang;
    }
    
    public void setNamaBarang(String namaBarang){
        this.namaBarang=namaBarang;
    }
    
    public String getNamaBarang(){
        return this.namaBarang;
    }
    
    public void setVendorBarang(String vendorBarang){
        this.vendorBarang=vendorBarang;
    }
    
    public String getVendorBarang(){
        return this.vendorBarang;
    }
    
    public void setStokBarang(int stokBarang){
        this.stokBarang =stokBarang;
    }
    
    public int getStokBarang(){
        return this.stokBarang;
    }
    
    public void setHargaBarang(int hargaBarang){
        this.hargaBarang=hargaBarang;
    }
    
    public int getHargaBarang(){
        return this.hargaBarang;
    }
}
