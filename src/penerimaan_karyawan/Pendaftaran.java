package penerimaan_karyawan;

import static Models.Config.conn;
import Models.ArrayData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Pendaftaran extends javax.swing.JFrame {
    
    ImageIcon img1 = new ImageIcon(getClass().getResource("/Icon/ceklist32.png"));
    public String dateChooser;
    public String dateChooser2;
    public String dateChooser3;
    PreparedStatement ps;
    ResultSet rs;
    
    public ArrayData arrayData = new ArrayData();
//    private 
    ImageIcon sucess = new ImageIcon(getClass().getResource("/Icon/checked.png"));
    ImageIcon invalid = new ImageIcon(getClass().getResource("/Icon/cancel.png"));
    ImageIcon warning = new ImageIcon(getClass().getResource("/Icon/warning.png"));
//    String idPelamar;
    
    public Pendaftaran() {
        initComponents();
        idauto();
        pendaftaran_id.setEnabled(false);
        txt_tanggal.setEnabled(false);
        simpan1.setVisible(false);
        simpan2.setVisible(false);
        simpan3.setVisible(false);
//        pendaftaran_provinsi.setModel(new DefaultComboBoxModel<String>(provinsi(new String[0])));
        provinsi();
        Kota();
        tanggal();
    }
    
    private void tanggal(){
        Date date = new Date();
        String myFormat = "EEEE, dd MMMM yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        txt_tanggal.setText(sdf.format(date));
    }
    
    private void provinsi(){
        DefaultComboBoxModel dm = new DefaultComboBoxModel(arrayData.provinsi);
        pendaftaran_provinsi.setModel(dm);
    }
    
    public void Kota(){
        String getProvinsi = pendaftaran_provinsi.getSelectedItem().toString();

        if(getProvinsi.equals("Pilih")){
            DefaultComboBoxModel dm1 = new DefaultComboBoxModel(arrayData.Pilih);
            pendaftaran_kota.setModel(dm1);
        }
        else if(getProvinsi.equals("Aceh")){
            DefaultComboBoxModel dm2 = new DefaultComboBoxModel(arrayData.Aceh);
            pendaftaran_kota.setModel(dm2);
        }
        else if(getProvinsi.equals("Bali")){
            DefaultComboBoxModel dm3 = new DefaultComboBoxModel(arrayData.Bali);
            pendaftaran_kota.setModel(dm3);
        }
        else if(getProvinsi.equals("Banten")){
            DefaultComboBoxModel dm4 = new DefaultComboBoxModel(arrayData.Banten);
            pendaftaran_kota.setModel(dm4);
        }
        else if(getProvinsi.equals("Bengkulu")){
            DefaultComboBoxModel dm5 = new DefaultComboBoxModel(arrayData.Bengkulu);
            pendaftaran_kota.setModel(dm5);
        }
        else if(getProvinsi.equals("DI Yogyakarta")){
            DefaultComboBoxModel dm6 = new DefaultComboBoxModel(arrayData.Yogyakarta);
            pendaftaran_kota.setModel(dm6);
        }
        else if(getProvinsi.equals("DKI Jakarta")){
            DefaultComboBoxModel dm7 = new DefaultComboBoxModel(arrayData.DKI_Jakarta);
            pendaftaran_kota.setModel(dm7);
        }
        else if(getProvinsi.equals("Gorontalo")){
            DefaultComboBoxModel dm8 = new DefaultComboBoxModel(arrayData.Gorontalo);
            pendaftaran_kota.setModel(dm8);
        }
        else if(getProvinsi.equals("Jambi")){
            DefaultComboBoxModel dm9 = new DefaultComboBoxModel(arrayData.Jambi);
            pendaftaran_kota.setModel(dm9);
        }
        else if(getProvinsi.equals("Jawa Barat")){
            DefaultComboBoxModel dm10 = new DefaultComboBoxModel(arrayData.Jawa_Barat);
            pendaftaran_kota.setModel(dm10);
        }
        else if(getProvinsi.equals("Jawa Tengah")){
            DefaultComboBoxModel dm11 = new DefaultComboBoxModel(arrayData.Jawa_Tengah);
            pendaftaran_kota.setModel(dm11);
        }
        else if(getProvinsi.equals("Jawa Timur")){
            DefaultComboBoxModel dm12 = new DefaultComboBoxModel(arrayData.Jawa_Timur);
            pendaftaran_kota.setModel(dm12);
        }
        else if(getProvinsi.equals("Kalimantan Barat")){
            DefaultComboBoxModel dm13 = new DefaultComboBoxModel(arrayData.Kal_Bar);
            pendaftaran_kota.setModel(dm13);
        }
        else if(getProvinsi.equals("Kalimantan Selatan")){
            DefaultComboBoxModel dm14 = new DefaultComboBoxModel(arrayData.Kal_Sel);
            pendaftaran_kota.setModel(dm14);
        }
        else if(getProvinsi.equals("Kalimantan Tengah")){
            DefaultComboBoxModel dm15 = new DefaultComboBoxModel(arrayData.Kal_Teng);
            pendaftaran_kota.setModel(dm15);
        }
        else if(getProvinsi.equals("Kalimantan Timur")){
            DefaultComboBoxModel dm16 = new DefaultComboBoxModel(arrayData.Kal_Tim);
            pendaftaran_kota.setModel(dm16);
        }
        else if(getProvinsi.equals("Kalimantan Utara")){
            DefaultComboBoxModel dm17 = new DefaultComboBoxModel(arrayData.Kal_Utr);
            pendaftaran_kota.setModel(dm17);
        }
        else if(getProvinsi.equals("Kepulauan Bangka Belitung")){
            DefaultComboBoxModel dm18 = new DefaultComboBoxModel(arrayData.Kep_Belitung);
            pendaftaran_kota.setModel(dm18);
        }
        else if(getProvinsi.equals("Kepulauan Riau")){
            DefaultComboBoxModel dm19 = new DefaultComboBoxModel(arrayData.Kep_Riau);
            pendaftaran_kota.setModel(dm19);
        }
        else if(getProvinsi.equals("Lampung")){
            DefaultComboBoxModel dm20 = new DefaultComboBoxModel(arrayData.Lampung);
            pendaftaran_kota.setModel(dm20);
        }
        else if(getProvinsi.equals("Maluku")){
            DefaultComboBoxModel dm21 = new DefaultComboBoxModel(arrayData.Maluku);
            pendaftaran_kota.setModel(dm21);
        }
        else if(getProvinsi.equals("Maluku Utara")){
            DefaultComboBoxModel dm22 = new DefaultComboBoxModel(arrayData.Maluku_Utr);
            pendaftaran_kota.setModel(dm22);
        }
        else if(getProvinsi.equals("Nusa Tenggara Barat")){
            DefaultComboBoxModel dm23 = new DefaultComboBoxModel(arrayData.Ntb);
            pendaftaran_kota.setModel(dm23);
        }
        else if(getProvinsi.equals("Nusa Tenggara Timur")){
            DefaultComboBoxModel dm24 = new DefaultComboBoxModel(arrayData.Ntt);
            pendaftaran_kota.setModel(dm24);
        }
        else if(getProvinsi.equals("Papua")){
            DefaultComboBoxModel dm25 = new DefaultComboBoxModel(arrayData.Papua);
            pendaftaran_kota.setModel(dm25);
        }
        else if(getProvinsi.equals("Papua Barat")){
            DefaultComboBoxModel dm26 = new DefaultComboBoxModel(arrayData.Papua_Bar);
            pendaftaran_kota.setModel(dm26);
        }
        else if(getProvinsi.equals("Riau")){
            DefaultComboBoxModel dm27 = new DefaultComboBoxModel(arrayData.Riau);
            pendaftaran_kota.setModel(dm27);
        }
        else if(getProvinsi.equals("Sulawesi Selatan")){
            DefaultComboBoxModel dm28 = new DefaultComboBoxModel(arrayData.Sul_Sel);
            pendaftaran_kota.setModel(dm28);
        }
        else if(getProvinsi.equals("Sulawesi Tengah")){
            DefaultComboBoxModel dm29 = new DefaultComboBoxModel(arrayData.Sul_Teng);
            pendaftaran_kota.setModel(dm29);
        }
        else if(getProvinsi.equals("Sulawesi Tenggara")){
            DefaultComboBoxModel dm30 = new DefaultComboBoxModel(arrayData.Sul_Tgr);
            pendaftaran_kota.setModel(dm30);
        }
        else if(getProvinsi.equals("Sulawesi Barat")){
            DefaultComboBoxModel dm31 = new DefaultComboBoxModel(arrayData.Sul_Bar);
            pendaftaran_kota.setModel(dm31);
        }
        else if(getProvinsi.equals("Sulawesi Utara")){
            DefaultComboBoxModel dm32 = new DefaultComboBoxModel(arrayData.Sul_Utr);
            pendaftaran_kota.setModel(dm32);
        }
        else if(getProvinsi.equals("Sumatera Barat")){
            DefaultComboBoxModel dm33 = new DefaultComboBoxModel(arrayData.Sum_Bar);
            pendaftaran_kota.setModel(dm33);
        }
        else if(getProvinsi.equals("Sumatera Selatan")){
            DefaultComboBoxModel dm34 = new DefaultComboBoxModel(arrayData.Sum_Sel);
            pendaftaran_kota.setModel(dm34);
        }
        else if(getProvinsi.equals("Sumatera Utara")){
            DefaultComboBoxModel dm35 = new DefaultComboBoxModel(arrayData.Sum_Utr);
            pendaftaran_kota.setModel(dm35);
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

        jenis_kelamin = new javax.swing.ButtonGroup();
        warganegara = new javax.swing.ButtonGroup();
        pilihan1 = new javax.swing.ButtonGroup();
        pilihan2 = new javax.swing.ButtonGroup();
        pilihan3 = new javax.swing.ButtonGroup();
        Menu = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        icon1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        icon2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        icon3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        Form = new javax.swing.JPanel();
        Form_satu = new javax.swing.JPanel();
        pendaftaran_id = new javax.swing.JTextField();
        pendaftaran_nama = new javax.swing.JTextField();
        pendaftaran_email = new javax.swing.JTextField();
        pendaftaran_telp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rlaki = new javax.swing.JRadioButton();
        rcw = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        pendaftaran_pendidikan = new javax.swing.JComboBox<>();
        pendaftaran_institusi = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        pendaftaran_jurusan = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        pendaftaran_status = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pendaftaran_alamat_ins = new javax.swing.JTextArea();
        pendaftaran_lanjut1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        simpan1 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        txt_tanggal = new javax.swing.JLabel();
        tgl_lahir = new com.toedter.calendar.JDateChooser();
        jLabel71 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        Form_dua = new javax.swing.JPanel();
        pendaftaran_no_ktp = new javax.swing.JTextField();
        pendaftaran_nama_ktp = new javax.swing.JTextField();
        pendaftaran_telp1 = new javax.swing.JTextField();
        pendaftaran_tmpt_tinggal = new javax.swing.JComboBox<>();
        pendaftaran_kawin = new javax.swing.JComboBox<>();
        wni = new javax.swing.JRadioButton();
        wna = new javax.swing.JRadioButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        pendaftaran_telp2 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        pendaftaran_provinsi = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        pendaftaran_kota = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        pendaftaran_alamat_ktp = new javax.swing.JTextArea();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        pendaftaran_lanjut2 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        simpan2 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        Form_tiga = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        pendaftaran_perusahaan = new javax.swing.JTextField();
        pendaftaran_divisi = new javax.swing.JTextField();
        pendaftaran_jabatan = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        tanggal_masuk = new com.toedter.calendar.JDateChooser();
        tanggal_keluar = new com.toedter.calendar.JDateChooser();
        jLabel50 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        pendaftaran_keterangan = new javax.swing.JTextArea();
        pendaftaran_save = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        simpan3 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        cb_posisi = new javax.swing.JComboBox<>();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        rb1 = new javax.swing.JRadioButton();
        rb1_2 = new javax.swing.JRadioButton();
        rb2 = new javax.swing.JRadioButton();
        rb2_2 = new javax.swing.JRadioButton();
        rb3 = new javax.swing.JRadioButton();
        rb3_2 = new javax.swing.JRadioButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        txt_penjelasan = new javax.swing.JTextArea();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        form_empat = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_pengalaman = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_penduduk = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_pelamar = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        check_simpan = new javax.swing.JCheckBox();
        simpan_bt = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        Menu.setBackground(new java.awt.Color(0, 18, 61));
        Menu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(0, 18, 61));

        icon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/question32.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Data Pelamar Dan Pendidikan");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(icon1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(icon1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jPanel3.setBackground(new java.awt.Color(0, 18, 61));

        icon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/question32.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Data Kependudukan");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(icon2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icon2, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(22, 22, 22))
        );

        jPanel4.setBackground(new java.awt.Color(0, 18, 61));

        icon3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/question32.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Data Pengalaman Kerja & Magang");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(icon3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(icon3, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penerimaan_karyawan/Img/logo-kecil.png"))); // NOI18N

        javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(MenuLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MenuLayout.setVerticalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        Form.setBackground(new java.awt.Color(255, 255, 255));
        Form.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Form.setPreferredSize(new java.awt.Dimension(800, 500));
        Form.setLayout(new java.awt.CardLayout());

        Form_satu.setBackground(new java.awt.Color(255, 255, 255));
        Form_satu.setPreferredSize(new java.awt.Dimension(800, 428));

        pendaftaran_id.setPreferredSize(new java.awt.Dimension(6, 30));

        pendaftaran_nama.setPreferredSize(new java.awt.Dimension(6, 30));

        pendaftaran_email.setPreferredSize(new java.awt.Dimension(6, 30));

        pendaftaran_telp.setPreferredSize(new java.awt.Dimension(6, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Id Pelamar");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Nama Lengkap");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Email");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Telepon");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Kelamin");

        jenis_kelamin.add(rlaki);
        rlaki.setText("Laki-Laki");
        rlaki.setPreferredSize(new java.awt.Dimension(65, 30));

        jenis_kelamin.add(rcw);
        rcw.setText("Perempuan");
        rcw.setPreferredSize(new java.awt.Dimension(79, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Pendidikan Terakhir");

        pendaftaran_pendidikan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "SMA/SMK", "S1", "S2" }));
        pendaftaran_pendidikan.setPreferredSize(new java.awt.Dimension(56, 30));

        pendaftaran_institusi.setMinimumSize(new java.awt.Dimension(6, 30));
        pendaftaran_institusi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pendaftaran_institusiActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Nama Institusi");

        pendaftaran_jurusan.setPreferredSize(new java.awt.Dimension(6, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Jurusan");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Status");

        pendaftaran_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Belum Lulus", "Lulus" }));
        pendaftaran_status.setPreferredSize(new java.awt.Dimension(56, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Alamat Domisili");

        pendaftaran_alamat_ins.setColumns(20);
        pendaftaran_alamat_ins.setRows(5);
        jScrollPane1.setViewportView(pendaftaran_alamat_ins);

        pendaftaran_lanjut1.setBackground(new java.awt.Color(0, 153, 0));
        pendaftaran_lanjut1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pendaftaran_lanjut1MouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Lanjut");

        javax.swing.GroupLayout pendaftaran_lanjut1Layout = new javax.swing.GroupLayout(pendaftaran_lanjut1);
        pendaftaran_lanjut1.setLayout(pendaftaran_lanjut1Layout);
        pendaftaran_lanjut1Layout.setHorizontalGroup(
            pendaftaran_lanjut1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 133, Short.MAX_VALUE)
            .addGroup(pendaftaran_lanjut1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pendaftaran_lanjut1Layout.createSequentialGroup()
                    .addGap(0, 44, Short.MAX_VALUE)
                    .addComponent(jLabel16)
                    .addGap(0, 44, Short.MAX_VALUE)))
        );
        pendaftaran_lanjut1Layout.setVerticalGroup(
            pendaftaran_lanjut1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(pendaftaran_lanjut1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pendaftaran_lanjut1Layout.createSequentialGroup()
                    .addGap(0, 16, Short.MAX_VALUE)
                    .addComponent(jLabel16)
                    .addGap(0, 17, Short.MAX_VALUE)))
        );

        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setText("*");

        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setText("*");

        jLabel19.setForeground(new java.awt.Color(255, 0, 0));
        jLabel19.setText("*");

        jLabel20.setForeground(new java.awt.Color(255, 0, 0));
        jLabel20.setText("*");

        jLabel21.setForeground(new java.awt.Color(255, 0, 0));
        jLabel21.setText("*");

        jLabel22.setForeground(new java.awt.Color(255, 0, 0));
        jLabel22.setText("*");

        jLabel23.setForeground(new java.awt.Color(255, 0, 0));
        jLabel23.setText("*");

        jLabel24.setForeground(new java.awt.Color(255, 0, 0));
        jLabel24.setText("*");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel43.setText("Data Pelamar Dan Pendidikan");

        simpan1.setBackground(new java.awt.Color(0, 153, 0));
        simpan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                simpan1MouseClicked(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setText("Simpan");

        javax.swing.GroupLayout simpan1Layout = new javax.swing.GroupLayout(simpan1);
        simpan1.setLayout(simpan1Layout);
        simpan1Layout.setHorizontalGroup(
            simpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 133, Short.MAX_VALUE)
            .addGroup(simpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(simpan1Layout.createSequentialGroup()
                    .addGap(0, 41, Short.MAX_VALUE)
                    .addComponent(jLabel62)
                    .addGap(0, 41, Short.MAX_VALUE)))
        );
        simpan1Layout.setVerticalGroup(
            simpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(simpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(simpan1Layout.createSequentialGroup()
                    .addGap(0, 16, Short.MAX_VALUE)
                    .addComponent(jLabel62)
                    .addGap(0, 17, Short.MAX_VALUE)))
        );

        txt_tanggal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_tanggal.setText("jLabel71");
        txt_tanggal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tgl_lahir.setDateFormatString("dd MMMM yyyy");
        tgl_lahir.setPreferredSize(new java.awt.Dimension(101, 30));
        tgl_lahir.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tgl_lahirPropertyChange(evt);
            }
        });

        jLabel71.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel71.setText("Tanggal  Lahir");

        jLabel75.setForeground(new java.awt.Color(255, 0, 0));
        jLabel75.setText("*");

        javax.swing.GroupLayout Form_satuLayout = new javax.swing.GroupLayout(Form_satu);
        Form_satu.setLayout(Form_satuLayout);
        Form_satuLayout.setHorizontalGroup(
            Form_satuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Form_satuLayout.createSequentialGroup()
                .addGroup(Form_satuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Form_satuLayout.createSequentialGroup()
                        .addGap(270, 270, 270)
                        .addGroup(Form_satuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator1)
                            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Form_satuLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(Form_satuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(Form_satuLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_satuLayout.createSequentialGroup()
                                .addComponent(jLabel71)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(214, 214, 214))
                            .addGroup(Form_satuLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pendaftaran_email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(Form_satuLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pendaftaran_institusi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pendaftaran_jurusan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pendaftaran_id, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tgl_lahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(Form_satuLayout.createSequentialGroup()
                                .addGroup(Form_satuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(pendaftaran_pendidikan, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(Form_satuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Form_satuLayout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(pendaftaran_status, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addGroup(Form_satuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_satuLayout.createSequentialGroup()
                                .addGroup(Form_satuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pendaftaran_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(Form_satuLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(Form_satuLayout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(Form_satuLayout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(Form_satuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(Form_satuLayout.createSequentialGroup()
                                            .addComponent(rlaki, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(18, 18, 18)
                                            .addComponent(rcw, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(pendaftaran_telp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(61, 61, 61))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_satuLayout.createSequentialGroup()
                                .addComponent(pendaftaran_lanjut1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(simpan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38))
                            .addComponent(jLabel9))))
                .addContainerGap())
        );
        Form_satuLayout.setVerticalGroup(
            Form_satuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Form_satuLayout.createSequentialGroup()
                .addGroup(Form_satuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Form_satuLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel43))
                    .addGroup(Form_satuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txt_tanggal)))
                .addGap(9, 9, 9)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(Form_satuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Form_satuLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pendaftaran_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Form_satuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pendaftaran_email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Form_satuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel71)
                            .addComponent(jLabel75))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tgl_lahir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(Form_satuLayout.createSequentialGroup()
                        .addGroup(Form_satuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pendaftaran_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Form_satuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pendaftaran_telp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Form_satuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rlaki, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rcw, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)))
                .addGroup(Form_satuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Form_satuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel22))
                    .addGroup(Form_satuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jLabel20)
                        .addComponent(jLabel15)
                        .addComponent(jLabel23)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Form_satuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Form_satuLayout.createSequentialGroup()
                        .addGroup(Form_satuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pendaftaran_pendidikan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pendaftaran_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(Form_satuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel21)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pendaftaran_institusi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(Form_satuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel24))
                .addGroup(Form_satuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Form_satuLayout.createSequentialGroup()
                        .addComponent(pendaftaran_jurusan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_satuLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(Form_satuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pendaftaran_lanjut1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(simpan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        Form.add(Form_satu, "card2");

        Form_dua.setBackground(new java.awt.Color(255, 255, 255));
        Form_dua.setPreferredSize(new java.awt.Dimension(800, 500));

        pendaftaran_tmpt_tinggal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Rumah Sendiri", "Rumah Orang Tua", "Kost'an", "Kontrakan" }));

        pendaftaran_kawin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Belum Menikah", "Sudah Menikah", "Bercerai" }));

        warganegara.add(wni);
        wni.setText("WNI");

        warganegara.add(wna);
        wna.setText("WNA");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setText("Nomor KTP");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel26.setText("Nama Sesuai KTP");

        jLabel27.setText("Status");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel28.setText("Status Tempat Tinggal");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel29.setText("No Telepon Darurat");

        jLabel30.setText("Kewarganegaraan");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel31.setText("Provinsi");

        pendaftaran_provinsi.setPreferredSize(new java.awt.Dimension(56, 30));
        pendaftaran_provinsi.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                pendaftaran_provinsiItemStateChanged(evt);
            }
        });
        pendaftaran_provinsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pendaftaran_provinsiActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel32.setText("Kota");

        pendaftaran_kota.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pendaftaran_kota.setPreferredSize(new java.awt.Dimension(56, 30));

        pendaftaran_alamat_ktp.setColumns(20);
        pendaftaran_alamat_ktp.setRows(5);
        jScrollPane2.setViewportView(pendaftaran_alamat_ktp);

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel33.setText("Alamat Sesuai KTP");

        jLabel34.setForeground(new java.awt.Color(255, 0, 0));
        jLabel34.setText("*");

        jLabel35.setForeground(new java.awt.Color(255, 0, 0));
        jLabel35.setText("*");

        jLabel36.setForeground(new java.awt.Color(255, 0, 0));
        jLabel36.setText("*");

        jLabel37.setForeground(new java.awt.Color(255, 0, 0));
        jLabel37.setText("*");

        jLabel38.setForeground(new java.awt.Color(255, 0, 0));
        jLabel38.setText("*");

        jLabel39.setForeground(new java.awt.Color(255, 0, 0));
        jLabel39.setText("*");

        jLabel41.setForeground(new java.awt.Color(255, 0, 0));
        jLabel41.setText("*");

        pendaftaran_lanjut2.setBackground(new java.awt.Color(0, 153, 0));
        pendaftaran_lanjut2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pendaftaran_lanjut2MouseClicked(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Lanjut");

        javax.swing.GroupLayout pendaftaran_lanjut2Layout = new javax.swing.GroupLayout(pendaftaran_lanjut2);
        pendaftaran_lanjut2.setLayout(pendaftaran_lanjut2Layout);
        pendaftaran_lanjut2Layout.setHorizontalGroup(
            pendaftaran_lanjut2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pendaftaran_lanjut2Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel40)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        pendaftaran_lanjut2Layout.setVerticalGroup(
            pendaftaran_lanjut2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pendaftaran_lanjut2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel42.setText("Data Kependudukan");

        jLabel57.setForeground(new java.awt.Color(255, 0, 0));
        jLabel57.setText("*");

        jLabel58.setForeground(new java.awt.Color(255, 0, 0));
        jLabel58.setText("*");

        simpan2.setBackground(new java.awt.Color(0, 153, 0));
        simpan2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                simpan2MouseClicked(evt);
            }
        });

        jLabel63.setBackground(new java.awt.Color(0, 153, 0));
        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("Simpan");

        javax.swing.GroupLayout simpan2Layout = new javax.swing.GroupLayout(simpan2);
        simpan2.setLayout(simpan2Layout);
        simpan2Layout.setHorizontalGroup(
            simpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 186, Short.MAX_VALUE)
            .addGroup(simpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(simpan2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel63)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        simpan2Layout.setVerticalGroup(
            simpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 51, Short.MAX_VALUE)
            .addGroup(simpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(simpan2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel63)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout Form_duaLayout = new javax.swing.GroupLayout(Form_dua);
        Form_dua.setLayout(Form_duaLayout);
        Form_duaLayout.setHorizontalGroup(
            Form_duaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Form_duaLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(Form_duaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Form_duaLayout.createSequentialGroup()
                        .addGroup(Form_duaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pendaftaran_no_ktp, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pendaftaran_nama_ktp, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pendaftaran_kawin, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pendaftaran_tmpt_tinggal, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pendaftaran_telp1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Form_duaLayout.createSequentialGroup()
                                .addGroup(Form_duaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Form_duaLayout.createSequentialGroup()
                                        .addComponent(jLabel27)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel36))
                                    .addGroup(Form_duaLayout.createSequentialGroup()
                                        .addComponent(jLabel26)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel34))
                                    .addGroup(Form_duaLayout.createSequentialGroup()
                                        .addComponent(jLabel25)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel35))
                                    .addComponent(pendaftaran_telp2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(110, 110, 110))
                    .addGroup(Form_duaLayout.createSequentialGroup()
                        .addGroup(Form_duaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Form_duaLayout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel38))
                            .addGroup(Form_duaLayout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel37)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(Form_duaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_duaLayout.createSequentialGroup()
                        .addGroup(Form_duaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Form_duaLayout.createSequentialGroup()
                                .addComponent(wni, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                .addComponent(wna, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))
                            .addComponent(pendaftaran_provinsi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pendaftaran_kota, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(84, 84, 84))
                    .addGroup(Form_duaLayout.createSequentialGroup()
                        .addGroup(Form_duaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Form_duaLayout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel58))
                            .addGroup(Form_duaLayout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel57))
                            .addGroup(Form_duaLayout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel39))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Form_duaLayout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel41))
                            .addGroup(Form_duaLayout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(pendaftaran_lanjut2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(Form_duaLayout.createSequentialGroup()
                .addGap(345, 345, 345)
                .addGroup(Form_duaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(Form_duaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_duaLayout.createSequentialGroup()
                    .addContainerGap(528, Short.MAX_VALUE)
                    .addComponent(simpan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(84, Short.MAX_VALUE)))
        );
        Form_duaLayout.setVerticalGroup(
            Form_duaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Form_duaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(Form_duaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel30)
                    .addComponent(jLabel39)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Form_duaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pendaftaran_no_ktp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wni, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wna, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(Form_duaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Form_duaLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(Form_duaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jLabel34))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pendaftaran_nama_ktp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_duaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Form_duaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(jLabel57))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pendaftaran_provinsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(Form_duaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Form_duaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(jLabel36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_duaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel32)
                        .addComponent(jLabel58)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Form_duaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pendaftaran_kota, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pendaftaran_kawin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(Form_duaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Form_duaLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(Form_duaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(jLabel41))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Form_duaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(Form_duaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(jLabel37))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pendaftaran_tmpt_tinggal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(Form_duaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(jLabel38))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pendaftaran_telp1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(Form_duaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Form_duaLayout.createSequentialGroup()
                        .addComponent(pendaftaran_telp2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_duaLayout.createSequentialGroup()
                        .addComponent(pendaftaran_lanjut2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))))
            .addGroup(Form_duaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_duaLayout.createSequentialGroup()
                    .addContainerGap(427, Short.MAX_VALUE)
                    .addComponent(simpan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(25, Short.MAX_VALUE)))
        );

        Form.add(Form_dua, "card3");

        Form_tiga.setBackground(new java.awt.Color(255, 255, 255));
        Form_tiga.setPreferredSize(new java.awt.Dimension(800, 500));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel44.setText("Pengalaman Kerja & Posisi Diminati");

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel45.setText("Nama Perusahaan");

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel46.setText("Divisi");

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel47.setText("Jabatan");

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel48.setText("Tanggal Masuk");

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel49.setText("Tanggal Keluar");

        tanggal_masuk.setDateFormatString("dd MMMM yyyy");
        tanggal_masuk.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tanggal_masukPropertyChange(evt);
            }
        });

        tanggal_keluar.setDateFormatString("dd MMMM yyyy");
        tanggal_keluar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tanggal_keluarPropertyChange(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel50.setText("Keterangan Pekerjaan");

        pendaftaran_keterangan.setColumns(20);
        pendaftaran_keterangan.setRows(5);
        jScrollPane3.setViewportView(pendaftaran_keterangan);

        pendaftaran_save.setBackground(new java.awt.Color(0, 153, 0));
        pendaftaran_save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pendaftaran_saveMouseClicked(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("Lanjut");

        javax.swing.GroupLayout pendaftaran_saveLayout = new javax.swing.GroupLayout(pendaftaran_save);
        pendaftaran_save.setLayout(pendaftaran_saveLayout);
        pendaftaran_saveLayout.setHorizontalGroup(
            pendaftaran_saveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 171, Short.MAX_VALUE)
            .addGroup(pendaftaran_saveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pendaftaran_saveLayout.createSequentialGroup()
                    .addGap(0, 60, Short.MAX_VALUE)
                    .addComponent(jLabel51)
                    .addGap(0, 60, Short.MAX_VALUE)))
        );
        pendaftaran_saveLayout.setVerticalGroup(
            pendaftaran_saveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(pendaftaran_saveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pendaftaran_saveLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel51)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jLabel52.setForeground(new java.awt.Color(255, 0, 0));
        jLabel52.setText("*");

        jLabel53.setForeground(new java.awt.Color(255, 0, 0));
        jLabel53.setText("*");

        jLabel54.setForeground(new java.awt.Color(255, 0, 0));
        jLabel54.setText("*");

        jLabel55.setForeground(new java.awt.Color(255, 0, 0));
        jLabel55.setText("*");

        jLabel56.setForeground(new java.awt.Color(255, 0, 0));
        jLabel56.setText("*");

        simpan3.setBackground(new java.awt.Color(0, 153, 0));
        simpan3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                simpan3MouseClicked(evt);
            }
        });

        jLabel64.setBackground(new java.awt.Color(0, 153, 0));
        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 255, 255));
        jLabel64.setText("Simpan");

        javax.swing.GroupLayout simpan3Layout = new javax.swing.GroupLayout(simpan3);
        simpan3.setLayout(simpan3Layout);
        simpan3Layout.setHorizontalGroup(
            simpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
            .addGroup(simpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(simpan3Layout.createSequentialGroup()
                    .addGap(0, 60, Short.MAX_VALUE)
                    .addComponent(jLabel64)
                    .addGap(0, 59, Short.MAX_VALUE)))
        );
        simpan3Layout.setVerticalGroup(
            simpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 53, Short.MAX_VALUE)
            .addGroup(simpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(simpan3Layout.createSequentialGroup()
                    .addGap(0, 18, Short.MAX_VALUE)
                    .addComponent(jLabel64)
                    .addGap(0, 18, Short.MAX_VALUE)))
        );

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel65.setText("Posisi Yang Anda Minati");

        jLabel66.setForeground(new java.awt.Color(255, 0, 0));
        jLabel66.setText("*");

        cb_posisi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Administrasi", "Admin Reporting", "Admin Database", "Data Analyst", "Operasional", "IT Support", "IT Application" }));

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel67.setText("Pilih 3 Jawaban yang anda sukai");

        jLabel68.setForeground(new java.awt.Color(255, 0, 0));
        jLabel68.setText("*");

        pilihan1.add(rb1);
        rb1.setText("Mengambil Data");

        pilihan1.add(rb1_2);
        rb1_2.setText("Mengolah Data");

        pilihan2.add(rb2);
        rb2.setText("Kerja Lembur");

        pilihan2.add(rb2_2);
        rb2_2.setText("Kerja Cepat");

        pilihan3.add(rb3);
        rb3.setText("Administrasi");

        pilihan3.add(rb3_2);
        rb3_2.setText("Staff IT");

        txt_penjelasan.setColumns(20);
        txt_penjelasan.setRows(5);
        jScrollPane7.setViewportView(txt_penjelasan);

        jLabel69.setText("Beri Penjelasan atas Pilihan Anda");

        jLabel70.setForeground(new java.awt.Color(255, 0, 0));
        jLabel70.setText("*");

        jLabel72.setText("/");

        jLabel73.setText("/");

        jLabel74.setText("/");

        javax.swing.GroupLayout Form_tigaLayout = new javax.swing.GroupLayout(Form_tiga);
        Form_tiga.setLayout(Form_tigaLayout);
        Form_tigaLayout.setHorizontalGroup(
            Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_tigaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pendaftaran_save, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(289, 289, 289))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_tigaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_tigaLayout.createSequentialGroup()
                        .addGroup(Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44))
                        .addGap(207, 207, 207))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_tigaLayout.createSequentialGroup()
                        .addGroup(Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Form_tigaLayout.createSequentialGroup()
                                .addGroup(Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Form_tigaLayout.createSequentialGroup()
                                        .addComponent(jLabel45)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel52))
                                    .addGroup(Form_tigaLayout.createSequentialGroup()
                                        .addComponent(jLabel46)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel53))
                                    .addGroup(Form_tigaLayout.createSequentialGroup()
                                        .addComponent(jLabel47)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel54))
                                    .addGroup(Form_tigaLayout.createSequentialGroup()
                                        .addComponent(jLabel48)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel55))
                                    .addComponent(tanggal_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel49)
                                    .addComponent(tanggal_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(pendaftaran_jabatan)
                                .addComponent(pendaftaran_divisi)
                                .addComponent(pendaftaran_perusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Form_tigaLayout.createSequentialGroup()
                                .addComponent(jLabel50)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel56))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addGroup(Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Form_tigaLayout.createSequentialGroup()
                                .addComponent(jLabel65)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel66))
                            .addGroup(Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cb_posisi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Form_tigaLayout.createSequentialGroup()
                                    .addComponent(jLabel67)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel68))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Form_tigaLayout.createSequentialGroup()
                                    .addGroup(Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(rb1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(rb2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(rb3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(32, 32, 32)
                                    .addGroup(Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel72)
                                        .addComponent(jLabel73)
                                        .addComponent(jLabel74))
                                    .addGap(28, 28, 28)
                                    .addGroup(Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(rb1_2)
                                        .addComponent(rb2_2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(rb3_2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Form_tigaLayout.createSequentialGroup()
                                    .addComponent(jLabel69)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel70))
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(38, 38, 38))))
            .addGroup(Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_tigaLayout.createSequentialGroup()
                    .addContainerGap(340, Short.MAX_VALUE)
                    .addComponent(simpan3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(288, Short.MAX_VALUE)))
        );
        Form_tigaLayout.setVerticalGroup(
            Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Form_tigaLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Form_tigaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel66)
                            .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_posisi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel67)
                            .addComponent(jLabel68))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rb1)
                            .addComponent(rb1_2)
                            .addComponent(jLabel72))
                        .addGap(32, 32, 32)
                        .addGroup(Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rb2)
                            .addComponent(rb2_2)
                            .addComponent(jLabel73))
                        .addGap(32, 32, 32)
                        .addGroup(Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rb3)
                            .addComponent(rb3_2)
                            .addComponent(jLabel74))
                        .addGap(18, 18, 18)
                        .addGroup(Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel69)
                            .addComponent(jLabel70))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Form_tigaLayout.createSequentialGroup()
                        .addGroup(Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel45)
                            .addComponent(jLabel52))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pendaftaran_perusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addGroup(Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53)
                            .addComponent(jLabel46))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pendaftaran_divisi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(jLabel54))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pendaftaran_jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(jLabel49)
                            .addComponent(jLabel55))
                        .addGap(5, 5, 5)
                        .addGroup(Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tanggal_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tanggal_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel50)
                            .addComponent(jLabel56))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator5))
                .addGap(43, 43, 43)
                .addComponent(pendaftaran_save, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(Form_tigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_tigaLayout.createSequentialGroup()
                    .addContainerGap(439, Short.MAX_VALUE)
                    .addComponent(simpan3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        Form.add(Form_tiga, "card4");

        form_empat.setBackground(new java.awt.Color(255, 255, 255));

        tbl_pengalaman.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama Perusahaan", "Divisi", "Jabatan", "Tgl-Masuk", "Tgl-Keluar", "Keterangan"
            }
        ));
        tbl_pengalaman.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_pengalamanMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_pengalaman);

        tbl_penduduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No KTP", "Nama", "Provinsi", "Kota", "Alamat", "Tempat Tinggal", "Status", "Telp Darurat"
            }
        ));
        tbl_penduduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_pendudukMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_penduduk);

        tbl_pelamar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama", "Email", "Telp", "Pendidikan", "Institusi", "Jurusan", "Status"
            }
        ));
        tbl_pelamar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_pelamarMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbl_pelamar);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Data Pelamar dan Pendidikan");

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel59.setText("Data Kependudukan Pelamar");

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel60.setText("Pengalaman Kerja Terakhir");

        check_simpan.setText("Bertanggung Jawab Akan Kebenaran Data Yang Diberikan...");

        simpan_bt.setBackground(new java.awt.Color(0, 153, 0));
        simpan_bt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                simpan_btMouseClicked(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setText("Simpan");

        javax.swing.GroupLayout simpan_btLayout = new javax.swing.GroupLayout(simpan_bt);
        simpan_bt.setLayout(simpan_btLayout);
        simpan_btLayout.setHorizontalGroup(
            simpan_btLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 163, Short.MAX_VALUE)
            .addGroup(simpan_btLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(simpan_btLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel61)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        simpan_btLayout.setVerticalGroup(
            simpan_btLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(simpan_btLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(simpan_btLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel61)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout form_empatLayout = new javax.swing.GroupLayout(form_empat);
        form_empat.setLayout(form_empatLayout);
        form_empatLayout.setHorizontalGroup(
            form_empatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jScrollPane5)
            .addComponent(jScrollPane4)
            .addGroup(form_empatLayout.createSequentialGroup()
                .addGroup(form_empatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(form_empatLayout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(jLabel3))
                    .addGroup(form_empatLayout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(simpan_bt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(form_empatLayout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addComponent(check_simpan)))
                .addGap(168, 272, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, form_empatLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(form_empatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, form_empatLayout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addGap(295, 295, 295))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, form_empatLayout.createSequentialGroup()
                        .addComponent(jLabel60)
                        .addGap(305, 305, 305))))
        );
        form_empatLayout.setVerticalGroup(
            form_empatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form_empatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel59)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel60)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(check_simpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(simpan_bt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Form.add(form_empat, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Form, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Form, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(Menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    protected void idauto() {
         try {
            String query = "select MAX(RIGHT(id,8)) AS NO  from pelamar order by id desc";
            ps = Models.Config.Conn().prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                if (rs.first() == false) {
                    pendaftaran_id.setText("IDS10000001");
                } else {
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int Nomor = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 8 - Nomor; j++) {
                        no = "0" + no;
                    }
                    pendaftaran_id.setText("IDS" + no);
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }
    
    protected void kosong_pendaftaran1() {
        pendaftaran_nama.setText("");
        pendaftaran_email.setText("");
        pendaftaran_telp.setText("");
        pendaftaran_institusi.setText("");
        pendaftaran_jurusan.setText("");
        tgl_lahir.setDateFormatString("");
        pendaftaran_alamat_ins.setText("");
        pendaftaran_pendidikan.setSelectedItem("Pilih");
        pendaftaran_status.setSelectedItem("Pilih");
    }
    
    
    protected void kosong_pendaftaran2() {
        pendaftaran_no_ktp.setText("");
        pendaftaran_nama_ktp.setText("");
        pendaftaran_kawin.setSelectedItem("Pilih");
        pendaftaran_tmpt_tinggal.setSelectedItem("Pilih");
        pendaftaran_telp1.setText("");
        pendaftaran_telp2.setText("");
        pendaftaran_provinsi.setSelectedItem("Pilih");
        pendaftaran_kota.setSelectedItem("Pilih");
        pendaftaran_alamat_ktp.setText("");
    }
    
    
    
    protected void kosong_pendaftaran3() {
        pendaftaran_perusahaan.setText("");
        pendaftaran_divisi.setText("");
        pendaftaran_jabatan.setText("");
        pendaftaran_keterangan.setText("");
    }
    
    protected void pendaftaran1(){
        String sql = "INSERT INTO `pelamar`(`id`, `nama`, `email`, `telp`, `tgl_lahir`, `kelamin`, `pendidikan`, `kampus`, `jurusan`, `status`, `alamat`, `tanggal`, `status_lamaran`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String JenisKel = null;
        String status =  "";
        String date  = ((JTextField)tgl_lahir.getDateEditor().getUiComponent()).getText();
        if(rlaki.isSelected()){
            JenisKel = "Laki-laki";
        }
        else{
            JenisKel = "Perempuan";
        }
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, pendaftaran_id.getText());
            stat.setString(2, pendaftaran_nama.getText());           
            stat.setString(3, pendaftaran_email.getText());
            stat.setString(4, pendaftaran_telp.getText());
            stat.setString(5, date);
            stat.setString(6, JenisKel);
            stat.setString(7, pendaftaran_pendidikan.getSelectedItem().toString());
            stat.setString(8, pendaftaran_institusi.getText());
            stat.setString(9, pendaftaran_jurusan.getText());
            stat.setString(10, pendaftaran_status.getSelectedItem().toString());
            stat.setString(11, pendaftaran_alamat_ins.getText());
            stat.setString(12, txt_tanggal.getText());
            stat.setString(13, status);

            stat.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Data Tersimpan");
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Berhasil....", HEIGHT, sucess);
            form_empat.setVisible(false);
            Awal aw = new Awal();
            aw.setVisible(true);
            dispose();
           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Tersimpan" + e);
        }
        
    }
    
    private void Pendaftaran2(){
        String sql = "INSERT INTO `kependudukan`(`id_pelamar`, `no_ktp`, `nama_ktp`, `warganegara`, `provinsi`, `kota`, `alamat_ktp`, `status_rumah`, `status_kawin`, `telp1`, `telp2`, `status`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        String warganegara = null;
        String status = "";
        if(wni.isSelected()){
            warganegara = "WNI";
        }
        else{
            warganegara = "WNA";
        }
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, pendaftaran_id.getText());
            stat.setString(2, pendaftaran_no_ktp.getText());           
            stat.setString(3, pendaftaran_nama_ktp.getText());
            stat.setString(4, warganegara);
            stat.setString(5, pendaftaran_provinsi.getSelectedItem().toString());
            stat.setString(6, pendaftaran_kota.getSelectedItem().toString());
            stat.setString(7, pendaftaran_alamat_ktp.getText());
            stat.setString(8, pendaftaran_tmpt_tinggal.getSelectedItem().toString());
            stat.setString(9, pendaftaran_kawin.getSelectedItem().toString());
            stat.setString(10, pendaftaran_telp1.getText());
            stat.setString(11, pendaftaran_telp2.getText());
            stat.setString(12, status);

            stat.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Data Tersimpan");
           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Tersimpan" + e);
        }
    }
    
    private void Pendaftaran3(){
        String sql = "INSERT INTO `pengalaman`(`id_pelamar`, `nama_perusahaan`, `divisi`, `jabatan`, `tanggal_masuk`, `tanggal_keluar`, `keterangan`, `status`) VALUES (?,?,?,?,?,?,?,?)";
        String d1  = ((JTextField)tanggal_masuk.getDateEditor().getUiComponent()).getText();
        String d2  = ((JTextField)tanggal_keluar.getDateEditor().getUiComponent()).getText();
        String status = "";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, pendaftaran_id.getText());
            stat.setString(2, pendaftaran_perusahaan.getText());           
            stat.setString(3, pendaftaran_divisi.getText());           
            stat.setString(4, pendaftaran_jabatan.getText());           
            stat.setString(5, d1);           
            stat.setString(6, d2);          
            stat.setString(7, pendaftaran_keterangan.getText());           
            stat.setString(8, status);

            stat.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Data Tersimpan");
//            JOptionPane.showMessageDialog(null, "Berhasil!, Data Berhasil Disimpan", "Alert Message!", HEIGHT, sucess);
            
            kosong_pendaftaran1();
            kosong_pendaftaran2();
            kosong_pendaftaran3();
           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Tersimpan" + e);
        }
    }
    
    private void Pendaftaran4(){
        String sql = "INSERT INTO `kriteria`(`id_pelamar`, `posisi`, `pilihan1`, `pilihan2`, `pilihan3`, `keterangan`,`status`) VALUES (?,?,?,?,?,?,?)";
        String pilih1 = null;
        String status = "";
        if(rb1.isSelected()){
            pilih1 = "Mengambil Data";
        }
        else{
            pilih1 = "Mengolah Data";
        }
        
        String pilih2 = null;
        if(rb2.isSelected()){
            pilih2 = "Kerja Lembur";
        }
        else{
            pilih2 = "Kerja Cepat";
        }
        
        String pilih3 = null;
        if(rb3.isSelected()){
            pilih3 = "Administrasi";
        }
        else{
            pilih3 = "Staff IT";
        }
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, pendaftaran_id.getText());
            stat.setString(2, cb_posisi.getSelectedItem().toString());           
            stat.setString(3, pilih1);           
            stat.setString(4, pilih2);           
            stat.setString(5, pilih3);           
            stat.setString(6, txt_penjelasan.getText());           
            stat.setString(7, status);

            stat.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Data Tersimpan");
//            JOptionPane.showMessageDialog(null, "Berhasil!, Data Berhasil Disimpan", "Alert Message!", HEIGHT, sucess);
           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Tersimpan" + e);
        }
    }
    
    private void pendaftaran_lanjut1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pendaftaran_lanjut1MouseClicked
        if(pendaftaran_id.getText().isEmpty() || pendaftaran_nama.getText().isEmpty() || pendaftaran_email.getText().isEmpty()
                || pendaftaran_telp.getText().isEmpty() || pendaftaran_pendidikan.getSelectedItem().equals("Pilih")
                || pendaftaran_alamat_ins.getText().isEmpty() || pendaftaran_jurusan.getText().isEmpty()
                || pendaftaran_alamat_ins.getText().isEmpty() || pendaftaran_status.getSelectedItem().equals("Pilih")){
            JOptionPane.showMessageDialog(null, "Gagal!, Harap Isi Dengan Lengkap!", "Alert Message!", HEIGHT, warning);
        }
        else{
            DefaultTableModel modeldata1 = (DefaultTableModel)tbl_pelamar.getModel();
            modeldata1.addRow(new Object[]{pendaftaran_nama.getText(), pendaftaran_email.getText(), pendaftaran_telp.getText(),
            pendaftaran_pendidikan.getSelectedItem().toString(), pendaftaran_institusi.getText(),
            pendaftaran_jurusan.getText(), pendaftaran_status.getSelectedItem().toString()});
            Form_satu.setVisible(false);
            Form_dua.setVisible(true);
            icon1.setIcon(img1);
        }
    }//GEN-LAST:event_pendaftaran_lanjut1MouseClicked

    private void pendaftaran_lanjut2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pendaftaran_lanjut2MouseClicked
        if(pendaftaran_no_ktp.getText().isEmpty() || pendaftaran_nama_ktp.getText().isEmpty() || pendaftaran_telp1.getText().isEmpty()
                || pendaftaran_telp2.getText().isEmpty() || pendaftaran_kawin.getSelectedItem().equals("Pilih")
                || pendaftaran_tmpt_tinggal.getSelectedItem().equals("Pilih") || pendaftaran_provinsi.getSelectedItem().equals("Pilih")
                || pendaftaran_kota.getSelectedItem().equals("Pilih") || pendaftaran_alamat_ktp.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Gagal!, Harap Isi Dengan Lengkap!", "Alert Message!", HEIGHT, warning);
        }
        else{
            DefaultTableModel modeldata2 = (DefaultTableModel)tbl_penduduk.getModel();
            modeldata2.addRow(new Object[]{pendaftaran_no_ktp.getText(), pendaftaran_nama_ktp.getText(),
                pendaftaran_provinsi.getSelectedItem().toString(), pendaftaran_kota.getSelectedItem().toString(),
                pendaftaran_alamat_ktp.getText(), pendaftaran_tmpt_tinggal.getSelectedItem().toString(),
                pendaftaran_kawin.getSelectedItem().toString(), pendaftaran_telp1.getText()});
            Form_dua.setVisible(false);
            Form_tiga.setVisible(true);
            icon2.setIcon(img1);
        }
    }//GEN-LAST:event_pendaftaran_lanjut2MouseClicked

    private void tanggal_masukPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tanggal_masukPropertyChange
        try{
            if(tanggal_masuk.getDate() != null){
                String patern = "dd MMMM yyyy";
                SimpleDateFormat format = new SimpleDateFormat(patern);
                dateChooser = String.valueOf(format.format(tanggal_masuk.getDate()));
            }
        }
        catch (Exception e){
            
        }
    }//GEN-LAST:event_tanggal_masukPropertyChange

    private void tanggal_keluarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tanggal_keluarPropertyChange
        try{
            if(tanggal_keluar.getDate() != null){
                String patern = "dd MMMM yyyy";
                SimpleDateFormat format = new SimpleDateFormat(patern);
                dateChooser2 = String.valueOf(format.format(tanggal_keluar.getDate()));
            }
        }
        catch (Exception e){
            
        }
    }//GEN-LAST:event_tanggal_keluarPropertyChange

    private void pendaftaran_provinsiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_pendaftaran_provinsiItemStateChanged
