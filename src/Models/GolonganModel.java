/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author psw
 */
public class GolonganModel extends Config{
    private String id;
    private String jenis;
    private String jabatan;
    private String gaji_diterima;
    private String gaji_diserahkan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getGaji_diterima() {
        return gaji_diterima;
    }

    public void setGaji_diterima(String gaji_diterima) {
        this.gaji_diterima = gaji_diterima;
    }

    public String getGaji_diserahkan() {
        return gaji_diserahkan;
    }

    public void setGaji_diserahkan(String gaji_diserahkan) {
        this.gaji_diserahkan = gaji_diserahkan;
    }
    
    
    
}
