/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalpro;

import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import phar.Phar;
import phar.db;

/**
 *
 * @author Ana
 */
public class receipt extends javax.swing.JFrame {
 public Connection con;
    /**
     * Creates new form receipt
     */
 String phn="";
 String custo="";
int receipt_value=0;
    public receipt() {
        initComponents();
    }

public receipt(String newtotal,String newamount,DefaultTableModel m,Date date,int rno,String cust,String phne,int gr,int dis,int tto,int ca,int tooo,int value) {
        initComponents();
        this.receipt_value=value;
        
        
        
        jLabel20.setVisible(true);
        jLabel22.setVisible(true);
        jLabel19.setVisible(true);
        jLabel21.setVisible(true);
        jLabel20.setText(newtotal);
        jLabel22.setText(newamount);
       
        phn=phne;
        custo=cust;
        con=db.connect();
        da.setText(String.valueOf(date));
        rec.setText(String.valueOf(rno));
        cusn.setText(String.valueOf(cust));
        
        // setting phone number (+880) 
        if(phne.equals("") || phne== null)
        {
            phnn.setText(phne);
        }
        else
        {
            phnn.setText("+880"+phne);
        }
        
        tt.setText(String.valueOf(gr));
        d.setText(String.valueOf(dis));
        ttt.setText(String.valueOf(tto));
        c.setText(String.valueOf(ca));
        r.setText(String.valueOf(tooo));
        receipttable.setModel(m);
         int rowcount= receipttable.getRowCount();
         receipttable.setVisible(false);
        int a=15, b=20;
        for (int i=0; i<rowcount; i++){
            String ss=receipttable.getValueAt(i, 0).toString();
            
            p2.add(new JLabel(ss)).setBounds(3, a, 50, 50);
            p2.add(new JLabel(receipttable.getValueAt(i, 1).toString())).setBounds(33,a,50,50);
            p2.add(new JLabel(receipttable.getValueAt(i, 2).toString())).setBounds(110,a,100,50);
            p2.add(new JLabel(receipttable.getValueAt(i, 3).toString())).setBounds(225,a,50,50);
            p2.add(new JLabel(receipttable.getValueAt(i, 4).toString())).setBounds(271,a,50,50);
            p2.add(new JLabel(receipttable.getValueAt(i, 5).toString())).setBounds(339,a,50,50);
            a+=15;
        }
        
        ptable.setVisible(false);
    }
public receipt(DefaultTableModel m,Date date,int rno,String cust,String phne,int gr,int dis,int tto,int ca,int tooo,int value) {
        initComponents();
        this.receipt_value=value;
        jLabel20.setVisible(false);
        jLabel21.setVisible(false);
        jLabel19.setVisible(false);
        jLabel22.setVisible(false);
        
        phn=phne;
        custo=cust;
        con=db.connect();
        da.setText(String.valueOf(date));
        rec.setText(String.valueOf(rno));
        cusn.setText(String.valueOf(cust));
        
        // setting phone number (+880) 
        if(phne.equals("") || phne== null)
        {
            phnn.setText(phne);
        }
        else
        {
            phnn.setText("+880"+phne);
        }
        
        tt.setText(String.valueOf(gr));
        d.setText(String.valueOf(dis));
        ttt.setText(String.valueOf(tto));
        c.setText(String.valueOf(ca));
        r.setText(String.valueOf(tooo));
        receipttable.setModel(m);
         int rowcount= receipttable.getRowCount();
         receipttable.setVisible(false);
        int a=15, b=20;
        for (int i=0; i<rowcount; i++){
            String ss=receipttable.getValueAt(i, 0).toString();
            
            p2.add(new JLabel(ss)).setBounds(3, a, 50, 50);
            p2.add(new JLabel(receipttable.getValueAt(i, 1).toString())).setBounds(33,a,50,50);
            p2.add(new JLabel(receipttable.getValueAt(i, 2).toString())).setBounds(110,a,100,50);
            p2.add(new JLabel(receipttable.getValueAt(i, 3).toString())).setBounds(225,a,50,50);
            p2.add(new JLabel(receipttable.getValueAt(i, 4).toString())).setBounds(271,a,50,50);
            p2.add(new JLabel(receipttable.getValueAt(i, 5).toString())).setBounds(339,a,50,50);
            a+=15;
        }
        
        ptable.setVisible(false);
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pan = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rec = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cusn = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        phnn = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tt = new javax.swing.JLabel();
        d = new javax.swing.JLabel();
        ttt = new javax.swing.JLabel();
        c = new javax.swing.JLabel();
        r = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        da = new javax.swing.JLabel();
        p2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        ptable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        receipttable = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(821, 806));

