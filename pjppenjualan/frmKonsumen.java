/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pjppenjualan;
import java.sql.*;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
//import javax.swing.JComboBox;
/**
 *
 * @author HP
 */
public class frmKonsumen extends javax.swing.JFrame {
    Connection Con;
    ResultSet RsKons;
    Statement stm;
    Boolean ada = false;
//    String sSatuan;
    Boolean edit=false;
    
    private Object[][] dataTable = null;
    private String[] header = {"Kode","Nama Konsumen","Alamat","Kota","Kode Pos","Phone","Email"};
    
    /**
     * Creates new form frmKonsumen
     */
    public frmKonsumen() {
        initComponents();
        open_db();
        baca_data();
        aktif(false);
        setTombol(true);
    }

    private void setField(){
        int row = table_kons.getSelectedRow();
        txt_kd_kons.setText((String)table_kons.getValueAt(row, 0));
        txt_nm_kons.setText((String)table_kons.getValueAt(row, 1));
        txt_alamat_kons.setText((String)table_kons.getValueAt(row, 2));
        txt_kota_kons.setText((String)table_kons.getValueAt(row, 3));
        String kd_pos = (String) table_kons.getValueAt(row, 4);
        txt_kd_pos_kons.setText(kd_pos);
        String tlpn = (String) table_kons.getValueAt(row, 5);
        txt_tlpn_kons.setText(tlpn);
//        String kd_pos = Integer.toString((Integer)table_kons.getValueAt(row, 4));
//        txt_kd_pos_kons.setText(kd_pos);
//        String tlpn = Integer.toString((Integer)table_kons.getValueAt(row, 5));
//        txt_tlpn_kons.setText(tlpn);
        Object emailValue = table_kons.getValueAt(row, 6);
        if (emailValue != null) {
            String email = emailValue.toString();
            if (isValidEmail(email)) {
                txt_email_kons.setText(email);
            } else {
                txt_email_kons.setText("Invalid email format");
            }
        } else {
            txt_email_kons.setText("");
        }
    }
    
    public boolean isValidEmail(String email) {
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    Pattern pat = Pattern.compile(emailRegex);
    if (email == null)
        return false;
    return pat.matcher(email).matches();
}
    
    private void open_db()
    { try{
            KoneksiMysql kon = new KoneksiMysql("localhost","root","","penjualan");
            Con = kon.getConnection();
                //System.out.println("Berhasil ");
                }catch (Exception e) {
                    System.out.println("Error : "+e);
                }
    }
    
    private void baca_data()
    {
    try{
    stm = Con.createStatement();
    RsKons = stm.executeQuery("select * from konsumen");
    
    ResultSetMetaData meta = RsKons.getMetaData();
    int col = meta.getColumnCount();
    int baris = 0;
    while(RsKons.next()) {
        baris = RsKons.getRow();
    }
    
    dataTable = new Object[baris][col];
    int x = 0;
    RsKons.beforeFirst();
    while(RsKons.next()) {
        dataTable[x][0] = RsKons.getString("kd_konsumen");
        dataTable[x][1] = RsKons.getString("nm_konsumen");
        dataTable[x][2] = RsKons.getString("almt_konsumen");
        dataTable[x][3] = RsKons.getString("kota_konsumen");
        dataTable[x][4] = RsKons.getString("kd_pos");
        dataTable[x][5] = RsKons.getString("phone");
        dataTable[x][6] = RsKons.getString("email");
        x++;
    }
    table_kons.setModel(new DefaultTableModel(dataTable,header));
    }
    catch(SQLException e)
    {
    JOptionPane.showMessageDialog(null, e);
    }
    }
    
    private void kosong()
    {
    txt_kd_kons.setText("");
    txt_nm_kons.setText("");
    txt_alamat_kons.setText("");
    txt_kota_kons.setText("");
    txt_kd_pos_kons.setText("");
    txt_tlpn_kons.setText("");
    txt_email_kons.setText("");
    }
    
     private void aktif(boolean x)
    {
    txt_kd_kons.setEditable(x);
    txt_nm_kons.setEditable(x);
    txt_alamat_kons.setEditable(x);
    txt_kota_kons.setEditable(x);
    txt_kd_pos_kons.setEditable(x);
    txt_tlpn_kons.setEditable(x);
    txt_email_kons.setEditable(x);
    }
    
