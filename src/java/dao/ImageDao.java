/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Image;
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
public class ImageDao implements MethodDao<Image> {

    @Override
    public List<Image> getAll() {
        String query = "SELECT * FROM image";
        List<Image> list = new ArrayList<>();
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            ResultSet rs = (ps != null) ? ps.executeQuery() : null;
            while (rs != null && rs.next()) {
                Image i = Image.builder()
                        .id(rs.getInt(1))
                        .proId(rs.getInt(4))
                        .name(rs.getString(2))
                        .status(rs.getInt(3))
                        .build();
                list.add(i);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<Image> getAllByProductId(int id) {
        String query = "SELECT * FROM image WHERE productID = ? ";
        List<Image> list = new ArrayList<>();
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = (ps != null) ? ps.executeQuery() : null;
                while (rs != null && rs.next()) {
                    Image i = Image.builder()
                            .id(rs.getInt(1))
                            .name(rs.getString(2))
                            .status(rs.getInt(3))
                            .proId(rs.getInt(4))
                            .build();
                    list.add(i);
                }
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public Image getOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(Image object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Image object, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