        pan.setBackground(new java.awt.Color(255, 255, 255));
        pan.setLayout(null);

        jLabel1.setText("Receipt No:");
        pan.add(jLabel1);
        jLabel1.setBounds(84, 81, 70, 14);

        rec.setText("jLabel2");
        pan.add(rec);
        rec.setBounds(158, 81, 34, 14);

        jLabel3.setText("Customer Name:");
        pan.add(jLabel3);
        jLabel3.setBounds(251, 81, 100, 14);

        cusn.setText("jLabel4");
        pan.add(cusn);
        cusn.setBounds(358, 81, 140, 14);

        jLabel5.setText("Phone Number:");
        pan.add(jLabel5);
        jLabel5.setBounds(251, 106, 100, 14);

        phnn.setText("jsdfh");
        pan.add(phnn);
        phnn.setBounds(358, 106, 140, 14);

        jLabel2.setText("Grand Total:");
        pan.add(jLabel2);
        jLabel2.setBounds(290, 610, 90, 14);

        jLabel4.setText("Discount:");
        pan.add(jLabel4);
        jLabel4.setBounds(290, 630, 60, 14);

        jLabel6.setText("Total Amount:");
        pan.add(jLabel6);
        jLabel6.setBounds(80, 610, 100, 14);

        jLabel7.setText("Cash:");
        pan.add(jLabel7);
        jLabel7.setBounds(80, 630, 60, 14);

        jLabel8.setText("Return:");
        pan.add(jLabel8);
        jLabel8.setBounds(80, 650, 70, 14);

        tt.setText("tt");
        pan.add(tt);
        tt.setBounds(380, 610, 92, 14);

        d.setText("jLabel10");
        pan.add(d);
        d.setBounds(380, 630, 92, 14);

        ttt.setText("jLabel11");
        pan.add(ttt);
        ttt.setBounds(180, 610, 74, 14);

        c.setText("jLabel12");
        pan.add(c);
        c.setBounds(180, 630, 94, 14);

        r.setText("jLabel13");
        pan.add(r);
        r.setBounds(180, 650, 94, 14);

