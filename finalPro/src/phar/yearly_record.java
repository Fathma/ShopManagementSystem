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
public class yearly_record extends dailyrecord{
        private int year,profit;
    
    Connection con;
     public yearly_record() throws SQLException
	        {
			con = db.connect(); 
                        
	        }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

   
    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }
    
    public void yearly_insert(){
        try {
            int ex=0,in=0, pro=0;
            PreparedStatement pst =con.prepareStatement("select * from yearly where year=? ");
            pst.setInt(1, year);
            ResultSet rs=pst.executeQuery();
            if (rs.next()){
                
            }
            else{
                PreparedStatement pst1 =con.prepareStatement("select * from dailyrecord where year(date)=? ");
           
            pst1.setInt(1, year);
            ResultSet rs1=pst1.executeQuery();
            while(rs1.next()){
                ex+=rs1.getInt("expenses");
                in+=rs1.getInt("income");   
            }
                PreparedStatement inse=con.prepareStatement("insert into yearly values(?,?,?,?)");
            
                inse.setInt(1, year);
                inse.setInt(2, in);
                inse.setInt(3, ex);
                inse.setInt(4, in-ex);
                inse.executeUpdate();
                
                    
            }
        } catch (SQLException ex) {
            Logger.getLogger(monthly_record.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