    private void setTombol(boolean t)
    {
    btn_tambah.setEnabled(t);
    btn_koreksi.setEnabled(t);
    btn_hapus.setEnabled(t);
    btn_simpan.setEnabled(!t);
    btn_batal.setEnabled(!t);
    btn_keluar.setEnabled(t);
    } 
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        judul_kons = new javax.swing.JLabel();
        kd_kons = new javax.swing.JLabel();
        nm_kons = new javax.swing.JLabel();
        alamat_kons = new javax.swing.JLabel();
        kota_kons = new javax.swing.JLabel();
        kd_pos_kons = new javax.swing.JLabel();
        tlpn_kons = new javax.swing.JLabel();
        eml_kons = new javax.swing.JLabel();
        txt_kd_kons = new javax.swing.JTextField();
        txt_nm_kons = new javax.swing.JTextField();
        txt_alamat_kons = new javax.swing.JTextField();
        txt_kota_kons = new javax.swing.JTextField();
        txt_kd_pos_kons = new javax.swing.JTextField();
        txt_tlpn_kons = new javax.swing.JTextField();
        txt_email_kons = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_kons = new javax.swing.JTable();
        btn_tambah = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_koreksi = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        btn_keluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        judul_kons.setText("Data Konsumen");

        kd_kons.setText("Kode Konsumen");

        nm_kons.setText("Nama Konsumen");

        alamat_kons.setText("Alamat");

        kota_kons.setText("Kota");

        kd_pos_kons.setText("Kode Pos");

        tlpn_kons.setText("Telepon");

        eml_kons.setText("Email");

