/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Account;
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
public class AccountDAO {

    public Account getOne(String user, String pass) {
        String query = "SELECT * FROM Account\n"
                + " WHERE email =? and password =?";
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, user);
                ps.setObject(2, pass);
                ResultSet rs = ps.executeQuery();
                while (rs != null && rs.next()) {
                    Account account = Account.builder()
                            .id(rs.getInt("id"))
                            .email(rs.getString("email"))
                            .name(rs.getString("username"))
                            .password(rs.getString("password"))
                            .address(rs.getString("address"))
                            .admin(rs.getBoolean("administrator"))
                            .phone(rs.getString("phone"))
                            .build();
                    return account;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<Account> getAll() {
        String query = "SELECT * FROM Account";
        List<Account> list = new ArrayList<>();
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            ResultSet rs = (ps != null) ? ps.executeQuery() : null;
            while (rs != null && rs.next()) {
                Account acc = Account.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("username"))
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))
                        .address(rs.getString("address"))
                        .phone(rs.getString("phone"))
                        .admin(rs.getBoolean("administrator"))
                        .build();
                list.add(acc);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean removeAccount(int id) {
        String query = "DELETE FROM Account WHERE id = ?";
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                return ps.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public boolean insertAccount(Account obj) {
        String query = "INSERT INTO Account(email, password, username, address, administrator,"
                + " phone)"
                + " VALUES(?, ?, ?, ?, ?, ?)";
        int check = 0;
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getEmail());
                ps.setObject(2, obj.getPassword());
                ps.setObject(3, obj.getName());
                ps.setObject(4, obj.getAddress());
                ps.setObject(5, obj.isAdmin());
                ps.setObject(6, obj.getPhone());
                check = ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean update(Account obj, int id) {
        String query = "UPDATE account SET email = ?, password = ?, username = ?,"
                + "address = ?, administrator = ?, phone = ? WHERE id=?";
        int check = 0;
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getEmail());
                ps.setObject(2, obj.getPassword());
                ps.setObject(3, obj.getName());
                ps.setObject(4, obj.getAddress());
                ps.setObject(5, 0);
                ps.setObject(6, obj.getPhone());
                ps.setObject(7, id);
                check = ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public static void main(String[] args) {
        // System.out.println(new AccountDAO().getOne("tainkhe130279@gmail.com", "123456"));
        Account a = Account.builder().address("a").admin(true).email("âa").password("aaaa").phone("0974807476").name("tai").build();
        System.out.println(new AccountDAO().getAll());
    }
}
