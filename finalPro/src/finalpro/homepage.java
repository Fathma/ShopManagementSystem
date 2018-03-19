/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalpro;

import java.awt.Color;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import phar.*;

/**
 *
 * @author Ana
 */
public class homepage extends javax.swing.JFrame {
    public Connection con;
    public int receipt_value=0;
    
    
    public homepage() throws SQLException {
        initComponents();
        
        
        con=db.connect();
        
        billingdate.setVisible(false);
        billingdate1.setVisible(false);
        rcpt.setText(String.valueOf(get_new_receiptno()+1));
        insert_emp.setVisible(true);
        attendance.setVisible(false);
        emptable.setVisible(false);
        stocktable.setVisible(false);
        updatee.setVisible(false);
        producttable.setVisible(false);
        cattable.setVisible(false);
        checksalary.setVisible(false);
       
        receiptdetails.setVisible(true);
        solditems.setVisible(false);
        
        // exchange variable
        jLabel93.setVisible(false);
        jLabel92.setVisible(false);
        jLabel88.setVisible(false);
        jLabel89.setVisible(false);
        
        
        expenses.setVisible(true); 
        income.setVisible(false);
        drecord.setVisible(false);
        expensed.setVisible(false);
         incomed.setVisible(false);
         
         check.setVisible(true);
        yearly.setVisible(false);
        monthly.setVisible(false);
         
        Phar n1=new Phar();
        date.setText(String.valueOf(n1.get_current_date()));
        income_date.setText(String.valueOf(n1.get_current_date()));
        setcombo();
        showtable1();
        showbilltable();
      
        
    }
    public void print_receipt(){
        Printsupport ps=new Printsupport();
        Object printitem [][]= ps.getTableData(billingtable);
        ps.setItems(printitem);
       
 PrinterJob pj = PrinterJob.getPrinterJob();
 pj.setPrintable(new Printsupport.MyPrintable(),ps.getPageFormat(pj));
       try {
            pj.print();
           
            }
        catch (PrinterException ex) {
                ex.printStackTrace();
            }
    }
    public int get_new_receiptno(){
        int rr=0;
        try {
            
            PreparedStatement pst=con.prepareStatement("select receiptNo from bill");
            ResultSet rs= pst.executeQuery();
            while(rs.next()){
                rr=rs.getInt("receiptNo");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rr;
    }
    public void setcombo()throws SQLException {
        java.sql.Statement stmt;

        
         
         stmt = con.createStatement();
             ResultSet ruu=stmt.executeQuery("SELECT catN FROM category group by catN order by catN");
           
            while(ruu.next())
            {
                 
                cat.addItem(ruu.getString("catN"));
            }
         
             ResultSet ru=stmt.executeQuery("SELECT pname FROM productinfo group by pname order by pname");
           
            while(ru.next())
            {
                product.addItem(ru.getString("pname"));
                
                
            } 
            
            
    }
    public void numberCheck(String value){
        char[] v=value.toCharArray();
        int l= v.length;
        for(int i=0; i <l ; i++)
        {
            if (v[i]>'9' ){
            JOptionPane.showMessageDialog(null,"please enter only numbers!");
            }
            
        }
    }
    public void alphabetCheck(String value){
        char[] v=value.toCharArray();
        int l= v.length;
        for(int i=0; i <l ; i++)
        {
            if (v[i]>'9' || v[i]==' '){
            
            }
            else{
                JOptionPane.showMessageDialog(null,"please enter only letters!");
            }
            
            
        }
    }
    
public void showsolditemstable(){
    
    try 
    {
        
        PreparedStatement st = con.prepareStatement("Select * from billingwithitems ;");
        
        ResultSet rs = st.executeQuery();
        solditemtable.setModel(DbUtils.resultSetToTableModel(rs));
    }
    catch (SQLException ex) {
        Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
    }
}
public void showmonthlytable(){
    
    try 
    {
        
        PreparedStatement st = con.prepareStatement("Select * from monthly  ;");
        
        ResultSet rs = st.executeQuery();
        monthly_table.setModel(DbUtils.resultSetToTableModel(rs));
    }
    catch (SQLException ex) {
        Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
    }
}
public void showyearlytable(){
    
    try 
    {
        
        PreparedStatement st = con.prepareStatement("Select * from yearly ;");
        
        ResultSet rs = st.executeQuery();
        yearly_table.setModel(DbUtils.resultSetToTableModel(rs));
    }
    catch (SQLException ex) {
        Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
    }
}
public void showExpensetable(){
        PreparedStatement st;
    try {
st = con.prepareStatement("Select* from expenses;");
       
         ResultSet rs = st.executeQuery();
        expensetable.setModel(DbUtils.resultSetToTableModel(rs));
    }
    catch (SQLException ex) {
        Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
public void showIncometable(){
        PreparedStatement st;
    try {
st = con.prepareStatement("Select* from income;");
       
         ResultSet rs = st.executeQuery();
        incometable.setModel(DbUtils.resultSetToTableModel(rs));
    }
    catch (SQLException ex) {
        Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
public void showdailyrecordtable(){
        PreparedStatement st;
    try {
st = con.prepareStatement("Select* from dailyrecord;");
       
         ResultSet rs = st.executeQuery();
        dailyrecord.setModel(DbUtils.resultSetToTableModel(rs));
    }
    catch (SQLException ex) {
        Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
public void showtable1(){
        PreparedStatement st;
    try {
         st = con.prepareStatement("Select p.pname,s.pid, s.size, s.color, s.wholeSalePrice, s.sellingPrice,s.quantity,c.catN from "
                 + "productinfo as p, stock as s, category as c where s.pid=p.pid and p.cid=c.cid order by quantity desc");
         ResultSet rs = st.executeQuery();
        table1.setModel(DbUtils.resultSetToTableModel(rs));
    }
    catch (SQLException ex) {
        Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
public void showtable2(){
        PreparedStatement st;
    try {
st = con.prepareStatement("Select* from emp ;");
       
         ResultSet rs = st.executeQuery();
        emp_table.setModel(DbUtils.resultSetToTableModel(rs));
    }
    catch (SQLException ex) {
        Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
public void showbilltable(){
        
    try {
PreparedStatement pst=con.prepareStatement("Select * from bill");
       ResultSet rs=pst.executeQuery();
        bill.setModel(DbUtils.resultSetToTableModel(rs));
    }
    catch (SQLException ex) {
        Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

public void showtableprotable(){
        PreparedStatement st;
    try {
st = con.prepareStatement("Select p.pname,p.pid, c.catN from category as c, productinfo as p where p.cid=c.cid group by p.pid order by p.pname");
       
         ResultSet rs = st.executeQuery();
        protable.setModel(DbUtils.resultSetToTableModel(rs));
    }
    catch (SQLException ex) {
        Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
public void showtablecattable(){
        PreparedStatement st;
    try {
st = con.prepareStatement("Select catN, cid from category order by catN;");
       
         ResultSet rs = st.executeQuery();
        categorytable.setModel(DbUtils.resultSetToTableModel(rs));
    }
    catch (SQLException ex) {
        Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
//    public void searchstock(String pname){
//        PreparedStatement st;
//    try {
//         st = con.prepareStatement("Select p.pname,p.pid, s.size, s.color, s.wholeSalePrice, s.sellingPrice,s.quantity,c.catN from "
//                 + "productinfo as p, stock as s, category as c where s.pid=p.pid and p.cid=c.cid pname=? ;");
//        st.setString(1, pname);
//         ResultSet rs = st.executeQuery();
//        table1.setModel(DbUtils.resultSetToTableModel(rs));
//    }
//    catch (SQLException ex) {
//        Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
//    }
//    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pop = new javax.swing.JPopupMenu();
        Delete = new javax.swing.JMenuItem();
        update = new javax.swing.JMenuItem();
        producttablePOP = new javax.swing.JPopupMenu();
        deletepro = new javax.swing.JMenuItem();
        catpop = new javax.swing.JPopupMenu();
        deletecat = new javax.swing.JMenuItem();
        salepop = new javax.swing.JPopupMenu();
        saleDelete = new javax.swing.JMenuItem();
        exchangepop = new javax.swing.JPopupMenu();
        deleteandadd = new javax.swing.JMenuItem();
        emppop = new javax.swing.JPopupMenu();
        empdelete = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        billing = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        billingtable = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cn = new javax.swing.JTextField();
        cph = new javax.swing.JTextField();
        gt = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        cash = new javax.swing.JTextField();
        change = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        dis = new javax.swing.JTextField();
        total = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jLabel61 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        rcpt = new javax.swing.JTextField();
        billingdate1 = new javax.swing.JLabel();
        billingdate = new javax.swing.JTextField();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        sl_size = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        sl_product = new javax.swing.JComboBox<>();
        sl_color = new javax.swing.JComboBox<>();
        sl_sp = new javax.swing.JTextField();
        qqq = new javax.swing.JSpinner();
        sellingprice = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        totalperitem = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        ADD = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        UPDATE = new javax.swing.JButton();
        searchpro = new javax.swing.JTextField();
        add = new javax.swing.JPanel();
        cattable = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        categorytable = new javax.swing.JTable();
        jLabel37 = new javax.swing.JLabel();
        producttable = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        protable = new javax.swing.JTable();
        jLabel36 = new javax.swing.JLabel();
        stocktable = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        updatee = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        pname = new javax.swing.JTextField();
        category = new javax.swing.JTextField();
        siz = new javax.swing.JTextField();
        colo = new javax.swing.JTextField();
        tradep = new javax.swing.JTextField();
        sellingp = new javax.swing.JTextField();
        quantit = new javax.swing.JTextField();
        proid = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel90 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        product = new javax.swing.JComboBox<>();
        cat = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        size = new javax.swing.JTextField();
        color = new javax.swing.JTextField();
        tp = new javax.swing.JTextField();
        sp = new javax.swing.JTextField();
        quantity = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        insert_emp = new javax.swing.JPanel();
        checksalary = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        yearr = new javax.swing.JComboBox<>();
        mon = new javax.swing.JComboBox<>();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        at = new javax.swing.JLabel();
        sal = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        emid = new javax.swing.JTextField();
        status = new javax.swing.JComboBox<>();
        jButton21 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        stat = new javax.swing.JLabel();
        attendance = new javax.swing.JPanel();
        empid = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        emptable = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        emp_table = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        emp_name = new javax.swing.JTextField();
        emp_phn = new javax.swing.JTextField();
        emp_address = new javax.swing.JTextField();
        emp_nid = new javax.swing.JTextField();
        emp_monthly_sal = new javax.swing.JTextField();
        emp_position = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        emp_search = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        receiptdetails = new javax.swing.JPanel();
        solditems = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        solditemtable = new javax.swing.JTable();
        jLabel50 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton13 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        bill = new javax.swing.JTable();
        jLabel49 = new javax.swing.JLabel();
        searchd = new com.toedter.calendar.JDateChooser();
        jButton20 = new javax.swing.JButton();
        jLabel53 = new javax.swing.JLabel();
        tm = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        expenses = new javax.swing.JPanel();
        incomed = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        incometable = new javax.swing.JTable();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jButton30 = new javax.swing.JButton();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        expensed = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        expensetable = new javax.swing.JTable();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton29 = new javax.swing.JButton();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        drecord = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        dailyrecord = new javax.swing.JTable();
        jLabel70 = new javax.swing.JLabel();
        income = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        income_date = new javax.swing.JLabel();
        aaa = new javax.swing.JTextField();
        ppp = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        ttt = new javax.swing.JLabel();
        jButton28 = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        amoun = new javax.swing.JTextField();
        pur = new javax.swing.JTextField();
        jButton24 = new javax.swing.JButton();
        jLabel64 = new javax.swing.JLabel();
        tootal = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jButton25 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jLabel63 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        yearly = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        yearly_table = new javax.swing.JTable();
        jLabel87 = new javax.swing.JLabel();
        check = new javax.swing.JPanel();
        monthly = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        monthly_table = new javax.swing.JTable();
        jLabel86 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        totalinc = new javax.swing.JLabel();
        totalprofit = new javax.swing.JLabel();
        totalexp = new javax.swing.JLabel();
        month = new javax.swing.JComboBox<>();
        year = new javax.swing.JComboBox<>();
        mess = new javax.swing.JLabel();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jLabel81 = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jLabel80 = new javax.swing.JLabel();

        pop.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        Delete.setText("Delete");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });
        pop.add(Delete);

        update.setText("Update");
        update.setToolTipText("");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        pop.add(update);

        deletepro.setText("Delete");
        deletepro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteproActionPerformed(evt);
            }
        });
        producttablePOP.add(deletepro);

        deletecat.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        deletecat.setText("Delete");
        deletecat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletecatActionPerformed(evt);
            }
        });
        catpop.add(deletecat);

        saleDelete.setText("Delete");
        saleDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saleDeleteActionPerformed(evt);
            }
        });
        salepop.add(saleDelete);

        deleteandadd.setText("Delete");
        deleteandadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteandaddActionPerformed(evt);
            }
        });
        exchangepop.add(deleteandadd);

        empdelete.setText("Delete");
        empdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empdeleteActionPerformed(evt);
            }
        });
        emppop.add(empdelete);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1366, 720));

        jTabbedPane1.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(1366, 700));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        billing.setLayout(null);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Baskerville Old Face", 1, 24)); // NOI18N
        jLabel10.setText("SELECTED ITEMS");
        jPanel6.add(jLabel10);
        jLabel10.setBounds(231, 11, 216, 24);

        billingtable.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        billingtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SL", "Item", "Color", "Size", "Quantity", "Total Amount"
            }
        ));
        billingtable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                billingtableMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(billingtable);
        if (billingtable.getColumnModel().getColumnCount() > 0) {
            billingtable.getColumnModel().getColumn(0).setResizable(false);
        }

        jPanel6.add(jScrollPane1);
        jScrollPane1.setBounds(0, 150, 650, 280);

        jLabel12.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jLabel12.setText("Customer Name: ");
        jPanel6.add(jLabel12);
        jLabel12.setBounds(30, 110, 120, 20);

        jLabel13.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jLabel13.setText("Customer Phone:");
        jPanel6.add(jLabel13);
        jLabel13.setBounds(320, 110, 120, 16);
        jPanel6.add(cn);
        cn.setBounds(150, 100, 140, 30);

        cph.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cphKeyReleased(evt);
            }
        });
        jPanel6.add(cph);
        cph.setBounds(470, 97, 140, 30);

        gt.setFont(new java.awt.Font("Baskerville Old Face", 1, 14)); // NOI18N
        jPanel6.add(gt);
        gt.setBounds(540, 430, 100, 30);

        jLabel56.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel56.setText("GRAND TOTAL:");
        jPanel6.add(jLabel56);
        jLabel56.setBounds(430, 430, 109, 30);

        cash.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        cash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashActionPerformed(evt);
            }
        });
        cash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cashKeyReleased(evt);
            }
        });
        jPanel6.add(cash);
        cash.setBounds(540, 550, 100, 32);

        change.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jPanel6.add(change);
        change.setBounds(540, 590, 100, 32);

        jLabel57.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel57.setText("CASH:");
        jPanel6.add(jLabel57);
        jLabel57.setBounds(430, 560, 44, 15);

        jLabel58.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel58.setText("CHANGE:");
        jPanel6.add(jLabel58);
        jLabel58.setBounds(430, 600, 64, 15);

        jLabel59.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel59.setText("DISCOUNT:");
        jPanel6.add(jLabel59);
        jLabel59.setBounds(430, 480, 78, 15);

        dis.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        dis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disActionPerformed(evt);
            }
        });
        dis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                disKeyReleased(evt);
            }
        });
        jPanel6.add(dis);
        dis.setBounds(540, 470, 100, 32);

        total.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jPanel6.add(total);
        total.setBounds(540, 510, 100, 28);

        jLabel60.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel60.setText("TOTAL:");
        jPanel6.add(jLabel60);
        jLabel60.setBounds(430, 520, 64, 15);

        jButton12.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jButton12.setText("DONE");
        jButton12.setBorderPainted(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton12);
        jButton12.setBounds(60, 560, 160, 44);

        jLabel61.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel61.setText("+880");
        jPanel6.add(jLabel61);
        jLabel61.setBounds(440, 110, 28, 15);

        jLabel11.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jLabel11.setText("Receipt Number:");
        jPanel6.add(jLabel11);
        jLabel11.setBounds(30, 70, 110, 16);

        rcpt.setEditable(false);
        rcpt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rcptActionPerformed(evt);
            }
        });
        jPanel6.add(rcpt);
        rcpt.setBounds(150, 60, 140, 30);

        billingdate1.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        billingdate1.setText("DATE:");
        jPanel6.add(billingdate1);
        billingdate1.setBounds(320, 70, 60, 15);

        billingdate.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jPanel6.add(billingdate);
        billingdate.setBounds(470, 60, 140, 30);

        jLabel88.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel88.setText("NEW TOTAL:");
        jPanel6.add(jLabel88);
        jLabel88.setBounds(60, 446, 92, 15);

        jLabel89.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel89.setText("EXTRA AMOUNT:");
        jPanel6.add(jLabel89);
        jLabel89.setBounds(60, 480, 120, 15);

        jLabel92.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel92.setText("0");
        jPanel6.add(jLabel92);
        jLabel92.setBounds(190, 440, 160, 30);

        jLabel93.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel93.setText("0");
        jPanel6.add(jLabel93);
        jLabel93.setBounds(190, 475, 160, 20);

        billing.add(jPanel6);
        jPanel6.setBounds(700, 0, 650, 650);

        jLabel21.setFont(new java.awt.Font("Baskerville Old Face", 0, 22)); // NOI18N
        jLabel21.setText("PRODUCT:");
        billing.add(jLabel21);
        jLabel21.setBounds(60, 110, 120, 20);

        jLabel23.setFont(new java.awt.Font("Baskerville Old Face", 0, 22)); // NOI18N
        jLabel23.setText("COLOR");
        billing.add(jLabel23);
        jLabel23.setBounds(60, 200, 90, 20);

        jLabel24.setFont(new java.awt.Font("Baskerville Old Face", 0, 22)); // NOI18N
        jLabel24.setText("SIZE:");
        billing.add(jLabel24);
        jLabel24.setBounds(60, 270, 80, 20);

        jLabel27.setFont(new java.awt.Font("Baskerville Old Face", 0, 22)); // NOI18N
        jLabel27.setText("SELLING PRICE:");
        billing.add(jLabel27);
        jLabel27.setBounds(60, 340, 170, 20);

        sl_size.setText("N/A");
        billing.add(sl_size);
        sl_size.setBounds(240, 260, 170, 40);

        jLabel28.setFont(new java.awt.Font("Baskerville Old Face", 0, 22)); // NOI18N
        jLabel28.setText("QUANTITY:");
        billing.add(jLabel28);
        jLabel28.setBounds(60, 430, 130, 30);

        sl_product.setEditable(true);
        sl_product.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        sl_product.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sl_productItemStateChanged(evt);
            }
        });
        sl_product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sl_productActionPerformed(evt);
            }
        });
        sl_product.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sl_productKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sl_productKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sl_productKeyTyped(evt);
            }
        });
        billing.add(sl_product);
        sl_product.setBounds(240, 130, 170, 40);

        sl_color.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sl_colorItemStateChanged(evt);
            }
        });
        sl_color.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sl_colorActionPerformed(evt);
            }
        });
        billing.add(sl_color);
        sl_color.setBounds(240, 190, 170, 40);

        sl_sp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sl_spActionPerformed(evt);
            }
        });
        sl_sp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sl_spKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sl_spKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sl_spKeyTyped(evt);
            }
        });
        billing.add(sl_sp);
        sl_sp.setBounds(240, 100, 170, 40);

        qqq.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        qqq.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                qqqStateChanged(evt);
            }
        });
        billing.add(qqq);
        qqq.setBounds(240, 420, 100, 30);

        sellingprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellingpriceActionPerformed(evt);
            }
        });
        sellingprice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sellingpriceKeyReleased(evt);
            }
        });
        billing.add(sellingprice);
        sellingprice.setBounds(240, 330, 170, 40);

        jLabel42.setFont(new java.awt.Font("Baskerville Old Face", 0, 22)); // NOI18N
        jLabel42.setText("TOTAL:");
        billing.add(jLabel42);
        jLabel42.setBounds(60, 510, 90, 23);
        billing.add(totalperitem);
        totalperitem.setBounds(240, 500, 170, 40);

        jButton11.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jButton11.setText("ADD TO TABLE");
        jButton11.setBorderPainted(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        billing.add(jButton11);
        jButton11.setBounds(480, 500, 180, 50);

        jButton14.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jButton14.setText("EXCHANGE ITEM");
        jButton14.setBorderPainted(false);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        billing.add(jButton14);
        jButton14.setBounds(0, 0, 360, 50);

        jButton15.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jButton15.setText("MAKE BILL");
        jButton15.setBorderPainted(false);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        billing.add(jButton15);
        jButton15.setBounds(360, 0, 340, 50);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finalpro/61DvV0Xsr+L._SL1500_.jpg"))); // NOI18N
        billing.add(jLabel9);
        jLabel9.setBounds(0, 0, 1360, 670);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(billing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(billing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("SALE MANAGEMENT", jPanel1);

        jPanel4.setLayout(null);

        ADD.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        ADD.setText("PRODUCT TABLE");
        ADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADDActionPerformed(evt);
            }
        });
        jPanel4.add(ADD);
        ADD.setBounds(0, 210, 350, 60);

        jButton6.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jButton6.setText("STOCK TABLE");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton6);
        jButton6.setBounds(0, 270, 350, 60);

        UPDATE.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        UPDATE.setText("ADD PRODUCT");
        UPDATE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UPDATEActionPerformed(evt);
            }
        });
        jPanel4.add(UPDATE);
        UPDATE.setBounds(0, 150, 350, 60);

        searchpro.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        searchpro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchproActionPerformed(evt);
            }
        });
        searchpro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchproKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchproKeyTyped(evt);
            }
        });
        jPanel4.add(searchpro);
        searchpro.setBounds(790, 50, 220, 40);

        add.setBackground(new java.awt.Color(255, 255, 255));
        add.setLayout(null);

        cattable.setBackground(new java.awt.Color(255, 255, 255));

        categorytable.setModel(new javax.swing.table.DefaultTableModel(
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
        categorytable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                categorytableMouseReleased(evt);
            }
        });
        jScrollPane5.setViewportView(categorytable);

        jLabel37.setFont(new java.awt.Font("Baskerville Old Face", 0, 24)); // NOI18N
        jLabel37.setText("CATEGORY TABLE");

        javax.swing.GroupLayout cattableLayout = new javax.swing.GroupLayout(cattable);
        cattable.setLayout(cattableLayout);
        cattableLayout.setHorizontalGroup(
            cattableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cattableLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(cattableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cattableLayout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addGap(320, 320, 320))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cattableLayout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94))))
        );
        cattableLayout.setVerticalGroup(
            cattableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cattableLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        add.add(cattable);
        cattable.setBounds(0, 0, 910, 490);

        producttable.setBackground(new java.awt.Color(255, 255, 255));
        producttable.setLayout(null);

        protable.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        protable.setModel(new javax.swing.table.DefaultTableModel(
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
        protable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                protableMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(protable);

        producttable.add(jScrollPane4);
        jScrollPane4.setBounds(18, 75, 882, 390);

        jLabel36.setFont(new java.awt.Font("Baskerville Old Face", 1, 24)); // NOI18N
        jLabel36.setText("PRODUCT TABLE");
        producttable.add(jLabel36);
        jLabel36.setBounds(352, 23, 216, 24);

        add.add(producttable);
        producttable.setBounds(0, 0, 910, 490);

        stocktable.setBackground(new java.awt.Color(255, 255, 255));
        stocktable.setLayout(null);

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "PRODUCT", "PID", "SIZE", "COLOR", "TRADE PRICE", "SELLING PRICE", "QUANTITY", "CATEGORY"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                table1MouseReleased(evt);
            }
        });
        table1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                table1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                table1KeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(table1);

        stocktable.add(jScrollPane2);
        jScrollPane2.setBounds(10, 70, 890, 400);

        jLabel22.setFont(new java.awt.Font("Baskerville Old Face", 1, 24)); // NOI18N
        jLabel22.setText("STOCK");
        stocktable.add(jLabel22);
        jLabel22.setBounds(390, 20, 100, 24);

        add.add(stocktable);
        stocktable.setBounds(0, 0, 910, 490);

        updatee.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Baskerville Old Face", 0, 24)); // NOI18N
        jLabel14.setText("Product Name:");

        jLabel15.setFont(new java.awt.Font("Baskerville Old Face", 0, 24)); // NOI18N
        jLabel15.setText("Category:");

        jLabel16.setFont(new java.awt.Font("Baskerville Old Face", 0, 24)); // NOI18N
        jLabel16.setText("Size:");

        jLabel17.setFont(new java.awt.Font("Baskerville Old Face", 0, 24)); // NOI18N
        jLabel17.setText("Color:");

        jLabel18.setFont(new java.awt.Font("Baskerville Old Face", 0, 24)); // NOI18N
        jLabel18.setText("Trade Price:");

        jLabel19.setFont(new java.awt.Font("Baskerville Old Face", 0, 24)); // NOI18N
        jLabel19.setText("Selling Price:");

        jLabel20.setFont(new java.awt.Font("Baskerville Old Face", 0, 24)); // NOI18N
        jLabel20.setText("Quantity:");

        tradep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tradepActionPerformed(evt);
            }
        });

        quantit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantitActionPerformed(evt);
            }
        });

        proid.setEditable(false);
        proid.setForeground(new java.awt.Color(255, 0, 0));

        jButton1.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jButton1.setText("UPDATE INFO");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel90.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jLabel90.setText("Product ID:");

        javax.swing.GroupLayout updateeLayout = new javax.swing.GroupLayout(updatee);
        updatee.setLayout(updateeLayout);
        updateeLayout.setHorizontalGroup(
            updateeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateeLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pname, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tradep, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(updateeLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140)
                .addComponent(jLabel19)
                .addGap(15, 15, 15)
                .addComponent(sellingp, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(updateeLayout.createSequentialGroup()
                .addGap(410, 410, 410)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(updateeLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(updateeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateeLayout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(siz, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(updateeLayout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(colo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(140, 140, 140)
                .addGroup(updateeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(updateeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(quantit)
                    .addComponent(proid, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)))
        );
        updateeLayout.setVerticalGroup(
            updateeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateeLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(updateeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(pname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(tradep, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(updateeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(sellingp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(updateeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(siz, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(quantit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(updateeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addGroup(updateeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(colo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(proid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(60, 60, 60)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add.add(updatee);
        updatee.setBounds(0, 0, 910, 490);

        jLabel2.setFont(new java.awt.Font("Baskerville Old Face", 0, 22)); // NOI18N
        jLabel2.setText("PRODUCT NAME:");
        add.add(jLabel2);
        jLabel2.setBounds(80, 50, 210, 40);

        jLabel3.setFont(new java.awt.Font("Baskerville Old Face", 0, 22)); // NOI18N
        jLabel3.setText("CATEGORY:");
        add.add(jLabel3);
        jLabel3.setBounds(510, 50, 140, 30);

        jLabel4.setFont(new java.awt.Font("Baskerville Old Face", 0, 22)); // NOI18N
        jLabel4.setText("SIZE:");
        add.add(jLabel4);
        jLabel4.setBounds(80, 110, 70, 40);

        jLabel5.setFont(new java.awt.Font("Baskerville Old Face", 0, 22)); // NOI18N
        jLabel5.setText("COLOR:");
        add.add(jLabel5);
        jLabel5.setBounds(80, 170, 100, 30);

        jLabel6.setFont(new java.awt.Font("Baskerville Old Face", 0, 22)); // NOI18N
        jLabel6.setText("TRADE PRICE:");
        add.add(jLabel6);
        jLabel6.setBounds(80, 230, 190, 40);

        jLabel7.setFont(new java.awt.Font("Baskerville Old Face", 0, 22)); // NOI18N
        jLabel7.setText("SELLING PRICE:");
        add.add(jLabel7);
        jLabel7.setBounds(80, 300, 190, 30);

        product.setEditable(true);
        add.add(product);
        product.setBounds(270, 50, 160, 30);

        cat.setEditable(true);
        cat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                catActionPerformed(evt);
            }
        });
        add.add(cat);
        cat.setBounds(660, 50, 150, 30);

        jLabel8.setFont(new java.awt.Font("Baskerville Old Face", 0, 22)); // NOI18N
        jLabel8.setText("QUANTITY:");
        add.add(jLabel8);
        jLabel8.setBounds(80, 370, 130, 30);

        size.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sizeActionPerformed(evt);
            }
        });
        size.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sizeKeyReleased(evt);
            }
        });
        add.add(size);
        size.setBounds(270, 110, 160, 30);
        add.add(color);
        color.setBounds(270, 170, 160, 30);

        tp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tpActionPerformed(evt);
            }
        });
        tp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tpKeyReleased(evt);
            }
        });
        add.add(tp);
        tp.setBounds(270, 230, 160, 30);

        sp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spActionPerformed(evt);
            }
        });
        sp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                spKeyReleased(evt);
            }
        });
        add.add(sp);
        sp.setBounds(270, 300, 160, 30);

        quantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                quantityKeyReleased(evt);
            }
        });
        add.add(quantity);
        quantity.setBounds(270, 370, 160, 30);

        jButton2.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jButton2.setText("SUBMIT");
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add.add(jButton2);
        jButton2.setBounds(670, 270, 160, 50);

        jButton34.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jButton34.setText("CLEAR");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });
        add.add(jButton34);
        jButton34.setBounds(669, 333, 160, 50);

        jPanel4.add(add);
        add.setBounds(360, 110, 910, 490);

        jButton10.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jButton10.setText("CATEGORY TABLE");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton10);
        jButton10.setBounds(0, 330, 350, 60);

        jLabel41.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jLabel41.setText("SEARCH PRODUCT:");
        jPanel4.add(jLabel41);
        jLabel41.setBounds(620, 60, 170, 19);

        jLabel1.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finalpro/61DvV0Xsr+L._SL1500_.jpg"))); // NOI18N
        jPanel4.add(jLabel1);
        jLabel1.setBounds(0, 0, 1360, 690);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("STOCK MANAGEMENT", jPanel2);

        jPanel3.setLayout(null);

        insert_emp.setBackground(new java.awt.Color(255, 255, 255));
        insert_emp.setLayout(null);

        checksalary.setBackground(new java.awt.Color(255, 255, 255));

        jLabel43.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jLabel43.setText("MONTH:");

        jLabel44.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jLabel44.setText("YEAR:");

        jButton9.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jButton9.setText("SUBMIT");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        yearr.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        yearr.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028" }));

        mon.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        mon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "july", "August", "September", "October", "November", "December" }));

        jLabel45.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jLabel45.setText("ATTENDANCE:");

        jLabel46.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jLabel46.setText("SALARY:");

        jLabel47.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jLabel47.setText("STATUS:");

        at.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N

        sal.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N

        jLabel55.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jLabel55.setText("EMPLOYEE ID:");

        emid.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        emid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emidActionPerformed(evt);
            }
        });

        status.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Paid", "Pending" }));

        jButton21.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jButton21.setText("UPDATE ");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        stat.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N

        javax.swing.GroupLayout checksalaryLayout = new javax.swing.GroupLayout(checksalary);
        checksalary.setLayout(checksalaryLayout);
        checksalaryLayout.setHorizontalGroup(
            checksalaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checksalaryLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(checksalaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addGroup(checksalaryLayout.createSequentialGroup()
                        .addGroup(checksalaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43)
                            .addComponent(jLabel44)
                            .addComponent(jLabel55))
                        .addGap(21, 21, 21)
                        .addGroup(checksalaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(yearr, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mon, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(emid))
                        .addGroup(checksalaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checksalaryLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(checksalaryLayout.createSequentialGroup()
                                .addGroup(checksalaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(checksalaryLayout.createSequentialGroup()
                                        .addGap(139, 139, 139)
                                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(checksalaryLayout.createSequentialGroup()
                                        .addGap(137, 137, 137)
                                        .addGroup(checksalaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(checksalaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(at, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                                    .addComponent(sal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(stat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(23, 23, 23))
        );
        checksalaryLayout.setVerticalGroup(
            checksalaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checksalaryLayout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(checksalaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checksalaryLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(at, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(sal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(checksalaryLayout.createSequentialGroup()
                        .addGroup(checksalaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emid, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel55)
                            .addComponent(jLabel45))
                        .addGap(28, 28, 28)
                        .addGroup(checksalaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43)
                            .addComponent(mon, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46))))
                .addGap(32, 32, 32)
                .addGroup(checksalaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(yearr, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47)
                    .addComponent(stat, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(checksalaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(129, 129, 129))
        );

        insert_emp.add(checksalary);
        checksalary.setBounds(0, 0, 840, 500);

        attendance.setBackground(new java.awt.Color(255, 255, 255));
        attendance.setLayout(null);

        empid.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        empid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empidActionPerformed(evt);
            }
        });
        empid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                empidKeyReleased(evt);
            }
        });
        attendance.add(empid);
        empid.setBounds(470, 170, 181, 37);

        jLabel38.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jLabel38.setText("ENTER EMPLOYEE ID:");
        attendance.add(jLabel38);
        jLabel38.setBounds(280, 180, 170, 16);

        jLabel39.setFont(new java.awt.Font("Baskerville Old Face", 1, 24)); // NOI18N
        jLabel39.setText("ATTENDANCE");
        attendance.add(jLabel39);
        jLabel39.setBounds(360, 60, 177, 24);

        insert_emp.add(attendance);
        attendance.setBounds(0, 0, 840, 500);

        emptable.setBackground(new java.awt.Color(255, 255, 255));
        emptable.setLayout(null);

        emp_table.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        emp_table.setModel(new javax.swing.table.DefaultTableModel(
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
        emp_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                emp_tableMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(emp_table);

        emptable.add(jScrollPane3);
        jScrollPane3.setBounds(10, 37, 820, 420);

        insert_emp.add(emptable);
        emptable.setBounds(0, 0, 840, 470);

        jLabel26.setFont(new java.awt.Font("Baskerville Old Face", 0, 22)); // NOI18N
        jLabel26.setText("Employee Name:");
        insert_emp.add(jLabel26);
        jLabel26.setBounds(120, 90, 154, 23);

        jLabel29.setFont(new java.awt.Font("Baskerville Old Face", 0, 22)); // NOI18N
        jLabel29.setText("Phone No:");
        insert_emp.add(jLabel29);
        jLabel29.setBounds(120, 160, 96, 23);

        jLabel30.setFont(new java.awt.Font("Baskerville Old Face", 0, 22)); // NOI18N
        jLabel30.setText("Address:");
        insert_emp.add(jLabel30);
        jLabel30.setBounds(120, 230, 96, 23);

        jLabel31.setFont(new java.awt.Font("Baskerville Old Face", 0, 22)); // NOI18N
        jLabel31.setText("NID:");
        insert_emp.add(jLabel31);
        jLabel31.setBounds(120, 300, 47, 23);

        jLabel32.setFont(new java.awt.Font("Baskerville Old Face", 0, 22)); // NOI18N
        jLabel32.setText("Salary(Monthly):");
        insert_emp.add(jLabel32);
        jLabel32.setBounds(120, 370, 142, 23);

        jLabel33.setFont(new java.awt.Font("Baskerville Old Face", 0, 22)); // NOI18N
        jLabel33.setText("Position:");
        insert_emp.add(jLabel33);
        jLabel33.setBounds(120, 440, 75, 23);

        emp_name.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        emp_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emp_nameActionPerformed(evt);
            }
        });
        emp_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emp_nameKeyReleased(evt);
            }
        });
        insert_emp.add(emp_name);
        emp_name.setBounds(280, 80, 170, 40);

        emp_phn.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        emp_phn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emp_phnActionPerformed(evt);
            }
        });
        emp_phn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emp_phnKeyReleased(evt);
            }
        });
        insert_emp.add(emp_phn);
        emp_phn.setBounds(280, 150, 170, 40);

        emp_address.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        insert_emp.add(emp_address);
        emp_address.setBounds(280, 220, 170, 40);

        emp_nid.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        emp_nid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emp_nidActionPerformed(evt);
            }
        });
        emp_nid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emp_nidKeyReleased(evt);
            }
        });
        insert_emp.add(emp_nid);
        emp_nid.setBounds(280, 290, 170, 40);

        emp_monthly_sal.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        emp_monthly_sal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emp_monthly_salActionPerformed(evt);
            }
        });
        emp_monthly_sal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emp_monthly_salKeyReleased(evt);
            }
        });
        insert_emp.add(emp_monthly_sal);
        emp_monthly_sal.setBounds(280, 360, 170, 40);

        emp_position.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        insert_emp.add(emp_position);
        emp_position.setBounds(280, 430, 170, 40);

        jButton8.setText("SUBMIT");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        insert_emp.add(jButton8);
        jButton8.setBounds(540, 430, 140, 40);

        jLabel34.setFont(new java.awt.Font("Baskerville Old Face", 0, 24)); // NOI18N
        jLabel34.setText("EMPLOYEE REGISTRATION");
        insert_emp.add(jLabel34);
        jLabel34.setBounds(290, 20, 330, 24);

        jLabel35.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jLabel35.setText("+880");
        insert_emp.add(jLabel35);
        jLabel35.setBounds(230, 160, 40, 20);

        jPanel3.add(insert_emp);
        insert_emp.setBounds(380, 110, 840, 500);

        jButton3.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jButton3.setText("EMPLOYEE REGISTRATION");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3);
        jButton3.setBounds(0, 170, 380, 60);

        jButton4.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jButton4.setText("ATTENDANCE");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4);
        jButton4.setBounds(0, 230, 380, 60);

        jButton5.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jButton5.setText("EMPLOYEE TABLE");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5);
        jButton5.setBounds(0, 290, 380, 60);

        emp_search.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        emp_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emp_searchActionPerformed(evt);
            }
        });
        emp_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emp_searchKeyReleased(evt);
            }
        });
        jPanel3.add(emp_search);
        emp_search.setBounds(770, 50, 240, 40);

        jLabel40.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jLabel40.setText("SEARCH EMPLOYEE:");
        jPanel3.add(jLabel40);
        jLabel40.setBounds(580, 60, 180, 19);

        jButton7.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jButton7.setText("CHECK SALARY");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7);
        jButton7.setBounds(0, 350, 380, 60);

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finalpro/61DvV0Xsr+L._SL1500_.jpg"))); // NOI18N
        jPanel3.add(jLabel25);
        jLabel25.setBounds(0, 0, 1360, 690);
        jPanel3.add(jSeparator2);
        jSeparator2.setBounds(1080, 40, 0, 2);

        jTabbedPane1.addTab("EMPLOYEE MANAGEMENT", jPanel3);

        jPanel7.setLayout(null);

        receiptdetails.setBackground(new java.awt.Color(255, 255, 255));
        receiptdetails.setLayout(null);

        solditems.setBackground(new java.awt.Color(255, 255, 255));
        solditems.setLayout(null);

        solditemtable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(solditemtable);

        solditems.add(jScrollPane7);
        jScrollPane7.setBounds(25, 77, 875, 452);

        jLabel50.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jLabel50.setText("SEARCH BY RECEIPT NO:");
        solditems.add(jLabel50);
        jLabel50.setBounds(25, 46, 193, 16);

        jTextField1.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        solditems.add(jTextField1);
        jTextField1.setBounds(222, 37, 137, 34);

        jLabel51.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jLabel51.setText("SEARCH BY DATE:");
        solditems.add(jLabel51);
        jLabel51.setBounds(440, 46, 141, 16);

        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        solditems.add(jDateChooser1);
        jDateChooser1.setBounds(591, 37, 147, 34);

        jButton13.setText("ENTER");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        solditems.add(jButton13);
        jButton13.setBounds(748, 37, 87, 34);

        receiptdetails.add(solditems);
        solditems.setBounds(0, 0, 930, 540);

        bill.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(bill);

        receiptdetails.add(jScrollPane6);
        jScrollPane6.setBounds(10, 93, 920, 380);

        jLabel49.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jLabel49.setText("ENTER A DATE:");
        receiptdetails.add(jLabel49);
        jLabel49.setBounds(308, 40, 120, 16);

        searchd.setDateFormatString("yyyy-MM-dd");
        receiptdetails.add(searchd);
        searchd.setBounds(432, 30, 167, 36);

        jButton20.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jButton20.setText("SEARCH");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        receiptdetails.add(jButton20);
        jButton20.setBounds(617, 30, 118, 36);

        jLabel53.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jLabel53.setText("TOTAL AMOUNT:");
        receiptdetails.add(jLabel53);
        jLabel53.setBounds(20, 510, 137, 20);

        tm.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        receiptdetails.add(tm);
        tm.setBounds(160, 500, 298, 32);

        jPanel7.add(receiptdetails);
        receiptdetails.setBounds(330, 80, 930, 540);

        jButton16.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jButton16.setText("RECEIPT DETAILS");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton16);
        jButton16.setBounds(0, 150, 330, 60);

        jButton17.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jButton17.setText("SOLD ITEMS");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton17);
        jButton17.setBounds(0, 210, 330, 60);

        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finalpro/61DvV0Xsr+L._SL1500_.jpg"))); // NOI18N
        jPanel7.add(jLabel48);
        jLabel48.setBounds(0, 0, 1360, 670);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );

        jPanel7.add(jPanel10);
        jPanel10.setBounds(0, 0, 930, 540);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("RECEIPT MANAGEMENT", jPanel5);

        jPanel8.setLayout(null);

        jButton22.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jButton22.setText("SAVE DAILY EXPENSES");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton22);
        jButton22.setBounds(0, 190, 340, 70);

        jButton23.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jButton23.setText("DAILY RECORDS");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton23);
        jButton23.setBounds(0, 330, 340, 70);

        expenses.setBackground(new java.awt.Color(255, 255, 255));
        expenses.setLayout(null);

        incomed.setBackground(new java.awt.Color(255, 255, 255));
        incomed.setLayout(null);

        incometable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane11.setViewportView(incometable);

        incomed.add(jScrollPane11);
        jScrollPane11.setBounds(18, 125, 829, 364);

        jLabel76.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jLabel76.setText("INCOME DETAILS");
        incomed.add(jLabel76);
        jLabel76.setBounds(403, 19, 154, 19);

        jLabel77.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jLabel77.setText("ENTER A DATE:");
        incomed.add(jLabel77);
        jLabel77.setBounds(18, 70, 120, 16);

        jDateChooser3.setDateFormatString("yyyy-MM-dd");
        incomed.add(jDateChooser3);
        jDateChooser3.setBounds(140, 60, 151, 40);

        jButton30.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jButton30.setText("SEARCH");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        incomed.add(jButton30);
        jButton30.setBounds(310, 63, 120, 40);

        jLabel78.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jLabel78.setText("TOTAL INCOME:");
        incomed.add(jLabel78);
        jLabel78.setBounds(540, 80, 130, 16);

        jLabel79.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        incomed.add(jLabel79);
        jLabel79.setBounds(680, 70, 110, 30);

        expenses.add(incomed);
        incomed.setBounds(0, 0, 870, 500);

        expensed.setBackground(new java.awt.Color(255, 255, 255));
        expensed.setLayout(null);

        expensetable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane10.setViewportView(expensetable);

        expensed.add(jScrollPane10);
        jScrollPane10.setBounds(31, 113, 793, 380);

        jLabel72.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jLabel72.setText("EXPENSE DETAILS");
        expensed.add(jLabel72);
        jLabel72.setBounds(373, 11, 159, 19);

        jLabel73.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jLabel73.setText("ENTER A DATE:");
        expensed.add(jLabel73);
        jLabel73.setBounds(40, 68, 120, 16);

        jDateChooser2.setDateFormatString("yyyy-MM-dd");
        expensed.add(jDateChooser2);
        jDateChooser2.setBounds(164, 60, 145, 35);

        jButton29.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jButton29.setText("SEARCH");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });
        expensed.add(jButton29);
        jButton29.setBounds(327, 60, 119, 35);

        jLabel74.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jLabel74.setText("TOTAL EXPENSES:");
        expensed.add(jLabel74);
        jLabel74.setBounds(579, 69, 143, 16);

        jLabel75.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        expensed.add(jLabel75);
        jLabel75.setBounds(730, 60, 120, 30);

        expenses.add(expensed);
        expensed.setBounds(0, 0, 870, 500);

        drecord.setBackground(new java.awt.Color(255, 255, 255));
        drecord.setLayout(null);

        dailyrecord.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane9.setViewportView(dailyrecord);

        drecord.add(jScrollPane9);
        jScrollPane9.setBounds(10, 45, 850, 444);

        jLabel70.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jLabel70.setText("Daily Record");
        drecord.add(jLabel70);
        jLabel70.setBounds(370, 20, 100, 19);

        expenses.add(drecord);
        drecord.setBounds(0, 0, 870, 500);

        income.setBackground(new java.awt.Color(255, 255, 255));
        income.setLayout(null);

        jLabel66.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jLabel66.setText("DATE:");
        income.add(jLabel66);
        jLabel66.setBounds(150, 120, 53, 19);

        jLabel67.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jLabel67.setText("ENTTER AMOUNT:");
        income.add(jLabel67);
        jLabel67.setBounds(150, 180, 170, 19);

        jLabel68.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jLabel68.setText("PURPOSE:");
        income.add(jLabel68);
        jLabel68.setBounds(150, 250, 85, 19);

        jLabel69.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jLabel69.setText("TODAY'S TOTAL INCOME:");
        income.add(jLabel69);
        jLabel69.setBounds(150, 310, 227, 19);

        income_date.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        income_date.setText("jLabel70");
        income.add(income_date);
        income_date.setBounds(330, 120, 130, 19);

        aaa.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        aaa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                aaaKeyReleased(evt);
            }
        });
        income.add(aaa);
        aaa.setBounds(320, 170, 207, 35);

        ppp.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        income.add(ppp);
        ppp.setBounds(320, 230, 207, 36);

        jLabel71.setFont(new java.awt.Font("Baskerville Old Face", 0, 24)); // NOI18N
        jLabel71.setText("SAVE DAILY INCOME");
        income.add(jLabel71);
        jLabel71.setBounds(300, 50, 260, 24);

        ttt.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        income.add(ttt);
        ttt.setBounds(380, 300, 190, 30);

        jButton28.setText("jButton28");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });
        income.add(jButton28);
        jButton28.setBounds(580, 230, 113, 36);

        expenses.add(income);
        income.setBounds(0, 0, 870, 500);

        jLabel52.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jLabel52.setText("DATE:");
        expenses.add(jLabel52);
        jLabel52.setBounds(136, 104, 53, 19);

        jLabel54.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jLabel54.setText("ENTER AMOUNT:");
        expenses.add(jLabel54);
        jLabel54.setBounds(130, 170, 152, 19);

        jLabel62.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jLabel62.setText("PURPOSE:");
        expenses.add(jLabel62);
        jLabel62.setBounds(130, 240, 85, 19);

        date.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        expenses.add(date);
        date.setBounds(300, 100, 200, 30);

        amoun.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        amoun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amounActionPerformed(evt);
            }
        });
        amoun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                amounKeyReleased(evt);
            }
        });
        expenses.add(amoun);
        amoun.setBounds(300, 160, 199, 38);

        pur.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        pur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purActionPerformed(evt);
            }
        });
        expenses.add(pur);
        pur.setBounds(300, 230, 199, 39);

        jButton24.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jButton24.setText("SAVE");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        expenses.add(jButton24);
        jButton24.setBounds(540, 231, 118, 40);

        jLabel64.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jLabel64.setText("TODAY'S TOTAL EXPENSES:");
        expenses.add(jLabel64);
        jLabel64.setBounds(130, 310, 241, 19);

        tootal.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        expenses.add(tootal);
        tootal.setBounds(380, 310, 62, 19);

        jLabel65.setFont(new java.awt.Font("Baskerville Old Face", 0, 24)); // NOI18N
        jLabel65.setText("RECORD DAILY EXPENSES");
        expenses.add(jLabel65);
        jLabel65.setBounds(310, 40, 320, 24);

        jPanel8.add(expenses);
        expenses.setBounds(340, 140, 870, 500);

        jButton25.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jButton25.setText("SAVE DAILY INCOME");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton25);
        jButton25.setBounds(0, 260, 340, 70);

        jButton27.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jButton27.setText("INCOME DETAILS");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton27);
        jButton27.setBounds(0, 470, 340, 70);

        jButton26.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jButton26.setText("EXPENSE DETAILS");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton26);
        jButton26.setBounds(0, 400, 340, 70);

        jLabel63.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jLabel63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finalpro/61DvV0Xsr+L._SL1500_.jpg"))); // NOI18N
        jPanel8.add(jLabel63);
        jLabel63.setBounds(0, 0, 1360, 670);

        jTabbedPane1.addTab("DAILY RECORD MANAGEMENT", jPanel8);

        jPanel11.setLayout(null);

        yearly.setBackground(new java.awt.Color(255, 255, 255));

        yearly_table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane12.setViewportView(yearly_table);

        jLabel87.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jLabel87.setText("YEARLY RECORD");

        javax.swing.GroupLayout yearlyLayout = new javax.swing.GroupLayout(yearly);
        yearly.setLayout(yearlyLayout);
        yearlyLayout.setHorizontalGroup(
            yearlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(yearlyLayout.createSequentialGroup()
                .addGroup(yearlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(yearlyLayout.createSequentialGroup()
                        .addGap(322, 322, 322)
                        .addComponent(jLabel87))
                    .addGroup(yearlyLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        yearlyLayout.setVerticalGroup(
            yearlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, yearlyLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel87)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel11.add(yearly);
        yearly.setBounds(420, 40, 830, 470);

        check.setBackground(new java.awt.Color(255, 255, 255));
        check.setLayout(null);

        monthly.setBackground(new java.awt.Color(255, 255, 255));
        monthly.setLayout(null);

        monthly_table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(monthly_table);

        monthly.add(jScrollPane8);
        jScrollPane8.setBounds(10, 102, 820, 387);

        jLabel86.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jLabel86.setText("MONTHLY RECORD");
        monthly.add(jLabel86);
        jLabel86.setBounds(360, 33, 176, 19);

        check.add(monthly);
        monthly.setBounds(0, 0, 910, 500);

        jLabel82.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jLabel82.setText("ENTER YEAR:");
        check.add(jLabel82);
        jLabel82.setBounds(80, 210, 120, 19);

        jLabel83.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jLabel83.setText("TOTAL EXPENSES:");
        check.add(jLabel83);
        jLabel83.setBounds(80, 260, 160, 20);

        jLabel84.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jLabel84.setText("TOTAL INCOME:");
        check.add(jLabel84);
        jLabel84.setBounds(80, 310, 150, 19);

        jLabel85.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jLabel85.setText("PROFIT:");
        check.add(jLabel85);
        jLabel85.setBounds(80, 360, 80, 19);

        totalinc.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        check.add(totalinc);
        totalinc.setBounds(250, 300, 120, 30);

        totalprofit.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        check.add(totalprofit);
        totalprofit.setBounds(250, 350, 120, 30);

        totalexp.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        check.add(totalexp);
        totalexp.setBounds(250, 249, 120, 30);

        month.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        check.add(month);
        month.setBounds(250, 140, 130, 40);

        year.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        check.add(year);
        year.setBounds(250, 200, 130, 40);

        mess.setFont(new java.awt.Font("Baskerville Old Face", 0, 36)); // NOI18N
        mess.setForeground(new java.awt.Color(255, 0, 0));
        check.add(mess);
        mess.setBounds(340, 490, 750, 30);

        jButton31.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jButton31.setText("MONTHLY");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });
        check.add(jButton31);
        jButton31.setBounds(430, 140, 130, 41);

        jButton32.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jButton32.setText("YEARLY");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });
        check.add(jButton32);
        jButton32.setBounds(580, 140, 127, 41);

        jLabel81.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jLabel81.setText("ENTER MONTH:");
        check.add(jLabel81);
        jLabel81.setBounds(80, 160, 150, 19);

        jPanel11.add(check);
        check.setBounds(420, 40, 830, 500);

        jButton18.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jButton18.setText("CHECK PROFIT");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton18);
        jButton18.setBounds(0, 170, 400, 60);

        jButton19.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jButton19.setText("MONTHLY RECORD");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton19);
        jButton19.setBounds(0, 230, 400, 60);

        jButton33.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        jButton33.setText("YEARLY RECORD");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton33);
        jButton33.setBounds(0, 290, 400, 60);

        jLabel80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finalpro/61DvV0Xsr+L._SL1500_.jpg"))); // NOI18N
        jPanel11.add(jLabel80);
        jLabel80.setBounds(0, 0, 1360, 670);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("REPORT GENERATE", jPanel9);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ADDActionPerformed
      showtableprotable();
      add.setVisible(true);
      producttable.setVisible(true);
      stocktable.setVisible(false);
      updatee.setVisible(false);
      cattable.setVisible(false);
      
      
        
    }//GEN-LAST:event_ADDActionPerformed

    private void UPDATEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UPDATEActionPerformed
        stocktable.setVisible(false);
        updatee.setVisible(false);
        producttable.setVisible(false);
        cattable.setVisible(false);
        add.setVisible(true);
    }//GEN-LAST:event_UPDATEActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        if( 
                (tp.getText()).equals("") || (sp.getText()).equals("") ||
                (quantity.getText()).equals("")){
            JOptionPane.showMessageDialog(null,"Please fill up all fields!");
            
        
        }
        else{
            try {
            stock st1=new stock();
            st1.setPname((product.getEditor().getItem()).toString().toUpperCase());
            st1.setCatname((cat.getEditor().getItem()).toString().toUpperCase());
            if(color.getText().equals(""))
            {
                st1.setColor(null);
            }
            else
            {
                st1.setColor(color.getText().toUpperCase());
            }
            if(size.getText().equals(""))
            {
                st1.setSize(null);
            }
            else
            {
                st1.setSize(size.getText().toUpperCase());
            }
            
            st1.setWholesaleprice(Integer.parseInt(tp.getText()));
            st1.setSellingp(Integer.parseInt(sp.getText()));
            st1.setQuantity(Integer.parseInt(quantity.getText()));
            st1.stock_add();
            table1.removeAll();
           showtable1();
           
           
            
        } catch (SQLException ex) {
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        
                
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String nname=pname.getText();
        String sii=siz.getText();
        String coll=colo.getText();
        int proidd=Integer.parseInt(proid.getText());
      
      
        try {
            
            PreparedStatement ppp=con.prepareStatement("select * from stock where pid=?");
            ppp.setInt(1, proidd);
            ResultSet rrr=ppp.executeQuery();
            while(rrr.next()){
                
                if(!(sii.equals(rrr.getString("size"))))
                {
                    PreparedStatement pr=con.prepareStatement("update stock set size=? where pid=?");
                    pr.setString(1, sii);
                    pr.setInt(2, proidd);
                    pr.executeUpdate();
                    
                }
                
                if(!(coll.equals(rrr.getString("color"))))
                {
                    PreparedStatement pr=con.prepareStatement("update stock set color=? where pid=?");
                    pr.setString(1, coll);
                    pr.setInt(2, proidd);
                    pr.executeUpdate();
                    
                }
                if(Integer.parseInt(quantit.getText())!=Integer.parseInt(rrr.getString("quantity")))
                {
                    PreparedStatement pr=con.prepareStatement("update stock set quantity=? where pid=?");
                    pr.setInt(1, Integer.parseInt(quantit.getText()));
                    pr.setInt(2, proidd);
                    pr.executeUpdate();
                    
                }
                if(Integer.parseInt(tradep.getText())!=Integer.parseInt(rrr.getString("wholeSalePrice")))
                {
                    PreparedStatement pr=con.prepareStatement("update stock set wholeSalePrice=? where pid=?");
                    pr.setInt(1, Integer.parseInt(tradep.getText()));
                    pr.setInt(2, proidd);
                    pr.executeUpdate();
                    
                }
                if(Integer.parseInt(sellingp.getText())!=Integer.parseInt(rrr.getString("sellingPrice")))
                {
                    PreparedStatement pr=con.prepareStatement("update stock set sellingPrice=? where pid=?");
                    pr.setInt(1, Integer.parseInt(sellingp.getText()));
                    pr.setInt(2, proidd);
                    pr.executeUpdate();
                    
                }
                
            }
            
            PreparedStatement prin=con.prepareStatement("select * from productinfo where pid=?");
            prin.setInt(1, proidd);
            ResultSet rrin=prin.executeQuery();
            while(rrin.next()){
                if(!(nname.equals(rrin.getString("pname"))))
                {
                    PreparedStatement pr=con.prepareStatement("update productinfo set pname=? where pid=?");
                    pr.setString(1, nname);
                    pr.setInt(2, proidd);
                    pr.executeUpdate();
                    
                }
            }
            PreparedStatement prcat=con.prepareStatement("select * from category where cid="
                    + "(select cid from productinfo where pid=?)");
            prcat.setInt(1, proidd);
            ResultSet rrcat=prcat.executeQuery();
            while(rrcat.next()){
                if(!(category.getText().equals(rrcat.getString("catN"))))
                {
                    PreparedStatement pr=con.prepareStatement("update category set catN=? where cid=?");
                    pr.setString(1, category.getText());
                    pr.setInt(2, rrcat.getInt("cid"));
                    pr.executeUpdate();
                    
                }
            }
//            PreparedStatement pd=con.prepareStatement("delete from stock where pid=(select pid from productinfo where pname=?)and size=? and color=?");
//            pd.setString(1, nname);
//            pd.setString(2, sii);
//            pd.setString(3, coll);
//            pd.executeUpdate();
//
//            
//            String catt=category.getText();
//
//            int t=Integer.parseInt(tradep.getText());
//            int s=Integer.parseInt(sellingp.getText());
//            int q=Integer.parseInt(quantit.getText());
//
//            Phar obo = new Phar();
//            System.out.println("before call");
//            obo.addproduct(nname, catt,sii,coll,t,s, q, pidd);
            table1.removeAll();
            showtable1();
            add.setVisible(true);
            stocktable.setVisible(true);
            updatee.setVisible(false);

        } catch (SQLException ex) {
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void sizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sizeActionPerformed
        
    }//GEN-LAST:event_sizeActionPerformed

    private void sizeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sizeKeyReleased
        
    }//GEN-LAST:event_sizeKeyReleased

    private void tpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tpKeyReleased
        String value = tp.getText();
        numberCheck(value);
        
        
    
    }//GEN-LAST:event_tpKeyReleased

    private void tpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tpActionPerformed

    private void spActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_spActionPerformed

    private void spKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spKeyReleased
        String value = sp.getText();
        numberCheck(value);
    }//GEN-LAST:event_spKeyReleased

    private void quantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantityKeyReleased
        String value = tp.getText();
        numberCheck(value);
    }//GEN-LAST:event_quantityKeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       table1.removeAll();
        showtable1();
        updatee.setVisible(false);
        add.setVisible(true);
        stocktable.setVisible(true);
        producttable.setVisible(false);
        cattable.setVisible(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void quantitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantitActionPerformed
       
                
    }//GEN-LAST:event_quantitActionPerformed

    private void table1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseReleased
        if(evt.isPopupTrigger())
            {
                pop.show(this,evt.getXOnScreen(),evt.getYOnScreen());
                
            }
    }//GEN-LAST:event_table1MouseReleased

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        try {
             if((table1.getValueAt(table1.getSelectedRow(), 1)) == null && (table1.getValueAt(table1.getSelectedRow(), 2)) == null && (table1.getValueAt(table1.getSelectedRow(), 3)) == null)
            {
                JOptionPane.showMessageDialog(null,"Select a row first");
            }
             else
             {
            if((table1.getValueAt(table1.getSelectedRow(), 2)) != null && (table1.getValueAt(table1.getSelectedRow(), 3)) != null)
            {
        PreparedStatement pst = con.prepareStatement("delete from stock where pid=? and size=? and color=?");
        pst.setString(1, table1.getValueAt(table1.getSelectedRow(), 1).toString().toUpperCase());
        pst.setString(2, table1.getValueAt(table1.getSelectedRow(), 2).toString().toUpperCase());
        pst.setString(3, table1.getValueAt(table1.getSelectedRow(), 3).toString().toUpperCase());
        pst.executeUpdate();}
            if((table1.getValueAt(table1.getSelectedRow(), 2)) == null && (table1.getValueAt(table1.getSelectedRow(), 3)) != null)
            {
        PreparedStatement pst = con.prepareStatement("delete from stock where pid=? and color=?");
        pst.setString(1, table1.getValueAt(table1.getSelectedRow(), 1).toString().toUpperCase());
       
        pst.setString(3, table1.getValueAt(table1.getSelectedRow(), 3).toString().toUpperCase());
        pst.executeUpdate();}
            if((table1.getValueAt(table1.getSelectedRow(), 2)) != null && (table1.getValueAt(table1.getSelectedRow(), 3)) == null)
            {
        PreparedStatement pst = con.prepareStatement("delete from stock where pid=? and color=?");
        pst.setString(1, table1.getValueAt(table1.getSelectedRow(), 1).toString().toUpperCase());
       
        pst.setString(3, table1.getValueAt(table1.getSelectedRow(), 2).toString().toUpperCase());
        pst.executeUpdate();}
            if((table1.getValueAt(table1.getSelectedRow(), 2)) == null && (table1.getValueAt(table1.getSelectedRow(), 3)) == null)
            {
        PreparedStatement pst = con.prepareStatement("delete from stock where pid=?");
        pst.setString(1, table1.getValueAt(table1.getSelectedRow(), 1).toString().toUpperCase());

        pst.executeUpdate();}
            
        cat.removeAllItems();
           product.removeAllItems();
          
        showtable1();
        
        setcombo();}
    } catch (SQLException ex) {
        Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_DeleteActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
       String id=(table1.getValueAt(table1.getSelectedRow(), 1)).toString();
       int pidd=Integer.parseInt(id);
       String sii=table1.getValueAt(table1.getSelectedRow(), 2).toString();
       String coo=table1.getValueAt(table1.getSelectedRow(), 3).toString();
       try {
            
            PreparedStatement pst =con.prepareStatement("select p.pid, p.cid ,p.pname,  s.* from productinfo as p, stock as s where s.pid=? and s.color=? and s.size=? and p.pid=s.pid");
            pst.setInt(1, pidd);
            pst.setString(2, coo);
            pst.setString(3, sii);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                pname.setText(rs.getString("p.pname"));
                int cid=rs.getInt("p.cid");
                Phar ob=new Phar();
                category.setText(ob.getcatN(cid));
                siz.setText(rs.getString("s.size"));
                colo.setText(rs.getString("s.color"));
                proid.setText(rs.getString("p.pid"));
                tradep.setText(String.valueOf(rs.getInt("s.wholeSalePrice")));
                sellingp.setText(String.valueOf(rs.getInt("s.sellingPrice")));
                quantit.setText(String.valueOf(rs.getInt("s.quantity")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
       add.setVisible(true);
       stocktable.setVisible(false);
       updatee.setVisible(true);
        
        
    }//GEN-LAST:event_updateActionPerformed

    private void sl_spActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sl_spActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sl_spActionPerformed

    private void sl_colorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sl_colorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sl_colorActionPerformed

    private void sl_productActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sl_productActionPerformed
        
        
        
    }//GEN-LAST:event_sl_productActionPerformed

    private void catActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_catActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_catActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
    
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void sl_productKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sl_productKeyReleased
                      
        
        
    }//GEN-LAST:event_sl_productKeyReleased

    private void sl_productKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sl_productKeyTyped
        
    }//GEN-LAST:event_sl_productKeyTyped

    private void sl_productKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sl_productKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_sl_productKeyPressed

    private void searchproKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchproKeyReleased
    String value=searchpro.getText();
    PreparedStatement st;
    try {
         st = con.prepareStatement("Select p.pname,p.pid, s.size, s.color, s.wholeSalePrice, s.sellingPrice,s.quantity,c.catN from "
                 + "productinfo as p, stock as s, category as c where s.pid=p.pid and p.cid=c.cid and pname LIKE ?;");
        st.setString(1, "%"+value+"%");
         ResultSet rs = st.executeQuery();
        table1.setModel(DbUtils.resultSetToTableModel(rs));
    }
    catch (SQLException ex) {
        Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
    }
                
                
                updatee.setVisible(false);
                add.setVisible(true);
                stocktable.setVisible(true); 
                cattable.setVisible(false);
                producttable.setVisible(false);
    }//GEN-LAST:event_searchproKeyReleased

    private void emp_nidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emp_nidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emp_nidActionPerformed

    private void emp_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emp_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emp_nameActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String emp_nnid="null"; 
        if( (emp_name.getText()).equals("")||
                (emp_phn.getText()).equals("")|| (emp_address.getText()).equals("") || 
                (emp_monthly_sal.getText()).equals("") || (emp_position.getText()).equals("")){
            JOptionPane.showMessageDialog(null,"Please fill up all fields!");
            
        
        }else{
            try {
                employee em=new employee();
                em.setName(emp_name.getText());
                em.setPhone("+880"+emp_phn.getText());
                em.setAddress(emp_address.getText());
                em.setNid(emp_nid.getText());
                em.setPerdaysalary(Integer.parseInt(emp_monthly_sal.getText())/26);
                em.setPosition(emp_position.getText());
                
                em.add_employee();
                emp_name.setText("");
                emp_phn.setText("");
                emp_address.setText(""); 
                emp_nid.setText(""); 
                emp_monthly_sal.setText("");
                emp_position.setText("");
            } catch (SQLException ex) {
                Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
         }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void searchproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchproActionPerformed
         
    }//GEN-LAST:event_searchproActionPerformed

    private void table1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_table1KeyTyped

       
    }//GEN-LAST:event_table1KeyTyped

    private void table1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_table1KeyPressed
               
    }//GEN-LAST:event_table1KeyPressed

    private void searchproKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchproKeyTyped
        
    }//GEN-LAST:event_searchproKeyTyped

    private void sl_spKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sl_spKeyReleased
        sl_product.removeAllItems();
        
        String productname=sl_sp.getText();
      