        jButton1.setText("print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        pan.add(jButton1);
        jButton1.setBounds(730, 600, 55, 23);

        jLabel9.setText("Date:");
        pan.add(jLabel9);
        jLabel9.setBounds(84, 106, 70, 14);

        da.setText("jLabel10");
        pan.add(da);
        da.setBounds(158, 106, 90, 14);

        p2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setText("SL");

        jLabel11.setText("Item");

        jLabel12.setText("Color");

        jLabel13.setText("Size");

        jLabel14.setText("Quantity");

        jLabel15.setText("Total");

        javax.swing.GroupLayout p2Layout = new javax.swing.GroupLayout(p2);
        p2.setLayout(p2Layout);
        p2Layout.setHorizontalGroup(
            p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p2Layout.createSequentialGroup()
                .addComponent(jSeparator4)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p2Layout.createSequentialGroup()
                .addGroup(p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
        );
        p2Layout.setVerticalGroup(
            p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p2Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 442, Short.MAX_VALUE)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        pan.add(p2);
        p2.setBounds(84, 134, 440, 480);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Access Shop Simulator");
        pan.add(jLabel16);
        jLabel16.setBounds(200, 20, 139, 15);
        pan.add(jLabel17);
        jLabel17.setBounds(398, 117, 0, 0);

        jLabel18.setText("17 Sonargaon Janapath, Dhaka 1230, Bangladesh");
        pan.add(jLabel18);
        jLabel18.setBounds(140, 40, 282, 14);
        pan.add(jSeparator3);
        jSeparator3.setBounds(140, 60, 282, 2);

        receipttable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "SL", "Item", "color", "Size", "Quantity", "Total"
            }
        ));
        receipttable.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(receipttable);

        javax.swing.GroupLayout ptableLayout = new javax.swing.GroupLayout(ptable);
        ptable.setLayout(ptableLayout);
        ptableLayout.setHorizontalGroup(
            ptableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ptableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ptableLayout.setVerticalGroup(
            ptableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ptableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pan.add(ptable);
        ptable.setBounds(639, 0, 350, 100);

        jLabel19.setText("New total:");
        pan.add(jLabel19);
        jLabel19.setBounds(290, 650, 90, 14);

        jLabel20.setText("jLabel20");
        pan.add(jLabel20);
        jLabel20.setBounds(380, 650, 140, 14);

        jLabel21.setText("New Amount:");
        pan.add(jLabel21);
        jLabel21.setBounds(290, 670, 90, 14);

        jLabel22.setText("jLabel22");
        pan.add(jLabel22);
        jLabel22.setBounds(380, 670, 110, 14);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 874, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pan, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     try {
         int prebil=Integer.parseInt(ttt.getText());
         int rept=Integer.parseInt(rec.getText());
        Phar ob=new Phar();
        java.sql.Date curdate = ob.get_current_date();
        
        if(receipt_value==0){
         PreparedStatement pst =con.prepareStatement( "insert into bill(receiptNo,customername, phone, date,"
                 + "totalAmount, DiscountonTotal,totalwithDiscount, cash ,back) values (?,?,?,?,?,?,?,?,?)");
         pst.setInt(1, rept);
         pst.setString(2, custo);
         pst.setString(3, phn);
         pst.setDate(4, curdate);
         pst.setInt(5, Integer.parseInt(tt.getText()));
         pst.setInt(6, Integer.parseInt(d.getText()));
         pst.setInt(7, prebil);
         pst.setInt(8, Integer.parseInt(c.getText()));
         pst.setInt(9, Integer.parseInt(r.getText()));
         pst.executeUpdate();
         ob.set_daily_income(Integer.parseInt(ttt.getText()),rept);
          int rowcount= receipttable.getRowCount();
        
        
        for (int i=0; i<rowcount; i++){
            String pna=receipttable.getValueAt(i, 1).toString();
            
            int pro=0;
            PreparedStatement pp=con.prepareStatement("Select pid from productinfo where pname=?");
            pp.setString(1, pna);
            ResultSet rse=pp.executeQuery();
            while(rse.next()){
                pro=Integer.parseInt(rse.getString("pid"));
            }
            String c=receipttable.getValueAt(i, 2).toString();
            String s=receipttable.getValueAt(i, 3).toString();
            int q=Integer.parseInt(receipttable.getValueAt(i, 4).toString());
            int dbquan=0;
            PreparedStatement ps =con.prepareStatement("select quantity from stock where pid=? and color=? and size=?");
            ps.setInt(1, pro);
            ps.setString(2, c);
            ps.setString(3, s);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                dbquan=rs.getInt(1);
            }
            PreparedStatement pn =con.prepareStatement("update stock set quantity= ? where pid=? and color=? and size=?");
            pn.setInt(1,dbquan-q);
            pn.setInt(2, pro);
            pn.setString(3, c);
            pn.setString(4, s);
            pn.executeUpdate();
            homepage oo=new homepage();
            oo.showtable1();}}
        else{
            //getting the previous bill 
                int prebi=0;
                int add_income=0;
                PreparedStatement prebill =con.prepareStatement("select totalwithDiscount from bill where receiptNo=?");
                prebill.setInt(1,rept );
                ResultSet ree=prebill.executeQuery();
                while(ree.next()){
                    prebi=ree.getInt("totalwithDiscount");
                }
//                if(prebi<prebil){
//                    add_income=Integer.parseInt(jLabel20.getText())-prebi;
//                }
                PreparedStatement pst =con.prepareStatement( "update bill set date=?, totalAmount=?, discountonTotal=?, "
                        + "totalwithDiscount=?, cash=?,back=? where receiptNo=?");
                
                
                pst.setDate(1, curdate);
                pst.setInt(2, Integer.parseInt(jLabel20.getText()));
                pst.setInt(3, Integer.parseInt(d.getText()));
                pst.setInt(4, Integer.parseInt(jLabel20.getText()));
                pst.setInt(5, Integer.parseInt(c.getText()));
                pst.setInt(6, Integer.parseInt(r.getText()));
                pst.setInt(7, rept);
                pst.executeUpdate();
                
                // adding to daily income
                ob.set_daily_income(Integer.parseInt(jLabel22.getText()),rept);
                homepage oo=new homepage();
                oo.showtable1();
         }
        
         
     } catch (Exception ex) {
         Logger.getLogger(receipt.class.getName()).log(Level.SEVERE, null, ex);
     }
        
        
    Toolkit tkp = pan.getToolkit();
    PrintJob pjp = tkp.getPrintJob(this, null, null);
    Graphics g = pjp.getGraphics();
    pan.print(g);
    g.dispose();
    pjp.end();
    setVisible(false);
    
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new receipt().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel c;
    private javax.swing.JLabel cusn;
    private javax.swing.JLabel d;
    private javax.swing.JLabel da;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPanel p2;
    private javax.swing.JPanel pan;
    private javax.swing.JLabel phnn;
    private javax.swing.JPanel ptable;
    private javax.swing.JLabel r;
    private javax.swing.JLabel rec;
    private javax.swing.JTable receipttable;
    private javax.swing.JLabel tt;
    private javax.swing.JLabel ttt;
    // End of variables declaration//GEN-END:variables
}
