/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pjppenjualan;

//import static java.awt.SystemColor.text;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.sql.*;
//import java.text.MessageFormat;
//import java.util.Calendar;
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.JComboBox;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class frmTransaksi extends javax.swing.JFrame {

    Connection Con;
    ResultSet RsBrg;
    ResultSet RsKons;
    Statement stm;
    double total = 0;
    String tanggal;
    Boolean edit=false;
    PreparedStatement pstat;
    String sKd_Kons;
    String sKd_Brg;
    
    DefaultTableModel tableModel = new DefaultTableModel(
            new Object [][]{},
            new String []{
            "Kode Barang", "Nama Barang", "Harga Barang", "Jumlah", "Total"
            }
    );
    String idBrg;
    String namaBrg;
    String hargaBrg;
    
    /**
     * Creates new form frmTransaksi
     */
    public frmTransaksi() {
        initComponents();
        open_db();
        inisialisasi_table();
        aktif(false);
        setTombol(true);
        Spinner_tgl.setEditor(new JSpinner.DateEditor(Spinner_tgl, "yyyy/MM/dd"));
//        txtTgl.setEditor(new JSpinner.DateEditor(txtTgl, "yyyy/MM/dd"));
    }

    private void setField(){
        int row = hasil_table.getSelectedRow();
        combo_kd_brg.setSelectedItem((String)hasil_table.getValueAt(row, 0));
        txt_nm_brg.setText((String)hasil_table.getValueAt(row, 1));
        String harga = Double.toString((Double)hasil_table.getValueAt(row, 2));
        txt_harga.setText(harga);
        String jumlah = Double.toString((Double)hasil_table.getValueAt(row, 3));
        txt_jumlah.setText(jumlah);
        String total = Integer.toString((Integer)hasil_table.getValueAt(row, 4));
        txt_total.setText(total);
    }
    
    private void hitung_jual(){
        double x_total, x_harga;
        int x_jumlah;
        x_harga = Double.parseDouble(txt_harga.getText());
        x_jumlah = Integer.parseInt(txt_jumlah.getText());
        x_total = x_harga * x_jumlah;
        String x_tl = Double.toString(x_total);
        txt_ttl.setText(x_tl);
        total = total + x_total;
        txt_ttl.setText(Double.toString(total));
    }
    
    private void open_db(){
    try{
            KoneksiMysql kon = new KoneksiMysql("localhost","root","","penjualan");
            Con = kon.getConnection();
                //System.out.println("Berhasil ");
                }catch (Exception e) {
                    System.out.println("Error : "+e);
                }
    }
    
    
    private void baca_konsumen(){
    try{
        stm=Con.createStatement();
        pstat = Con.prepareStatement("select kd_konsumen, nm_konsumen from konsumen", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = pstat.executeQuery();
//        ResultSet rs=stm.executeQuery("select kd_konsumen, nm_konsumen from konsumen");
        rs.beforeFirst();
        while(rs.next()){
        combo_kd_kons.addItem(rs.getString(1).trim());
        }
        rs.close();    
    }
    catch(SQLException e){
        System.out.println("Error : "+e);
    }
    }
    
    public void inisialisasi_table(){
    hasil_table.setModel(tableModel);
    }
    
    private void baca_barang(){
    try{
        stm=Con.createStatement();
        pstat = Con.prepareStatement("select * from barang", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = pstat.executeQuery();
//        ResultSet rs=stm.executeQuery("select * from barang");
        rs.beforeFirst();
        while(rs.next()){
        combo_kd_brg.addItem(rs.getString(1).trim());
        }
        rs.close();
    }
    catch(SQLException e){
        System.out.println("Error :" +e);
    }
    }
    
    private void detail_barang(String x_kode){
    try{
        stm=Con.createStatement();
        pstat = Con.prepareStatement("select * from barang where kd_barang='"+x_kode+"'", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = pstat.executeQuery();
//        ResultSet rs=stm.executeQuery("select * from barang where kd_barang='"+x_kode+"'");
        rs.beforeFirst();
        while(rs.next()){
            txt_nm_brg.setText(rs.getString(2).trim());
            txt_harga.setText(Double.toString((Double)rs.getDouble(4)));
        }
        rs.close();
    }
    catch(SQLException e){
        System.out.println("Error : "+e);
    }
    }
    
    private void detail_konsumen(String x_kode){
    try{
        stm=Con.createStatement();
        pstat = Con.prepareStatement("select * from konsumen where kd_konsumen='"+x_kode+"'", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = pstat.executeQuery();
//        ResultSet rs=stm.executeQuery("select * from konsumen where kd_konsumen='"+x_kode+"'");
        rs.beforeFirst();
        while(rs.next()){
            txt_nm_kons.setText(rs.getString(2).trim());
        }
        rs.close();
    }
    catch(SQLException e){
        System.out.println("Errorr : "+e);
    }
    }
    
    //pengkosongan isian
    private void kosong(){
    txt_no_jual.setText("");
    txt_nm_brg.setText("");
    txt_harga.setText("");
    txt_ttl.setText("");
    
    text.setText("");//text= tetx_area
    DefaultTableModel dataModel = (DefaultTableModel) hasil_table.getModel();
    if (hasil_table.getRowCount() > 0) {
    for (int i = hasil_table.getRowCount() - 1; i > -1; i--) {
    dataModel.removeRow(i);
    }
    }
    
    }
    
    //kosongkan detail jual
    private void kosong_detail(){
    txt_nm_brg.setText("");
    txt_harga.setText("");
    txt_jumlah.setText("");
    txt_ttl.setText("");
    }
    
    //aktif on/off isian
    private void aktif(boolean x){
    combo_kd_kons.setEnabled(x);  
    combo_kd_brg.setEnabled(x);
    combo_kd_kons.removeAllItems();//tambahan sndiri
    combo_kd_brg.removeAllItems();
    txtTgl.setEnabled(x);
    txt_jumlah.setEditable(x);
    }
    
    private void setTombol(boolean t){
    btn_tambah.setEnabled(t);
    btn_hpus_item.setEnabled(!t);//(t)
    btn_simpan.setEnabled(!t);
    btn_batal.setEnabled(!t);
    btn_keluar.setEnabled(t);
    }
    
    private void nomor_jual(){
    try{
        stm=Con.createStatement();
        pstat = Con.prepareStatement("select no_jual from jual ORDER BY no_jual DESC LIMIT 1", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = pstat.executeQuery();
//        ResultSet rs=stm.executeQuery("select no_jual from jual");
        int brs = 0;
        while(rs.next()){
        brs=rs.getRow();
        }
        if(brs==0)
            txt_no_jual.setText("1");
        else{
           rs.beforeFirst();
           while(rs.next()){
               int no=rs.getInt("no_jual")+1;
               txt_no_jual.setText(Integer.toString(no));
//           int nom=brs+1;
//           txt_no_jual.setText(Integer.toString(nom));
           }
           
        }
        rs.close();
    }catch(SQLException e){
        System.out.println("Error : "+e);
    }
        
    }
    
    private void simpan_di_table(){
    try{
    String t_kode = combo_kd_brg.getSelectedItem().toString();
    String t_nama = txt_nm_brg.getText();
    
    double harga = Double.parseDouble(txt_harga.getText());
    int jumlah = Integer.parseInt(txt_jumlah.getText());
    double total = Double.parseDouble(txt_total.getText());
    tableModel.addRow(new Object[]{t_kode, t_nama, harga, jumlah, total});
    inisialisasi_table();
    
    }catch(Exception e){
        System.out.println("Error : "+e);
    }
    
    }
    
    private void simpan_transaksi(){
    try{
        String x_nojual=txt_no_jual.getText();
        format_tanggal();
        String x_kode=combo_kd_kons.getSelectedItem().toString();
        String msql="insert into jual values('"+x_nojual+"','"+x_kode+"','"+tanggal+"')";
        stm.executeUpdate(msql);
        
        for(int i=0;i<hasil_table.getRowCount();i++)
        {
        String xkd=(String)hasil_table.getValueAt(i,0);
        double xhrg=(Double)hasil_table.getValueAt(i,2);
        int xjml=(Integer)hasil_table.getValueAt(i,3);
        String zsql="insert into djual values('"+x_nojual+"','"+xkd+"',"+xhrg+","+xjml+")";
        stm.executeUpdate(zsql);
        
        //update stok
        String ysql="update barang set stok=stok-"+xjml+" where kd_barang='"+xkd+"'";
        stm.executeUpdate(ysql);
        
        }
    }catch(SQLException e){
        System.out.println("Error : "+e);
    }
    
    }
    
    private void format_tanggal()
    {
    String DATE_FORMAT = "yyyy-MM-dd";
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
    Calendar c1 = Calendar.getInstance();
    int year=c1.get(Calendar.YEAR);
    int month=c1.get(Calendar.MONTH)+1;
    int day=c1.get(Calendar.DAY_OF_MONTH);
    tanggal=Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(day);
    }
    
    private class PrintingTask extends SwingWorker<Object, Object> {
    private final MessageFormat headerFormat;
    private final MessageFormat footerFormat;
    private final boolean interactive;
    private volatile boolean complete = false;
    private volatile String message;
//    private final TextArea teks;
    
    public PrintingTask( MessageFormat header, MessageFormat footer, boolean interactive) {
//    this.teks = teks;
    this.headerFormat = header;
    this.footerFormat = footer;
    this.interactive = interactive;
    }
    @Override
    protected Object doInBackground() {
    try {
        
    complete = text.print(headerFormat, footerFormat, true, null, null, interactive);
    message = "Printing " + (complete ? "complete" : "canceled");
    } catch (PrinterException ex) {
    message = "Sorry, a printer error occurred";
    } catch (SecurityException ex) {
    message = "Sorry, cannot access the printer due to security reasons";
    }
    return null;
    }

    @Override
    protected void done() {
        System.out.println(message);
    //message(!complete, message);
    }
    }
    
//    private MessageFormat createFormat(String source) {
//        String text = source;
//        if (text != null && text.length() > 0) {
//            try {
//                return new MessageFormat(text);
//            } catch (IllegalArgumentException e) {
//                error("Sorry, this format is invalid.");
//            }
//        }
//        return null;
//    }
//    
//    private void message(boolean error, String msg) {
//        int type = (error ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE);
//        JOptionPane.showMessageDialog(this, msg, "Printing", type);
//    }
//
//    private void error(String msg) {
//        message(true, msg);
//    }
    
    
    public void itemTerpilih(){
    frmCariBarang fDB = new frmCariBarang();
    fDB.fAB = this;
    txtId.setText(idBrg);
    txt_nm_brg.setText(namaBrg);
    txt_harga.setText(hargaBrg);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooserDialog1 = new datechooser.beans.DateChooserDialog();
        dateChooserDialog2 = new datechooser.beans.DateChooserDialog();
        no_jual = new javax.swing.JLabel();
        tgl_jual = new javax.swing.JLabel();
        kd_kons = new javax.swing.JLabel();
        nm_kons = new javax.swing.JLabel();
        combo_kd_kons = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        hasil_table = new javax.swing.JTable();
        txt_nm_brg = new javax.swing.JTextField();
        txt_harga = new javax.swing.JTextField();
        txt_ttl = new javax.swing.JTextField();
        btn_hpus_item = new javax.swing.JButton();
        combo_kd_brg = new javax.swing.JComboBox<>();
        ttl = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        btn_tambah = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        btn_cetak = new javax.swing.JButton();
        btn_keluar = new javax.swing.JButton();
        txt_no_jual = new javax.swing.JTextField();
        txt_jumlah = new javax.swing.JTextField();
        btn_pilih_brg = new javax.swing.JButton();
        nm_brg = new javax.swing.JTextField();
        Bayar = new javax.swing.JLabel();
        Kembalian = new javax.swing.JLabel();
        txt_bayar = new javax.swing.JTextField();
        txt_kembalian = new javax.swing.JTextField();
        txt_nm_kons = new javax.swing.JTextField();
        kd_brg = new javax.swing.JLabel();
        nm_barang = new javax.swing.JLabel();
        harga_satuan = new javax.swing.JLabel();
        jumlah = new javax.swing.JLabel();
        totall = new javax.swing.JLabel();
        date_chooser = new datechooser.beans.DateChooserCombo();
        txtTgl = new com.toedter.calendar.JDateChooser();
        Spinner_tgl = new javax.swing.JSpinner();
        text = new java.awt.TextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        no_jual.setText("No. Jual");

        tgl_jual.setText("Tgl. Jual");

        kd_kons.setText("Kode Konsumen");

        nm_kons.setText("Nama Konsumen");

        combo_kd_kons.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "K001", "K002", "K003", "K004" }));
        combo_kd_kons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_kd_konsActionPerformed(evt);
            }
        });

        hasil_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Kode Barang", "Nama Barang", "Harga Barang", "Jumlah", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        hasil_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hasil_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(hasil_table);

        txt_nm_brg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nm_brgActionPerformed(evt);
            }
        });

        txt_harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hargaActionPerformed(evt);
            }
        });

        txt_ttl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ttlActionPerformed(evt);
            }
        });

        btn_hpus_item.setText("Hapus Item");
        btn_hpus_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hpus_itemActionPerformed(evt);
            }
        });

        combo_kd_brg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "K001", "K002", "K003", "K004" }));
        combo_kd_brg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_kd_brgActionPerformed(evt);
            }
        });

        ttl.setText("Total");

        txt_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalActionPerformed(evt);
            }
        });

        btn_tambah.setText("Tambah");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_batal.setText("Batal");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });

        btn_cetak.setText("Cetak");
        btn_cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cetakActionPerformed(evt);
            }
        });

        btn_keluar.setText("Keluar");
        btn_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_keluarActionPerformed(evt);
            }
        });

        txt_no_jual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_no_jualActionPerformed(evt);
            }
        });

        txt_jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jumlahActionPerformed(evt);
            }
        });
        txt_jumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_jumlahKeyPressed(evt);
            }
        });

        btn_pilih_brg.setText("Pilih Barang");
        btn_pilih_brg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pilih_brgActionPerformed(evt);
            }
        });

        nm_brg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nm_brgActionPerformed(evt);
            }
        });

        Bayar.setText("Bayar");

        Kembalian.setText("Kembalian");

        txt_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bayarActionPerformed(evt);
            }
        });

        txt_kembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kembalianActionPerformed(evt);
            }
        });

        txt_nm_kons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nm_konsActionPerformed(evt);
            }
        });

        kd_brg.setText("Kode Barang");

        nm_barang.setText("Nama Barang");

        harga_satuan.setText("Harga Satuan");

        jumlah.setText("Jumlah");

        totall.setText("Total");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(kd_brg)
                        .addGap(53, 53, 53)
                        .addComponent(nm_barang)
                        .addGap(78, 78, 78)
                        .addComponent(harga_satuan)
                        .addGap(89, 89, 89)
                        .addComponent(jumlah)
                        .addGap(92, 92, 92)
                        .addComponent(totall))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(no_jual)
                                .addGap(18, 18, 18)
                                .addComponent(txt_no_jual, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tgl_jual)
                                .addGap(18, 18, 18)
                                .addComponent(Spinner_tgl)))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTgl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(date_chooser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nm_kons)
                                .addGap(26, 26, 26)
                                .addComponent(txt_nm_kons, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(kd_kons)
                                .addGap(31, 31, 31)
                                .addComponent(combo_kd_kons, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(combo_kd_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(txt_nm_brg, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(txt_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(txt_ttl, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_hpus_item)
                        .addGap(19, 19, 19)
                        .addComponent(btn_pilih_brg)
                        .addGap(17, 17, 17)
                        .addComponent(nm_brg, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btn_tambah)
                        .addGap(37, 37, 37)
                        .addComponent(btn_simpan)
                        .addGap(28, 28, 28)
                        .addComponent(btn_batal)
                        .addGap(28, 28, 28)
                        .addComponent(btn_cetak)
                        .addGap(18, 18, 18)
                        .addComponent(btn_keluar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(text, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ttl)
                                .addGap(54, 54, 54)
                                .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Bayar)
                                .addGap(51, 51, 51)
                                .addComponent(txt_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Kembalian)
                                .addGap(24, 24, 24)
                                .addComponent(txt_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(no_jual)
                    .addComponent(txt_no_jual)
                    .addComponent(kd_kons)
                    .addComponent(combo_kd_kons)
                    .addComponent(txtTgl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(date_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nm_kons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tgl_jual)
                                .addComponent(Spinner_tgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(nm_kons))))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kd_brg)
                    .addComponent(nm_barang)
                    .addComponent(harga_satuan)
                    .addComponent(jumlah)
                    .addComponent(totall))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combo_kd_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nm_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ttl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_hpus_item)
                    .addComponent(btn_pilih_brg)
                    .addComponent(nm_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_tambah)
                            .addComponent(btn_simpan)
                            .addComponent(btn_batal)
                            .addComponent(btn_cetak)
                            .addComponent(btn_keluar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ttl)
                            .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Bayar)
                            .addComponent(txt_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(Kembalian))
                            .addComponent(txt_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_no_jualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_no_jualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_no_jualActionPerformed

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        // TODO add your handling code here:
        aktif(false);
        setTombol(true);
        kosong();
        
    }//GEN-LAST:event_btn_batalActionPerformed

    private void btn_cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cetakActionPerformed
        // TODO add your handling code here:
        format_tanggal();
        String ctk="Nota Penjualan\nNo:"+txt_no_jual.getText()+"\nTanggal : "+tanggal;
        ctk=ctk+"\n"+"-------------------------------------------------------------------------------------------------";
        ctk=ctk+"\n"+"Kode\tNama Barang\tHarga\tJml\tTotal";
        ctk=ctk+"\n"+"-------------------------------------------------------------------------------------------------";
        for(int i=0;i<hasil_table.getRowCount();i++)
        {
        String xkd=(String)hasil_table.getValueAt(i,0);
        String xnama=(String)hasil_table.getValueAt(i,1);
        double xhrg=(Double)hasil_table.getValueAt(i,2);
        int xjml=(Integer)hasil_table.getValueAt(i,3);
        double xtot=(Double)hasil_table.getValueAt(i,4);
        ctk=ctk+"\n"+xkd+"\t"+xnama+"\t"+xhrg+"\t"+xjml+"\t"+xtot;
        }
        ctk=ctk+"\n"+"-------------------------------------------------------------------------------------------------";
        text.setText(ctk);
        String headerField="";
        String footerField="";
        MessageFormat header = new MessageFormat(headerField);
        MessageFormat footer = new MessageFormat(footerField);;
//        MessageFormat header = createFormat(headerField);
//        MessageFormat footer = createFormat(footerField);
        boolean interactive = true;//interactiveCheck.isSelected();
        boolean background = true;//backgroundCheck.isSelected();
        PrintingTask task = new PrintingTask(header, footer,interactive);
        if (background) {
        task.execute();
        } else {
        task.run();
        }
    }//GEN-LAST:event_btn_cetakActionPerformed

    private void combo_kd_konsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_kd_konsActionPerformed
        // TODO add your handling code here:
         JComboBox combo_kd_kons = (javax.swing.JComboBox)evt.getSource();
         sKd_Kons = (String)combo_kd_kons.getSelectedItem();
        detail_konsumen(sKd_Kons);

         
    }//GEN-LAST:event_combo_kd_konsActionPerformed

    private void txt_nm_konsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nm_konsActionPerformed
        // TODO add your handling code here:
        JComboBox combo_kd_brg = (javax.swing.JComboBox)evt.getSource();
        sKd_Brg = (String)combo_kd_brg.getSelectedItem();
        detail_barang(sKd_Brg);
        txt_jumlah.setText("");
        txt_ttl.setText("");
    }//GEN-LAST:event_txt_nm_konsActionPerformed

    private void combo_kd_brgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_kd_brgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_kd_brgActionPerformed

    private void txt_nm_brgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nm_brgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nm_brgActionPerformed

    private void txt_hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hargaActionPerformed

    private void txt_jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jumlahActionPerformed

    private void txt_ttlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ttlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ttlActionPerformed

    private void btn_hpus_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hpus_itemActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dataModel = (DefaultTableModel) hasil_table.getModel();
        int row=hasil_table.getSelectedRow();
        if (hasil_table.getRowCount() > 0) {
        dataModel.removeRow(row);
        }
        double x_total, x_harga;
        int x_jumlah;
        x_harga = Double.parseDouble(txt_harga.getText());
        x_jumlah = Integer.parseInt(txt_jumlah.getText());
        x_total = x_harga * x_jumlah;
