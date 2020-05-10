/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Product;
import entity.Provinct;
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
public class ProvinctDAO implements MethodDao<Provinct> {

    @Override
    public List<Provinct> getAll() {
        String query = "SELECT * FROM provincial";
        List<Provinct> provincts = new ArrayList<>();
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            ResultSet rs = (ps != null) ? ps.executeQuery() : null;
            while (rs != null && rs.next()) {
                Provinct p = Provinct.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .rank(rs.getString("rank"))
                        .build();
                provincts.add(p);
            }
            return provincts;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public Provinct getOne(int id) {
        String query = "SELECT * FROM provincial WHERE id = ?";
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs != null && rs.next()) {
                    Provinct p = Provinct.builder()
                            .id(rs.getInt("id"))
                            .name(rs.getString("name"))
                            .rank(rs.getString("rank"))
                            .build();
                    return p;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public boolean add(Provinct object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Provinct object, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) {
        List<Provinct> list = new ProvinctDAO().getAll();
    }
}