//        String productname=((sl_product.getEditor().getItem()).toString());
        
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select p.pname from productinfo as p, stock as s where p.pname LIKE ? and s.pid= p.pid group by p.pname ");
            ps.setString(1, "%"+productname+"%");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                sl_product.addItem(rs.getString("p.pname"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
        }   
      
    }//GEN-LAST:event_sl_spKeyReleased

    private void sl_spKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sl_spKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_sl_spKeyPressed

    private void sl_spKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sl_spKeyTyped
 // TODO add your handling code here:
    }//GEN-LAST:event_sl_spKeyTyped

    private void emp_monthly_salKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emp_monthly_salKeyReleased
        String value = emp_monthly_sal.getText();
        numberCheck(value);
    }//GEN-LAST:event_emp_monthly_salKeyReleased

    private void emp_phnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emp_phnActionPerformed
        
        char[] c=(emp_phn.getText()).toCharArray();
        if (c.length <10){
            JOptionPane.showMessageDialog(null,"phone number must have 10 digits!");
        }
    }//GEN-LAST:event_emp_phnActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       

                
                showtable2();
                insert_emp.setVisible(true);
                attendance.setVisible(false);
                emptable.setVisible(true);
                checksalary.setVisible(false);
       
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        insert_emp.setVisible(true);
        attendance.setVisible(true);
        emptable.setVisible(false);
         checksalary.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
              insert_emp.setVisible(true);
               attendance.setVisible(false);
                 checksalary.setVisible(false);
                emptable.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void emp_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emp_searchKeyReleased
        String value=emp_search.getText();
        
        PreparedStatement st;
    try {
        st = con.prepareStatement("Select e.ename,e.eid, e.phone, e.address,e.nid, e.perdaysalary,e.position from emp as e where e.ename LIKE ?;");
        st.setString(1,"%"+value+"%" );             
       
         ResultSet rs = st.executeQuery();
         
        
             emp_table.setModel(DbUtils.resultSetToTableModel(rs));  
         
      
    }catch(Exception ex){
        Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
    }
    insert_emp.setVisible(true);
    attendance.setVisible(false);
    emptable.setVisible(true);
    checksalary.setVisible(false);
    }//GEN-LAST:event_emp_searchKeyReleased

    private void emp_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emp_searchActionPerformed
        
    }//GEN-LAST:event_emp_searchActionPerformed

    private void protableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_protableMouseReleased
        if(evt.isPopupTrigger())
            {
                producttablePOP.show(this,evt.getXOnScreen(),evt.getYOnScreen());
                
            }
          
    }//GEN-LAST:event_protableMouseReleased

    private void deleteproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteproActionPerformed
        
        int ppid=Integer.parseInt(protable.getValueAt(protable.getSelectedRow(), 1).toString());
        try {
        PreparedStatement pstt=con.prepareStatement("select pid from stock where pid=?");
        pstt.setInt(1,ppid);
        ResultSet rs=pstt.executeQuery();
        if(rs.next())
        {
            JOptionPane.showMessageDialog(null,"Can't delete the product because the product is present in stock table.");
        }
        else{    
        PreparedStatement pst = con.prepareStatement("delete from productinfo where pid=?");
        pst.setInt(1,ppid);
        pst.executeUpdate();
        cat.removeAllItems();
        product.removeAllItems();
        showtableprotable();
        setcombo();
        }
        
    } catch (SQLException ex) {
        Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_deleteproActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        showtablecattable();
        add.setVisible(true);
        cattable.setVisible(true);
        updatee.setVisible(false);
        stocktable.setVisible(false);
        producttable.setVisible(false);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void deletecatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletecatActionPerformed
       int pcid=Integer.parseInt(categorytable.getValueAt(categorytable.getSelectedRow(), 1).toString());
        try {
        PreparedStatement pstt=con.prepareStatement("select * from productinfo where cid=?");
        pstt.setInt(1,pcid);
        ResultSet rs=pstt.executeQuery();
        if(rs.next())
        {
            JOptionPane.showMessageDialog(null,"Can't delete the category because product of this category is present.");
        }
        else{    
        PreparedStatement pst = con.prepareStatement("delete from category where cid=?");
        pst.setInt(1,pcid);
        pst.executeUpdate();
        cat.removeAllItems();
        product.removeAllItems();
        showtablecattable();
        setcombo();
        }
        
    } catch (SQLException ex) {
        Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_deletecatActionPerformed

    private void categorytableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categorytableMouseReleased
       if(evt.isPopupTrigger())
            {
                catpop.show(this,evt.getXOnScreen(),evt.getYOnScreen());
                
            }
          
    }//GEN-LAST:event_categorytableMouseReleased

    private void empidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empidActionPerformed
       employee emp1;
        try {
            emp1 = new employee();
            emp1.setId(Integer.parseInt(empid.getText()));
            emp1.attendance();
            empid.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
       

       
    }//GEN-LAST:event_empidActionPerformed

    private void sl_productItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sl_productItemStateChanged
        sl_color.removeAllItems();
  
         
        if(sl_product.getEditor().getItem()=="" || sl_product.getEditor().getItem()== null){}
        else{
            String value=(sl_product.getEditor().getItem()).toString(); 
        try {
            int amount=0;
            PreparedStatement pst=con.prepareStatement("select s.color,s.sellingPrice from stock as s, productinfo as p where p.pid=s.pid and p.pname=? group by s.color");
            pst.setString(1, value);
            ResultSet rs =pst.executeQuery();
            while(rs.next())
            {
               amount=rs.getInt("s.sellingPrice");
               sl_color.addItem(rs.getString("s.color"));
            }
            sellingprice.setText(String.valueOf(amount));
            totalperitem.setText(String.valueOf(amount));

            
        } catch (Exception ex) {
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
        }        }
    }//GEN-LAST:event_sl_productItemStateChanged

    private void qqqStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_qqqStateChanged
       int spin=(int)qqq.getValue();
       int selling=Integer.parseInt(sellingprice.getText());
       
       totalperitem.setText(String.valueOf(selling*spin));
        
    }//GEN-LAST:event_qqqStateChanged

    private void sl_colorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sl_colorItemStateChanged
       
