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
public class dailyrecord {
    private int expenses, income;
    Connection con;
     public dailyrecord() throws SQLException
	        {
			con = db.connect(); 
                        
	        }
    public int getExpenses() {
        return expenses;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }
    public void dailyre_total_monthly(int mont,int year){
        try {
            PreparedStatement pst=con.prepareStatement("Select * from dailyrecord where MONTH(date)=? and Year(date)=?");
            pst.setInt(1, mont);
            pst.setInt(2, year);
            ResultSet rs= pst.executeQuery();
            while (rs.next()){
                expenses +=rs.getInt("expenses");
                income +=rs.getInt("income");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(dailyrecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void dailyre_total_yearly(int year){
        try {
            PreparedStatement pst=con.prepareStatement("Select * from dailyrecord where Year(date)=?");
            
            pst.setInt(1, year);
            ResultSet rs= pst.executeQuery();
            while (rs.next()){
                expenses +=rs.getInt("expenses");
                income +=rs.getInt("income");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(dailyrecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
