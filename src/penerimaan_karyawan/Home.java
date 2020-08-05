package penerimaan_karyawan;

import static Models.Config.conn;
import Models.ArrayData;
import Models.ClientModel;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.HEIGHT;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author psw
 */
public class Home extends javax.swing.JFrame {

    private DefaultTableModel tablemodelpelamar;
    private DefaultTableModel tablemodelpenduduk;
    private DefaultTableModel tablemodelpengalaman;
    private DefaultTableModel tablemodelkriteria;
    private DefaultTableModel tablemodelMasterClient;
    private DefaultTableModel tablemodelMasterArea;
    private DefaultTableModel tablemodelMasterGolongan;
    private DefaultTableModel tablemodelMasterPosisi;
    private DefaultTableModel tablemodelKaryawan;
    private DefaultTableModel tablemodelGajiKaryawan;
    private DefaultTableModel TabModel;
    ImageIcon sucess = new ImageIcon(getClass().getResource("/Icon/checked.png"));
    ImageIcon invalid = new ImageIcon(getClass().getResource("/Icon/cancel.png"));
    ImageIcon warning = new ImageIcon(getClass().getResource("/Icon/warning.png"));
    public ArrayData arrayData = new ArrayData();
    PreparedStatement ps;
    ResultSet rs;
    String ex_id_client, ex_nama, ex_telp, ex_email, ex_alamat;
    String ex_id_posisi, ex_divisi, ex_jabatan, ex_posisi, ex_detail;
    String ex_id_area, ex_pt_area, ex_wilayah_area, ex_kota_area, ex_telp_area, ex_alamat_area;
    String ex_id_pelamar, ex_nama_pelamar, ex_jk_pelamar, ex_tgl_pelamar, ex_telp_pelamar, ex_alamat_pelamar;
    String ex_id_karyawan, ex_nama_karyawan, ex_dept_karyawan, ex_jabatan_karyawan, ex_posisi_karyawan, ex_pt_karyawan;
    String ex_id_gol, ex_jenis_gol, ex_gaji_gol;
    String statTolak = "Di Tolak";
    String statTerima = "Di Terima";
    String statGabung = "Bergabung";

    public Home() {
        initComponents();
        clearPelamar();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.setMaximizedBounds(env.getMaximumWindowBounds());
        this.setVisible(true);
        sub_menu_master.setVisible(false);
        provinsi();
        Kota();
        IdClient();
        IdGol();
        Disable();
        TglSekarang();
        hiden();
        RunTable();
//        DisableKaryawan();
    }

    public void itemTerpilihClient() {
        ShowClients sc = new ShowClients();
        sc.hmClient = this;
//        return_id_trans.setText(ex_id_client);
//        return_kd_barang.setText(ex_nama);
//        return_nama_barang.setText(ex_telp);
//        return_harga_barang.setText(ex_email);
//        return_qty.setText(ex_alamat);
    }

    public void itemTerpilihPosisi() {
        ShowClients sc = new ShowClients();
        sc.hmClient = this;
        posisi_karyawan.setText(ex_posisi);
        divisi_karyawan.setText(ex_divisi);
        jabatan_karyawan.setText(ex_jabatan);
    }

    public void itemTerpilihKaryawan() {
        ShowKaryawan sk = new ShowKaryawan();
        sk.hmkaryawan = this;
        gaji_id_karyawan.setText(ex_id_karyawan);
        gaji_nama.setText(ex_nama_karyawan);
        gaji_perusahaan.setText(ex_pt_karyawan);
        gaji_dept.setText(ex_dept_karyawan);
        gaji_posisi.setText(ex_posisi_karyawan);
        gaji_jabatan.setText(ex_jabatan_karyawan);
    }

    public void itemTerpilihGolongan() {
        ShowClients sc = new ShowClients();
        sc.hmClient = this;
        gaji_golongan.setText(ex_jenis_gol);
        gaji_gaji_diserahkan.setText(ex_gaji_gol);
        gaji_gaji_bersih.setText(ex_gaji_gol);
    }

    public void itemTerpilihArea() {
        ShowArea sa = new ShowArea();
        sa.hmArea = this;
        penempatan_karyawan.setText(ex_pt_area);
        area_karyawan.setText(ex_kota_area);
        alamat_penempatan.setText(ex_alamat_area);
    }

    public void itemTerpilihPelamar() {
        ShowPelamar sp = new ShowPelamar();
        sp.hmPelamar = this;
        nama_karyawan.setText(ex_nama_pelamar);
        kj_karyawan.setText(ex_jk_pelamar);
        tanggal_lahir.setText(ex_tgl_pelamar);
        no_telp_karyawan.setText(ex_telp_pelamar);
        alamat_karyawan.setText(ex_alamat_pelamar);
    }

    protected void hiden() {
        client_tanggal.setVisible(false);
        txt_status_client.setVisible(false);
        cb_status_client.setVisible(false);
        id_posisi.setVisible(false);
        id_area.setVisible(false);
        cb_area_status.setVisible(false);
        txt_area_status.setVisible(false);
        txt_jht.setVisible(false);
        txt_kesehatan.setVisible(false);
        txt_jp.setVisible(false);
    }

    protected void DisableBT() {
        area_ubah.setEnabled(false);
        client_ubah.setEnabled(false);
        posisi_hapus.setEnabled(false);
        posisi_ubah.setEnabled(false);
        gol_hapus.setEnabled(false);
        gol_ubah.setEnabled(false);
    }

    protected void RunTable() {
        TableAreaMaster();
        TableClientMaster();
        TableGolonganMaster();
        TablePosisiMaster();
        TableCalonPelamar();
        TableKriteria();
        TablePengalaman();
        TableKependudukan();
        TableKaryawan();
        TableGajiKaryawan();
        IdKaryawan();
        IdClient();
        IdGol();
        hiden();
        setCBAreaName();
    }

    protected void clearPelamar() {
        tfID.setText("");
        tfNama.setText("");
        tfEmail.setText("");
        tfTelp.setText("");
        tfInstitusi.setText("");
        tfJurusan.setText("");
        tfPendidikan.setText("");
    }

    protected void ClearClient() {
        client_nama.setText("");
        client_telp.setText("");
        client_email.setText("");
        client_alamat.setText("");
        IdClient();
    }

    protected void normalWarna() {
        bt_client.setBackground(new Color(255, 255, 255));
        bt_area.setBackground(new Color(255, 255, 255));
        bt_golongan.setBackground(new Color(255, 255, 255));
        bt_posisi.setBackground(new Color(255, 255, 255));
    }

    private void TglSekarang() {
        Date date = new Date();
        String myFormat = "EEEE, dd MMMM yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        client_tanggal.setText(sdf.format(date));
        tgl_penerimaan.setText(sdf.format(date));
    }

    protected void Disable() {
        client_id.setEnabled(false);
        golongan_id.setEnabled(false);
        client_tanggal.setEnabled(false);
        tgl_penerimaan.setEnabled(false);
        tfID.setEnabled(false);
    }

    protected void Clear() {
        client_nama.setText("");
        client_alamat.setText("");
        client_email.setText("");
        client_telp.setText("");
        IdClient();

        gol_gaji_diterima.setText("");
        gol_jenis.setText("");
        IdGol();

        area_cb_wilayah.setSelectedItem("Pilih");
        area_cb_nama.setSelectedItem("Pilih");
        area_telp.setText("");
        area_alamat.setText("");

        posisi_cb_divisi.setSelectedItem("Pilih");
        posisi_keterangan.setText("");
        posisi_jabatan.setText("");
        posisi_posisi.setText("");

        nama_karyawan.setText("");
        kj_karyawan.setText("");
        tanggal_lahir.setText("");
        no_telp_karyawan.setText("");
        alamat_karyawan.setText("");
        posisi_karyawan.setText("");
        divisi_karyawan.setText("");
        jabatan_karyawan.setText("");
        penempatan_karyawan.setText("");
        area_karyawan.setText("");
        alamat_penempatan.setText("");
        txt_cari_karyawan.setText("");

        gaji_id_karyawan.setText("");
        gaji_nama.setText("");
        gaji_dept.setText("");
        gaji_jabatan.setText("");
        gaji_posisi.setText("");
        gaji_perusahaan.setText("");
        gaji_golongan.setText("");
        gaji_gaji_bersih.setText("");
        gaji_gaji_diserahkan.setText("");
        ck_jht.setSelected(false);
        ck_kesehatan.setSelected(false);
        ck_ketenagakerjaan.setSelected(false);
    }

