/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Product;
import entity.Size;
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
public class SizeDao implements MethodDao<Size> {

    @Override
    public List<Size> getAll() {
        String query = "SELECT * FROM size";
        List<Size> list = new ArrayList<>();
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            ResultSet rs = (ps != null) ? ps.executeQuery() : null;
            while (rs != null && rs.next()) {
                Size size = Size.builder()
                        .proId(rs.getInt(1))
                        .size(rs.getString(2))
                        .build();
                list.add(size);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public Size getOne(int id) {
        String query = "SELECT * FROM size WHERE id=?";
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();
                Size size = null;
                while (rs != null && rs.next()) {
                    size = Size.builder()
                            .proId(rs.getInt(1))
                            .size(rs.getString(2))
                            .build();
                }
                return size;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<Size> getAllByProduct(int id) {
        List<Size> list = new ArrayList<>();
        String query = "SELECT tbl1.sizeId,tbl1.size FROM (SELECT * FROM product_size INNER JOIN size ON product_size.sizeId = size.id WHERE product_size.status = 1) as tbl1 INNER JOIN product\n"
                + " ON product.id = tbl1.proId\n"
                + " WHERE product.id = ?";
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs != null && rs.next()) {
                    Size size = Size.builder()
                            .proId(rs.getInt(1))
                            .size(rs.getString(2))
                            .build();
                    list.add(size);
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public boolean add(Size object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Size object, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) {
        for(Size s : new SizeDao().getAllByProduct(1)){
            System.out.println(s.toString());
        }
    }
}
