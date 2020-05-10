/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import entity.Account;
import entity.Cart;
import entity.Order;
import entity.ShippingInfo;
import entity.Size;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbc.SQLconnection;

/**
 *
 * @author NGUYEN KHANH TAI
 */
public class ShippingInfoDAO {

    public int addShipingInfoReturnId(ShippingInfo shipingInfo) {
        String query = "INSERT INTO shipping_info([name], [phone],[email], [address])"
                + " VALUES(?, ?, ?,?)";
        try (Connection con = jdbc.SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS) : null;) { // get ID when user input data is recently.
            if (ps != null) {
                ps.setObject(1, shipingInfo.getName());
                ps.setObject(2, shipingInfo.getPhone());
                ps.setObject(3, shipingInfo.getEmail());
                ps.setObject(4, shipingInfo.getAddress());
                int isCheck = ps.executeUpdate();
                if (isCheck > 0) {
                    ResultSet rs = ps.getGeneratedKeys(); // get ID when user input data is recently.
                    rs.next();
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }

    public int getSizeOrder(int status) {
        int number = 0;
        String query = "SELECT count(*) as total FROM [order]\n"
                + "WHERE [order].status = ?";
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, status);
                ResultSet rs = ps.executeQuery();
                while (rs != null && rs.next()) {
                    number = rs.getInt(1);
                }
                return number;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return -1;
    }

    public boolean updateStatusOrder(int orderID, int status) {
        String query = "UPDATE [order] SET status= ? \n"
                + " WHERE id = ?";
        int check = 0;
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, status);
                ps.setObject(2, orderID);
                check = ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public static void main(String[] args) {
        System.out.println(new ShippingInfoDAO().updateStatusOrder(8, 2));
    }

    public List<Order> getAllByStatus(int status) {
        String query = "SELECT * FROM [order]\n"
                + " WHERE [order].status = ?";
        List<Order> orders = new ArrayList<>();
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, status);
                ResultSet rs = ps.executeQuery();
                while (rs != null && rs.next()) {
                    Order order = Order
                            .builder()
                            .id(rs.getInt("id"))
                            .id_account(rs.getString("acc_id"))
                            .ship_infoId(rs.getInt("shipping_info_id"))
                            .date(rs.getString("create_date"))
                            .total_price(rs.getDouble("total_price"))
                            .note(rs.getString("note"))
                            .status(rs.getInt("status"))
                            .build();
                    orders.add(order);
                }
                return orders;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean removeOrder(int id) {
        String query = "DELETE FROM [order] WHERE shipping_info_id = ?";
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

    public boolean removeShipping(int id) {
        String query = "DELETE FROM shipping_info WHERE id = ?";
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

    public int addOrderReturnId(Order order) {
        String query = "INSERT INTO [order]([shipping_info_id], [total_price], [note], [status],[acc_id])"
                + " VALUES(?, ?, ?, ?,?)";
        try (Connection con = jdbc.SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS) : null;) {
            if (ps != null) {
                ps.setObject(1, order.getShip_infoId());
                ps.setObject(2, order.getTotal_price());
                ps.setObject(3, order.getNote());
                ps.setObject(4, order.getStatus());
                ps.setObject(5, order.getId_account());
                int isCheck = ps.executeUpdate();
                if (isCheck > 0) {
                    ResultSet rs = ps.getGeneratedKeys();
                    rs.next();
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }

    public boolean addListCart(List<Cart> list, int orderId) {
        String query = "INSERT INTO order_details([orderId], [productId], [product_name], [product_size],[product_price], [quantity])"
                + " VALUES(?, ?, ?, ?, ?,?)";
        int[] arr = {};
        try (Connection con = jdbc.SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                for (Cart c : list) {
                    ps.setObject(1, orderId);
                    ps.setObject(2, c.getProductId());
                    ps.setObject(3, c.getProductName());
                    ps.setObject(4, c.getSize().getSize());
                    ps.setObject(5, c.getProductPrice());
                    ps.setObject(6, c.getQuantity());
                    ps.addBatch();
                }
                arr = ps.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return arr.length > 0;
    }

}
