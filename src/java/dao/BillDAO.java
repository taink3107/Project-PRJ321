/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Bill;
import entity.BillElement;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.SQLconnection;

/**
 *
 * @author NGUYEN KHANH TAI
 */
public class BillDAO {
    
    public List<Bill> getAllBillById(int id) {
        String query = "SELECT tbl1.id as billId,tbl1.create_date as cDate,shipping_info.name as name,quantity,tbl1.status,\n" +
"                shipping_info.address as address,tbl1.total_price as total,tbl1.note as note\n" +
"                FROM\n" +
"                (SELECT [order].id,[order].create_date,[order].shipping_info_id as ship_id,sum(order_details.quantity)as quantity,[order].total_price,[order].note,[order].status FROM\n" +
"                 [order] INNER JOIN order_details\n" +
"                 ON [order].id = order_details.orderId\n" +
"                WHERE acc_id = ?\n" +
"                GROUP BY [order].id,[order].create_date,[order].total_price,[order].note,shipping_info_id, [order].status)as tbl1 INNER JOIN shipping_info\n" +
"                ON shipping_info.id = tbl1.ship_id";
        List<Bill> list = new ArrayList<>();
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = (ps != null) ? ps.executeQuery() : null;
                while (rs != null && rs.next()) {
                    Bill bill = Bill.builder()
                            .billId(rs.getInt("billId"))
                            .name(rs.getString("name"))
                            .date(rs.getString("cDate"))
                            .quantity(rs.getInt("quantity"))
                            .total_price(rs.getDouble("total"))
                            .note(rs.getString("note"))
                            .address(rs.getString("address"))
                            .status(rs.getInt("status"))
                            .build();
                    list.add(bill);
                }
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    public List<BillElement> getAllByBillId(int id) {
        String query = "SELECT order_details.id as id, product.image,product_name as name,product_price as price,order_details.product_size,order_details.quantity FROM [dbo].[order_details]\n"
                + " INNER JOIN product\n"
                + " ON order_details.productId = product.id\n"
                + " WHERE order_details.orderId = ?";
        List<BillElement> elements = new ArrayList<>();
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = (ps != null) ? ps.executeQuery() : null;
                while (rs != null && rs.next()) {
                    BillElement element = BillElement
                            .builder()
                            .id(rs.getInt("id"))
                            .image(rs.getString("image"))
                            .name(rs.getString("name"))
                            .price(rs.getDouble("price"))
                            .quantity(rs.getInt("quantity"))
                            .size(rs.getString("product_size"))
                            .build();
                    elements.add(element);
                }
            }
            return elements;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
   
    public static void main(String[] args) {
        System.out.println(new BillDAO().getAllByBillId(8));
    }
}
