/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phar;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana
 */
public class expenseWithPurpose {
    private int amount;
    private String purpose;
    private java.sql.Date date;
    Connection con;
    public expenseWithPurpose() throws SQLException
    {
        con = db.connect(); 

    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public void exp_insert_value(java.sql.Date da){
        try 
        {
            PreparedStatement pst =con.prepareStatement("insert into expenses(date, amount, purpose) values(?,?,?)");
            pst.setDate(1, da);
            pst.setInt(2, amount);
            pst.setString(3, purpose);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(expenseWithPurpose.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int exp_get_total(java.sql.Date da){
        int ta=0;
        try {
            
            PreparedStatement pst=con.prepareStatement("select expenses from dailyrecord where date=?");
            pst.setDate(1, da);
            ResultSet rs=  pst.executeQuery();
            if (rs.next()){
                ta=rs.getInt("expenses");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(expenseWithPurpose.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (ta);
    }
}