        txt_kd_kons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kd_konsActionPerformed(evt);
            }
        });

        txt_nm_kons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nm_konsActionPerformed(evt);
            }
        });

        txt_alamat_kons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_alamat_konsActionPerformed(evt);
            }
        });

        txt_kota_kons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kota_konsActionPerformed(evt);
            }
        });

        txt_kd_pos_kons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kd_pos_konsActionPerformed(evt);
            }
        });

        txt_tlpn_kons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tlpn_konsActionPerformed(evt);
            }
        });

        txt_email_kons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_email_konsActionPerformed(evt);
            }
        });

        table_kons.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Kode", "Nama Konsumen", "Alamat", "Kota", "Kode Pos", "Phone", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table_kons.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_konsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_kons);
        if (table_kons.getColumnModel().getColumnCount() > 0) {
            table_kons.getColumnModel().getColumn(0).setHeaderValue("Kode");
            table_kons.getColumnModel().getColumn(1).setHeaderValue("Nama Konsumen");
            table_kons.getColumnModel().getColumn(2).setHeaderValue("Alamat");
            table_kons.getColumnModel().getColumn(3).setHeaderValue("Kota");
            table_kons.getColumnModel().getColumn(4).setHeaderValue("Kode Pos");
            table_kons.getColumnModel().getColumn(5).setHeaderValue("Phone");
            table_kons.getColumnModel().getColumn(6).setHeaderValue("Email");
        }

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

        btn_koreksi.setText("Koreksi");
        btn_koreksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_koreksiActionPerformed(evt);
            }
        });

        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_batal.setText("Batal");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });

        btn_keluar.setText("Keluar");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        btn_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_keluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tlpn_kons)
                                    .addComponent(eml_kons))
                                .addGap(89, 89, 89)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_tlpn_kons, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                                    .addComponent(txt_email_kons)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(kd_kons)
                                    .addComponent(nm_kons)
                                    .addComponent(alamat_kons)
                                    .addComponent(kota_kons)
                                    .addComponent(kd_pos_kons))
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_kd_kons, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                                    .addComponent(txt_nm_kons)
                                    .addComponent(txt_alamat_kons)
                                    .addComponent(txt_kota_kons)
                                    .addComponent(txt_kd_pos_kons, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(judul_kons))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_tambah)
                .addGap(18, 18, 18)
                .addComponent(btn_simpan)
                .addGap(18, 18, 18)
                .addComponent(btn_koreksi)
                .addGap(18, 18, 18)
                .addComponent(btn_hapus)
                .addGap(18, 18, 18)
                .addComponent(btn_batal)
                .addGap(18, 18, 18)
                .addComponent(btn_keluar)
                .addContainerGap(140, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(judul_kons)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kd_kons)
                    .addComponent(txt_kd_kons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nm_kons)
                    .addComponent(txt_nm_kons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alamat_kons)
                    .addComponent(txt_alamat_kons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kota_kons)
                    .addComponent(txt_kota_kons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kd_pos_kons)
                    .addComponent(txt_kd_pos_kons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tlpn_kons)
                    .addComponent(txt_tlpn_kons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eml_kons)
                    .addComponent(txt_email_kons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tambah)
                    .addComponent(btn_simpan)
                    .addComponent(btn_koreksi)
                    .addComponent(btn_hapus)
                    .addComponent(btn_batal)
                    .addComponent(btn_keluar))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_kd_konsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kd_konsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kd_konsActionPerformed

    private void txt_nm_konsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nm_konsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nm_konsActionPerformed

    private void txt_alamat_konsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_alamat_konsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_alamat_konsActionPerformed

    private void txt_kota_konsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kota_konsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kota_konsActionPerformed

    private void txt_kd_pos_konsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kd_pos_konsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kd_pos_konsActionPerformed

    private void txt_tlpn_konsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tlpn_konsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tlpn_konsActionPerformed

    private void txt_email_konsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_email_konsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_email_konsActionPerformed

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        aktif(true);
        setTombol(false);
        kosong();
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        String tkode = txt_kd_kons.getText();
        String tnm = txt_nm_kons.getText();
        String talmt = txt_alamat_kons.getText();
        String tkota = txt_kota_kons.getText();
        String tkodepos = txt_kd_pos_kons.getText();
        String ttlpn = txt_tlpn_kons.getText();
        String temail = txt_email_kons.getText();
        
         try{
        if (edit==true){
//        stm.executeUpdate("update konsumen set nm_konsumen='"+tnm+"',almt_konsumen='"+talmt+"',kota_konsumen="+tkota+",kd_pos="+tkodepos+",phone="+ttlpn+",email="+temail+" where kd_konsumen='" + tkode + "'");
        stm.executeUpdate("UPDATE konsumen SET nm_konsumen='"+tnm+"', almt_konsumen='"+talmt+"', kota_konsumen='"+tkota+"', kd_pos='"+tkodepos+"', phone='"+ttlpn+"', email='"+temail+"' WHERE kd_konsumen='" + tkode + "'");
        }else
        {
//        stm.executeUpdate("INSERT into konsumen VALUES('"+tkode+"','"+tnm+"','"+talmt+"',"+tkota+","+tkodepos+","+ttlpn+","+temail+")");
         stm.executeUpdate("INSERT INTO konsumen (kd_konsumen, nm_konsumen, almt_konsumen, kota_konsumen, kd_pos, phone, email) VALUES ('"+tkode+"', '"+tnm+"', '"+talmt+"', '"+tkota+"', '"+tkodepos+"', '"+ttlpn+"', '"+temail+"')");
        }
        table_kons.setModel(new DefaultTableModel(dataTable,header));
        baca_data();
        aktif(false);
        setTombol(true);
        }catch(SQLException e) {
        JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_koreksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_koreksiActionPerformed
        // TODO add your handling code here:
        edit=true;
        aktif(true);
        setTombol(false);
        txt_kd_kons.setEditable(false);
    }//GEN-LAST:event_btn_koreksiActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        try{
        String sql="delete from konsumen where kd_konsumen='" + txt_kd_kons.getText()+ "'";
        stm.executeUpdate(sql);
        baca_data();
        }
        catch(SQLException e)
        {
        JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        // TODO add your handling code here:
        aktif(false);
        setTombol(true);
    }//GEN-LAST:event_btn_batalActionPerformed

    private void btn_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_keluarActionPerformed
        // TODO add your handling code here:
//        System.exit(0);
        dispose();
    }//GEN-LAST:event_btn_keluarActionPerformed

    private void table_konsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_konsMouseClicked
        // TODO add your handling code here:
        setField();
    }//GEN-LAST:event_table_konsMouseClicked

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
            java.util.logging.Logger.getLogger(frmKonsumen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmKonsumen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmKonsumen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmKonsumen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmKonsumen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alamat_kons;
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_keluar;
    private javax.swing.JButton btn_koreksi;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JLabel eml_kons;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel judul_kons;
    private javax.swing.JLabel kd_kons;
    private javax.swing.JLabel kd_pos_kons;
    private javax.swing.JLabel kota_kons;
    private javax.swing.JLabel nm_kons;
    private javax.swing.JTable table_kons;
    private javax.swing.JLabel tlpn_kons;
    private javax.swing.JTextField txt_alamat_kons;
    private javax.swing.JTextField txt_email_kons;
    private javax.swing.JTextField txt_kd_kons;
    private javax.swing.JTextField txt_kd_pos_kons;
    private javax.swing.JTextField txt_kota_kons;
    private javax.swing.JTextField txt_nm_kons;
    private javax.swing.JTextField txt_tlpn_kons;
    // End of variables declaration//GEN-END:variables
}