//       if (sl_color.getSelectedItem()=="" || sl_color.getSelectedItem()== null || sl_product.getEditor().getItem() =="" || sl_product.getEditor().getItem() == null){
//           
//       }
//       else{
//           sl_size.removeAllItems();
//           String c=sl_color.getSelectedItem().toString();
//       
//       String value=(sl_product.getEditor().getItem()).toString();
//       PreparedStatement pstt;
//        try {
//            pstt = con.prepareStatement("select s.size from stock as s, productinfo as p where p.pid=s.pid and "
//                    + "p.pname=? and s.color=? group by s.size ");
//            pstt.setString(1, value);
//            pstt.setString(2, c);
//        
//            ResultSet rss =pstt.executeQuery();
//            while(rss.next()){
//                sl_size.addItem(rss.getString("s.size"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
//        }}
    }//GEN-LAST:event_sl_colorItemStateChanged
   
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
    try
    {
        String s="N/A", c="";
        
        c=sl_color.getSelectedItem().toString();
       

        if(sl_size.getText().equals(""))
        {
            s="N/A";
        }
        else
        {
            s=sl_size.getText();
        }
        System.err.println("heyhey");
        
     
        String value=(sl_product.getEditor().getItem()).toString();
        int p=Integer.parseInt(sellingprice.getText());
        int spin=(int)qqq.getValue();
        int t=Integer.parseInt(totalperitem.getText());
        product prd=new product();
        prd.setPname(value);
        prd.pro_get_pid();
        int piiid=prd.getPid();
        
        System.err.println(piiid);
        PreparedStatement stockCheck=con.prepareStatement("Select * from stock where pid=? and color=?");
        stockCheck.setInt(1, piiid);
        stockCheck.setString(2, c);
        
        ResultSet ruirui=stockCheck.executeQuery();
        if(!ruirui.next())
        {
            
        }
        else
        {
            int fquantity=ruirui.getInt("quantity");
            if(spin>fquantity){
            JOptionPane.showMessageDialog(null,"available amount="+fquantity);
            }
            
            else{
               //if it is for making bill
                if(receipt_value==0)
                {    
                    DefaultTableModel m=(DefaultTableModel)billingtable.getModel();
                     int rowcount= billingtable.getRowCount();

                    m.addRow(new Object[] {rowcount+1,value,c,s,spin,t});
                    
                    int totalamount=0;

                    for (int i=0; i<billingtable.getRowCount(); i++){
                        totalamount=totalamount + Integer.parseInt(billingtable.getValueAt(i, 5).toString());

                    }
                    gt.setText(String.valueOf(totalamount));
                    total.setText(String.valueOf(totalamount));
                }
                else
                {
                    
                    product ob=new product();
                    ob.setPname(value);
                    ob.pro_get_pid();
                    int ppid=ob.getPid();
                    PreparedStatement pl =con.prepareStatement("select quantity from stock where pid=? and color=?");
                    pl.setInt(1, ppid);
                    pl.setString(2, c);
                    
                    ResultSet rr=pl.executeQuery();
                    while(rr.next()){
                        PreparedStatement p4 =con.prepareStatement("update stock set quantity=? where pid=? and color=?");
                        p4.setInt(1, rr.getInt("quantity")-spin);
                        p4.setInt(2, ppid);
                        p4.setString(3, c);
                      
                        p4.executeUpdate();
                    }

              DefaultTableModel m=(DefaultTableModel)billingtable.getModel();
            
            int rowcount= billingtable.getRowCount();
            
            m.addRow(new Object[] {rowcount+1,value,c,s,spin,t});
            
            int totalamount=0;

            for (int i=0; i<billingtable.getRowCount(); i++){
                totalamount=totalamount + Integer.parseInt(billingtable.getValueAt(i, 5).toString());

            }
            jLabel92.setText(String.valueOf(totalamount));
            jLabel93.setText(String.valueOf(totalamount-Integer.parseInt(total.getText())));
        }
            
        }
        dis.setText("0");
        cash.setText("0");
        change.setText("0");
        
        sl_sp.setText("");
            
        }
       } catch (Exception ex) {
                 Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
             }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void cashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashActionPerformed
      
    }//GEN-LAST:event_cashActionPerformed

    private void disKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_disKeyReleased
   if(dis.getText().equals("") ||  dis.getText()== null )
   {}
   else{
       
        int grand=Integer.parseInt(gt.getText());
        int disc=Integer.parseInt(dis.getText());
        
        total.setText(String.valueOf(grand-disc));}
    }//GEN-LAST:event_disKeyReleased

    private void cashKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cashKeyReleased
        if(cash.getText().equals("") ||  cash.getText()== null )
        {}
        else{
            
            if(receipt_value==0){
                int ca=Integer.parseInt(cash.getText());
                int t=Integer.parseInt(total.getText());
                change.setText(String.valueOf(ca-t));
            }
            else
            {
                change.setText(String.valueOf(Integer.parseInt(cash.getText())-Integer.parseInt(jLabel93.getText()))); 
            }
            
        }
    }//GEN-LAST:event_cashKeyReleased

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
       
        if(rcpt.getText().equals("") || rcpt.getText()== null ){
        JOptionPane.showMessageDialog(null,"Fill up Receipt Number!");
        }
        else{ 
            
            int ggt=Integer.parseInt(gt.getText());
            int d=Integer.parseInt(dis.getText());
            int tto=Integer.parseInt(total.getText());
            int ca=Integer.parseInt(cash.getText());
            int chen=Integer.parseInt(change.getText());
        try {
            int receipt=Integer.parseInt(rcpt.getText());
            Phar ob=new Phar();
            java.sql.Date curdate = ob.get_current_date();
            
            if(receipt_value==0){
            // inserting into billing table    
            PreparedStatement pst=con.prepareStatement("insert into billingwithitems(ReceiptNo, pid, Color,Size,quantity,"
                    + " productTotal,date) values(?,?,?,?,?,?,?)");
            int rowcount= billingtable.getRowCount();
            int piid=0;
            
            for (int i=0; i<rowcount; i++){
                String pro=billingtable.getValueAt(i, 1).toString();
                piid=ob.get_pid(pro);
                String c=billingtable.getValueAt(i, 2).toString();
                String s=billingtable.getValueAt(i, 3).toString();
                int q=Integer.parseInt(billingtable.getValueAt(i, 4).toString());
                int t=Integer.parseInt(billingtable.getValueAt(i, 5).toString());
                
                pst.setInt(1, receipt);
                pst.setInt(2, piid);
                pst.setString(3, c);
                pst.setString(4, s);
                pst.setInt(5, q);
                pst.setInt(6, t);
                pst.setDate(7, curdate);
                pst.executeUpdate();
                
            }
            
            }
            else
            {   
                
                
                //delete from all by receipt no
                PreparedStatement ll=con.prepareStatement("Delete from billingwithitems where receiptNo=?");
                ll.setInt(1, receipt );
                ll.executeUpdate();
                
                // insert new value in billingwithitems
                PreparedStatement po=con.prepareStatement("insert into billingwithitems(ReceiptNo, pid, Color,Size,quantity, productTotal,date) values(?,?,?,?,?,?,?)");
                int rowcount= billingtable.getRowCount();
                int piid=0;
            
                for (int i=0; i<rowcount; i++)
                {
                String pro=billingtable.getValueAt(i, 1).toString();
                piid=ob.get_pid(pro);
                String c=billingtable.getValueAt(i, 2).toString();
                String s=billingtable.getValueAt(i, 3).toString();
                int q=Integer.parseInt(billingtable.getValueAt(i, 4).toString());
                int t=Integer.parseInt(billingtable.getValueAt(i, 5).toString());
                
                po.setInt(1, receipt);
                po.setInt(2, piid);
                po.setString(3, c);
                po.setString(4, s);
                po.setInt(5, q);
                po.setInt(6, t);
                po.setDate(7, curdate);
                po.executeUpdate();
                
            }
                   
        }
             DefaultTableModel m=(DefaultTableModel)billingtable.getModel(); 
             if(receipt_value==0){
             receipt obj=new receipt(m,curdate,receipt,cn.getText(),cph.getText(),ggt,d,tto,ca,chen,receipt_value);
             obj.setVisible(true);
             }
             else{
                 receipt obj=new receipt(jLabel92.getText(),jLabel93.getText(),m,curdate,receipt,cn.getText(),cph.getText(),ggt,d,tto,ca,chen,receipt_value);
                 obj.setVisible(true);
             }

             
             cn.setText("");
            cph.setText("");
        } catch (Exception ex) {
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        attendance.setVisible(false);
        insert_emp.setVisible(true);
        emptable.setVisible(false);
        checksalary.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void cphKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cphKeyReleased
        numberCheck(cph.getText());
    }//GEN-LAST:event_cphKeyReleased

    private void disActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_disActionPerformed

    private void saleDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saleDeleteActionPerformed
       int selectedRow = billingtable.getSelectedRow();
       DefaultTableModel m=(DefaultTableModel)billingtable.getModel();
       m.removeRow(selectedRow);
    }//GEN-LAST:event_saleDeleteActionPerformed

    private void billingtableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_billingtableMouseReleased
        // 0 for selling a product
        if(evt.isPopupTrigger() && receipt_value==0)
            {
                salepop.show(this,evt.getXOnScreen(),evt.getYOnScreen());
                
            }
        
        // 1 for exchanging returning a product(a delete option will delete from table and add the product back to the stock)
        else if(evt.isPopupTrigger() && receipt_value==1)
            {  
                exchangepop.show(this,evt.getXOnScreen(),evt.getYOnScreen());
            }
        // 1 for searching a product
        else 
            {  
            }
    }//GEN-LAST:event_billingtableMouseReleased

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        receipt_value=0;
        gt.setEnabled(true);
        total.setEnabled(true);
        dis.setEnabled(true);
        rcpt.setText(String.valueOf(get_new_receiptno()+1));//auto generating receipt no
        rcpt.setEditable(false);
        billingdate.setVisible(false);
        billingdate1.setVisible(false);
        jLabel61.setVisible(true);
        cn.setText("");
        cph.setText("");
        gt.setText("");
        dis.setText("");
        total.setText("");
        cash.setText("");
        change.setText("");
        jLabel93.setVisible(false);
        jLabel92.setVisible(false);
        jLabel88.setVisible(false);
        jLabel89.setVisible(false);
        billingtable.removeAll();
        
        DefaultTableModel model = (DefaultTableModel) billingtable.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        receipt_value=1;
        
        rcpt.setEditable(true);
        gt.setEnabled(false);