//       String itemSelect = ArrayData.arr[provinsi];
           Kota();
    }//GEN-LAST:event_pendaftaran_provinsiItemStateChanged

    private void pendaftaran_provinsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pendaftaran_provinsiActionPerformed

    }//GEN-LAST:event_pendaftaran_provinsiActionPerformed

    private void pendaftaran_saveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pendaftaran_saveMouseClicked
        
        if(pendaftaran_perusahaan.getText().isEmpty() || pendaftaran_divisi.getText().isEmpty() ||
                pendaftaran_jabatan.getText().isEmpty() || pendaftaran_keterangan.getText().isEmpty() ||
                cb_posisi.getSelectedItem().equals("Pilih") || txt_penjelasan.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Gagal!, Harap Isi Dengan Lengkap!", "Peringatan!", HEIGHT, warning);
        }
        else
        {
            String d1  = ((JTextField)tanggal_masuk.getDateEditor().getUiComponent()).getText();
            String d2  = ((JTextField)tanggal_keluar.getDateEditor().getUiComponent()).getText();
            DefaultTableModel modeldata3 = (DefaultTableModel)tbl_pengalaman.getModel();
            modeldata3.addRow(new Object[]{pendaftaran_perusahaan.getText(), pendaftaran_divisi.getText(),
                pendaftaran_jabatan.getText(), d1, d2, pendaftaran_keterangan.getText()});
            Form_tiga.setVisible(false);
            form_empat.setVisible(true);
            icon3.setIcon(img1);
            JOptionPane.showMessageDialog(null, "Pilih Data yang ingin di Ubah Pada Table...", "Perhatian!", HEIGHT, warning);

//            pendaftaran1();
            
            
//            JOptionPane.showMessageDialog(null, "Berhasil!, Harap Isi Dengan Lengkap!", "Alert Message!", HEIGHT, sucess);

        }
    }//GEN-LAST:event_pendaftaran_saveMouseClicked

    private void tbl_pelamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_pelamarMouseClicked
        Form_satu.setVisible(true);
        form_empat.setVisible(false);
        DefaultTableModel model1 = (DefaultTableModel)tbl_pelamar.getModel();
        model1.removeRow(tbl_pelamar.getSelectedRow());
        simpan1.setVisible(true);
        pendaftaran_lanjut1.setVisible(false);
    }//GEN-LAST:event_tbl_pelamarMouseClicked

    private void simpan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_simpan1MouseClicked
        if(pendaftaran_id.getText().isEmpty() || pendaftaran_nama.getText().isEmpty() || pendaftaran_email.getText().isEmpty()
                || pendaftaran_telp.getText().isEmpty() || pendaftaran_pendidikan.getSelectedItem().equals("Pilih")
                || pendaftaran_alamat_ins.getText().isEmpty() || pendaftaran_jurusan.getText().isEmpty()
                || pendaftaran_alamat_ins.getText().isEmpty() || pendaftaran_status.getSelectedItem().equals("Pilih")){
            JOptionPane.showMessageDialog(null, "Gagal!, Harap Isi Dengan Lengkap!", "Alert Message!", HEIGHT, warning);
        }
        else{
            DefaultTableModel modeldata1 = (DefaultTableModel)tbl_pelamar.getModel();
            modeldata1.addRow(new Object[]{pendaftaran_nama.getText(), pendaftaran_email.getText(), pendaftaran_telp.getText(),
            pendaftaran_pendidikan.getSelectedItem().toString(), pendaftaran_institusi.getText(),
            pendaftaran_jurusan.getText(), pendaftaran_status.getSelectedItem().toString()});
            Form_satu.setVisible(false);
            form_empat.setVisible(true);
        }
    }//GEN-LAST:event_simpan1MouseClicked

    private void tbl_pendudukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_pendudukMouseClicked
        Form_dua.setVisible(true);
        form_empat.setVisible(false);
        DefaultTableModel model1 = (DefaultTableModel)tbl_penduduk.getModel();
        model1.removeRow(tbl_penduduk.getSelectedRow());
        simpan2.setVisible(true);
        pendaftaran_lanjut2.setVisible(false);
    }//GEN-LAST:event_tbl_pendudukMouseClicked

    private void simpan2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_simpan2MouseClicked
        if(pendaftaran_no_ktp.getText().isEmpty() || pendaftaran_nama_ktp.getText().isEmpty() || pendaftaran_telp1.getText().isEmpty()
                || pendaftaran_telp2.getText().isEmpty() || pendaftaran_kawin.getSelectedItem().equals("Pilih")
                || pendaftaran_tmpt_tinggal.getSelectedItem().equals("Pilih") || pendaftaran_provinsi.getSelectedItem().equals("Pilih")
                || pendaftaran_kota.getSelectedItem().equals("Pilih") || pendaftaran_alamat_ktp.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Gagal!, Harap Isi Dengan Lengkap!", "Alert Message!", HEIGHT, warning);
        }
        else{
            DefaultTableModel modeldata2 = (DefaultTableModel)tbl_penduduk.getModel();
            modeldata2.addRow(new Object[]{pendaftaran_no_ktp.getText(), pendaftaran_nama_ktp.getText(),
                pendaftaran_provinsi.getSelectedItem().toString(), pendaftaran_kota.getSelectedItem().toString(),
                pendaftaran_alamat_ktp.getText(), pendaftaran_tmpt_tinggal.getSelectedItem().toString(),
                pendaftaran_kawin.getSelectedItem().toString(), pendaftaran_telp1.getText()});
            Form_dua.setVisible(false);
            form_empat.setVisible(true);
        }
    }//GEN-LAST:event_simpan2MouseClicked

    private void simpan3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_simpan3MouseClicked
        if(pendaftaran_perusahaan.getText().isEmpty() || pendaftaran_divisi.getText().isEmpty() ||
                pendaftaran_jabatan.getText().isEmpty() || pendaftaran_keterangan.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Gagal!, Harap Isi Dengan Lengkap!", "Peringatan!", HEIGHT, warning);
        }
        else
        {
            String d1  = ((JTextField)tanggal_masuk.getDateEditor().getUiComponent()).getText();
            String d2  = ((JTextField)tanggal_keluar.getDateEditor().getUiComponent()).getText();
            DefaultTableModel modeldata3 = (DefaultTableModel)tbl_pengalaman.getModel();
            modeldata3.addRow(new Object[]{pendaftaran_perusahaan.getText(), pendaftaran_divisi.getText(),
                pendaftaran_jabatan.getText(), d1, d2, pendaftaran_keterangan.getText()});
            Form_tiga.setVisible(false);
            form_empat.setVisible(true);
//            pendaftaran1();
            
            
//            JOptionPane.showMessageDialog(null, "Berhasil!, Harap Isi Dengan Lengkap!", "Alert Message!", HEIGHT, sucess);

        }
    }//GEN-LAST:event_simpan3MouseClicked

    private void tbl_pengalamanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_pengalamanMouseClicked
        Form_tiga.setVisible(true);
        form_empat.setVisible(false);
        DefaultTableModel model3 = (DefaultTableModel)tbl_pengalaman.getModel();
        model3.removeRow(tbl_pengalaman.getSelectedRow());
        simpan3.setVisible(true);
        pendaftaran_save.setVisible(false);
    }//GEN-LAST:event_tbl_pengalamanMouseClicked

    private void simpan_btMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_simpan_btMouseClicked
        if(check_simpan.isSelected()){
            pendaftaran1();
            Pendaftaran2();
            Pendaftaran3();
            Pendaftaran4();
            idauto();
        }
        else{
            JOptionPane.showMessageDialog(null, "Gagal!, Ceklist Perstujuan Data!", "Peringatan!", HEIGHT, warning);
        }
    }//GEN-LAST:event_simpan_btMouseClicked

    private void tgl_lahirPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tgl_lahirPropertyChange
        try{
            if(tgl_lahir.getDate() != null){
                String patern3 = "dd-MMM-yyyy";
                SimpleDateFormat format3 = new SimpleDateFormat(patern3);
                dateChooser3 = String.valueOf(format3.format(tgl_lahir.getDate()));
            }
        }
        catch (Exception e){
            
        }
    }//GEN-LAST:event_tgl_lahirPropertyChange

    private void pendaftaran_institusiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pendaftaran_institusiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pendaftaran_institusiActionPerformed

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
            java.util.logging.Logger.getLogger(Pendaftaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pendaftaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pendaftaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pendaftaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pendaftaran().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Form;
    private javax.swing.JPanel Form_dua;
    private javax.swing.JPanel Form_satu;
    private javax.swing.JPanel Form_tiga;
    private javax.swing.JPanel Menu;
    private javax.swing.JComboBox<String> cb_posisi;
    private javax.swing.JCheckBox check_simpan;
    private javax.swing.JPanel form_empat;
    private javax.swing.JLabel icon1;
    private javax.swing.JLabel icon2;
    private javax.swing.JLabel icon3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel32;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.ButtonGroup jenis_kelamin;
    private javax.swing.JTextArea pendaftaran_alamat_ins;
    private javax.swing.JTextArea pendaftaran_alamat_ktp;
    private javax.swing.JTextField pendaftaran_divisi;
    private javax.swing.JTextField pendaftaran_email;
    private javax.swing.JTextField pendaftaran_id;
    private javax.swing.JTextField pendaftaran_institusi;
    private javax.swing.JTextField pendaftaran_jabatan;
    private javax.swing.JTextField pendaftaran_jurusan;
    private javax.swing.JComboBox<String> pendaftaran_kawin;
    private javax.swing.JTextArea pendaftaran_keterangan;
    private javax.swing.JComboBox<String> pendaftaran_kota;
    private javax.swing.JPanel pendaftaran_lanjut1;
    private javax.swing.JPanel pendaftaran_lanjut2;
    private javax.swing.JTextField pendaftaran_nama;
    private javax.swing.JTextField pendaftaran_nama_ktp;
    private javax.swing.JTextField pendaftaran_no_ktp;
    private javax.swing.JComboBox<String> pendaftaran_pendidikan;
    private javax.swing.JTextField pendaftaran_perusahaan;
    private javax.swing.JComboBox<String> pendaftaran_provinsi;
    private javax.swing.JPanel pendaftaran_save;
    private javax.swing.JComboBox<String> pendaftaran_status;
    private javax.swing.JTextField pendaftaran_telp;
    private javax.swing.JTextField pendaftaran_telp1;
    private javax.swing.JTextField pendaftaran_telp2;
    private javax.swing.JComboBox<String> pendaftaran_tmpt_tinggal;
    private javax.swing.ButtonGroup pilihan1;
    private javax.swing.ButtonGroup pilihan2;
    private javax.swing.ButtonGroup pilihan3;
    private javax.swing.JRadioButton rb1;
    private javax.swing.JRadioButton rb1_2;
    private javax.swing.JRadioButton rb2;
    private javax.swing.JRadioButton rb2_2;
    private javax.swing.JRadioButton rb3;
    private javax.swing.JRadioButton rb3_2;
    private javax.swing.JRadioButton rcw;
    private javax.swing.JRadioButton rlaki;
    private javax.swing.JPanel simpan1;
    private javax.swing.JPanel simpan2;
    private javax.swing.JPanel simpan3;
    private javax.swing.JPanel simpan_bt;
    private com.toedter.calendar.JDateChooser tanggal_keluar;
    private com.toedter.calendar.JDateChooser tanggal_masuk;
    private javax.swing.JTable tbl_pelamar;
    private javax.swing.JTable tbl_penduduk;
    private javax.swing.JTable tbl_pengalaman;
    private com.toedter.calendar.JDateChooser tgl_lahir;
    private javax.swing.JTextArea txt_penjelasan;
    private javax.swing.JLabel txt_tanggal;
    private javax.swing.ButtonGroup warganegara;
    private javax.swing.JRadioButton wna;
    private javax.swing.JRadioButton wni;
    // End of variables declaration//GEN-END:variables
}
