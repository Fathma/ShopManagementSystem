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
import javax.swing.JOptionPane;

/**
 *
 * @author Ana
 */
public class stock extends product{
    private String color,size;
    private int wholesaleprice, sellingp,quantity;
    Connection con;
    public stock() throws SQLException
	        {
			con = db.connect(); 
                        
	        }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getWholesaleprice() {
        return wholesaleprice;
    }

    public void setWholesaleprice(int wholesaleprice) {
        this.wholesaleprice = wholesaleprice;
    }

    public int getSellingp() {
        return sellingp;
    }

    public void setSellingp(int sellingp) {
        this.sellingp = sellingp;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void stock_get_wholesaleprice(){
        try {
            PreparedStatement pst=con.prepareStatement("select wholeSalePrice from stock where pid=? and color=? and size=?");
            pst.setInt(1, getPid());
            pst.setString(2, color);
            pst.setString(3, size);
            ResultSet rs =pst.executeQuery();
            if (rs.next()){
                wholesaleprice=rs.getInt("wholeSalePrice");
            }
        } catch (SQLException ex) {
            Logger.getLogger(stock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void stock_add() {
            
            int cid=0;
            try
            {   
                PreparedStatement pst123=con.prepareStatement("Select * from category where catN=?");
                pst123.setString(1, getCatname());
                ResultSet rescat=pst123.executeQuery();
                if(rescat.next())
                {
                    cid=rescat.getInt("cid");
                }
                else
                {
                    PreparedStatement pst12=con.prepareStatement("insert into category(catN) values(?)");
                    pst12.setString(1, getCatname());
                    pst12.executeUpdate();
                    PreparedStatement pst1234=con.prepareStatement("Select * from category where catN=?");
                    pst1234.setString(1, getCatname());
                    ResultSet rspr=pst1234.executeQuery();
                    while(rspr.next())
                    {
                        cid=rspr.getInt("cid");
                    }
                }
                int piddd=0;
                
                Phar omh=new Phar();
                
                PreparedStatement pst22=con.prepareStatement("Select pid from productinfo where pname=?");
                pst22.setString(1, getPname());
                ResultSet rsn=pst22.executeQuery();
                if(rsn.next()){
                    piddd=rsn.getInt("pid");
                    PreparedStatement pst2=con.prepareStatement("insert into stock(pid,size,color,wholeSalePrice,"
                        + "sellingPrice,quantity) values(?,?,?,?,?,?)");
                pst2.setInt(1, piddd);
                pst2.setString(2, size);
                pst2.setString(3, color);
                pst2.setInt(4, wholesaleprice);
                pst2.setInt(5, sellingp);
                pst2.setInt(6, quantity);
               
                int t3=pst2.executeUpdate();
                 if( t3==1)
                {
                     JOptionPane.showMessageDialog(null,"Product is added!");
                }
                else
                {
                    
                    JOptionPane.showMessageDialog(null,"Error!");
                } 
                }
                else{       
                PreparedStatement pst1t=con.prepareStatement("insert into productinfo(pname,cid) values(?,?)");
                pst1t.setString(1,getPname());
                pst1t.setInt(2,cid);   
                pst1t.executeUpdate();
                
                PreparedStatement pst2=con.prepareStatement("insert into stock(pid,size,color,wholeSalePrice,"
                        + "sellingPrice,quantity) values((select pid from productinfo where pname=?),?,?,?,?,?)");
                pst2.setString(1, getPname());
                pst2.setString(2, size);
                pst2.setString(3, color);
                pst2.setInt(4, wholesaleprice);
                pst2.setInt(5, sellingp);
                pst2.setInt(6, quantity);
                int t3=pst2.executeUpdate();
                 if( t3==1)
                {
                     JOptionPane.showMessageDialog(null,"Product is added!");
                }
                else
                {
                    
                    JOptionPane.showMessageDialog(null,"Error!");
                }  }
               
            }
        catch (Exception ex) 
        {
           System.out.println(ex);
        } 
            
            
    }
    
}
