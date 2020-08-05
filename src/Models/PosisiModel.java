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
public class PosisiModel extends Config{
    private Integer id;
    private String divisi;
    private String jabatan;
    private String posisi;
    private String detail_tugas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public String getDetail_tugas() {
        return detail_tugas;
    }

    public void setDetail_tugas(String detail_tugas) {
        this.detail_tugas = detail_tugas;
    }
    
    
}
