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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana
 */
public class incomewithpurpose {
    int amount;
    String purpose;
    Connection con;
    public incomewithpurpose() throws SQLException
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
    public void exp_insert_value(java.sql.Date da){
        try 
        {
            PreparedStatement pst =con.prepareStatement("insert into income(date, amount, purpose) values(?,?,?)");
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
            
            PreparedStatement pst=con.prepareStatement("select income from dailyrecord where date=?");
            pst.setDate(1, da);
            ResultSet rs=  pst.executeQuery();
            if (rs.next()){
                ta=rs.getInt("income");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(expenseWithPurpose.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (ta);
    }
}
