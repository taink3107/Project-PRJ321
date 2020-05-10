/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.District;
import entity.Product;
import entity.Provinct;
import entity.Town;
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
public class AddressDAO {

    public List<District> getAllByProvincialID(String value) {
        String query = "SELECT * FROM district WHERE district.provincId = ?";
        List<District> list = new ArrayList<>();
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, value);
                ResultSet rs = (ps != null) ? ps.executeQuery() : null;
                while (rs != null && rs.next()) {

                    District district = District.builder()
                            .id(rs.getString("id"))
                            .name(rs.getString("name"))
                            .prov(rs.getString("provincId"))
                            .rank(rs.getString("rank"))
                            .build();
                    list.add(district);
                }
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public District getOneDistrict(String id) {
        String query = "SELECT * FROM district WHERE district.id = ?";
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs != null && rs.next()) {
                    District district = District.builder()
                            .id(rs.getString("id"))
                            .name(rs.getString("name"))
                            .prov(rs.getString("provincId"))
                            .rank(rs.getString("rank"))
                            .build();
                    return district;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Town getOneTown(String id) {
        String query = "SELECT * FROM town WHERE town.id = ? ";
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs != null && rs.next()) {
                    Town town = Town.builder()
                            .id(rs.getString("id"))
                            .name(rs.getString("name"))
                            .prov(rs.getString("provincId"))
                            .district(rs.getString("districtId"))
                            .rank(rs.getString("rank"))
                            .build();
                    return town;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<Town> getAllByDistrictID(String value) {
        String query = "SELECT * FROM town WHERE town.districtId = ? ";
        List<Town> list = new ArrayList<>();
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, value);
                ResultSet rs = (ps != null) ? ps.executeQuery() : null;
                while (rs != null && rs.next()) {
                    Town town = Town.builder()
                            .id(rs.getString("id"))
                            .name(rs.getString("name"))
                            .prov(rs.getString("provincId"))
                            .district(rs.getString("districtId"))
                            .rank(rs.getString("rank"))
                            .build();
                    list.add(town);
                }
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new AddressDAO().getAllByDistrictID("001"));
    }
}