//        String x_tl = Double.toString(x_total);
//        txt_ttl.setText(x_tl);
        total = total - x_total;
        txt_ttl.setText(Double.toString(total));
        
    }//GEN-LAST:event_btn_hpus_itemActionPerformed

    private void btn_pilih_brgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pilih_brgActionPerformed
        // TODO add your handling code here:
        frmCariBarang fDB = new frmCariBarang();
        fDB.fAB = this;
        fDB.setVisible(true);
        fDB.setResizable(false);
    }//GEN-LAST:event_btn_pilih_brgActionPerformed

    private void nm_brgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nm_brgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nm_brgActionPerformed

    private void hasil_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hasil_tableMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_hasil_tableMouseClicked

    private void txt_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalActionPerformed

    private void txt_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bayarActionPerformed

    private void txt_kembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kembalianActionPerformed

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        aktif(true);
        setTombol(false);
        kosong();
        baca_konsumen();
        baca_barang();
        nomor_jual();
        
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
         simpan_transaksi();
        aktif(false);
        setTombol(true);
        kosong();
        kosong_detail();
        
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_keluarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btn_keluarActionPerformed

    private void txt_jumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jumlahKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        hitung_jual();
        simpan_di_table();
        }
    }//GEN-LAST:event_txt_jumlahKeyPressed

    
    
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
            java.util.logging.Logger.getLogger(frmTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Bayar;
    private javax.swing.JLabel Kembalian;
    private javax.swing.JSpinner Spinner_tgl;
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_cetak;
    private javax.swing.JButton btn_hpus_item;
    private javax.swing.JButton btn_keluar;
    private javax.swing.JButton btn_pilih_brg;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JComboBox<String> combo_kd_brg;
    private javax.swing.JComboBox<String> combo_kd_kons;
    private datechooser.beans.DateChooserDialog dateChooserDialog1;
    private datechooser.beans.DateChooserDialog dateChooserDialog2;
    private datechooser.beans.DateChooserCombo date_chooser;
    private javax.swing.JLabel harga_satuan;
    private javax.swing.JTable hasil_table;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jumlah;
    private javax.swing.JLabel kd_brg;
    private javax.swing.JLabel kd_kons;
    private javax.swing.JLabel nm_barang;
    private javax.swing.JTextField nm_brg;
    private javax.swing.JLabel nm_kons;
    private javax.swing.JLabel no_jual;
    private java.awt.TextArea text;
    private javax.swing.JLabel tgl_jual;
    private javax.swing.JLabel totall;
    private javax.swing.JLabel ttl;
    private com.toedter.calendar.JDateChooser txtTgl;
    private javax.swing.JTextField txt_bayar;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_jumlah;
    private javax.swing.JTextField txt_kembalian;
    private javax.swing.JTextField txt_nm_brg;
    private javax.swing.JTextField txt_nm_kons;
    private javax.swing.JTextField txt_no_jual;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_ttl;
    // End of variables declaration//GEN-END:variables
}
