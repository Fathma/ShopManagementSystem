/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phar;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author Ana
 */
public class Phar {

    Connection con;
     public Phar() throws SQLException
	        {
			con = db.connect(); 
                        
	        }
public void set_daily_income(int value, int rcpt){
        try {
            java.sql.Date d=get_current_date();
            PreparedStatement incomeinert =con.prepareStatement("insert into income(date, amount, purpose) values(?,?,?)");
            incomeinert.setDate(1, d);
            incomeinert.setInt(2, value);
            incomeinert.setString(3, "rcpt"+rcpt);
            incomeinert.executeUpdate();
            PreparedStatement checkingDate =con.prepareStatement("Select * from dailyrecord where date=?");
            checkingDate.setDate(1, d);
            ResultSet rs=checkingDate.executeQuery();
            
            if (rs.next()){
                
                PreparedStatement updatingValues=con.prepareStatement("update dailyrecord set income=? where date=?");
                updatingValues.setInt(1, value+rs.getInt("income"));
                updatingValues.setDate(2, d);
                updatingValues.executeUpdate();
            }
            else{
                PreparedStatement insertingvalue=con.prepareStatement("insert into dailyrecord(date,income) values(?,?)");
                insertingvalue.setDate(1, d);
                insertingvalue.setInt(2, value);
//                
                insertingvalue.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Phar.class.getName()).log(Level.SEVERE, null, ex);
        }
} 
public void set_daily_income(int value){
        try {
            java.sql.Date d=get_current_date();
            
            PreparedStatement checkingDate =con.prepareStatement("Select * from dailyrecord where date=?");
            checkingDate.setDate(1, d);
            ResultSet rs=checkingDate.executeQuery();
            
            if (rs.next()){
                
                PreparedStatement updatingValues=con.prepareStatement("update dailyrecord set income=? where date=?");
                updatingValues.setInt(1, value+rs.getInt("income"));
                updatingValues.setDate(2, d);
                updatingValues.executeUpdate();
                 
            }
            else{
                PreparedStatement insertingvalue=con.prepareStatement("insert into dailyrecord(date,income) values(?,?)");
                insertingvalue.setDate(1, d);
                insertingvalue.setInt(2, value);
//                
                insertingvalue.executeUpdate();
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Phar.class.getName()).log(Level.SEVERE, null, ex);
        }
} 
public void set_daily_expenses(int value){
        try {
            java.sql.Date d=get_current_date();
            
            
            PreparedStatement checkingDate =con.prepareStatement("Select * from dailyrecord where date=?");
            checkingDate.setDate(1, d);
            ResultSet rs=checkingDate.executeQuery();
            if (rs.next()){
                
                PreparedStatement updatingValues=con.prepareStatement("update dailyrecord set expenses=? where date=?");
                updatingValues.setInt(1, value+rs.getInt("expenses"));
                updatingValues.setDate(2, d);
                updatingValues.executeUpdate();
            }
            else{
                PreparedStatement insertingvalue=con.prepareStatement("insert into dailyrecord(date,expenses) values(?,?)");
                insertingvalue.setDate(1, d);
                insertingvalue.setInt(2, value);
                insertingvalue.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Phar.class.getName()).log(Level.SEVERE, null, ex);
        }

        
}

public int get_pid(String pname){
    int piid=0;    
    try {
            PreparedStatement p=con.prepareStatement("Select pid from productinfo where pname=?");
            p.setString(1, pname);
            ResultSet rs=p.executeQuery();
            while(rs.next()){
                piid=rs.getInt("pid");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Phar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (piid);
}
    
   public String getcatN(int cid) throws SQLException{
               String cat="";
               PreparedStatement ps= con.prepareStatement("select *from category where cid=?");
               ps.setInt(1, cid);
               ResultSet rss= ps.executeQuery();
               while(rss.next()){
                   cat=rss.getString("catN");
               }
               return cat;
   }
    public int loginCheck(String name,String pass)  {
        int f=0;
        try {
          
            java.sql.Statement stmt;
                 stmt = con.createStatement();
                 
            ResultSet rs2=stmt.executeQuery("select * from admin WHERE uname='"+name+"' "); 
            
            
            if(rs2.next())
            {
                if (pass.equals(rs2.getString(2))){
                    f=1;  
                }   
            }
            else {
                f=2;
            }
            
              
             
       } catch (SQLException ex) {
                System.out.println(ex);
             }

          return f;  
    }
    
public int get_month_no(String mo){
    int mont=0;
        if((mo.toLowerCase()).equals("january")){
            mont=1;
        }
        else if(mo.toLowerCase().equals("february")){
            mont=2;
        }
        else if(mo.toLowerCase().equals("march")){
            mont=3;
        }
        else if(mo.toLowerCase().equals("april")){
            mont=4;
        }
        else if(mo.toLowerCase().equals("may")){
            mont=5;
        }
        else if(mo.toLowerCase().equals("june")){
            mont=6;
        }
        else if(mo.toLowerCase().equals("july")){
            mont=7;
        }
        else if(mo.toLowerCase().equals("august")){
            mont=8;
        }
        else if(mo.toLowerCase().equals("september")){
            mont=9;
        }
        else if(mo.toLowerCase().equals("october")){
            mont=10;
        }
        else if(mo.toLowerCase().equals("november")){
            mont=11;
        }
        else if(mo.toLowerCase().equals("december")){
            mont=12;
        }
        else{
            JOptionPane.showMessageDialog(null,"0");
        }
        return mont;
}    
public java.sql.Date get_current_date(){
    java.sql.Date curdate = null;    
    try {
            // getting current as sql.date date
            Date date = new Date();
            DateFormat dtoday = new SimpleDateFormat("yyyy-MM-dd");
            String dat = dtoday.format(date);
            java.util.Date dt = dtoday.parse(dat);
             curdate = new java.sql.Date(dt.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(Phar.class.getName()).log(Level.SEVERE, null, ex);
        }
    return curdate;
}
    public int checkStock(){
        int nt=0;
        try {
            PreparedStatement p = con.prepareStatement("select *from drugreg");
            ResultSet rs=p.executeQuery();
            while(rs.next()){
                if((rs.getInt("quantity"))<=15){
                    nt++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Phar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nt;        
    }
    
    public void addproduct(String pname,String pcat,String size,String color,int tp,int sp, int quantity, int pid) {
            
            int cid=0;
            try
                   
            {   
                System.out.println("after call");
                PreparedStatement pst123=con.prepareStatement("Select * from category where catN=?");
                pst123.setString(1, pcat);
                ResultSet rescat=pst123.executeQuery();
                if(rescat.next())
                {
                    cid=rescat.getInt("cid");
                }
                else
                {
                    PreparedStatement pst12=con.prepareStatement("insert into category(catN) values(?)");
                    pst12.setString(1, pcat);
                    pst12.executeUpdate();
                    PreparedStatement pst1234=con.prepareStatement("Select * from category where catN=?");
                    pst1234.setString(1, pcat);
                    ResultSet rsp=pst1234.executeQuery();
                    while(rsp.next())
                    {
                        cid=rsp.getInt("cid");
                    }
                }
                int t2=0;
                 PreparedStatement p=con.prepareStatement("Select pname from productinfo where pname=?");
                p.setString(1, pname);
                ResultSet rt=p.executeQuery();
                if(rt.next())
                {
                   t2=1; 
                }   
                else{
                PreparedStatement pst1=con.prepareStatement("insert into productinfo(pname,cid) values(?,?)");
                pst1.setString(1, pname);
                pst1.setInt(2,cid);   
                t2=pst1.executeUpdate();
                
                }
                 
                PreparedStatement pst2=con.prepareStatement("insert into stock(pid,size,color,wholeSalePrice,sellingPrice,quantity) values(?,?,?,?,?,?)");
                pst2.setInt(1, pid);
                System.out.println("calling");
                pst2.setString(2, size);
                pst2.setString(3, color);
                pst2.setInt(4, tp);
                pst2.setInt(5, sp);
                pst2.setInt(6, quantity);
                int t3=pst2.executeUpdate();
                System.out.print("8");
                
                if(t2==1 && t3==1)
                {
                     JOptionPane.showMessageDialog(null,"Information Updated!");
                }
                else
                {
                    System.out.print("9");
                    JOptionPane.showMessageDialog(null,"Error!");
                }  
            }
        catch (Exception ex) 
        {
           System.out.println(ex);
        } 
    }
public void deletet() throws SQLException{
    int quan=0;
      Statement s=con.createStatement(); 
              
            int r1= s.executeUpdate("delete from drugreg where quantity='"+quan+"'");
            
}
public void updatet(int q,String n){
    
    int qua=0,rest=0;
   
    try
        {    Statement s=con.createStatement(); 
            ResultSet rs= s.executeQuery("Select * from drugreg where drugname='"+n+"'");
            while(rs.next()){
               qua=rs.getInt("QUANTITY");
                rest=(qua-q);
            
            }
           
           
            PreparedStatement pst=con.prepareStatement("update drugreg set quantity=? where drugname=?");
            pst.setInt(1, rest);
            pst.setString(2, n);
            pst.executeUpdate();
            
        }
        catch(SQLException | NumberFormatException e)
        { 
            System.out.println(e);
        }  
        
    }
    
    
}
