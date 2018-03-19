/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phar;

import finalpro.homepage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ana
 */
public class employee {
    
    private String name, phone, address, nid,position;
    private int id, perdaysalary;
    
     Connection con;
     public employee() throws SQLException
	        {
			con = db.connect(); 
                        
	        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPerdaysalary() {
        return perdaysalary;
    }

    public void setPerdaysalary(int perdaysalary) {
        this.perdaysalary = perdaysalary;
    }
    
    public ResultSet emp_validity_check(){
        ResultSet nr = null;
        try {
            PreparedStatement pre=con.prepareStatement("Select * from emp where eid=?");
            pre.setInt(1, id);
            nr=pre.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nr;
    }
    public void add_employee(){
        try {
            PreparedStatement pst=con.prepareStatement("insert into emp(ename, phone, address,nid, perdaysalary,position)"
                    + "values(?,?,?,?,?,?)");
            pst.setString(1, name);
            pst.setString(2, phone);
            pst.setString(3, address);
            pst.setString(4, nid);
            pst.setInt(5, perdaysalary);
            pst.setString(6, position);
            int g=pst.executeUpdate();
            
            if(g==1){
                JOptionPane.showMessageDialog(null,"information inserted.");
            }
            else{
                JOptionPane.showMessageDialog(null,"Password is incorrect!");
            }
            } catch (SQLException ex) {
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void attendance(){
         try{
        // getting today's date by calling function   
        Phar ob=new Phar(); 
        java.sql.Date curdate= ob.get_current_date();
        
        ResultSet nr=emp_validity_check();
        if(nr.next()){  
            //checking attendance is given or not
            PreparedStatement p=con.prepareStatement("Select * from attendance where eid=? and date=? ");
            p.setInt(1, id);
            p.setDate(2, curdate);
            ResultSet mula=p.executeQuery();
       
            //if attendance is done
            if(mula.next())
            {
                JOptionPane.showMessageDialog(null,"Already given!");
            }
            
            //if attendance is not done
            else
            {
                // giving attendance 
                PreparedStatement pst=con.prepareStatement("insert into attendance(eid, attendance, date) values(?,?,?)");
                pst.setInt(1, id);
                pst.setInt(2, 1);
                pst.setDate(3, curdate);
                pst.executeUpdate();
                
                // getting perday salary
                int perday=0;
                int sal=0;
                System.out.println(id);
                PreparedStatement polo=con.prepareStatement("Select perdaysalary from emp where eid=? ");
                polo.setInt(1, id);
                ResultSet alu=polo.executeQuery();
                while (alu.next())
                {
                    perday=alu.getInt("perdaysalary");
                    System.err.println(perday);

                }
                // checking current month data is present in the salary table or not
                PreparedStatement lol=con.prepareStatement("select s.salary from salary as s where eid=? and "
                        + "s.month=MONTH(?) and s.year=YEAR(?)");
                lol.setInt(1, id);
                lol.setDate(2, curdate);
                lol.setDate(3, curdate);
                ResultSet tt=lol.executeQuery();
                
                // if current month is not preset in the table,update that row
                if(tt.next())
                {
                    sal=tt.getInt("salary")+perday;
                    PreparedStatement pss=con.prepareStatement("update salary set salary=? where eid=? ");
                    pss.setInt(1, sal);
                    pss.setInt(2, id);
                    pss.executeUpdate();
                }
                //if current month is not preset in the table, insert new row
                else{
                    PreparedStatement pssl=con.prepareStatement("insert into salary(eid,salary, month, year) values(?,?,month(?),year(?))");
                    pssl.setInt(1, id);
                    pssl.setInt(2, perday);
                    pssl.setDate(3, curdate);
                    pssl.setDate(4, curdate);

                    pssl.executeUpdate();

                }
                

                JOptionPane.showMessageDialog(null,"Done!");
                }
        }
        else{
              JOptionPane.showMessageDialog(null,"Invalid ID"); 
               }
        }
       catch(Exception ex){
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    public int emp_total_attendance(String mont, int ya){
        int totalA = 0;
        try {
            
            //calling the function converting string month to int
            Phar ob=new Phar();
            int monthinNum=ob.get_month_no(mont);
            
            // query for getting value from Attendance and salary table
            PreparedStatement pst= con.prepareStatement("select SUM(attendance) from attendance "
                    + "where eid=? and MONTH(date)=? and YEAR(date)=?");
            pst.setInt(1, id);
            pst.setInt(2, monthinNum);
            pst.setInt(3, ya);
            ResultSet rs=pst.executeQuery();
            
            // sending value to interface
            if (rs.next()){
               totalA= rs.getInt(1);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalA;
    }    
    
    public int emp_total_salary(String mont,int ya){
        int salary = 0;    
        try {
            //calling the function converting string month to int
            Phar ob=new Phar();
            int monthinNum=ob.get_month_no(mont);
            PreparedStatement p= con.prepareStatement("Select * from salary as s where eid=? and month=? and year=?");
            p.setInt(1, id);
            p.setInt(2, monthinNum);
            p.setInt(3, ya);
            ResultSet rsw=p.executeQuery();
            
            if (rsw.next()){
                salary=rsw.getInt("s.salary");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salary;
    }
    public String emp_salary_status(String mont,int ya){
        String sta = "";    
        try {
            //calling the function converting string month to int
            Phar ob=new Phar();
            int monthinNum=ob.get_month_no(mont);
            PreparedStatement p= con.prepareStatement("Select * from salary as s where eid=? and month=? and year=?");
            p.setInt(1, id);
            p.setInt(2, monthinNum);
            p.setInt(3, ya);
            ResultSet rsw=p.executeQuery();
            
            if (rsw.next()){
                sta=rsw.getString("s.status");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sta;
    }
}