//        gt.setText("0");
//        total.setText("0");
//        dis.setText("0");
        total.setEnabled(false);
        dis.setEnabled(false);
        rcpt.setText("");
        jLabel93.setVisible(true);
        jLabel92.setVisible(true);
        jLabel88.setVisible(true);
        jLabel89.setVisible(true);
        billingtable.removeAll();
        DefaultTableModel model = (DefaultTableModel) billingtable.getModel();
        model.setRowCount(0);
        
    }//GEN-LAST:event_jButton14ActionPerformed

    private void rcptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rcptActionPerformed
       DefaultTableModel dm = (DefaultTableModel)billingtable.getModel();
dm.getDataVector().removeAllElements();        
        // Searching the recipt info from both table(billing and billwithitems)
       int count=0;
        if(rcpt.getText().equals("") || rcpt.getText()== null){
        }
        else{
             try {
                int receipt = Integer.parseInt(rcpt.getText());
                 DefaultTableModel m=(DefaultTableModel)billingtable.getModel();
                
                PreparedStatement pst=con.prepareStatement("select p.pname, b.color,b.size,b.quantity"
                        + ",b.productTotal from billingwithitems as b , productinfo as p where b.pid=p.pid and receiptNo=?");
                pst.setInt(1, receipt);
                ResultSet rs=pst.executeQuery();
                while(rs.next()){
                    
                    count++;
                    
                    m.addRow(new Object[] {count,rs.getString("p.pname"),rs.getString("b.color"),rs.getString("b.size"),rs.getString("b.quantity"),rs.getString("b.productTotal")});
                }
                
              
                PreparedStatement pp=con.prepareStatement("select * from bill where receiptNo=?");
                pp.setInt(1, receipt);
                ResultSet ro=pp.executeQuery();
                while(ro.next()){
                    billingdate.setText(String.valueOf(ro.getDate("date")));
                    cn.setText(ro.getString("customername"));
                    cph.setText(ro.getString("phone"));
                    gt.setText(String.valueOf(ro.getInt("totalAmount")));
                    dis.setText(String.valueOf(ro.getInt("discountonTotal")));
                    total.setText(String.valueOf(ro.getInt("totalwithDiscount")));
                    cash.setText(String.valueOf(ro.getInt("cash")));
                    change.setText(String.valueOf(ro.getInt("back")));
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_rcptActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        jScrollPane6.setVisible(true);
        searchd.setVisible(true);
        jDateChooser1.setVisible(false);
        showbilltable();
        solditems.setVisible(false);
       
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        try {
        String dat=((JTextField)searchd.getDateEditor().getUiComponent()).getText();
        Date date = new Date();
        DateFormat dtoday = new SimpleDateFormat("yyyy-MM-dd");
        
        java.util.Date dt = dtoday.parse(dat);
        java.sql.Date vdate = new java.sql.Date(dt.getTime());
        PreparedStatement pn=con.prepareStatement("Select * from bill where date=? ");
        pn.setDate(1, vdate);
        ResultSet rs=pn.executeQuery();
        bill.setModel(DbUtils.resultSetToTableModel(rs));
        int rowcount= bill.getRowCount();
        int totalamount=0;
        
        for (int i=0; i<rowcount; i++){
            totalamount=totalamount + Integer.parseInt(bill.getValueAt(i, 6).toString());
            
        }
        tm.setText("TK "+totalamount);
    } catch (Exception ex) {
        Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            employee emp4=new employee();
            emp4.setId(Integer.parseInt(emid.getText()));
            //getting value from inteface
          
            String mont=mon.getSelectedItem().toString();
            int ya=Integer.parseInt(yearr.getSelectedItem().toString());
            at.setText(String.valueOf(emp4.emp_total_attendance(mont, ya)));
            sal.setText("Tk"+String.valueOf(emp4.emp_total_salary(mont, ya)));
            stat.setText(String.valueOf(emp4.emp_salary_status(mont, ya)));   
            System.gc();
        } catch (SQLException ex) {
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
           
           
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        try {
            String ss=status.getSelectedItem().toString();
             int id=Integer.parseInt(emid.getText());
            String mont=mon.getSelectedItem().toString();
            int ya=Integer.parseInt(yearr.getSelectedItem().toString());
            
            //calling the function converting string month to int
            Phar ob=new Phar();
            int monthinNum=ob.get_month_no(mont);
            
            PreparedStatement pst =con.prepareStatement("update salary set status=? where eid=? and month=? and year=?");
            pst.setString(1, ss);
            pst.setInt(2, id);
            pst.setInt(3, monthinNum);
            pst.setInt(4, ya);
            int n=pst.executeUpdate();
            if(n==1)
            {
                JOptionPane.showMessageDialog(null,"Status updated!");
            }
            
            
                    } catch (SQLException ex) {
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        jScrollPane6.setVisible(false);
        jDateChooser1.setVisible(true);    
        searchd.setVisible(false);
            receiptdetails.setVisible(true);
            solditems.setVisible(true);
            showsolditemstable();
            
            
           
        
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        try {
            int rcpt=Integer.parseInt(jTextField1.getText());
            
            PreparedStatement pst=con.prepareStatement("select * from billingwithitems where receiptNo=?");
            pst.setInt(1, rcpt);
            ResultSet rs=pst.executeQuery();
            solditemtable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        try {
        String dat=((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
        Date date = new Date();
        DateFormat dtoday = new SimpleDateFormat("yyyy-MM-dd");
        
        java.util.Date dt = dtoday.parse(dat);
        java.sql.Date vdate = new java.sql.Date(dt.getTime());
        PreparedStatement pn=con.prepareStatement("Select * from billingwithitems where date=? ");
        pn.setDate(1, vdate);
        ResultSet rs=pn.executeQuery();
        solditemtable.setModel(DbUtils.resultSetToTableModel(rs));
        
       
    } catch (Exception ex) {
        Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void deleteandaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteandaddActionPerformed
        try {
            product ob=new product();
            ob.setPname(billingtable.getValueAt(billingtable.getSelectedRow(), 1).toString());
            ob.pro_get_pid();
            int pid=ob.getPid();
            String color=billingtable.getValueAt(billingtable.getSelectedRow(), 2).toString();
            String size=billingtable.getValueAt(billingtable.getSelectedRow(), 3).toString();
            int q=0;
            int quantity= Integer.parseInt(billingtable.getValueAt(billingtable.getSelectedRow(), 4).toString());
            int rrr=Integer.parseInt(rcpt.getText());
            // adding the product back in Stock
            PreparedStatement ps=con.prepareStatement("Select quantity from stock where pid=? and color=? and size =? ");
            ps.setInt(1, pid);
            ps.setString(2, color);
            ps.setString(3, size);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                q=rs.getInt("quantity");
                PreparedStatement pu=con.prepareStatement("update stock set quantity =? where pid=? and color=? and size =? ");
            pu.setInt(1, q+quantity);
            pu.setInt(2, pid);
            pu.setString(3, color);
            pu.setString(4, size);
            pu.executeUpdate();
            }
            // deleting the product from receipt
            PreparedStatement pst=con.prepareStatement("Delete from billingwithitems where receiptNo=? ");
            pst.setInt(1, rrr);
//            pst.setInt(2, pid);
//            pst.setString(3, color);
//            pst.setString(4, size);
//            pst.setInt(5, quantity);
            pst.executeUpdate();
            
            int selectedRow = billingtable.getSelectedRow();
       DefaultTableModel m=(DefaultTableModel)billingtable.getModel();
       m.removeRow(selectedRow);
        } catch (SQLException ex) {
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteandaddActionPerformed

    private void amounActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amounActionPerformed
        numberCheck(amoun.getText());
    }//GEN-LAST:event_amounActionPerformed

    private void purActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_purActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        try {
            int aamount = Integer.parseInt(amoun.getText());
            Phar ex4=new Phar();
            // inserting into dailyrecord
            ex4.set_daily_expenses(aamount);
            
            expenseWithPurpose nwr=new expenseWithPurpose();
            nwr.setAmount(aamount);
            nwr.setPurpose(pur.getText());
              // inserting into expenses table
            nwr.exp_insert_value(ex4.get_current_date());
            amoun.setText("");
            pur.setText("");
            tootal.setText(String.valueOf(nwr.exp_get_total(ex4.get_current_date()))+"TK");
          
            
        } catch (SQLException ex) {
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.gc();
    }//GEN-LAST:event_jButton24ActionPerformed

    private void sellingpriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellingpriceActionPerformed
        
    }//GEN-LAST:event_sellingpriceActionPerformed

    private void sellingpriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sellingpriceKeyReleased
        
        if(sellingprice.getText()=="")
        {}
        else{
//        numberCheck(sellingprice.getText());
        try {
            int spin=(int)qqq.getValue();
            stock obj=new stock();
            obj.setPname(sl_product.getEditor().getItem().toString());
            obj.pro_get_pid();
            obj.setColor(sl_color.getSelectedItem().toString());
            obj.setSize(sl_size.getText());
            obj.stock_get_wholesaleprice();
            
            
            int sellingp=Integer.parseInt(sellingprice.getText());
            
           if (sellingp<= obj.getWholesaleprice()){
               sellingprice.setForeground(Color.red);
               totalperitem.setText("0");
           }
           else{
               sellingprice.setForeground(Color.black);
               totalperitem.setText(String.valueOf(spin*(Integer.parseInt(sellingprice.getText()))));
           }
                
            
        } catch (SQLException ex) {
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_sellingpriceKeyReleased

    private void amounKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amounKeyReleased
       numberCheck(amoun.getText());
    }//GEN-LAST:event_amounKeyReleased

    private void aaaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aaaKeyReleased
        numberCheck(aaa.getText());
    }//GEN-LAST:event_aaaKeyReleased

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        try {
            int am = Integer.parseInt(aaa.getText());
            Phar ex4=new Phar();
            // inserting into dailyrecord
            ex4.set_daily_income(am);
            
            incomewithpurpose nwr=new incomewithpurpose();
            nwr.setAmount(am);
            nwr.setPurpose(ppp.getText());
              // inserting into expenses table
            nwr.exp_insert_value(ex4.get_current_date());
            aaa.setText("");
            ppp.setText("");
            ttt.setText(String.valueOf(nwr.exp_get_total(ex4.get_current_date()))+"TK");
          
            
        } catch (SQLException ex) {
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        expenses.setVisible(true); 
        income.setVisible(false);
        drecord.setVisible(false);
        expensed.setVisible(false);
         incomed.setVisible(false);
      
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
       expenses.setVisible(true); 
        income.setVisible(true);
      drecord.setVisible(false);
      expensed.setVisible(false);
       incomed.setVisible(false);
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
       showdailyrecordtable();
       expenses.setVisible(true); 
       income.setVisible(false);
       drecord.setVisible(true);
       expensed.setVisible(false);
        incomed.setVisible(false);
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        showExpensetable();
        expenses.setVisible(true); 
       income.setVisible(false);
       drecord.setVisible(false);
       expensed.setVisible(true);
       incomed.setVisible(false);
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        try {
            String dat=((JTextField)jDateChooser2.getDateEditor().getUiComponent()).getText();
            Date date = new Date();
            DateFormat dtoday = new SimpleDateFormat("yyyy-MM-dd");
            
            java.util.Date dt = dtoday.parse(dat);
            java.sql.Date vdate = new java.sql.Date(dt.getTime());
            
            PreparedStatement pst =con.prepareStatement("Select * from expenses where date=?");
            pst.setDate(1, vdate);
            ResultSet rs=pst.executeQuery();
            expensetable.setModel(DbUtils.resultSetToTableModel(rs));
            int rowcount= expensetable.getRowCount();
             int totalamount=0;
        
        for (int i=0; i<rowcount; i++){
            totalamount=totalamount + Integer.parseInt(expensetable.getValueAt(i, 1).toString());
            
        }
        jLabel75.setText(totalamount+"TK");
            
        } catch (Exception ex) {
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        showIncometable();
        
        expenses.setVisible(true); 
       income.setVisible(false);
       drecord.setVisible(false);
       expensed.setVisible(false);
       incomed.setVisible(true);
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        try {
            String dat=((JTextField)jDateChooser3.getDateEditor().getUiComponent()).getText();
            Date date = new Date();
            DateFormat dtoday = new SimpleDateFormat("yyyy-MM-dd");
            
            java.util.Date dt = dtoday.parse(dat);
            java.sql.Date vdate = new java.sql.Date(dt.getTime());
            
            PreparedStatement pst =con.prepareStatement("Select * from income where date=?");
            pst.setDate(1, vdate);
            ResultSet rs=pst.executeQuery();
            incometable.setModel(DbUtils.resultSetToTableModel(rs));
            int rowcount= incometable.getRowCount();
             int totalamount=0;
        
        for (int i=0; i<rowcount; i++){
            totalamount=totalamount + Integer.parseInt(incometable.getValueAt(i, 1).toString());
            
        }
        jLabel79.setText(totalamount+"TK");
            
        } catch (Exception ex) {
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        try {
            
            //getting value from inteface
            String mont=month.getSelectedItem().toString();
            int ya=Integer.parseInt(year.getSelectedItem().toString());
            Phar obp=new Phar();
            int mnp=obp.get_month_no(mont);
            dailyrecord obn=new dailyrecord();
            obn.dailyre_total_monthly(mnp, ya);
            
            totalexp.setText(String.valueOf(obn.getExpenses()));
            totalinc.setText(String.valueOf(obn.getIncome()));
            int resul=obn.getIncome()- obn.getExpenses();
            totalprofit.setText(String.valueOf(resul));
            
//            if(resul>0){
//                mess.setText("Congratulations! I can see future Bill Gates in you.");
//                mess.setForeground(Color.blue);
//            }
//            if(resul<0 && resul !=0){
//                mess.setText("Tore diye kiccu hobe na... go and die!");
//                mess.setForeground(Color.red);
//            }
//            if(resul==0){
//                mess.setText("just die!");
//                mess.setForeground(Color.red);
//                
//            }
//            
            System.gc();
        } catch (Exception ex) {
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
try
{
//getting value from inteface
            
            int ya=Integer.parseInt(year.getSelectedItem().toString());
            
            
            dailyrecord obn=new dailyrecord();
            obn.dailyre_total_yearly( ya);
            
            totalexp.setText(String.valueOf(obn.getExpenses()));
            totalinc.setText(String.valueOf(obn.getIncome()));
            int resul=obn.getIncome()- obn.getExpenses();
            totalprofit.setText(String.valueOf(resul));
            
//            if(resul>0){
//                mess.setText("Congratulations! I can see future Bill Gates in you.");
//                mess.setForeground(Color.blue);
//            }
//            if(resul<0 && resul !=0){
//                mess.setText("Tore diye kiccu hobe na... go and die!");
//                mess.setForeground(Color.red);
//            }
//            if(resul==0){
//                mess.setText("just die!");
//                mess.setForeground(Color.red);
//                
//            }
            
            System.gc();
        } catch (Exception ex) {
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton32ActionPerformed

    private void emp_nidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emp_nidKeyReleased
        numberCheck(emp_nid.getText());
    }//GEN-LAST:event_emp_nidKeyReleased

    private void emp_phnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emp_phnKeyReleased
        numberCheck(emp_phn.getText());
    }//GEN-LAST:event_emp_phnKeyReleased

    private void emidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emidActionPerformed
        numberCheck(emid.getText());
    }//GEN-LAST:event_emidActionPerformed

    private void empidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_empidKeyReleased
        numberCheck(empid.getText());
    }//GEN-LAST:event_empidKeyReleased

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        yearly_table.setVisible(false);
        monthly_table.setVisible(true);
        showmonthlytable();
        check.setVisible(true);
        yearly.setVisible(false);
        monthly.setVisible(true);
        
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        monthly_table.setVisible(false);
        yearly_table.setVisible(true);
        showyearlytable();
        check.setVisible(true);
        yearly.setVisible(true);
        monthly.setVisible(false);
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        check.setVisible(true);
        yearly.setVisible(false);
        monthly.setVisible(false);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
           
        try {
            cat.removeAllItems();
            product.removeAllItems();
            setcombo();
        } catch (SQLException ex) {
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
           tp.setText("");
           sp.setText("");
           quantity.setText("");
           color.setText("");
           size.setText("");
    }//GEN-LAST:event_jButton34ActionPerformed

    private void emp_tableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emp_tableMouseReleased
      if(evt.isPopupTrigger())
            {
                emppop.show(this,evt.getXOnScreen(),evt.getYOnScreen());
                
            }
    }//GEN-LAST:event_emp_tableMouseReleased

    private void empdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empdeleteActionPerformed
       int eeid=Integer.parseInt(emp_table.getValueAt(emp_table.getSelectedRow(), 1).toString());
        try {
           
        PreparedStatement pst = con.prepareStatement("delete from emp where eid=?");
        pst.setInt(1,eeid);
        pst.executeUpdate();

        showtable2();
        
        
        
    } catch (SQLException ex) {
        Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_empdeleteActionPerformed

    private void emp_monthly_salActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emp_monthly_salActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emp_monthly_salActionPerformed

    private void emp_nameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emp_nameKeyReleased
        alphabetCheck(emp_name.getText());
    }//GEN-LAST:event_emp_nameKeyReleased

    private void tradepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tradepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tradepActionPerformed

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
            java.util.logging.Logger.getLogger(homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new homepage().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ADD;
    private javax.swing.JMenuItem Delete;
    private javax.swing.JButton UPDATE;
    private javax.swing.JTextField aaa;
    private javax.swing.JPanel add;
    private javax.swing.JTextField amoun;
    private javax.swing.JLabel at;
    private javax.swing.JPanel attendance;
    private javax.swing.JTable bill;
    private javax.swing.JPanel billing;
    private javax.swing.JTextField billingdate;
    private javax.swing.JLabel billingdate1;
    private javax.swing.JTable billingtable;
    private javax.swing.JTextField cash;
    private javax.swing.JComboBox<String> cat;
    private javax.swing.JTextField category;
    private javax.swing.JTable categorytable;
    private javax.swing.JPopupMenu catpop;
    private javax.swing.JPanel cattable;
    private javax.swing.JTextField change;
    private javax.swing.JPanel check;
    private javax.swing.JPanel checksalary;
    private javax.swing.JTextField cn;
    private javax.swing.JTextField colo;
    private javax.swing.JTextField color;
    private javax.swing.JTextField cph;
    private javax.swing.JTable dailyrecord;
    private javax.swing.JLabel date;
    private javax.swing.JMenuItem deleteandadd;
    private javax.swing.JMenuItem deletecat;
    private javax.swing.JMenuItem deletepro;
    private javax.swing.JTextField dis;
    private javax.swing.JPanel drecord;
    private javax.swing.JTextField emid;
    private javax.swing.JTextField emp_address;
    private javax.swing.JTextField emp_monthly_sal;
    private javax.swing.JTextField emp_name;
    private javax.swing.JTextField emp_nid;
    private javax.swing.JTextField emp_phn;
    private javax.swing.JTextField emp_position;
    private javax.swing.JTextField emp_search;
    private javax.swing.JTable emp_table;
    private javax.swing.JMenuItem empdelete;
    private javax.swing.JTextField empid;
    private javax.swing.JPopupMenu emppop;
    private javax.swing.JPanel emptable;
    private javax.swing.JPopupMenu exchangepop;
    private javax.swing.JPanel expensed;
    private javax.swing.JPanel expenses;
    private javax.swing.JTable expensetable;
    private javax.swing.JLabel gt;
    private javax.swing.JPanel income;
    private javax.swing.JLabel income_date;
    private javax.swing.JPanel incomed;
    private javax.swing.JTable incometable;
    private javax.swing.JPanel insert_emp;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
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
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel mess;
    private javax.swing.JComboBox<String> mon;
    private javax.swing.JComboBox<String> month;
    private javax.swing.JPanel monthly;
    private javax.swing.JTable monthly_table;
    private javax.swing.JTextField pname;
    private javax.swing.JPopupMenu pop;
    private javax.swing.JTextField ppp;
    private javax.swing.JComboBox<String> product;
    private javax.swing.JPanel producttable;
    private javax.swing.JPopupMenu producttablePOP;
    private javax.swing.JTextField proid;
    private javax.swing.JTable protable;
    private javax.swing.JTextField pur;
    private javax.swing.JSpinner qqq;
    private javax.swing.JTextField quantit;
    private javax.swing.JTextField quantity;
    private javax.swing.JTextField rcpt;
    private javax.swing.JPanel receiptdetails;
    private javax.swing.JLabel sal;
    private javax.swing.JMenuItem saleDelete;
    private javax.swing.JPopupMenu salepop;
    private com.toedter.calendar.JDateChooser searchd;
    private javax.swing.JTextField searchpro;
    private javax.swing.JTextField sellingp;
    private javax.swing.JTextField sellingprice;
    private javax.swing.JTextField siz;
    private javax.swing.JTextField size;
    private javax.swing.JComboBox<String> sl_color;
    private javax.swing.JComboBox<String> sl_product;
    private javax.swing.JTextField sl_size;
    private javax.swing.JTextField sl_sp;
    private javax.swing.JPanel solditems;
    private javax.swing.JTable solditemtable;
    private javax.swing.JTextField sp;
    private javax.swing.JLabel stat;
    private javax.swing.JComboBox<String> status;
    private javax.swing.JPanel stocktable;
    private javax.swing.JTable table1;
    private javax.swing.JLabel tm;
    private javax.swing.JLabel tootal;
    private javax.swing.JTextField total;
    private javax.swing.JLabel totalexp;
    private javax.swing.JLabel totalinc;
    private javax.swing.JTextField totalperitem;
    private javax.swing.JLabel totalprofit;
    private javax.swing.JTextField tp;
    private javax.swing.JTextField tradep;
    private javax.swing.JLabel ttt;
    private javax.swing.JMenuItem update;
    private javax.swing.JPanel updatee;
    private javax.swing.JComboBox<String> year;
    private javax.swing.JPanel yearly;
    private javax.swing.JTable yearly_table;
    private javax.swing.JComboBox<String> yearr;
    // End of variables declaration//GEN-END:variables
}
