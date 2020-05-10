/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import entity.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.SQLconnection;

/**
 *
 * @author NGUYEN KHANH TAI
 */
public class CategoryDAO implements MethodDao<Category> {

    public List<Category> getAllElement() {
        List<Category> categorys = new ArrayList<>();
        String query = "SELECT * FROM category";
        try (Connection conn = SQLconnection.getConnection();
                PreparedStatement statement = (conn != null) ? conn.prepareStatement(query) : null;) {
            ResultSet rs = (statement != null) ? statement.executeQuery() : null;
            while (rs != null && rs.next()) {
                Category category = Category.builder()
                        .cateID(rs.getInt(1))
                        .cateName(rs.getString(2))
                        .status(rs.getInt(3))
                        .build();
                categorys.add(category);
            }
            return categorys;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
   
    @Override
    public Category getOne(int id) {
        Category category;
        String query = "SELECT * FROM category"
                + " Where [category_ID]= ?";
        try (Connection conn = SQLconnection.getConnection();
                PreparedStatement statement = (conn != null) ? conn.prepareStatement(query) : null) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs != null && rs.next()) {
                category = Category.builder()
                        .cateID(rs.getInt(1))
                        .cateName(rs.getString(2))
                        .status(rs.getInt(3))
                        .banner(rs.getString(4))
                        .build();
                return category;
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public boolean add(Category obj) {
        String query = "INSERT INTO category(category_Name,status)"
                + " VALUES ?, ?, ?";
        int check = 0;
        try (Connection connection = SQLconnection.getConnection();
                PreparedStatement statement = (connection != null) ? connection.prepareStatement(query) : null;) {
            statement.setObject(1, obj.getCateName());
            statement.setObject(2, obj.getStatus());
            check = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check > 0;
    }

    @Override
    public boolean update(Category obj, int id) {
        String query = "UPDATE category SETcategory_Name = ?, status = ? WHERE [category_ID] = ?";
        int check = 0;
        try (Connection connection = SQLconnection.getConnection();
                PreparedStatement statement = (connection != null) ? connection.prepareStatement(query) : null;) {
            if (statement != null) {
                statement.setObject(1, obj.getCateName());
                statement.setObject(2, obj.getStatus());
                statement.setObject(3, id);
                check = statement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check > 0;
    }

    @Override
    public boolean remove(int id) {
        String query = "DELETE FROM category WHERE [category_ID] =?";
        int check = 0;
        try (Connection connection = SQLconnection.getConnection();
                PreparedStatement statement = (connection != null) ? connection.prepareStatement(query) : null;) {
            if (statement != null) {
                statement.setObject(1, id);
                check = statement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check > 0;
    }

    public static void main(String[] args) {
        Category category = Category.builder()
                .cateName("taink")
                .status(2)
                .build();
        new CategoryDAO().remove(4);
    }

    @Override
    public List<Category> getAll() {
        List<Category> categorys = new ArrayList<>();
        String query = "SELECT * FROM category";
        try (Connection conn = SQLconnection.getConnection();
                PreparedStatement statement = (conn != null) ? conn.prepareStatement(query) : null;) {
            ResultSet rs = (statement != null) ? statement.executeQuery() : null;
            while (rs != null && rs.next()) {
                Category category = Category.builder()
                        .cateID(rs.getInt(1))
                        .cateName(rs.getString(2))
                        .status(rs.getInt(3))
                        .build();
                categorys.add(category);
            }
            return categorys;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
}