    public void filterHuruf(KeyEvent a) {
        if (Character.isDigit(a.getKeyChar())) {
            a.consume();
            JOptionPane.showMessageDialog(null, "Masukan Hanya Huruf", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void FilterAngka(KeyEvent b) {
        if (Character.isAlphabetic(b.getKeyChar())) {
            b.consume();
            JOptionPane.showMessageDialog(null, "Masukan Hanya Angka", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    //Fungsi  IdAuto
    protected void IdKaryawan() {
        try {
            String query = "select MAX(RIGHT(id,8)) AS NO  from karyawan order by id desc";
            ps = Models.Config.Conn().prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                if (rs.first() == false) {
                    id_karyawan.setText("IDK10000001");
                } else {
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int Nomor = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 8 - Nomor; j++) {
                        no = "0" + no;
                    }
                    id_karyawan.setText("IDK" + no);
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }

    protected void IdClient() {
        try {
            String query = "select MAX(RIGHT(id,8)) AS NO  from client order by id desc";
            ps = Models.Config.Conn().prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                if (rs.first() == false) {
                    client_id.setText("IDC10000001");
                } else {
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int Nomor = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 8 - Nomor; j++) {
                        no = "0" + no;
                    }
                    client_id.setText("IDC" + no);
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }

    protected void IdGol() {
        try {
            String query = "select MAX(RIGHT(id,8)) AS NO  from golongan order by id desc";
            ps = Models.Config.Conn().prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                if (rs.first() == false) {
                    golongan_id.setText("IDG10000001");
                } else {
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int Nomor = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 8 - Nomor; j++) {
                        no = "0" + no;
                    }
                    golongan_id.setText("IDG" + no);
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }

    //End Fungsi Id Auto
    private void provinsi() {
        DefaultComboBoxModel dm = new DefaultComboBoxModel(arrayData.provinsi);
        area_cb_wilayah.setModel(dm);
    }

    private void Kota() {
        String getProvinsi = area_cb_wilayah.getSelectedItem().toString();

        if (getProvinsi.equals("Pilih")) {
            DefaultComboBoxModel dm1 = new DefaultComboBoxModel(arrayData.Pilih);
            area_cb_kota.setModel(dm1);
        } else if (getProvinsi.equals("Aceh")) {
            DefaultComboBoxModel dm2 = new DefaultComboBoxModel(arrayData.Aceh);
            area_cb_kota.setModel(dm2);
        } else if (getProvinsi.equals("Bali")) {
            DefaultComboBoxModel dm3 = new DefaultComboBoxModel(arrayData.Bali);
            area_cb_kota.setModel(dm3);
        } else if (getProvinsi.equals("Banten")) {
            DefaultComboBoxModel dm4 = new DefaultComboBoxModel(arrayData.Banten);
            area_cb_kota.setModel(dm4);
        } else if (getProvinsi.equals("Bengkulu")) {
            DefaultComboBoxModel dm5 = new DefaultComboBoxModel(arrayData.Bengkulu);
            area_cb_kota.setModel(dm5);
        } else if (getProvinsi.equals("DI Yogyakarta")) {
            DefaultComboBoxModel dm6 = new DefaultComboBoxModel(arrayData.Yogyakarta);
            area_cb_kota.setModel(dm6);
        } else if (getProvinsi.equals("DKI Jakarta")) {
            DefaultComboBoxModel dm7 = new DefaultComboBoxModel(arrayData.DKI_Jakarta);
            area_cb_kota.setModel(dm7);
        } else if (getProvinsi.equals("Gorontalo")) {
            DefaultComboBoxModel dm8 = new DefaultComboBoxModel(arrayData.Gorontalo);
            area_cb_kota.setModel(dm8);
        } else if (getProvinsi.equals("Jambi")) {
            DefaultComboBoxModel dm9 = new DefaultComboBoxModel(arrayData.Jambi);
            area_cb_kota.setModel(dm9);
        } else if (getProvinsi.equals("Jawa Barat")) {
            DefaultComboBoxModel dm10 = new DefaultComboBoxModel(arrayData.Jawa_Barat);
            area_cb_kota.setModel(dm10);
        } else if (getProvinsi.equals("Jawa Tengah")) {
            DefaultComboBoxModel dm11 = new DefaultComboBoxModel(arrayData.Jawa_Tengah);
            area_cb_kota.setModel(dm11);
        } else if (getProvinsi.equals("Jawa Timur")) {
            DefaultComboBoxModel dm12 = new DefaultComboBoxModel(arrayData.Jawa_Timur);
            area_cb_kota.setModel(dm12);
        } else if (getProvinsi.equals("Kalimantan Barat")) {
            DefaultComboBoxModel dm13 = new DefaultComboBoxModel(arrayData.Kal_Bar);
            area_cb_kota.setModel(dm13);
        } else if (getProvinsi.equals("Kalimantan Selatan")) {
            DefaultComboBoxModel dm14 = new DefaultComboBoxModel(arrayData.Kal_Sel);
            area_cb_kota.setModel(dm14);
        } else if (getProvinsi.equals("Kalimantan Tengah")) {
            DefaultComboBoxModel dm15 = new DefaultComboBoxModel(arrayData.Kal_Teng);
            area_cb_kota.setModel(dm15);
        } else if (getProvinsi.equals("Kalimantan Timur")) {
            DefaultComboBoxModel dm16 = new DefaultComboBoxModel(arrayData.Kal_Tim);
            area_cb_kota.setModel(dm16);
        } else if (getProvinsi.equals("Kalimantan Utara")) {
            DefaultComboBoxModel dm17 = new DefaultComboBoxModel(arrayData.Kal_Utr);
            area_cb_kota.setModel(dm17);
        } else if (getProvinsi.equals("Kepulauan Bangka Belitung")) {
            DefaultComboBoxModel dm18 = new DefaultComboBoxModel(arrayData.Kep_Belitung);
            area_cb_kota.setModel(dm18);
        } else if (getProvinsi.equals("Kepulauan Riau")) {
            DefaultComboBoxModel dm19 = new DefaultComboBoxModel(arrayData.Kep_Riau);
            area_cb_kota.setModel(dm19);
        } else if (getProvinsi.equals("Lampung")) {
            DefaultComboBoxModel dm20 = new DefaultComboBoxModel(arrayData.Lampung);
            area_cb_kota.setModel(dm20);
        } else if (getProvinsi.equals("Maluku")) {
            DefaultComboBoxModel dm21 = new DefaultComboBoxModel(arrayData.Maluku);
            area_cb_kota.setModel(dm21);
        } else if (getProvinsi.equals("Maluku Utara")) {
            DefaultComboBoxModel dm22 = new DefaultComboBoxModel(arrayData.Maluku_Utr);
            area_cb_kota.setModel(dm22);
        } else if (getProvinsi.equals("Nusa Tenggara Barat")) {
            DefaultComboBoxModel dm23 = new DefaultComboBoxModel(arrayData.Ntb);
            area_cb_kota.setModel(dm23);
        } else if (getProvinsi.equals("Nusa Tenggara Timur")) {
            DefaultComboBoxModel dm24 = new DefaultComboBoxModel(arrayData.Ntt);
            area_cb_kota.setModel(dm24);
        } else if (getProvinsi.equals("Papua")) {
            DefaultComboBoxModel dm25 = new DefaultComboBoxModel(arrayData.Papua);
            area_cb_kota.setModel(dm25);
        } else if (getProvinsi.equals("Papua Barat")) {
            DefaultComboBoxModel dm26 = new DefaultComboBoxModel(arrayData.Papua_Bar);
            area_cb_kota.setModel(dm26);
        } else if (getProvinsi.equals("Riau")) {
            DefaultComboBoxModel dm27 = new DefaultComboBoxModel(arrayData.Riau);
            area_cb_kota.setModel(dm27);
        } else if (getProvinsi.equals("Sulawesi Selatan")) {
            DefaultComboBoxModel dm28 = new DefaultComboBoxModel(arrayData.Sul_Sel);
            area_cb_kota.setModel(dm28);
        } else if (getProvinsi.equals("Sulawesi Tengah")) {
            DefaultComboBoxModel dm29 = new DefaultComboBoxModel(arrayData.Sul_Teng);
            area_cb_kota.setModel(dm29);
        } else if (getProvinsi.equals("Sulawesi Tenggara")) {
            DefaultComboBoxModel dm30 = new DefaultComboBoxModel(arrayData.Sul_Tgr);
            area_cb_kota.setModel(dm30);
        } else if (getProvinsi.equals("Sulawesi Barat")) {
            DefaultComboBoxModel dm31 = new DefaultComboBoxModel(arrayData.Sul_Bar);
            area_cb_kota.setModel(dm31);
        } else if (getProvinsi.equals("Sulawesi Utara")) {
            DefaultComboBoxModel dm32 = new DefaultComboBoxModel(arrayData.Sul_Utr);
            area_cb_kota.setModel(dm32);
        } else if (getProvinsi.equals("Sumatera Barat")) {
            DefaultComboBoxModel dm33 = new DefaultComboBoxModel(arrayData.Sum_Bar);
            area_cb_kota.setModel(dm33);
        } else if (getProvinsi.equals("Sumatera Selatan")) {
            DefaultComboBoxModel dm34 = new DefaultComboBoxModel(arrayData.Sum_Sel);
            area_cb_kota.setModel(dm34);
        } else if (getProvinsi.equals("Sumatera Utara")) {
            DefaultComboBoxModel dm35 = new DefaultComboBoxModel(arrayData.Sum_Utr);
            area_cb_kota.setModel(dm35);
        }
    }

    protected void UpdateTerima() {
        if (tfID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data Belum Di Pilih..", "Perhatian!", HEIGHT, invalid);
        } else {
            try {
                String sql = "update kependudukan set status='" + statTerima + "' where id_pelamar='" + tfID.getText() + "'";
                String sql2 = "update kriteria set status='" + statTerima + "' where id_pelamar='" + tfID.getText() + "'";
                String sql3 = "update pengalaman set status='" + statTerima + "' where id_pelamar='" + tfID.getText() + "'";
                PreparedStatement stat = Models.Config.Conn().prepareStatement(sql);
                PreparedStatement stat2 = Models.Config.Conn().prepareStatement(sql2);
                PreparedStatement stat3 = Models.Config.Conn().prepareStatement(sql3);

                stat.executeUpdate();
                stat2.executeUpdate();
                stat3.executeUpdate();
//                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan..", "Berhasil!", HEIGHT, sucess);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Perhatian!", HEIGHT, invalid);
                clearPelamar();
            }
        }
    }

    protected void UpdateTolak() {
        if (tfID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data Belum Di Pilih..", "Perhatian!", HEIGHT, invalid);
        } else {
            try {
                String sql = "update kependudukan set status='" + statTolak + "' where id_pelamar='" + tfID.getText() + "'";
                String sql2 = "update kriteria set status='" + statTolak + "' where id_pelamar='" + tfID.getText() + "'";
                String sql3 = "update pengalaman set status='" + statTolak + "' where id_pelamar='" + tfID.getText() + "'";
                PreparedStatement stat = Models.Config.Conn().prepareStatement(sql);
                PreparedStatement stat2 = Models.Config.Conn().prepareStatement(sql2);
                PreparedStatement stat3 = Models.Config.Conn().prepareStatement(sql3);

                stat.executeUpdate();
                stat2.executeUpdate();
                stat3.executeUpdate();
//                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan..", "Berhasil!", HEIGHT, sucess);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Perhatian!", HEIGHT, invalid);
                clearPelamar();
            }
        }
    }

    protected void UpdateStatusKaryawan() {
        if (gaji_id_karyawan.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada...", "Perhatian!", HEIGHT, invalid);
        } else {
            try {
                String sql = "update karyawan set status='Probation' where id='" + gaji_id_karyawan.getText() + "'";
                PreparedStatement stat = Models.Config.Conn().prepareStatement(sql);

                stat.executeUpdate();
//                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan..", "Berhasil!", HEIGHT, sucess);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Perhatian!", HEIGHT, invalid);
                clearPelamar();
            }
        }
    }

    protected void UpdateGabung() {
        try {
            String sql = "update kependudukan set status='" + statGabung + "' where id_pelamar='" + ex_id_pelamar + "'";
            String sql2 = "update kriteria set status='" + statGabung + "' where id_pelamar='" + ex_id_pelamar + "'";
            String sql3 = "update pengalaman set status='" + statGabung + "' where id_pelamar='" + ex_id_pelamar + "'";
            String sql4 = "update pelamar set status_lamaran='" + statGabung + "' where id='" + ex_id_pelamar + "'";
            PreparedStatement stat = Models.Config.Conn().prepareStatement(sql);
            PreparedStatement stat2 = Models.Config.Conn().prepareStatement(sql2);
            PreparedStatement stat3 = Models.Config.Conn().prepareStatement(sql3);
            PreparedStatement stat4 = Models.Config.Conn().prepareStatement(sql4);

            stat.executeUpdate();
            stat2.executeUpdate();
            stat3.executeUpdate();
            stat4.executeUpdate();
//                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan..", "Berhasil!", HEIGHT, sucess);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Perhatian!", HEIGHT, invalid);
            clearPelamar();
        }

    }

    //Fungsi Insert
    protected void InsertClient() {
        String status = "Aktiv";
        if (client_nama.getText().isEmpty() || client_telp.getText().isEmpty() || client_email.getText().isEmpty()
                || client_alamat.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data Belum Terpenuhi!", "Peringatan!", HEIGHT, invalid);
        } else {
            String sql = "INSERT INTO `client`(`id`, `nama`, `telp`, `email`, `alamat`, `tanggal`, `status`) VALUES (?,?,?,?,?,?,?)";

            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, client_id.getText());
                stat.setString(2, client_nama.getText());
                stat.setString(3, client_telp.getText());
                stat.setString(4, client_email.getText());
                stat.setString(5, client_alamat.getText());
                stat.setString(6, client_tanggal.getText());
                stat.setString(7, status);

                stat.executeUpdate();
                client_nama.requestFocus();
                JOptionPane.showMessageDialog(null, "Berhasil!, Data Berhasil Disimpan", "Berhasil!", HEIGHT, sucess);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Gagal Menambah: " + e);
            }
        }
    }

    protected void InsertGolongan() {
        if (gol_gaji_diterima.getText().isEmpty()
                || gol_jenis.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data Belum Terpenuhi!", "Peringatan!", HEIGHT, invalid);
        } else {
            String sql = "INSERT INTO `golongan`(`id`, `jenis`, `gaji`) VALUES (?,?,?)";

            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, golongan_id.getText());
                stat.setString(2, gol_jenis.getText());
                stat.setString(3, gol_gaji_diterima.getText());

                stat.executeUpdate();
                gol_gaji_diterima.requestFocus();
                JOptionPane.showMessageDialog(null, "Berhasil!, Data Berhasil Disimpan", "Berhasil!", HEIGHT, sucess);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Gagal Menambah: " + e);
            }
        }
    }

    protected void InsertArea() {
        String areaStatus = "Active";
        if (area_cb_nama.getSelectedItem().equals("Pilih") || area_telp.getText().isEmpty()
                || area_alamat.getText().isEmpty() || area_cb_wilayah.getSelectedItem().equals("Pilih")) {
            JOptionPane.showMessageDialog(null, "Data Belum Terpenuhi!", "Peringatan!", HEIGHT, invalid);
        } else {
            String sql = "INSERT INTO `area`(`perusahaan`, `wilayah`, `kota`, `telp`, `alamat`, `status`) VALUES (?,?,?,?,?,?)";

            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, area_cb_nama.getSelectedItem().toString());
                stat.setString(2, area_cb_wilayah.getSelectedItem().toString());
                stat.setString(3, area_cb_kota.getSelectedItem().toString());
                stat.setString(4, area_telp.getText());
                stat.setString(5, area_alamat.getText());
                stat.setString(6, areaStatus);

                stat.executeUpdate();
                area_cb_nama.requestFocus();
                JOptionPane.showMessageDialog(null, "Berhasil!, Data Berhasil Disimpan", "Berhasil!", HEIGHT, sucess);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Gagal Menambah: " + e);
            }
        }
    }

    protected void InsertPosisi() {
        if (posisi_cb_divisi.getSelectedItem().equals("Pilih") || posisi_jabatan.getText().isEmpty()
                || posisi_posisi.getText().isEmpty() || posisi_keterangan.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data Belum Terpenuhi!", "Peringatan!", HEIGHT, invalid);
        } else {
            String sql = "INSERT INTO `posisi`(`divisi`, `jabatan`, `posisi`, `detail_tugas`) VALUES (?,?,?,?)";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, posisi_cb_divisi.getSelectedItem().toString());
                stat.setString(2, posisi_jabatan.getText());
                stat.setString(3, posisi_posisi.getText());
                stat.setString(4, posisi_keterangan.getText());

                stat.executeUpdate();
                posisi_cb_divisi.requestFocus();
                JOptionPane.showMessageDialog(null, "Berhasil!, Data Berhasil Disimpan", "Berhasil!", HEIGHT, sucess);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Gagal Pelamar: " + e);
            }
        }
    }

    protected void InsertKaryawan() {
        String Status = "Probation";
        String idGol = "";
        String Jenis = "";
        String Gaji = "";
        String StatusData = "";
        if (id_karyawan.getText().isEmpty() || nama_karyawan.getText().isEmpty()
                || posisi_karyawan.getText().isEmpty() || penempatan_karyawan.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data Belum Terpenuhi!", "Peringatan!", HEIGHT, invalid);
        } else {
            String sql = "INSERT INTO `karyawan`(`id`, `id_pelamar`, `nama`, `tgl_lahir`, `jenis_kelamin`, `telp`, `alamat`, `department`,`jabatan`,`posisi`,`id_area`, `nama_pt`, `tgl_gabung`, `status`,`golongan`,`gaji_kotor`, `gaji_bersih`,`status_data`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, id_karyawan.getText());
                stat.setString(2, ex_id_pelamar);
                stat.setString(3, nama_karyawan.getText());
                stat.setString(4, tanggal_lahir.getText());
                stat.setString(5, kj_karyawan.getText());
                stat.setString(6, no_telp_karyawan.getText());
                stat.setString(7, alamat_karyawan.getText());
                stat.setString(8, divisi_karyawan.getText());
                stat.setString(9, jabatan_karyawan.getText());
                stat.setString(10, posisi_karyawan.getText());
                stat.setString(11, ex_id_area);
                stat.setString(12, penempatan_karyawan.getText());
                stat.setString(13, client_tanggal.getText());
                stat.setString(14, Status);
                stat.setString(15, idGol);
                stat.setString(16, Jenis);
                stat.setString(17, Gaji);
                stat.setString(18, StatusData);

                stat.executeUpdate();
                posisi_cb_divisi.requestFocus();
                JOptionPane.showMessageDialog(null, "Berhasil!, Data Berhasil Disimpan", "Berhasil!", HEIGHT, sucess);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Gagal Pelamar: " + e);
            }
        }
    }

//    protected void InsertGajiKaryawan() {
//        String Status = "Probation";
//        if (gaji_id_karyawan.getText().isEmpty() || gaji_nama.getText().isEmpty()
//                || gaji_perusahaan.getText().isEmpty() || gaji_dept.getText().isEmpty() ||
//                gaji_jabatan.getText().isEmpty() || gaji_posisi.getText().isEmpty() || 
//                gaji_golongan.getText().isEmpty() ||  gaji_gaji_diserahkan.getText().isEmpty() ||
//                gaji_gaji_bersih.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Data Belum Terpenuhi!", "Peringatan!", HEIGHT, invalid);
//        } else {
//            String sql = "INSERT INTO `gaji_karyawan`(`id_karyawan`, `nama_karyawan`, `perusahaan`, `department`, `jabatan`, `posisi`, `golongan`, `gaji_diserahkan`, `gaji_bersih`,`jk`,`jht`,`jp`, `status`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
//
//            try {
//                PreparedStatement stat = conn.prepareStatement(sql);
//                stat.setString(1, gaji_id_karyawan.getText());
//                stat.setString(2, gaji_nama.getText());
//                stat.setString(3, gaji_perusahaan.getText());
//                stat.setString(4, gaji_dept.getText());
//                stat.setString(5, gaji_jabatan.getText());
//                stat.setString(6, gaji_posisi.getText());
//                stat.setString(7, gaji_golongan.getText());
//                stat.setString(8, gaji_gaji_diserahkan.getText());
//                stat.setString(9, gaji_gaji_bersih.getText());
//                stat.setString(10, txt_kesehatan.getText());
//                stat.setString(11, txt_jht.getText());
//                stat.setString(12, txt_jp.getText());
//                stat.setString(13, Status);
//               
//                stat.executeUpdate();
//                Clear();
//                JOptionPane.showMessageDialog(null, "Berhasil!, Data Berhasil Disimpan", "Berhasil!", HEIGHT, sucess);
//
//            } catch (SQLException e) {
//                JOptionPane.showMessageDialog(null, "Gagal Pelamar: " + e);
//            }
//        }
//    }
    //End  Fungsi Insert
    //Fungsi Table
    protected void TableCalonPelamar() {

        Object[] Baris = {"ID", "Nama", "Email", "Telp", "Tgl.Lahir", "Kelamin", "Pendidikan", "Institusi", "Jurusan"};
        tablemodelpelamar = new DefaultTableModel(null, Baris);

        try {
            String sql = "SELECT * FROM pelamar where status_lamaran='' order by id asc";
            Statement stat = Models.Config.Conn().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tablemodelpelamar.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6),
                    hasil.getString(7),
                    hasil.getString(8),
                    hasil.getString(9)
                });
            }
            tb_pelamar.setModel(tablemodelpelamar);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Pelamar: " + e);
        }
    }

    protected void TableKependudukan() {

        Object[] Baris = {"No KTP", "Warganegara", "Provinsi", "Kota", "Alamat KTP", "Tempat Tinggal", "Pernikahan"};
        tablemodelpenduduk = new DefaultTableModel(null, Baris);

        try {
            String sql = "SELECT `no_ktp`, `warganegara`, `provinsi`, `kota`, `alamat_ktp`, `status_rumah`, `status_kawin` FROM kependudukan where id_pelamar like '%" + tfID.getText() + "%' AND status=''";
            Statement stat = Models.Config.Conn().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tablemodelpenduduk.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6),
                    hasil.getString(7)
                });
            }
            tb_kependudukan.setModel(tablemodelpenduduk);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Kependudukan: " + e);
        }
    }

    protected void TablePengalaman() {

        Object[] Baris = {"ID", "Perusahaan", "Divisi", "Jabatan", "Tgl-Masuk", "Tgl Keluar"};
        tablemodelpengalaman = new DefaultTableModel(null, Baris);

        try {
            String sql = "SELECT * FROM pengalaman where id_pelamar like '%" + tfID.getText() + "%' AND status=''";
            Statement stat = Models.Config.Conn().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tablemodelpengalaman.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6)
                });
            }
            tb_pengalaman.setModel(tablemodelpengalaman);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Pengalaman: " + e);
        }
    }

    protected void TableKriteria() {
        Object[] Baris = {"ID", "Posisi", "Kriteria 1", "Kriteria 2", "Kriteria 3"};
        tablemodelkriteria = new DefaultTableModel(null, Baris);

        try {
            String sql = "SELECT * FROM kriteria where id_pelamar like '%" + tfID.getText() + "%' AND status=''";
            Statement stat = Models.Config.Conn().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tablemodelkriteria.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5)
                });
            }
            tb_kriteria.setModel(tablemodelkriteria);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Kriteria: " + e);
        }
    }

    protected void TableClientMaster() {

        Object[] Baris = {"ID", "Nama", "Telp", "Email", "Alamat", "Tanggal", "Status"};
        tablemodelMasterClient = new DefaultTableModel(null, Baris);

        try {
            String sql = "SELECT * FROM client order by id asc";
            Statement stat = Models.Config.Conn().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tablemodelMasterClient.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6),
                    hasil.getString(7)
                });
            }
            client_table.setModel(tablemodelMasterClient);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Pelamar: " + e);
        }
    }

    protected void TableGolonganMaster() {

        Object[] Baris = {"ID", "Jenis", "Gaji Karyawan"};
        tablemodelMasterGolongan = new DefaultTableModel(null, Baris);

        try {
            String sql = "SELECT * FROM golongan order by id asc";
            Statement stat = Models.Config.Conn().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tablemodelMasterGolongan.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3)
                });
            }
            gol_table.setModel(tablemodelMasterGolongan);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Golongan Master: " + e);
        }
    }

    protected void TableAreaMaster() {

        Object[] Baris = {"ID", "Perusahaan", "Wilayah", "Kota", "Telp", "Alamat", "Status"};
        tablemodelMasterArea = new DefaultTableModel(null, Baris);

        try {
            String sql = "SELECT * FROM area order by id asc";
            Statement stat = Models.Config.Conn().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tablemodelMasterArea.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6),
                    hasil.getString(7)
                });
            }
            area_table.setModel(tablemodelMasterArea);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Area Master: " + e);
        }
    }

    protected void TablePosisiMaster() {

        Object[] Baris = {"ID", "Divisi", "Jabatan", "Posisi", "Tugas"};
        tablemodelMasterPosisi = new DefaultTableModel(null, Baris);

        try {
            String sql = "SELECT * FROM posisi order by id asc";
            Statement stat = Models.Config.Conn().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tablemodelMasterPosisi.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5)
                });
            }
            posisi_tabel.setModel(tablemodelMasterPosisi);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Posisi Master: " + e);
        }
    }

    public void TableKaryawan() {

        Object[] Baris = {"ID", "Nama", "Telp", "Alamat", "Department", "Jabatan", "Posisi", "Perusahaan", "Tgl.Join", "Status"};
        tablemodelKaryawan = new DefaultTableModel(null, Baris);
        String cariitem=txt_cari_karyawan.getText();

        try {
            String sql = "SELECT `id`, `nama`, `telp`, `alamat`, `department`, `jabatan`, `posisi`, `nama_pt`, `tgl_gabung`, `status` FROM `karyawan` where id like '%"+cariitem+"%' or nama like '%"+cariitem+"%' order by status asc";
            Statement stat = Models.Config.Conn().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tablemodelKaryawan.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6),
                    hasil.getString(7),
                    hasil.getString(8),
                    hasil.getString(9),
                    hasil.getString(10)
                });
            }
            table_karyawan.setModel(tablemodelKaryawan);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Posisi Master: " + e);
        }
    }

    protected void TableGajiKaryawan() {
        Object[] Baris = {"ID", "Nama", "Perusahaan", "Department", "Jabatan", "Posisi", "Golongan", "Gaji Kotor", "Gaji Bersih"};
        tablemodelGajiKaryawan = new DefaultTableModel(null, Baris);
        try {
            String sql = "SELECT `id`, `nama`, `nama_pt`, `department`, `jabatan`, `posisi`, `golongan`,`gaji_kotor`, `gaji_bersih` FROM `karyawan` order by status asc";
            Statement stat = Models.Config.Conn().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tablemodelGajiKaryawan.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6),
                    hasil.getString(7),
                    hasil.getString(8),
                    hasil.getString(9)
                });
            }
            gaji_table_gaji.setModel(tablemodelGajiKaryawan);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Posisi Master: " + e);
        }
    }

    protected void TableGajiProbation() {
        Object[] Baris = {"ID", "Nama", "Perusahaan", "Department", "Jabatan", "Posisi", "Golongan", "Gaji Kotor", "Gaji Bersih"};
        tablemodelGajiKaryawan = new DefaultTableModel(null, Baris);
        try {
            String sql = "SELECT `id`, `nama`, `nama_pt`, `department`, `jabatan`, `posisi`, `golongan`,`gaji_kotor`, `gaji_bersih` FROM `karyawan`where status='Probation' order by id asc";
            Statement stat = Models.Config.Conn().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tablemodelGajiKaryawan.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6),
                    hasil.getString(7),
                    hasil.getString(8),
                    hasil.getString(9)
                });
            }
            gaji_table_gaji.setModel(tablemodelGajiKaryawan);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Posisi Master: " + e);
        }
    }

    protected void TableGajiContract() {
        Object[] Baris = {"ID", "Nama", "Perusahaan", "Department", "Jabatan", "Posisi", "Golongan", "Gaij Kotor", "Gaji Bersih"};
        tablemodelGajiKaryawan = new DefaultTableModel(null, Baris);
        try {
            String sql = "SELECT `id`, `nama`, `nama_pt`, `department`, `jabatan`, `posisi`, `golongan`,`gaji_kotor`, `gaji_bersih` FROM `karyawan` where status='Contract' order by id asc";
            Statement stat = Models.Config.Conn().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tablemodelGajiKaryawan.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6),
                    hasil.getString(7),
                    hasil.getString(8),
                    hasil.getString(9)
                });
            }
            gaji_table_gaji.setModel(tablemodelGajiKaryawan);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Posisi Master: " + e);
        }
    }

    protected void TableGajiPermanent() {
        Object[] Baris = {"ID", "Nama", "Perusahaan", "Department", "Jabatan", "Posisi", "Golongan", "Gaji Kotor", "Gaji Bersih"};
        tablemodelGajiKaryawan = new DefaultTableModel(null, Baris);
        try {
            String sql = "SELECT `id`, `nama`, `nama_pt`, `department`, `jabatan`, `posisi`, `golongan`,`gaji_kotor`, `gaji_bersih` FROM `karyawan` where status='Permanent' order by id asc";
            Statement stat = Models.Config.Conn().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tablemodelGajiKaryawan.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6),
                    hasil.getString(7),
                    hasil.getString(8),
                    hasil.getString(9)
                });
            }
            gaji_table_gaji.setModel(tablemodelGajiKaryawan);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Posisi Master: " + e);
        }
    }

    protected void TableGajiPerubahan() {
        Object[] Baris = {"ID", "Nama", "Perusahaan", "Department", "Jabatan", "Posisi", "Golongan", "Gaji Kotor", "Gaji Bersih"};
        tablemodelGajiKaryawan = new DefaultTableModel(null, Baris);
        try {
            String sql = "SELECT `id`, `nama`, `nama_pt`, `department`, `jabatan`, `posisi`, `golongan`,`gaji_kotor`, `gaji_bersih` FROM `karyawan` where status_data='Pembaharuan' order by id asc";
            Statement stat = Models.Config.Conn().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tablemodelGajiKaryawan.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6),
                    hasil.getString(7),
                    hasil.getString(8),
                    hasil.getString(9)
                });
            }
            gaji_table_gaji.setModel(tablemodelGajiKaryawan);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Posisi Master: " + e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        top = new javax.swing.JPanel();
        Header = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        pelamar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        karyawan = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        gaji = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        laporan = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        category = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        Pusat = new javax.swing.JPanel();
        awal_pusat = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel104 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        Panel_Pelamar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_pelamar = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_kependudukan = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_pengalaman = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_kriteria = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        bt_Tolak = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        bt_terima = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        tfID = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tfNama = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        tfTelp = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        tfPendidikan = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        tfInstitusi = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        tfJurusan = new javax.swing.JTextField();
        tgl_penerimaan = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        Panel_Karyawan = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        table_karyawan = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        id_karyawan = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        nama_karyawan = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        posisi_karyawan = new javax.swing.JTextField();
        bt_cari_posisi = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        kj_karyawan = new javax.swing.JTextField();
        divisi_karyawan = new javax.swing.JTextField();
        tanggal_lahir = new javax.swing.JTextField();
        jabatan_karyawan = new javax.swing.JTextField();
        penempatan_karyawan = new javax.swing.JTextField();
        no_telp_karyawan = new javax.swing.JTextField();
        bt_simpan_karyawan = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        bt_batal_karyawan = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        alamat_karyawan = new javax.swing.JTextArea();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        bt_cari_area = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        area_karyawan = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        alamat_penempatan = new javax.swing.JTextArea();
        jLabel75 = new javax.swing.JLabel();
        bt_cari_posisi1 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txt_cari_karyawan = new javax.swing.JTextField();
        bt_cari_table = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        bt_cari_table1 = new javax.swing.JPanel();
        jLabel94 = new javax.swing.JLabel();
        Panel_Gaji = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        gaji_table_gaji = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        gaji_id_karyawan = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        gaji_cari_karyawan = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        gaji_nama = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        gaji_dept = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        gaji_jabatan = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        gaji_posisi = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        gaji_golongan = new javax.swing.JTextField();
        cari_gol_karyawan = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        gaji_gaji_diserahkan = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        ck_ketenagakerjaan = new javax.swing.JCheckBox();
        ck_kesehatan = new javax.swing.JCheckBox();
        ck_jht = new javax.swing.JCheckBox();
        jLabel89 = new javax.swing.JLabel();
        gaji_perusahaan = new javax.swing.JTextField();
        gaji_gaji_bersih = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        gaji_bt_simpan = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        gaji_bt_batal = new javax.swing.JPanel();
        jLabel91 = new javax.swing.JLabel();
        txt_jp = new javax.swing.JLabel();
        txt_kesehatan = new javax.swing.JLabel();
        txt_jht = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        gaji_cb_filter = new javax.swing.JComboBox<>();
        jLabel44 = new javax.swing.JLabel();
        Panel_Master = new javax.swing.JPanel();
        sub_menu_master = new javax.swing.JPanel();
        bt_area = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        bt_posisi = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        bt_client = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        bt_golongan = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pusat_master = new javax.swing.JPanel();
        data_client = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        client_table = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        client_simpan = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        client_id = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        client_nama = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        client_telp = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        client_email = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        client_alamat = new javax.swing.JTextArea();
        client_ubah = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        txt_status_client = new javax.swing.JLabel();
        cb_status_client = new javax.swing.JComboBox<>();
        client_tanggal = new javax.swing.JLabel();
        data_golongan = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        gol_simpan = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        golongan_id = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        gol_gaji_diterima = new javax.swing.JTextField();
        gol_ubah = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        gol_hapus = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        gol_jenis = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        gol_table = new javax.swing.JTable();
        data_area = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        area_simpan = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        area_telp = new javax.swing.JTextField();
        area_ubah = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        area_cb_wilayah = new javax.swing.JComboBox<>();
        area_cb_kota = new javax.swing.JComboBox<>();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        area_cb_nama = new javax.swing.JComboBox<>();
        jScrollPane11 = new javax.swing.JScrollPane();
        area_alamat = new javax.swing.JTextArea();
        cb_area_status = new javax.swing.JComboBox<>();
        txt_area_status = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        area_table = new javax.swing.JTable();
        id_area = new javax.swing.JLabel();
        data_posisi = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        posisi_simpan = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        posisi_ubah = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        posisi_hapus = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        posisi_jabatan = new javax.swing.JTextField();
        posisi_posisi = new javax.swing.JTextField();
        posisi_cb_divisi = new javax.swing.JComboBox<>();
        jScrollPane9 = new javax.swing.JScrollPane();
        posisi_keterangan = new javax.swing.JTextArea();
        id_posisi = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        posisi_tabel = new javax.swing.JTable();
        Panel_Laporan = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cb_report_kar = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        cb_param_pelamar = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        cb_param_gaji = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel96 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        cb_param_master = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        top.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout topLayout = new javax.swing.GroupLayout(top);
        top.setLayout(topLayout);
        topLayout.setHorizontalGroup(
            topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        topLayout.setVerticalGroup(
            topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        Header.setBackground(new java.awt.Color(255, 255, 255));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penerimaan_karyawan/Img/logo-kecil.png"))); // NOI18N

        pelamar.setBackground(new java.awt.Color(0, 153, 153));
        pelamar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pelamar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SwitchPanel(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Data Pelamar");

        javax.swing.GroupLayout pelamarLayout = new javax.swing.GroupLayout(pelamar);
        pelamar.setLayout(pelamarLayout);
        pelamarLayout.setHorizontalGroup(
            pelamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
            .addGroup(pelamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pelamarLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pelamarLayout.setVerticalGroup(
            pelamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(pelamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pelamarLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        karyawan.setBackground(new java.awt.Color(0, 153, 153));
        karyawan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        karyawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SwitchPanel(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Data Karyawan");

        javax.swing.GroupLayout karyawanLayout = new javax.swing.GroupLayout(karyawan);
        karyawan.setLayout(karyawanLayout);
        karyawanLayout.setHorizontalGroup(
            karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
            .addGroup(karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(karyawanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        karyawanLayout.setVerticalGroup(
            karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
            .addGroup(karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(karyawanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        gaji.setBackground(new java.awt.Color(0, 153, 153));
        gaji.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gaji.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SwitchPanel(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Gaji Karyawan");

        javax.swing.GroupLayout gajiLayout = new javax.swing.GroupLayout(gaji);
        gaji.setLayout(gajiLayout);
        gajiLayout.setHorizontalGroup(
            gajiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
            .addGroup(gajiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gajiLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        gajiLayout.setVerticalGroup(
            gajiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(gajiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gajiLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        laporan.setBackground(new java.awt.Color(0, 153, 153));
        laporan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SwitchPanel(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Laporan");

        javax.swing.GroupLayout laporanLayout = new javax.swing.GroupLayout(laporan);
        laporan.setLayout(laporanLayout);
        laporanLayout.setHorizontalGroup(
            laporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
            .addGroup(laporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(laporanLayout.createSequentialGroup()
                    .addGap(0, 47, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addGap(0, 46, Short.MAX_VALUE)))
        );
        laporanLayout.setVerticalGroup(
            laporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(laporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(laporanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        category.setBackground(new java.awt.Color(0, 153, 153));
        category.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        category.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SwitchPanel(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Master Category");

        javax.swing.GroupLayout categoryLayout = new javax.swing.GroupLayout(category);
        category.setLayout(categoryLayout);
        categoryLayout.setHorizontalGroup(
            categoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
            .addGroup(categoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(categoryLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        categoryLayout.setVerticalGroup(
            categoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(categoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(categoryLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(logo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pelamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(gaji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(laporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeaderLayout.createSequentialGroup()
                .addGap(26, 34, Short.MAX_VALUE)
                .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(karyawan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pelamar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gaji, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(laporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(category, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
            .addGroup(HeaderLayout.createSequentialGroup()
                .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Pusat.setBackground(new java.awt.Color(255, 255, 255));
        Pusat.setLayout(new java.awt.CardLayout());

        awal_pusat.setBackground(new java.awt.Color(255, 255, 255));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jLabel100.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel100.setText("Selamat Datang Di");

        jLabel101.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel101.setText("Aplikasi Pengelolaan Data Karyawan");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel100)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel101)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel100)
                .addGap(18, 18, 18)
                .addComponent(jLabel101)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penerimaan_karyawan/Img/logo.png"))); // NOI18N
        jLabel31.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel102.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(204, 0, 0));
        jLabel102.setText("PT. Perdana Prima Bhakti Mandiri");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel102)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel102)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jLabel104.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel104.setText("Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12160");

        jLabel103.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel103.setText("Jl. Falatehan I No.20, RT.2/RW.1, Melawai, Kecamatan. Kby. Baru,");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(450, Short.MAX_VALUE)
                .addComponent(jLabel103)
                .addContainerGap(451, Short.MAX_VALUE))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel104)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel104)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout awal_pusatLayout = new javax.swing.GroupLayout(awal_pusat);
        awal_pusat.setLayout(awal_pusatLayout);
        awal_pusatLayout.setHorizontalGroup(
            awal_pusatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(awal_pusatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        awal_pusatLayout.setVerticalGroup(
            awal_pusatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, awal_pusatLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        Pusat.add(awal_pusat, "card7");

        Panel_Pelamar.setBackground(new java.awt.Color(255, 255, 255));

        tb_pelamar.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_pelamar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_pelamarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_pelamar);

        tb_kependudukan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tb_kependudukan);

        tb_pengalaman.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tb_pengalaman);

        tb_kriteria.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tb_kriteria);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Data Kependukan");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Pengalaman Terakhir");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Kriteria Posisi");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Data Pelamar");

        bt_Tolak.setBackground(new java.awt.Color(204, 0, 0));
        bt_Tolak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_TolakMouseClicked(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Tolak");

        javax.swing.GroupLayout bt_TolakLayout = new javax.swing.GroupLayout(bt_Tolak);
        bt_Tolak.setLayout(bt_TolakLayout);
        bt_TolakLayout.setHorizontalGroup(
            bt_TolakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
            .addGroup(bt_TolakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bt_TolakLayout.createSequentialGroup()
                    .addGap(0, 41, Short.MAX_VALUE)
                    .addComponent(jLabel20)
                    .addGap(0, 42, Short.MAX_VALUE)))
        );
        bt_TolakLayout.setVerticalGroup(
            bt_TolakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(bt_TolakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bt_TolakLayout.createSequentialGroup()
                    .addGap(0, 17, Short.MAX_VALUE)
                    .addComponent(jLabel20)
                    .addGap(0, 16, Short.MAX_VALUE)))
        );

        bt_terima.setBackground(new java.awt.Color(0, 153, 0));
        bt_terima.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_terimaMouseClicked(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Terima");

        javax.swing.GroupLayout bt_terimaLayout = new javax.swing.GroupLayout(bt_terima);
        bt_terima.setLayout(bt_terimaLayout);
        bt_terimaLayout.setHorizontalGroup(
            bt_terimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
            .addGroup(bt_terimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bt_terimaLayout.createSequentialGroup()
                    .addGap(0, 36, Short.MAX_VALUE)
                    .addComponent(jLabel24)
                    .addGap(0, 37, Short.MAX_VALUE)))
        );
        bt_terimaLayout.setVerticalGroup(
            bt_terimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(bt_terimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bt_terimaLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel24)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("ID Pelamar");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Nama Pelamar");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("Email Pelamar");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("Telepon");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("Pendidikan");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("Institusi");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setText("Jurusan");

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel41.setText("Tanggal");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tfNama)
            .addComponent(tfTelp)
            .addComponent(tfEmail)
            .addComponent(tfPendidikan)
            .addComponent(tfInstitusi)
            .addComponent(tfJurusan)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41)
                    .addComponent(tgl_penerimaan, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tgl_penerimaan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfTelp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfPendidikan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfInstitusi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Panel_PelamarLayout = new javax.swing.GroupLayout(Panel_Pelamar);
        Panel_Pelamar.setLayout(Panel_PelamarLayout);
        Panel_PelamarLayout.setHorizontalGroup(
            Panel_PelamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_PelamarLayout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(Panel_PelamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_PelamarLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addGroup(Panel_PelamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel15)
                            .addComponent(jLabel6)
                            .addGroup(Panel_PelamarLayout.createSequentialGroup()
                                .addGroup(Panel_PelamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addGap(18, 18, 18)
                                .addGroup(Panel_PelamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1)))
                    .addGroup(Panel_PelamarLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(bt_terima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(bt_Tolak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        Panel_PelamarLayout.setVerticalGroup(
            Panel_PelamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_PelamarLayout.createSequentialGroup()
                .addGroup(Panel_PelamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_PelamarLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Panel_PelamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bt_terima, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bt_Tolak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_PelamarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Panel_PelamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Panel_PelamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        Pusat.add(Panel_Pelamar, "card2");

        Panel_Karyawan.setBackground(new java.awt.Color(255, 255, 255));
        Panel_Karyawan.setPreferredSize(new java.awt.Dimension(1291, 567));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Data Karyawan");

        table_karyawan.setModel(new javax.swing.table.DefaultTableModel(
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
        table_karyawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_karyawanMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(table_karyawan);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        id_karyawan.setEnabled(false);
        id_karyawan.setPreferredSize(new java.awt.Dimension(6, 30));

        jLabel34.setText("ID Karyawan");

        jLabel40.setText("Nama");

        nama_karyawan.setEnabled(false);

        jLabel45.setText("Posisi Pekerjaan");

        posisi_karyawan.setEnabled(false);
        posisi_karyawan.setPreferredSize(new java.awt.Dimension(59, 30));

        bt_cari_posisi.setBackground(new java.awt.Color(0, 102, 255));
        bt_cari_posisi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_cari_posisiMouseClicked(evt);
            }
        });

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setText("Cari");

        javax.swing.GroupLayout bt_cari_posisiLayout = new javax.swing.GroupLayout(bt_cari_posisi);
        bt_cari_posisi.setLayout(bt_cari_posisiLayout);
        bt_cari_posisiLayout.setHorizontalGroup(
            bt_cari_posisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(bt_cari_posisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bt_cari_posisiLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel76)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        bt_cari_posisiLayout.setVerticalGroup(
            bt_cari_posisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(bt_cari_posisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bt_cari_posisiLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel76)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        penempatan_karyawan.setEnabled(false);

        bt_simpan_karyawan.setBackground(new java.awt.Color(0, 153, 0));
        bt_simpan_karyawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_simpan_karyawanMouseClicked(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Simpan");

        javax.swing.GroupLayout bt_simpan_karyawanLayout = new javax.swing.GroupLayout(bt_simpan_karyawan);
        bt_simpan_karyawan.setLayout(bt_simpan_karyawanLayout);
        bt_simpan_karyawanLayout.setHorizontalGroup(
            bt_simpan_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
            .addGroup(bt_simpan_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bt_simpan_karyawanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel48)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        bt_simpan_karyawanLayout.setVerticalGroup(
            bt_simpan_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(bt_simpan_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bt_simpan_karyawanLayout.createSequentialGroup()
                    .addGap(0, 15, Short.MAX_VALUE)
                    .addComponent(jLabel48)
                    .addGap(0, 16, Short.MAX_VALUE)))
        );

        bt_batal_karyawan.setBackground(new java.awt.Color(153, 0, 0));
        bt_batal_karyawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_batal_karyawanMouseClicked(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("Batal");

        javax.swing.GroupLayout bt_batal_karyawanLayout = new javax.swing.GroupLayout(bt_batal_karyawan);
        bt_batal_karyawan.setLayout(bt_batal_karyawanLayout);
        bt_batal_karyawanLayout.setHorizontalGroup(
            bt_batal_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
            .addGroup(bt_batal_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bt_batal_karyawanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel57)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        bt_batal_karyawanLayout.setVerticalGroup(
            bt_batal_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
            .addGroup(bt_batal_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bt_batal_karyawanLayout.createSequentialGroup()
                    .addGap(0, 15, Short.MAX_VALUE)
                    .addComponent(jLabel57)
                    .addGap(0, 16, Short.MAX_VALUE)))
        );

        jLabel67.setText("Jenis Kelamin");

        jLabel68.setText("Tanggal Lahir");

        jLabel69.setText("No.Telp");

        jLabel70.setText("Alamat Domisili");

        alamat_karyawan.setColumns(2);
        alamat_karyawan.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        alamat_karyawan.setRows(5);
        alamat_karyawan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel71.setText("Divisi");

        jLabel72.setText("Jabatan");

        jLabel73.setText("Penempatan  Kerja");

        bt_cari_area.setBackground(new java.awt.Color(0, 102, 255));
        bt_cari_area.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_cari_areaMouseClicked(evt);
            }
        });

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setText("Cari");

        javax.swing.GroupLayout bt_cari_areaLayout = new javax.swing.GroupLayout(bt_cari_area);
        bt_cari_area.setLayout(bt_cari_areaLayout);
        bt_cari_areaLayout.setHorizontalGroup(
            bt_cari_areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(bt_cari_areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bt_cari_areaLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel77)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        bt_cari_areaLayout.setVerticalGroup(
            bt_cari_areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(bt_cari_areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bt_cari_areaLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel77)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jLabel74.setText("Area");

        alamat_penempatan.setColumns(20);
        alamat_penempatan.setRows(5);
        jScrollPane15.setViewportView(alamat_penempatan);

        jLabel75.setText("Alamat Detail");

        bt_cari_posisi1.setBackground(new java.awt.Color(0, 102, 255));
        bt_cari_posisi1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_cari_posisi1MouseClicked(evt);
            }
        });

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setText("Cari");

        javax.swing.GroupLayout bt_cari_posisi1Layout = new javax.swing.GroupLayout(bt_cari_posisi1);
        bt_cari_posisi1.setLayout(bt_cari_posisi1Layout);
        bt_cari_posisi1Layout.setHorizontalGroup(
            bt_cari_posisi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(bt_cari_posisi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bt_cari_posisi1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel79)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        bt_cari_posisi1Layout.setVerticalGroup(
            bt_cari_posisi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(bt_cari_posisi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bt_cari_posisi1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel79)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tanggal_lahir)
                    .addComponent(kj_karyawan)
                    .addComponent(jLabel40)
                    .addComponent(no_telp_karyawan, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel70)
                    .addComponent(jLabel67)
                    .addComponent(jLabel68)
                    .addComponent(jLabel69)
                    .addComponent(jLabel34)
                    .addComponent(alamat_karyawan)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(bt_simpan_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_batal_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(id_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nama_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bt_cari_posisi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(posisi_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bt_cari_posisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel45)
                        .addComponent(jLabel73)
                        .addComponent(jLabel72)
                        .addComponent(jLabel71)
                        .addComponent(divisi_karyawan)
                        .addComponent(jabatan_karyawan)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(penempatan_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bt_cari_area, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(area_karyawan)
                        .addComponent(jLabel74)
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel75))
                .addGap(19, 19, 19))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel45))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(id_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(posisi_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(jLabel71)))
                    .addComponent(bt_cari_posisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nama_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(divisi_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_cari_posisi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(jLabel72))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kj_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jabatan_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel68)
                            .addComponent(jLabel73))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tanggal_lahir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(penempatan_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(bt_cari_area, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(jLabel74))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(area_karyawan)
                    .addComponent(no_telp_karyawan, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70)
                    .addComponent(jLabel75))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(alamat_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_batal_karyawan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_simpan_karyawan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel46.setText("Pendataan Karyawan Baru");

        txt_cari_karyawan.setPreferredSize(new java.awt.Dimension(59, 30));

        bt_cari_table.setBackground(new java.awt.Color(0, 102, 255));
        bt_cari_table.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_cari_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_cari_tableMouseClicked(evt);
            }
        });

        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setText("Cari");

        javax.swing.GroupLayout bt_cari_tableLayout = new javax.swing.GroupLayout(bt_cari_table);
        bt_cari_table.setLayout(bt_cari_tableLayout);
        bt_cari_tableLayout.setHorizontalGroup(
            bt_cari_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(bt_cari_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bt_cari_tableLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel78)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        bt_cari_tableLayout.setVerticalGroup(
            bt_cari_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(bt_cari_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bt_cari_tableLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel78)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        bt_cari_table1.setBackground(new java.awt.Color(255, 255, 255));
        bt_cari_table1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_cari_table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_cari_table1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bt_cari_table1MousePressed(evt);
            }
        });

        jLabel94.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(255, 255, 255));
        jLabel94.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/refresh.png"))); // NOI18N

        javax.swing.GroupLayout bt_cari_table1Layout = new javax.swing.GroupLayout(bt_cari_table1);
        bt_cari_table1.setLayout(bt_cari_table1Layout);
        bt_cari_table1Layout.setHorizontalGroup(
            bt_cari_table1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(bt_cari_table1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bt_cari_table1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel94)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        bt_cari_table1Layout.setVerticalGroup(
            bt_cari_table1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(bt_cari_table1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bt_cari_table1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel94)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout Panel_KaryawanLayout = new javax.swing.GroupLayout(Panel_Karyawan);
        Panel_Karyawan.setLayout(Panel_KaryawanLayout);
        Panel_KaryawanLayout.setHorizontalGroup(
            Panel_KaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_KaryawanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_KaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46))
                .addGap(10, 10, 10)
                .addGroup(Panel_KaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                    .addGroup(Panel_KaryawanLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_cari_table1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_cari_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_cari_table, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        Panel_KaryawanLayout.setVerticalGroup(
            Panel_KaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_KaryawanLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(Panel_KaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_KaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel46)
                        .addComponent(txt_cari_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bt_cari_table, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_cari_table1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Panel_KaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE))
                .addContainerGap())
        );

        Pusat.add(Panel_Karyawan, "card3");

        Panel_Gaji.setBackground(new java.awt.Color(255, 255, 255));
        Panel_Gaji.setPreferredSize(new java.awt.Dimension(1291, 567));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Data Gaji Karyawan");

        gaji_table_gaji.setModel(new javax.swing.table.DefaultTableModel(
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
        gaji_table_gaji.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gaji_table_gajiMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(gaji_table_gaji);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        gaji_id_karyawan.setEnabled(false);

        jLabel80.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel80.setText("ID Karyawan");

        gaji_cari_karyawan.setBackground(new java.awt.Color(0, 102, 255));
        gaji_cari_karyawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gaji_cari_karyawanMouseClicked(evt);
            }
        });

        jLabel92.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(255, 255, 255));
        jLabel92.setText("Cari");

        javax.swing.GroupLayout gaji_cari_karyawanLayout = new javax.swing.GroupLayout(gaji_cari_karyawan);
        gaji_cari_karyawan.setLayout(gaji_cari_karyawanLayout);
        gaji_cari_karyawanLayout.setHorizontalGroup(
            gaji_cari_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(gaji_cari_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gaji_cari_karyawanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel92)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        gaji_cari_karyawanLayout.setVerticalGroup(
            gaji_cari_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(gaji_cari_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gaji_cari_karyawanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel92)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jLabel81.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel81.setText("Nama");

        jLabel82.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel82.setText("Department");

        jLabel83.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel83.setText("Jabatan");

        gaji_jabatan.setColumns(1);

        jLabel84.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel84.setText("Posisi");

        jLabel85.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel85.setText("Golongan");

        gaji_golongan.setEnabled(false);

        cari_gol_karyawan.setBackground(new java.awt.Color(0, 102, 255));
        cari_gol_karyawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cari_gol_karyawanMouseClicked(evt);
            }
        });

        jLabel93.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(255, 255, 255));
        jLabel93.setText("Cari");

        javax.swing.GroupLayout cari_gol_karyawanLayout = new javax.swing.GroupLayout(cari_gol_karyawan);
        cari_gol_karyawan.setLayout(cari_gol_karyawanLayout);
        cari_gol_karyawanLayout.setHorizontalGroup(
            cari_gol_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(cari_gol_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(cari_gol_karyawanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel93)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        cari_gol_karyawanLayout.setVerticalGroup(
            cari_gol_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(cari_gol_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(cari_gol_karyawanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel93)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jLabel86.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel86.setText("Gaji Karyawan");

        jLabel88.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel88.setText("Tunjangan");

        ck_ketenagakerjaan.setText("Jaminan Pensiun");
        ck_ketenagakerjaan.setEnabled(false);
        ck_ketenagakerjaan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ck_ketenagakerjaanItemStateChanged(evt);
            }
        });

        ck_kesehatan.setText("Kesehatan");
        ck_kesehatan.setEnabled(false);
        ck_kesehatan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ck_kesehatanItemStateChanged(evt);
            }
        });

        ck_jht.setText("JHT");
        ck_jht.setEnabled(false);
        ck_jht.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ck_jhtItemStateChanged(evt);
            }
        });

        jLabel89.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel89.setText("Perusahaan");

        jLabel90.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel90.setText("Gaji Bersih");

        gaji_bt_simpan.setBackground(new java.awt.Color(0, 153, 0));
        gaji_bt_simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gaji_bt_simpanMouseClicked(evt);
            }
        });

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setText("Simpan");

        javax.swing.GroupLayout gaji_bt_simpanLayout = new javax.swing.GroupLayout(gaji_bt_simpan);
        gaji_bt_simpan.setLayout(gaji_bt_simpanLayout);
        gaji_bt_simpanLayout.setHorizontalGroup(
            gaji_bt_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
            .addGroup(gaji_bt_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gaji_bt_simpanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel87)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        gaji_bt_simpanLayout.setVerticalGroup(
            gaji_bt_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(gaji_bt_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gaji_bt_simpanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel87)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        gaji_bt_batal.setBackground(new java.awt.Color(153, 0, 0));
        gaji_bt_batal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gaji_bt_batalMouseClicked(evt);
            }
        });

        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(255, 255, 255));
        jLabel91.setText("Batal");

        javax.swing.GroupLayout gaji_bt_batalLayout = new javax.swing.GroupLayout(gaji_bt_batal);
        gaji_bt_batal.setLayout(gaji_bt_batalLayout);
        gaji_bt_batalLayout.setHorizontalGroup(
            gaji_bt_batalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
            .addGroup(gaji_bt_batalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gaji_bt_batalLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel91)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        gaji_bt_batalLayout.setVerticalGroup(
            gaji_bt_batalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(gaji_bt_batalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gaji_bt_batalLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel91)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        txt_jp.setText("NO");

        txt_kesehatan.setText("NO");

        txt_jht.setText("NO");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(gaji_perusahaan)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                    .addComponent(gaji_id_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(gaji_cari_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel80, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel81, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(gaji_nama, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel82, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(gaji_dept, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel83, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(gaji_jabatan, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel84, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(gaji_posisi, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel89))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel85)
                                .addComponent(gaji_gaji_diserahkan)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel86)
                                        .addComponent(jLabel88)
                                        .addComponent(jLabel90)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(ck_kesehatan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(ck_jht, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGap(18, 18, 18)
                                            .addComponent(ck_ketenagakerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(gaji_golongan, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cari_gol_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(gaji_gaji_bersih))
                            .addComponent(gaji_bt_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gaji_bt_batal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txt_jp)
                        .addGap(18, 18, 18)
                        .addComponent(txt_kesehatan)
                        .addGap(18, 18, 18)
                        .addComponent(txt_jht)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel80)
                            .addComponent(jLabel85))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(gaji_id_karyawan, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(gaji_cari_karyawan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gaji_golongan)))
                    .addComponent(cari_gol_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81)
                    .addComponent(jLabel86))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(gaji_gaji_diserahkan, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(gaji_nama))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel88)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ck_ketenagakerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ck_kesehatan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel89)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gaji_perusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(ck_jht, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel90))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel82)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gaji_dept, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(gaji_gaji_bersih, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(gaji_bt_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel83)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gaji_jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel84)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gaji_posisi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(gaji_bt_batal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_jp)
                    .addComponent(txt_kesehatan)
                    .addComponent(txt_jht)))
        );

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel65.setText("Pendataan Gaji Karyawan Baru");

        gaji_cb_filter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua", "Probation", "Contract", "Permanent", "Pembaharuan" }));
        gaji_cb_filter.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                gaji_cb_filterPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        gaji_cb_filter.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                gaji_cb_filterPropertyChange(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("OrderBY");

        javax.swing.GroupLayout Panel_GajiLayout = new javax.swing.GroupLayout(Panel_Gaji);
        Panel_Gaji.setLayout(Panel_GajiLayout);
        Panel_GajiLayout.setHorizontalGroup(
            Panel_GajiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_GajiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_GajiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel65)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel_GajiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_GajiLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(gaji_cb_filter, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE)))
        );
        Panel_GajiLayout.setVerticalGroup(
            Panel_GajiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_GajiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_GajiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel65)
                    .addComponent(jLabel44)
                    .addComponent(gaji_cb_filter, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addGap(4, 4, 4)
                .addGroup(Panel_GajiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane13))
                .addContainerGap())
        );

        Pusat.add(Panel_Gaji, "card4");

        Panel_Master.setBackground(new java.awt.Color(255, 255, 255));
        Panel_Master.setPreferredSize(new java.awt.Dimension(1291, 567));

        sub_menu_master.setBackground(new java.awt.Color(0, 153, 153));
        sub_menu_master.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        bt_area.setBackground(new java.awt.Color(255, 255, 255));
        bt_area.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sub_menu_pilih(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Area Kerja");

        javax.swing.GroupLayout bt_areaLayout = new javax.swing.GroupLayout(bt_area);
        bt_area.setLayout(bt_areaLayout);
        bt_areaLayout.setHorizontalGroup(
            bt_areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(bt_areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bt_areaLayout.createSequentialGroup()
                    .addGap(0, 64, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addGap(0, 65, Short.MAX_VALUE)))
        );
        bt_areaLayout.setVerticalGroup(
            bt_areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(bt_areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bt_areaLayout.createSequentialGroup()
                    .addGap(0, 17, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addGap(0, 18, Short.MAX_VALUE)))
        );

        bt_posisi.setBackground(new java.awt.Color(255, 255, 255));
        bt_posisi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sub_menu_pilih(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Posisi Kerja");

        javax.swing.GroupLayout bt_posisiLayout = new javax.swing.GroupLayout(bt_posisi);
        bt_posisi.setLayout(bt_posisiLayout);
        bt_posisiLayout.setHorizontalGroup(
            bt_posisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(bt_posisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bt_posisiLayout.createSequentialGroup()
                    .addGap(0, 62, Short.MAX_VALUE)
                    .addComponent(jLabel25)
                    .addGap(0, 61, Short.MAX_VALUE)))
        );
        bt_posisiLayout.setVerticalGroup(
            bt_posisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(bt_posisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bt_posisiLayout.createSequentialGroup()
                    .addGap(0, 17, Short.MAX_VALUE)
                    .addComponent(jLabel25)
                    .addGap(0, 18, Short.MAX_VALUE)))
        );

        bt_client.setBackground(new java.awt.Color(255, 255, 255));
        bt_client.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sub_menu_pilih(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Data Client");

        javax.swing.GroupLayout bt_clientLayout = new javax.swing.GroupLayout(bt_client);
        bt_client.setLayout(bt_clientLayout);
        bt_clientLayout.setHorizontalGroup(
            bt_clientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(bt_clientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bt_clientLayout.createSequentialGroup()
                    .addGap(0, 61, Short.MAX_VALUE)
                    .addComponent(jLabel26)
                    .addGap(0, 61, Short.MAX_VALUE)))
        );
        bt_clientLayout.setVerticalGroup(
            bt_clientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(bt_clientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bt_clientLayout.createSequentialGroup()
                    .addGap(0, 17, Short.MAX_VALUE)
                    .addComponent(jLabel26)
                    .addGap(0, 18, Short.MAX_VALUE)))
        );

        bt_golongan.setBackground(new java.awt.Color(255, 255, 255));
        bt_golongan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sub_menu_pilih(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Data Per-Golongan");

        javax.swing.GroupLayout bt_golonganLayout = new javax.swing.GroupLayout(bt_golongan);
        bt_golongan.setLayout(bt_golonganLayout);
        bt_golonganLayout.setHorizontalGroup(
            bt_golonganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bt_golonganLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel27)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        bt_golonganLayout.setVerticalGroup(
            bt_golonganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bt_golonganLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Sub Menu Master");

        javax.swing.GroupLayout sub_menu_masterLayout = new javax.swing.GroupLayout(sub_menu_master);
        sub_menu_master.setLayout(sub_menu_masterLayout);
        sub_menu_masterLayout.setHorizontalGroup(
            sub_menu_masterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sub_menu_masterLayout.createSequentialGroup()
                .addGroup(sub_menu_masterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sub_menu_masterLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(sub_menu_masterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bt_client, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bt_golongan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bt_area, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bt_posisi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(sub_menu_masterLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(sub_menu_masterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sub_menu_masterLayout.setVerticalGroup(
            sub_menu_masterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sub_menu_masterLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel28)
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_client, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_golongan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_area, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_posisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pusat_master.setBackground(new java.awt.Color(51, 51, 51));
        pusat_master.setLayout(new java.awt.CardLayout());

        data_client.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        client_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        client_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                client_tableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(client_table);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        client_simpan.setBackground(new java.awt.Color(0, 102, 0));
        client_simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                client_simpanMouseClicked(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Simpan");

        javax.swing.GroupLayout client_simpanLayout = new javax.swing.GroupLayout(client_simpan);
        client_simpan.setLayout(client_simpanLayout);
        client_simpanLayout.setHorizontalGroup(
            client_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(client_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(client_simpanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel29)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        client_simpanLayout.setVerticalGroup(
            client_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(client_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(client_simpanLayout.createSequentialGroup()
                    .addGap(0, 16, Short.MAX_VALUE)
                    .addComponent(jLabel29)
                    .addGap(0, 17, Short.MAX_VALUE)))
        );

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel33.setText("ID Client");

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel35.setText("Nama Perusahaan");

        client_nama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                client_namaKeyReleased(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel36.setText("No. Telp");

        client_telp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                client_telpKeyTyped(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel37.setText("Email");

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel38.setText("Alamat Kantor");

        client_alamat.setColumns(20);
        client_alamat.setRows(5);
        jScrollPane6.setViewportView(client_alamat);

        client_ubah.setBackground(new java.awt.Color(0, 102, 153));
        client_ubah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                client_ubahMouseClicked(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Ubah");

        javax.swing.GroupLayout client_ubahLayout = new javax.swing.GroupLayout(client_ubah);
        client_ubah.setLayout(client_ubahLayout);
        client_ubahLayout.setHorizontalGroup(
            client_ubahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(client_ubahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(client_ubahLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel39)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        client_ubahLayout.setVerticalGroup(
            client_ubahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(client_ubahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(client_ubahLayout.createSequentialGroup()
                    .addGap(0, 16, Short.MAX_VALUE)
                    .addComponent(jLabel39)
                    .addGap(0, 17, Short.MAX_VALUE)))
        );

        txt_status_client.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_status_client.setText("Status");

        cb_status_client.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Active", "Non-Active" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel38)
                    .addComponent(jLabel37)
                    .addComponent(jLabel36)
                    .addComponent(jLabel35)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(client_id, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_status_client)
                                .addGap(141, 141, 141))
                            .addComponent(cb_status_client, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(client_nama)
                    .addComponent(client_email)
                    .addComponent(client_telp)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(client_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(client_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_status_client)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(client_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_status_client, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(client_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(client_telp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(client_email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(client_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(client_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        client_tanggal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        client_tanggal.setText("Tanggal");

        javax.swing.GroupLayout data_clientLayout = new javax.swing.GroupLayout(data_client);
        data_client.setLayout(data_clientLayout);
        data_clientLayout.setHorizontalGroup(
            data_clientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(data_clientLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(data_clientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(data_clientLayout.createSequentialGroup()
                        .addComponent(client_tanggal)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(data_clientLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))))
        );
        data_clientLayout.setVerticalGroup(
            data_clientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, data_clientLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(client_tanggal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(data_clientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pusat_master.add(data_client, "card2");

        data_golongan.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        gol_simpan.setBackground(new java.awt.Color(0, 102, 0));
        gol_simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gol_simpanMouseClicked(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Simpan");

        javax.swing.GroupLayout gol_simpanLayout = new javax.swing.GroupLayout(gol_simpan);
        gol_simpan.setLayout(gol_simpanLayout);
        gol_simpanLayout.setHorizontalGroup(
            gol_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(gol_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gol_simpanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel42)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        gol_simpanLayout.setVerticalGroup(
            gol_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(gol_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gol_simpanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel42)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel43.setText("ID Golongan");

        golongan_id.setPreferredSize(new java.awt.Dimension(6, 35));

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel47.setText("Gaji Karyawan");

        gol_gaji_diterima.setPreferredSize(new java.awt.Dimension(6, 35));
        gol_gaji_diterima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                gol_gaji_diterimaKeyTyped(evt);
            }
        });

        gol_ubah.setBackground(new java.awt.Color(0, 102, 153));
        gol_ubah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gol_ubahMouseClicked(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Ubah");

        javax.swing.GroupLayout gol_ubahLayout = new javax.swing.GroupLayout(gol_ubah);
        gol_ubah.setLayout(gol_ubahLayout);
        gol_ubahLayout.setHorizontalGroup(
            gol_ubahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(gol_ubahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gol_ubahLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel49)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        gol_ubahLayout.setVerticalGroup(
            gol_ubahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(gol_ubahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gol_ubahLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel49)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        gol_hapus.setBackground(new java.awt.Color(153, 0, 0));
        gol_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gol_hapusMouseClicked(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("Hapus");

        javax.swing.GroupLayout gol_hapusLayout = new javax.swing.GroupLayout(gol_hapus);
        gol_hapus.setLayout(gol_hapusLayout);
        gol_hapusLayout.setHorizontalGroup(
            gol_hapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(gol_hapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gol_hapusLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel50)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        gol_hapusLayout.setVerticalGroup(
            gol_hapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(gol_hapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gol_hapusLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel50)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jLabel30.setText("Jenis Golongan");

        gol_jenis.setPreferredSize(new java.awt.Dimension(59, 35));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(gol_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(gol_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(gol_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addComponent(jLabel43)
                            .addComponent(jLabel30)
                            .addComponent(gol_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(golongan_id, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gol_gaji_diterima, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(28, 28, 28))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(golongan_id, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gol_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gol_gaji_diterima, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gol_ubah, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gol_simpan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gol_hapus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        gol_table.setModel(new javax.swing.table.DefaultTableModel(
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
        gol_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gol_tableMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(gol_table);

        javax.swing.GroupLayout data_golonganLayout = new javax.swing.GroupLayout(data_golongan);
        data_golongan.setLayout(data_golonganLayout);
        data_golonganLayout.setHorizontalGroup(
            data_golonganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, data_golonganLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        data_golonganLayout.setVerticalGroup(
            data_golonganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(data_golonganLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(data_golonganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane8)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(113, Short.MAX_VALUE))
        );

        pusat_master.add(data_golongan, "card3");

        data_area.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        area_simpan.setBackground(new java.awt.Color(0, 102, 0));
        area_simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                area_simpanMouseClicked(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("Simpan");

        javax.swing.GroupLayout area_simpanLayout = new javax.swing.GroupLayout(area_simpan);
        area_simpan.setLayout(area_simpanLayout);
        area_simpanLayout.setHorizontalGroup(
            area_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(area_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(area_simpanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel51)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        area_simpanLayout.setVerticalGroup(
            area_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(area_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(area_simpanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel51)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel53.setText("Wilayah");

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel54.setText("Kota");

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel55.setText("No-Telp");

        area_telp.setPreferredSize(new java.awt.Dimension(6, 35));

        area_ubah.setBackground(new java.awt.Color(0, 102, 153));
        area_ubah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                area_ubahMouseClicked(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("Ubah");

        javax.swing.GroupLayout area_ubahLayout = new javax.swing.GroupLayout(area_ubah);
        area_ubah.setLayout(area_ubahLayout);
        area_ubahLayout.setHorizontalGroup(
            area_ubahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(area_ubahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(area_ubahLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel56)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        area_ubahLayout.setVerticalGroup(
            area_ubahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(area_ubahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(area_ubahLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel56)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        area_cb_wilayah.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        area_cb_wilayah.setPreferredSize(new java.awt.Dimension(56, 35));
        area_cb_wilayah.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                area_cb_wilayahItemStateChanged(evt);
            }
        });

        area_cb_kota.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        area_cb_kota.setPreferredSize(new java.awt.Dimension(56, 35));

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel58.setText("Alamat");

        jLabel59.setText("Nama Perusahaan");

        area_cb_nama.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        area_cb_nama.setPreferredSize(new java.awt.Dimension(56, 35));

        area_alamat.setColumns(20);
        area_alamat.setRows(5);
        jScrollPane11.setViewportView(area_alamat);

        cb_area_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Active", "Non-Active" }));
        cb_area_status.setPreferredSize(new java.awt.Dimension(78, 30));

        txt_area_status.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_area_status.setText("Status");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel59)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(area_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(area_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                        .addComponent(area_cb_wilayah, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(area_cb_kota, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(area_cb_nama, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel58, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel53, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(area_telp, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel55))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_area_status)
                                .addComponent(cb_area_status, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel59)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(area_cb_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(area_cb_wilayah, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel54)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(area_cb_kota, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(txt_area_status))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cb_area_status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(area_telp, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel58)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(area_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(area_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        area_table.setModel(new javax.swing.table.DefaultTableModel(
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
        area_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                area_tableMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(area_table);

        javax.swing.GroupLayout data_areaLayout = new javax.swing.GroupLayout(data_area);
        data_area.setLayout(data_areaLayout);
        data_areaLayout.setHorizontalGroup(
            data_areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(data_areaLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(data_areaLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(id_area)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        data_areaLayout.setVerticalGroup(
            data_areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, data_areaLayout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(data_areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(id_area)
                .addGap(14, 14, 14))
        );

        pusat_master.add(data_area, "card4");

        data_posisi.setBackground(new java.awt.Color(255, 255, 255));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        posisi_simpan.setBackground(new java.awt.Color(0, 102, 0));
        posisi_simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                posisi_simpanMouseClicked(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("Simpan");

        javax.swing.GroupLayout posisi_simpanLayout = new javax.swing.GroupLayout(posisi_simpan);
        posisi_simpan.setLayout(posisi_simpanLayout);
        posisi_simpanLayout.setHorizontalGroup(
            posisi_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(posisi_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(posisi_simpanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel52)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        posisi_simpanLayout.setVerticalGroup(
            posisi_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(posisi_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(posisi_simpanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel52)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel60.setText("Jabatan");

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel61.setText("Posisi");

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel62.setText("Keterangan Tugas");

        posisi_ubah.setBackground(new java.awt.Color(0, 102, 153));
        posisi_ubah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                posisi_ubahMouseClicked(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("Ubah");

        javax.swing.GroupLayout posisi_ubahLayout = new javax.swing.GroupLayout(posisi_ubah);
        posisi_ubah.setLayout(posisi_ubahLayout);
        posisi_ubahLayout.setHorizontalGroup(
            posisi_ubahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(posisi_ubahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(posisi_ubahLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel63)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        posisi_ubahLayout.setVerticalGroup(
            posisi_ubahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(posisi_ubahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(posisi_ubahLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel63)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        posisi_hapus.setBackground(new java.awt.Color(153, 0, 0));
        posisi_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                posisi_hapusMouseClicked(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 255, 255));
        jLabel64.setText("Hapus");

        javax.swing.GroupLayout posisi_hapusLayout = new javax.swing.GroupLayout(posisi_hapus);
        posisi_hapus.setLayout(posisi_hapusLayout);
        posisi_hapusLayout.setHorizontalGroup(
            posisi_hapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(posisi_hapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(posisi_hapusLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel64)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        posisi_hapusLayout.setVerticalGroup(
            posisi_hapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(posisi_hapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(posisi_hapusLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel64)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jLabel66.setText("Divisi");

        posisi_jabatan.setPreferredSize(new java.awt.Dimension(6, 35));

        posisi_posisi.setPreferredSize(new java.awt.Dimension(6, 35));

        posisi_cb_divisi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Recruitment", "Management", "IT Department", "Area Operasional" }));
        posisi_cb_divisi.setPreferredSize(new java.awt.Dimension(108, 35));

        posisi_keterangan.setColumns(20);
        posisi_keterangan.setRows(5);
        jScrollPane9.setViewportView(posisi_keterangan);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(id_posisi)
                    .addComponent(jLabel66)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(posisi_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(posisi_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(posisi_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                        .addComponent(posisi_cb_divisi, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(posisi_posisi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(posisi_jabatan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel62, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel61, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel60, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel66)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(posisi_cb_divisi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(posisi_jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel61)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(posisi_posisi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(posisi_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(posisi_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(posisi_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(id_posisi)
                .addContainerGap())
        );

        posisi_tabel.setModel(new javax.swing.table.DefaultTableModel(
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
        posisi_tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                posisi_tabelMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(posisi_tabel);

        javax.swing.GroupLayout data_posisiLayout = new javax.swing.GroupLayout(data_posisi);
        data_posisi.setLayout(data_posisiLayout);
        data_posisiLayout.setHorizontalGroup(
            data_posisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(data_posisiLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        data_posisiLayout.setVerticalGroup(
            data_posisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(data_posisiLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(data_posisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane10))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pusat_master.add(data_posisi, "card5");

        javax.swing.GroupLayout Panel_MasterLayout = new javax.swing.GroupLayout(Panel_Master);
        Panel_Master.setLayout(Panel_MasterLayout);
        Panel_MasterLayout.setHorizontalGroup(
            Panel_MasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_MasterLayout.createSequentialGroup()
                .addComponent(sub_menu_master, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pusat_master, javax.swing.GroupLayout.DEFAULT_SIZE, 1112, Short.MAX_VALUE))
        );
        Panel_MasterLayout.setVerticalGroup(
            Panel_MasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sub_menu_master, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pusat_master, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Pusat.add(Panel_Master, "card5");

        Panel_Laporan.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Parameter");

        cb_report_kar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Biodata", "Pekerjaan" }));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Laporan Data Karyawan");

        jButton1.setBackground(new java.awt.Color(0, 153, 204));
        jButton1.setText("Cetak");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(153, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10)
                    .addComponent(cb_report_kar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(155, 155, 155))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(67, 67, 67)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_report_kar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel95.setText("Laporan  Data  Pelamar");

        jLabel99.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel99.setText("Parameter");

        cb_param_pelamar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Lolos", "Tidak Lolos" }));

        jButton4.setBackground(new java.awt.Color(0, 153, 204));
        jButton4.setText("Cetak");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel95)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel99)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(cb_param_pelamar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel95)
                .addGap(58, 58, 58)
                .addComponent(jLabel99)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(cb_param_pelamar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("Laporan Penggajian");

        jLabel97.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel97.setText("Parameter");

        cb_param_gaji.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Gaji Karyawan" }));

        jButton2.setBackground(new java.awt.Color(0, 153, 204));
        jButton2.setText("Cetak");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(jLabel12))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel97)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(cb_param_gaji, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2)))))
                .addContainerGap(154, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(69, 69, 69)
                .addComponent(jLabel97)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(cb_param_gaji))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel96.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel96.setText("Laporan Master Data");

        jLabel98.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel98.setText("Parameter");

        cb_param_master.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Data Client", "Data Posisi", "Data Area" }));

        jButton3.setBackground(new java.awt.Color(0, 153, 204));
        jButton3.setText("Cetak");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel96)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel98)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(cb_param_master, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel96)
                .addGap(74, 74, 74)
                .addComponent(jLabel98)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(cb_param_master))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout Panel_LaporanLayout = new javax.swing.GroupLayout(Panel_Laporan);
        Panel_Laporan.setLayout(Panel_LaporanLayout);
        Panel_LaporanLayout.setHorizontalGroup(
            Panel_LaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_LaporanLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );
        Panel_LaporanLayout.setVerticalGroup(
            Panel_LaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_LaporanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Pusat.add(Panel_Laporan, "card6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Pusat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(top, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(top, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Pusat, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SwitchPanel(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SwitchPanel
        if (evt.getSource() == pelamar) {
            Panel_Pelamar.setVisible(true);
            Panel_Karyawan.setVisible(false);
            Panel_Gaji.setVisible(false);
            Panel_Master.setVisible(false);
            Panel_Laporan.setVisible(false);
            pelamar.setBackground(new Color(204, 0, 0));//red
            karyawan.setBackground(new Color(0, 153, 153));//dasar
            gaji.setBackground(new Color(0, 153, 153));//dasar
            category.setBackground(new Color(0, 153, 153));//dasar
            laporan.setBackground(new Color(0, 153, 153));//dasar
            normalWarna();
            awal_pusat.setVisible(false);
            setTitle("Halaman Pelamar");
            hiden();
            Clear();
            clearPelamar();
            DisableBT();
            RunTable();
            gaji_cari_karyawan.setVisible(true);

        }
        if (evt.getSource() == karyawan) {
            Panel_Pelamar.setVisible(false);
            Panel_Karyawan.setVisible(true);
            Panel_Gaji.setVisible(false);
            Panel_Master.setVisible(false);
            Panel_Laporan.setVisible(false);
            pelamar.setBackground(new Color(0, 153, 153));//dasar
            karyawan.setBackground(new Color(204, 0, 0));//red
            gaji.setBackground(new Color(0, 153, 153));//dasar
            category.setBackground(new Color(0, 153, 153));//dasar
            laporan.setBackground(new Color(0, 153, 153));//dasar
            normalWarna();
            awal_pusat.setVisible(false);
            setTitle("Halaman Karyawan");
            hiden();
            gaji_cari_karyawan.setVisible(true);
            Clear();
            clearPelamar();
            DisableBT();
            RunTable();
        }
        if (evt.getSource() == gaji) {
            Panel_Pelamar.setVisible(false);
            Panel_Karyawan.setVisible(false);
            Panel_Gaji.setVisible(true);
            Panel_Master.setVisible(false);
            Panel_Laporan.setVisible(false);
            pelamar.setBackground(new Color(0, 153, 153));//dasar
            karyawan.setBackground(new Color(0, 153, 153));//red
            gaji.setBackground(new Color(204, 0, 0));//dasar
            category.setBackground(new Color(0, 153, 153));//dasar
            laporan.setBackground(new Color(0, 153, 153));//dasar
            normalWarna();
            awal_pusat.setVisible(false);
            setTitle("Halaman Penggajian");
            hiden();
            Clear();
            clearPelamar();
            gaji_cari_karyawan.setVisible(true);
            RunTable();
            DisableBT();
        }
        if (evt.getSource() == category) {
            Panel_Pelamar.setVisible(false);
            Panel_Karyawan.setVisible(false);
            Panel_Gaji.setVisible(false);
            Panel_Master.setVisible(true);
            Panel_Laporan.setVisible(false);
            pelamar.setBackground(new Color(0, 153, 153));//dasar
            karyawan.setBackground(new Color(0, 153, 153));//red
            gaji.setBackground(new Color(0, 153, 153));//dasar
            category.setBackground(new Color(204, 0, 0));//dasar
            laporan.setBackground(new Color(0, 153, 153));//dasar
            sub_menu_master.setVisible(true);
            data_area.setVisible(false);
            data_client.setVisible(false);
            data_posisi.setVisible(false);
            data_golongan.setVisible(false);
            bt_client.setBackground(new Color(255, 255, 255));//red
            bt_area.setBackground(new Color(255, 255, 255));//dasar
            bt_golongan.setBackground(new Color(255, 255, 255));//dasar
            bt_posisi.setBackground(new Color(255, 255, 255));//dasar
            awal_pusat.setVisible(false);
            setTitle("Halaman Master");
            gaji_cari_karyawan.setVisible(true);
            hiden();
            Clear();
            clearPelamar();
            DisableBT();
            RunTable();
        }
        if (evt.getSource() == laporan) {
            Panel_Pelamar.setVisible(false);
            Panel_Karyawan.setVisible(false);
            Panel_Gaji.setVisible(false);
            Panel_Master.setVisible(false);
            Panel_Laporan.setVisible(true);
            pelamar.setBackground(new Color(0, 153, 153));//dasar
            karyawan.setBackground(new Color(0, 153, 153));//red
            gaji.setBackground(new Color(0, 153, 153));//dasar
            category.setBackground(new Color(0, 153, 153));//dasar
            laporan.setBackground(new Color(204, 0, 0));//dasar
            normalWarna();
            awal_pusat.setVisible(false);
            setTitle("Halaman Laporan");
            hiden();
            Clear();
            gaji_cari_karyawan.setVisible(true);
            clearPelamar();
            DisableBT();
            RunTable();
        }

    }//GEN-LAST:event_SwitchPanel

    private void tb_pelamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_pelamarMouseClicked
        int bar = tb_pelamar.getSelectedRow();
        String a = tablemodelpelamar.getValueAt(bar, 0).toString();
        String b = tablemodelpelamar.getValueAt(bar, 1).toString();
        String c = tablemodelpelamar.getValueAt(bar, 2).toString();
        String d = tablemodelpelamar.getValueAt(bar, 3).toString();
        String e = tablemodelpelamar.getValueAt(bar, 5).toString();
        String f = tablemodelpelamar.getValueAt(bar, 6).toString();
        String g = tablemodelpelamar.getValueAt(bar, 7).toString();

        tfID.setText(a);
        tfNama.setText(b);
        tfEmail.setText(c);
        tfTelp.setText(d);
        tfPendidikan.setText(e);
        tfInstitusi.setText(f);
        tfJurusan.setText(g);

        TableKriteria();
        TableKependudukan();
        TablePengalaman();
    }//GEN-LAST:event_tb_pelamarMouseClicked

    private void bt_terimaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_terimaMouseClicked
        String lolos = "Di Terima";
        if (tfID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data Belum Di Pilih..", "Perhatian!", HEIGHT, invalid);
        } else {
            try {
                String sql = "update pelamar set status_lamaran='" + lolos + "' where id='" + tfID.getText() + "'";
                PreparedStatement stat = Models.Config.Conn().prepareStatement(sql);

                stat.executeUpdate();
                UpdateTerima();
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan..", "Berhasil!", HEIGHT, sucess);
                clearPelamar();
                TableCalonPelamar();
                TableKependudukan();
                TableKriteria();
                TablePengalaman();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Perhatian!", HEIGHT, invalid);
                clearPelamar();
            }
        }
    }//GEN-LAST:event_bt_terimaMouseClicked

    private void bt_TolakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_TolakMouseClicked
        String lolos = "Di Tolak";
        if (tfID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data Belum Di Pilih..", "Perhatian!", HEIGHT, invalid);
        } else {
            try {
                String sql = "update pelamar set status_lamaran='" + lolos + "' where id='" + tfID.getText() + "'";
                PreparedStatement stat = Models.Config.Conn().prepareStatement(sql);

                stat.executeUpdate();
                UpdateTolak();
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan..", "Berhasil!", HEIGHT, sucess);
                clearPelamar();
                TableCalonPelamar();
                TableKependudukan();
                TableKriteria();
                TablePengalaman();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Perhatian!", HEIGHT, invalid);
                clearPelamar();
            }
        }
    }//GEN-LAST:event_bt_TolakMouseClicked

    private void sub_menu_pilih(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sub_menu_pilih
        if (evt.getSource() == bt_client) {
            data_client.setVisible(true);
            data_area.setVisible(false);
            data_golongan.setVisible(false);
            data_posisi.setVisible(false);
            bt_client.setBackground(new Color(204, 0, 0));//red
            bt_area.setBackground(new Color(255, 255, 255));//dasar
            bt_golongan.setBackground(new Color(255, 255, 255));//dasar
            bt_posisi.setBackground(new Color(255, 255, 255));//dasar
            hiden();
            Clear();
            DisableBT();
        }
        if (evt.getSource() == bt_area) {
            data_client.setVisible(false);
            data_area.setVisible(true);
            data_golongan.setVisible(false);
            data_posisi.setVisible(false);
            bt_area.setBackground(new Color(204, 0, 0));//red
            bt_client.setBackground(new Color(255, 255, 255));//dasar
            bt_golongan.setBackground(new Color(255, 255, 255));//dasar
            bt_posisi.setBackground(new Color(255, 255, 255));//dasar
            hiden();
            Clear();
            DisableBT();
        }
        if (evt.getSource() == bt_golongan) {
            data_client.setVisible(false);
            data_area.setVisible(false);
            data_golongan.setVisible(true);
            data_posisi.setVisible(false);
            bt_golongan.setBackground(new Color(204, 0, 0));//red
            bt_area.setBackground(new Color(255, 255, 255));//dasar
            bt_client.setBackground(new Color(255, 255, 255));//dasar
            bt_posisi.setBackground(new Color(255, 255, 255));//dasar
            hiden();
            Clear();
            DisableBT();
        }
        if (evt.getSource() == bt_posisi) {
            data_posisi.setVisible(true);
            data_area.setVisible(false);
            data_golongan.setVisible(false);
            data_client.setVisible(false);
            bt_posisi.setBackground(new Color(204, 0, 0));//red
            bt_area.setBackground(new Color(255, 255, 255));//dasar
            bt_golongan.setBackground(new Color(255, 255, 255));//dasar
            bt_client.setBackground(new Color(255, 255, 255));//dasar
            hiden();
            Clear();
            DisableBT();
        }
    }//GEN-LAST:event_sub_menu_pilih

    private void posisi_simpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_posisi_simpanMouseClicked
        InsertPosisi();
        Clear();
        RunTable();
        DisableBT();
    }//GEN-LAST:event_posisi_simpanMouseClicked

    private void area_cb_wilayahItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_area_cb_wilayahItemStateChanged
        Kota();
    }//GEN-LAST:event_area_cb_wilayahItemStateChanged

    private void client_simpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_client_simpanMouseClicked
        InsertClient();
        Clear();
        hiden();
        RunTable();
        DisableBT();
    }//GEN-LAST:event_client_simpanMouseClicked

    private void gol_simpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gol_simpanMouseClicked
        InsertGolongan();
        Clear();
        RunTable();
        DisableBT();
    }//GEN-LAST:event_gol_simpanMouseClicked

    private void area_simpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_area_simpanMouseClicked
        InsertArea();
        Clear();
        RunTable();
        DisableBT();
    }//GEN-LAST:event_area_simpanMouseClicked

    private void client_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_client_tableMouseClicked
        area_ubah.setEnabled(true);
        int bar = client_table.getSelectedRow();
        String a = tablemodelMasterClient.getValueAt(bar, 0).toString();
        String b = tablemodelMasterClient.getValueAt(bar, 1).toString();
        String c = tablemodelMasterClient.getValueAt(bar, 2).toString();
        String d = tablemodelMasterClient.getValueAt(bar, 3).toString();
        String e = tablemodelMasterClient.getValueAt(bar, 4).toString();
        String f = tablemodelMasterClient.getValueAt(bar, 5).toString();
        String g = tablemodelMasterClient.getValueAt(bar, 6).toString();

        client_id.setText(a);
        client_nama.setText(b);
        client_telp.setText(c);
        client_email.setText(d);
        client_alamat.setText(e);

        txt_status_client.setVisible(true);
        cb_status_client.setVisible(true);

    }//GEN-LAST:event_client_tableMouseClicked

    private void gol_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gol_tableMouseClicked
        gol_hapus.setEnabled(true);
        gol_ubah.setEnabled(true);
        int bar = gol_table.getSelectedRow();
        String a = tablemodelMasterGolongan.getValueAt(bar, 0).toString();
        String b = tablemodelMasterGolongan.getValueAt(bar, 1).toString();
        String c = tablemodelMasterGolongan.getValueAt(bar, 2).toString();

        golongan_id.setText(a);
        gol_jenis.setText(b);
        gol_gaji_diterima.setText(c);
    }//GEN-LAST:event_gol_tableMouseClicked

    private void area_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_area_tableMouseClicked
        area_ubah.setEnabled(true);
        cb_area_status.setVisible(true);
        cb_area_status.setVisible(true);
        int bar = area_table.getSelectedRow();
        String a = tablemodelMasterArea.getValueAt(bar, 0).toString();
        String b = tablemodelMasterArea.getValueAt(bar, 1).toString();
        String c = tablemodelMasterArea.getValueAt(bar, 2).toString();
        String d = tablemodelMasterArea.getValueAt(bar, 3).toString();
        String e = tablemodelMasterArea.getValueAt(bar, 4).toString();
        String f = tablemodelMasterArea.getValueAt(bar, 5).toString();
        String g = tablemodelMasterArea.getValueAt(bar, 6).toString();

        id_area.setText(a);
        area_cb_nama.setSelectedItem(b);
        area_cb_wilayah.setSelectedItem(c);
        area_cb_kota.setSelectedItem(d);
        area_telp.setText(e);
        area_alamat.setText(f);
        cb_area_status.setSelectedItem(g);
    }//GEN-LAST:event_area_tableMouseClicked

    private void posisi_tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_posisi_tabelMouseClicked
        posisi_ubah.setEnabled(true);
        posisi_hapus.setEnabled(true);
        int bar = posisi_tabel.getSelectedRow();
        String a = tablemodelMasterPosisi.getValueAt(bar, 0).toString();
        String b = tablemodelMasterPosisi.getValueAt(bar, 1).toString();
        String c = tablemodelMasterPosisi.getValueAt(bar, 2).toString();
        String d = tablemodelMasterPosisi.getValueAt(bar, 3).toString();
        String e = tablemodelMasterPosisi.getValueAt(bar, 4).toString();

        id_posisi.setText(a);
        posisi_cb_divisi.setSelectedItem(b);
        posisi_jabatan.setText(c);
        posisi_posisi.setText(d);
        posisi_keterangan.setText(e);
    }//GEN-LAST:event_posisi_tabelMouseClicked

    private void client_ubahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_client_ubahMouseClicked
        if (client_nama.getText().isEmpty() || client_telp.getText().isEmpty() || client_email.getText().isEmpty()
                || client_alamat.getText().isEmpty()) {

//            JOptionPane.showMessageDialog(null, "Data Belum DiPilih!", "Perhatian!", HEIGHT, invalid);
        } else {
            try {
                String sql = "update client set nama=?,telp=?,email=?,alamat=?,status=? where id='" + client_id.getText() + "'";
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, client_nama.getText());
                stat.setString(2, client_telp.getText());
                stat.setString(3, client_email.getText());
                stat.setString(4, client_alamat.getText());
                stat.setString(5, cb_status_client.getSelectedItem().toString());

                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Ubah!", "Berhasil!", HEIGHT, sucess);
                Clear();
                RunTable();
                DisableBT();
                client_nama.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Perhatian!", HEIGHT, invalid);
            }
        }
    }//GEN-LAST:event_client_ubahMouseClicked

    private void gol_ubahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gol_ubahMouseClicked
        if (gol_gaji_diterima.getText().isEmpty() || gol_jenis.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Data Belum DiPilih!", "Perhatian!", HEIGHT, invalid);
        } else {
            try {
                String sql = "update golongan set jenis=?,gaji=? where id='" + golongan_id.getText() + "'";
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, gol_jenis.getText());
                stat.setString(2, gol_gaji_diterima.getText());

                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Ubah!", "Berhasil!", HEIGHT, sucess);
                Clear();
                RunTable();
                DisableBT();
                gol_jenis.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Perhatian!", HEIGHT, invalid);
            }
        }
    }//GEN-LAST:event_gol_ubahMouseClicked

    private void area_ubahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_area_ubahMouseClicked
        if (area_cb_nama.getSelectedItem().equals("Pilih") || area_cb_wilayah.getSelectedItem().equals("Pilih")
                || area_telp.getText().isEmpty() || area_alamat.getText().isEmpty()) {

//            JOptionPane.showMessageDialog(null, "Data Belum Dipilih!", "Pemberitahuan!", HEIGHT, invalid);
        } else {

            try {
                String sql = "update area set perusahaan=?,wilayah=?,kota=?,telp=?,alamat=?, status=? where id='" + id_area.getText() + "'";
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, area_cb_nama.getSelectedItem().toString());
                stat.setString(2, area_cb_wilayah.getSelectedItem().toString());
                stat.setString(3, area_cb_kota.getSelectedItem().toString());
                stat.setString(4, area_telp.getText());
                stat.setString(5, area_alamat.getText());
                stat.setString(6, cb_area_status.getSelectedItem().toString());

                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Ubah!", "Berhasil!", HEIGHT, sucess);
                Clear();
                RunTable();
                DisableBT();
                area_cb_nama.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Perhatian!", HEIGHT, invalid);
            }
        }
    }//GEN-LAST:event_area_ubahMouseClicked

    private void posisi_ubahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_posisi_ubahMouseClicked
        if (posisi_cb_divisi.getSelectedItem().equals("Pilih") || posisi_jabatan.getText().isEmpty()
                || posisi_posisi.getText().isEmpty() || posisi_keterangan.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Data Belum Dipilih", "Alert Message!", HEIGHT, invalid);
        } else {
            try {
                String sql = "update posisi set divisi=?,jabatan=?,posisi=?,detail_tugas=? where id='" + id_posisi.getText() + "'";
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, posisi_cb_divisi.getSelectedItem().toString());
                stat.setString(2, posisi_jabatan.getText());
                stat.setString(3, posisi_posisi.getText());
                stat.setString(4, posisi_keterangan.getText());

                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah!", "Perhatian!", HEIGHT, sucess);
                Clear();
                RunTable();
                DisableBT();
                posisi_cb_divisi.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Alert Message!", HEIGHT, invalid);
            }
        }
    }//GEN-LAST:event_posisi_ubahMouseClicked

    private void gol_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gol_hapusMouseClicked
        if (golongan_id.getText().isEmpty() || gol_gaji_diterima.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Data Belum Di Pilih!", "Kesalahan!", HEIGHT, invalid);
        } else {
            int ok = JOptionPane.showConfirmDialog(null, "Apakah Yakin Menghapus Data ini???", "Perhatian!", JOptionPane.YES_NO_OPTION, HEIGHT, warning);
            if (ok == 0) {
                try {
                    String sql = "delete from golongan where id='" + golongan_id.getText() + "'";
                    PreparedStatement stat = conn.prepareStatement(sql);
                    stat.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                    Clear();
                    RunTable();
                    DisableBT();
                    gol_jenis.requestFocus();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data Gagal Dihapus");
                    Clear();
                    RunTable();
                }
            }
        }

    }//GEN-LAST:event_gol_hapusMouseClicked

    private void posisi_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_posisi_hapusMouseClicked
        if (id_posisi.getText().isEmpty() || posisi_jabatan.getText().isEmpty() || posisi_posisi.getText().isEmpty() || posisi_keterangan.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Data Belum Di Pilih!", "Kesalahan!", HEIGHT, invalid);
        } else {
            int ok = JOptionPane.showConfirmDialog(null, "Apakah Yakin Menghapus Data ini???", "Perhatian!", JOptionPane.YES_NO_OPTION, HEIGHT, warning);
            if (ok == 0) {
                try {
                    String sql = "delete from posisi where id='" + id_posisi.getText() + "'";
                    PreparedStatement stat = conn.prepareStatement(sql);
                    stat.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                    Clear();
                    RunTable();
                    DisableBT();
                    posisi_cb_divisi.requestFocus();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data Gagal Dihapus");
                    Clear();
                    RunTable();
                }
            }
        }
    }//GEN-LAST:event_posisi_hapusMouseClicked

    private void bt_cari_posisiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_cari_posisiMouseClicked
        ShowPosisi sp = new ShowPosisi();
        sp.hmPosisi = this;
        sp.setVisible(true);
        sp.setResizable(false);
    }//GEN-LAST:event_bt_cari_posisiMouseClicked

    private void bt_cari_areaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_cari_areaMouseClicked
        ShowArea sa = new ShowArea();
        sa.hmArea = this;
        sa.setVisible(true);
        sa.setResizable(false);
    }//GEN-LAST:event_bt_cari_areaMouseClicked

    private void bt_cari_posisi1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_cari_posisi1MouseClicked
        ShowPelamar sp = new ShowPelamar();
        sp.hmPelamar = this;
        sp.setVisible(true);
        sp.setResizable(false);
    }//GEN-LAST:event_bt_cari_posisi1MouseClicked

    private void bt_batal_karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_batal_karyawanMouseClicked
        Clear();
    }//GEN-LAST:event_bt_batal_karyawanMouseClicked

    private void bt_simpan_karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_simpan_karyawanMouseClicked
        InsertKaryawan();
        UpdateGabung();
        RunTable();
        Clear();
    }//GEN-LAST:event_bt_simpan_karyawanMouseClicked

    private void gaji_bt_simpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gaji_bt_simpanMouseClicked
        String statusdata = "";
        if (gaji_id_karyawan.getText().isEmpty() || gaji_nama.getText().isEmpty() || gaji_perusahaan.getText().isEmpty()
                || gaji_jabatan.getText().isEmpty() || gaji_posisi.getText().isEmpty() || gaji_golongan.getText().isEmpty()
                || gaji_gaji_diserahkan.getText().isEmpty() || gaji_gaji_bersih.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Belum Terpenuhi!", "Perhatian!", HEIGHT, warning);
        } else {
            try {
                String sql = "update karyawan set golongan=?,gaji_kotor=?,gaji_bersih=?, status_data=? where id='" + gaji_id_karyawan.getText() + "'";
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, gaji_golongan.getText());
                stat.setString(2, gaji_gaji_diserahkan.getText());
                stat.setString(3, gaji_gaji_bersih.getText());
                stat.setString(4, statusdata);

                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan!", "Berhasil!", HEIGHT, sucess);
                RunTable();
                Clear();
                gaji_cb_filter.setSelectedItem("Semua");
                gaji_cari_karyawan.setVisible(true);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!" + e.getMessage(), "Perhatian!", HEIGHT, invalid);
            }
        }
    }//GEN-LAST:event_gaji_bt_simpanMouseClicked

    private void gaji_cari_karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gaji_cari_karyawanMouseClicked
        ShowKaryawan sk = new ShowKaryawan();
        sk.hmkaryawan = this;
        sk.setVisible(true);
        sk.setResizable(false);
    }//GEN-LAST:event_gaji_cari_karyawanMouseClicked

    private void cari_gol_karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cari_gol_karyawanMouseClicked
        ShowGolongan sg = new ShowGolongan();
        sg.hmGolongan = this;
        sg.setVisible(true);
        sg.setResizable(false);
        ck_jht.setEnabled(true);
        ck_kesehatan.setEnabled(true);
        ck_ketenagakerjaan.setEnabled(true);
    }//GEN-LAST:event_cari_gol_karyawanMouseClicked

    private void gaji_bt_batalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gaji_bt_batalMouseClicked
        gaji_id_karyawan.setText("");
        gaji_nama.setText("");
        gaji_dept.setText("");
        gaji_jabatan.setText("");
        gaji_posisi.setText("");
        gaji_perusahaan.setText("");
        gaji_golongan.setText("");
        gaji_gaji_bersih.setText("");
        gaji_gaji_diserahkan.setText("");
        ck_jht.setSelected(false);
        ck_kesehatan.setSelected(false);
        ck_ketenagakerjaan.setSelected(false);
        gaji_cari_karyawan.setVisible(true);
        cari_gol_karyawan.setVisible(true);
    }//GEN-LAST:event_gaji_bt_batalMouseClicked

    private void ck_kesehatanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ck_kesehatanItemStateChanged

        if (ck_kesehatan.isSelected()) {
            txt_kesehatan.setText("YA");
            String gb = gaji_gaji_bersih.getText();
            int gaji = Integer.parseInt(gb);
            int hitungpersen = gaji * 1 / 100;
            int bersih = gaji - hitungpersen;
            String jumlah = String.valueOf(bersih);
            gaji_gaji_bersih.setText(jumlah);
        } else {
            txt_kesehatan.setText("NO");
//            gaji_gaji_bersih.setText(gaji_gaji_diserahkan.getText());
//            String  gb = gaji_gaji_diserahkan.getText();
//            String gaber = gaji_gaji_bersih.getText();
//            int gajibersih = Integer.parseInt(gaber);
//            int gaji =  Integer.parseInt(gb);
//            int hitungpersen = gaji * 1 / 100;
//            int bersih = gajibersih + hitungpersen;
//            String jumlah = String.valueOf(bersih);
//            gaji_gaji_bersih.setText(jumlah);
        }
    }//GEN-LAST:event_ck_kesehatanItemStateChanged

    private void ck_ketenagakerjaanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ck_ketenagakerjaanItemStateChanged
        if (ck_ketenagakerjaan.isSelected()) {
            txt_jp.setText("YA");
            String gb = gaji_gaji_bersih.getText();
            int gaji = Integer.parseInt(gb);
            int hitungpersen = gaji * 1 / 100;
            int bersih = gaji - hitungpersen;
            String jumlah = String.valueOf(bersih);
            gaji_gaji_bersih.setText(jumlah);
        } else {
            txt_jp.setText("NO");
//            gaji_gaji_bersih.setText(gaji_gaji_diserahkan.getText());
//            String  gb = gaji_gaji_diserahkan.getText();
//            String gaber = gaji_gaji_bersih.getText();
//            int gajibersih = Integer.parseInt(gaber);
//            int gaji =  Integer.parseInt(gb);
//            int hitungpersen = gaji * 1 / 100;
//            int bersih = gajibersih + hitungpersen;
//            String jumlah = String.valueOf(bersih);
//            gaji_gaji_bersih.setText(jumlah);
        }
    }//GEN-LAST:event_ck_ketenagakerjaanItemStateChanged

    private void ck_jhtItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ck_jhtItemStateChanged
        if (ck_jht.isSelected()) {
            txt_jht.setText("YA");
            String gb = gaji_gaji_bersih.getText();
            int gaji = Integer.parseInt(gb);
            int hitungpersen = gaji * 2 / 100;
            int bersih = gaji - hitungpersen;
            String jumlah = String.valueOf(bersih);
            gaji_gaji_bersih.setText(jumlah);
        } else {
            txt_jht.setText("NO");
//            gaji_gaji_bersih.setText(gaji_gaji_diserahkan.getText());
//            String  gb = gaji_gaji_diserahkan.getText();
//            String gaber = gaji_gaji_bersih.getText();
//            int gajibersih = Integer.parseInt(gaber);
//            int gaji =  Integer.parseInt(gb);
//            int hitungpersen = gaji * 2 / 100;
//            int bersih = gajibersih + hitungpersen;
//            String jumlah = String.valueOf(bersih);
//            gaji_gaji_bersih.setText(jumlah);
        }
    }//GEN-LAST:event_ck_jhtItemStateChanged

    private void gaji_table_gajiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gaji_table_gajiMouseClicked
        int bar = gaji_table_gaji.getSelectedRow();
        String a = tablemodelGajiKaryawan.getValueAt(bar, 0).toString();
        String b = tablemodelGajiKaryawan.getValueAt(bar, 1).toString();
        String c = tablemodelGajiKaryawan.getValueAt(bar, 2).toString();
        String d = tablemodelGajiKaryawan.getValueAt(bar, 3).toString();
        String e = tablemodelGajiKaryawan.getValueAt(bar, 4).toString();
        String f = tablemodelGajiKaryawan.getValueAt(bar, 5).toString();

        gaji_id_karyawan.setText(a);
        gaji_nama.setText(b);
        gaji_perusahaan.setText(c);
        gaji_dept.setText(d);
        gaji_jabatan.setText(e);
        gaji_posisi.setText(f);

        gaji_cari_karyawan.setVisible(false);

    }//GEN-LAST:event_gaji_table_gajiMouseClicked

    private void table_karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_karyawanMouseClicked
        ShowDataKaryawan sdk = new ShowDataKaryawan();
        int i = table_karyawan.getSelectedRow();
        TableModel tm = table_karyawan.getModel();

        String id = tm.getValueAt(i, 0).toString();
        String nama = tm.getValueAt(i, 1).toString();
        String telp = tm.getValueAt(i, 2).toString();
        String alamat = tm.getValueAt(i, 3).toString();
        String dept = tm.getValueAt(i, 4).toString();
        String jabatan = tm.getValueAt(i, 5).toString();
        String posisi = tm.getValueAt(i, 6).toString();
        String perusahaan = tm.getValueAt(i, 7).toString();
        String status = tm.getValueAt(i, 9).toString();

        sdk.ex_id_kar.setText(id);
        sdk.ex_nama_kar.setText(nama);
        sdk.ex_telp_kar.setText(telp);
        sdk.ex_alamat_kar.setText(alamat);
        sdk.ex_pt_kar.setSelectedItem(perusahaan);
        sdk.ex_dept_kar.setText(dept);
        sdk.ex_jabatan_kar.setText(jabatan);
        sdk.ex_sdk_posisi.setSelectedItem(posisi);
        sdk.ex_status_kar.setSelectedItem(status);

        sdk.setVisible(true);
        sdk.pack();
        sdk.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_table_karyawanMouseClicked

    private void gaji_cb_filterPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_gaji_cb_filterPropertyChange

    }//GEN-LAST:event_gaji_cb_filterPropertyChange

    private void gaji_cb_filterPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_gaji_cb_filterPopupMenuWillBecomeInvisible
        if (gaji_cb_filter.getSelectedItem().equals("Semua")) {
            TableGajiKaryawan();
        } else if (gaji_cb_filter.getSelectedItem().equals("Probation")) {
            TableGajiProbation();
        } else if (gaji_cb_filter.getSelectedItem().equals("Contract")) {
            TableGajiContract();
        } else if (gaji_cb_filter.getSelectedItem().equals("Permanent")) {
            TableGajiPermanent();
        } else if (gaji_cb_filter.getSelectedItem().equals("Pembaharuan")) {
            TableGajiPerubahan();
        }
    }//GEN-LAST:event_gaji_cb_filterPopupMenuWillBecomeInvisible

    private void client_namaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_client_namaKeyReleased
        int position = client_nama.getCaretPosition();
        client_nama.setText(client_nama.getText().toUpperCase());
        client_nama.setCaretPosition(position);
    }//GEN-LAST:event_client_namaKeyReleased

    private void client_telpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_client_telpKeyTyped
        FilterAngka(evt);
    }//GEN-LAST:event_client_telpKeyTyped

    private void gol_gaji_diterimaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gol_gaji_diterimaKeyTyped
        FilterAngka(evt);
    }//GEN-LAST:event_gol_gaji_diterimaKeyTyped

    private void bt_cari_table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_cari_table1MouseClicked
        Clear();
        RunTable();
    }//GEN-LAST:event_bt_cari_table1MouseClicked

    private void bt_cari_table1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_cari_table1MousePressed

    }//GEN-LAST:event_bt_cari_table1MousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String param = cb_report_kar.getSelectedItem().toString();

        if (param.equals("Pilih")) {
            JOptionPane.showMessageDialog(null, "Pilih Parameter Data", "Peringatan!", HEIGHT);

        } else if (param.equals("Biodata")) {
            try {
                HashMap parameter = new HashMap();
                Class.forName("com.mysql.jdbc.Driver");
                Connection cn = DriverManager.getConnection("jdbc:mysql:" + "///ppbm", "root", "passw0rd");
                File file = new File("src/LaporanModels/biodatakar.jasper");
                JasperReport jr = (JasperReport) JRLoader.loadObject(file);
                JasperPrint jp = JasperFillManager.fillReport(jr, parameter, cn);
                JasperViewer.viewReport(jp, false);
                JasperViewer.setDefaultLookAndFeelDecorated(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Tidak Bisa Merespon", "Peringatan!", HEIGHT);

            }
        } else if (param.equals("Pekerjaan")) {
            try {
                HashMap parameter = new HashMap();
                Class.forName("com.mysql.jdbc.Driver");
                Connection cn = DriverManager.getConnection("jdbc:mysql:" + "///ppbm", "root", "passw0rd");
                File file = new File("src/LaporanModels/karyawan.jasper");
                JasperReport jr = (JasperReport) JRLoader.loadObject(file);
                JasperPrint jp = JasperFillManager.fillReport(jr, parameter, cn);
                JasperViewer.viewReport(jp, false);
                JasperViewer.setDefaultLookAndFeelDecorated(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Tidak Bisa Merespon", "Peringatan!", HEIGHT);

            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String param = cb_param_gaji.getSelectedItem().toString();
        if (param.equals("Pilih")) {
            JOptionPane.showMessageDialog(null, "Pilih Parameter Data", "Peringatan!", HEIGHT);

        } else if (param.equals("Gaji Karyawan")) {
            try {
                HashMap parameter = new HashMap();
                Class.forName("com.mysql.jdbc.Driver");
                Connection cn = DriverManager.getConnection("jdbc:mysql:" + "///ppbm", "root", "passw0rd");
                File file = new File("src/LaporanModels/gajikaryawan.jasper");
                JasperReport jr = (JasperReport) JRLoader.loadObject(file);
                JasperPrint jp = JasperFillManager.fillReport(jr, parameter, cn);
                JasperViewer.viewReport(jp, false);
                JasperViewer.setDefaultLookAndFeelDecorated(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Tidak Bisa Merespon", "Peringatan!", HEIGHT);

            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String param = cb_param_master.getSelectedItem().toString();
        if (param.equals("Pilih")) {
            JOptionPane.showMessageDialog(null, "Pilih Parameter Data", "Peringatan!", HEIGHT);

        } else if (param.equals("Data Client")) {
            try {
                HashMap parameter = new HashMap();
                Class.forName("com.mysql.jdbc.Driver");
                Connection cn = DriverManager.getConnection("jdbc:mysql:" + "///ppbm", "root", "passw0rd");
                File file = new File("src/LaporanModels/mclient.jasper");
                JasperReport jr = (JasperReport) JRLoader.loadObject(file);
                JasperPrint jp = JasperFillManager.fillReport(jr, parameter, cn);
                JasperViewer.viewReport(jp, false);
                JasperViewer.setDefaultLookAndFeelDecorated(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Tidak Bisa Merespon", "Peringatan!", HEIGHT);

            }
        } else if (param.equals("Data Area")) {
            try {
                HashMap parameter = new HashMap();
                Class.forName("com.mysql.jdbc.Driver");
                Connection cn = DriverManager.getConnection("jdbc:mysql:" + "///ppbm", "root", "passw0rd");
                File file = new File("src/LaporanModels/marea.jasper");
                JasperReport jr = (JasperReport) JRLoader.loadObject(file);
                JasperPrint jp = JasperFillManager.fillReport(jr, parameter, cn);
                JasperViewer.viewReport(jp, false);
                JasperViewer.setDefaultLookAndFeelDecorated(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Tidak Bisa Merespon", "Peringatan!", HEIGHT);

            }
        } else if (param.equals("Data Posisi")) {
            try {
                HashMap parameter = new HashMap();
                Class.forName("com.mysql.jdbc.Driver");
                Connection cn = DriverManager.getConnection("jdbc:mysql:" + "///ppbm", "root", "passw0rd");
                File file = new File("src/LaporanModels/mposisi.jasper");
                JasperReport jr = (JasperReport) JRLoader.loadObject(file);
                JasperPrint jp = JasperFillManager.fillReport(jr, parameter, cn);
                JasperViewer.viewReport(jp, false);
                JasperViewer.setDefaultLookAndFeelDecorated(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Tidak Bisa Merespon", "Peringatan!", HEIGHT);

            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String param = cb_param_pelamar.getSelectedItem().toString();
        if (param.equals("Pilih")) {
            JOptionPane.showMessageDialog(null, "Pilih Parameter Data", "Peringatan!", HEIGHT);

        } else if (param.equals("Lolos")) {
            try {
                HashMap parameter = new HashMap();
                Class.forName("com.mysql.jdbc.Driver");
                Connection cn = DriverManager.getConnection("jdbc:mysql:" + "///ppbm", "root", "passw0rd");
                File file = new File("src/LaporanModels/pelamarlolos.jasper");
                JasperReport jr = (JasperReport) JRLoader.loadObject(file);
                JasperPrint jp = JasperFillManager.fillReport(jr, parameter, cn);
                JasperViewer.viewReport(jp, false);
                JasperViewer.setDefaultLookAndFeelDecorated(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Tidak Bisa Merespon", "Peringatan!", HEIGHT);

            }
        } else if (param.equals("Tidak Lolos")) {
            try {
                HashMap parameter = new HashMap();
                Class.forName("com.mysql.jdbc.Driver");
                Connection cn = DriverManager.getConnection("jdbc:mysql:" + "///ppbm", "root", "passw0rd");
                File file = new File("src/LaporanModels/gagal.jasper");
                JasperReport jr = (JasperReport) JRLoader.loadObject(file);
                JasperPrint jp = JasperFillManager.fillReport(jr, parameter, cn);
                JasperViewer.viewReport(jp, false);
                JasperViewer.setDefaultLookAndFeelDecorated(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Tidak Bisa Merespon", "Peringatan!", HEIGHT);

            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void bt_cari_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_cari_tableMouseClicked
       TableKaryawan();
    }//GEN-LAST:event_bt_cari_tableMouseClicked

    private void setCBAreaName() {
        String sql = "SELECT * FROM client order by nama asc";
        try {
            ps = Models.Config.Conn().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String uName = rs.getString("nama");
                area_cb_nama.addItem(uName);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Memuat" + e.getMessage(), "Peringatan", HEIGHT);
        }
    }

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Header;
    private javax.swing.JPanel Panel_Gaji;
    public javax.swing.JPanel Panel_Karyawan;
    private javax.swing.JPanel Panel_Laporan;
    private javax.swing.JPanel Panel_Master;
    private javax.swing.JPanel Panel_Pelamar;
    private javax.swing.JPanel Pusat;
    private javax.swing.JTextArea alamat_karyawan;
    private javax.swing.JTextArea alamat_penempatan;
    private javax.swing.JTextArea area_alamat;
    private javax.swing.JComboBox<String> area_cb_kota;
    private javax.swing.JComboBox<String> area_cb_nama;
    private javax.swing.JComboBox<String> area_cb_wilayah;
    private javax.swing.JTextField area_karyawan;
    private javax.swing.JPanel area_simpan;
    private javax.swing.JTable area_table;
    private javax.swing.JTextField area_telp;
    private javax.swing.JPanel area_ubah;
    private javax.swing.JPanel awal_pusat;
    private javax.swing.JPanel bt_Tolak;
    private javax.swing.JPanel bt_area;
    private javax.swing.JPanel bt_batal_karyawan;
    private javax.swing.JPanel bt_cari_area;
    private javax.swing.JPanel bt_cari_posisi;
    private javax.swing.JPanel bt_cari_posisi1;
    private javax.swing.JPanel bt_cari_table;
    private javax.swing.JPanel bt_cari_table1;
    private javax.swing.JPanel bt_client;
    private javax.swing.JPanel bt_golongan;
    private javax.swing.JPanel bt_posisi;
    private javax.swing.JPanel bt_simpan_karyawan;
    private javax.swing.JPanel bt_terima;
    private javax.swing.JPanel cari_gol_karyawan;
    private javax.swing.JPanel category;
    private javax.swing.JComboBox<String> cb_area_status;
    private javax.swing.JComboBox<String> cb_param_gaji;
    private javax.swing.JComboBox<String> cb_param_master;
    private javax.swing.JComboBox<String> cb_param_pelamar;
    private javax.swing.JComboBox<String> cb_report_kar;
    private javax.swing.JComboBox<String> cb_status_client;
    private javax.swing.JCheckBox ck_jht;
    private javax.swing.JCheckBox ck_kesehatan;
    private javax.swing.JCheckBox ck_ketenagakerjaan;
    public javax.swing.JTextArea client_alamat;
    public javax.swing.JTextField client_email;
    public javax.swing.JTextField client_id;
    public javax.swing.JTextField client_nama;
    public javax.swing.JPanel client_simpan;
    private javax.swing.JTable client_table;
    private javax.swing.JLabel client_tanggal;
    public javax.swing.JTextField client_telp;
    public javax.swing.JPanel client_ubah;
    private javax.swing.JPanel data_area;
    private javax.swing.JPanel data_client;
    private javax.swing.JPanel data_golongan;
    private javax.swing.JPanel data_posisi;
    private javax.swing.JTextField divisi_karyawan;
    private javax.swing.JPanel gaji;
    private javax.swing.JPanel gaji_bt_batal;
    private javax.swing.JPanel gaji_bt_simpan;
    private javax.swing.JPanel gaji_cari_karyawan;
    private javax.swing.JComboBox<String> gaji_cb_filter;
    private javax.swing.JTextField gaji_dept;
    private javax.swing.JTextField gaji_gaji_bersih;
    private javax.swing.JTextField gaji_gaji_diserahkan;
    private javax.swing.JTextField gaji_golongan;
    private javax.swing.JTextField gaji_id_karyawan;
    private javax.swing.JTextField gaji_jabatan;
    private javax.swing.JTextField gaji_nama;
    private javax.swing.JTextField gaji_perusahaan;
    private javax.swing.JTextField gaji_posisi;
    private javax.swing.JTable gaji_table_gaji;
    private javax.swing.JTextField gol_gaji_diterima;
    private javax.swing.JPanel gol_hapus;
    private javax.swing.JTextField gol_jenis;
    private javax.swing.JPanel gol_simpan;
    private javax.swing.JTable gol_table;
    private javax.swing.JPanel gol_ubah;
    private javax.swing.JTextField golongan_id;
    private javax.swing.JLabel id_area;
    private javax.swing.JTextField id_karyawan;
    private javax.swing.JLabel id_posisi;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jabatan_karyawan;
    private javax.swing.JPanel karyawan;
    private javax.swing.JTextField kj_karyawan;
    private javax.swing.JPanel laporan;
    private javax.swing.JLabel logo;
    private javax.swing.JTextField nama_karyawan;
    private javax.swing.JTextField no_telp_karyawan;
    private javax.swing.JPanel pelamar;
    private javax.swing.JTextField penempatan_karyawan;
    public static javax.swing.JComboBox<String> posisi_cb_divisi;
    private javax.swing.JPanel posisi_hapus;
    public static javax.swing.JTextField posisi_jabatan;
    private javax.swing.JTextField posisi_karyawan;
    public static javax.swing.JTextArea posisi_keterangan;
    public static javax.swing.JTextField posisi_posisi;
    private javax.swing.JPanel posisi_simpan;
    public javax.swing.JTable posisi_tabel;
    private javax.swing.JPanel posisi_ubah;
    private javax.swing.JPanel pusat_master;
    private javax.swing.JPanel sub_menu_master;
    public javax.swing.JTable table_karyawan;
    private javax.swing.JTextField tanggal_lahir;
    private javax.swing.JTable tb_kependudukan;
    private javax.swing.JTable tb_kriteria;
    private javax.swing.JTable tb_pelamar;
    private javax.swing.JTable tb_pengalaman;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfID;
    private javax.swing.JTextField tfInstitusi;
    private javax.swing.JTextField tfJurusan;
    private javax.swing.JTextField tfNama;
    private javax.swing.JTextField tfPendidikan;
    private javax.swing.JTextField tfTelp;
    private javax.swing.JTextField tgl_penerimaan;
    private javax.swing.JPanel top;
    private javax.swing.JLabel txt_area_status;
    private javax.swing.JTextField txt_cari_karyawan;
    private javax.swing.JLabel txt_jht;
    private javax.swing.JLabel txt_jp;
    private javax.swing.JLabel txt_kesehatan;
    private javax.swing.JLabel txt_status_client;
    // End of variables declaration//GEN-END:variables
}
