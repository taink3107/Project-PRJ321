/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Product;
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
 * @author TAI KHANH NGUYEN
 */
public class ProductDao implements MethodDao<Product> {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM product";
        List<Product> list = new ArrayList<>();
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            ResultSet rs = (ps != null) ? ps.executeQuery() : null;
            while (rs != null && rs.next()) {
                Product product = Product.builder()
                        .id(rs.getInt(1))
                        .categoryId(rs.getInt(2))
                        .code(rs.getString(3))
                        .name(rs.getString(4))
                        .quantity(rs.getInt(5))
                        .price(rs.getDouble(6))
                        .description(rs.getString(7))
                        .image(rs.getString(8))
                        .createDate(rs.getString(9))
                        .status(rs.getInt(10))
                        .build();
                list.add(product);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<Product> searchByName(String txt) {
        String query = "SELECT * FROM product WHERE name LIKE ?";
        List<Product> list = new ArrayList<>();
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, "%" + txt + "%");
                ResultSet rs = (ps != null) ? ps.executeQuery() : null;
                while (rs != null && rs.next()) {
                    Product product = Product.builder()
                            .id(rs.getInt(1))
                            .categoryId(rs.getInt(2))
                            .code(rs.getString(3))
                            .name(rs.getString(4))
                            .quantity(rs.getInt(5))
                            .price(rs.getDouble(6))
                            .description(rs.getString(7))
                            .image(rs.getString(8))
                            .createDate(rs.getString(9))
                            .status(rs.getInt(10))
                            .build();
                    list.add(product);
                }
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<Product> searchBySize(String txt) {
        String query = "    SELECT product.id,category_id,product.code,product.name,product.quantity,product.price,product.description,product.image,product.create_date,product.status FROM\n"
                + "   (SELECT * FROM size INNER JOIN product_size\n"
                + "   ON size.id = product_size.sizeId)as tbl1 INNER JOIN product\n"
                + "   ON product.Id = tbl1.proId\n"
                + "   WHERE tbl1.size = ?";
        List<Product> list = new ArrayList<>();
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, txt);
                ResultSet rs = (ps != null) ? ps.executeQuery() : null;
                while (rs != null && rs.next()) {
                    Product product = Product.builder()
                            .id(rs.getInt(1))
                            .categoryId(rs.getInt(2))
                            .code(rs.getString(3))
                            .name(rs.getString(4))
                            .quantity(rs.getInt(5))
                            .price(rs.getDouble(6))
                            .description(rs.getString(7))
                            .image(rs.getString(8))
                            .createDate(rs.getString(9))
                            .status(rs.getInt(10))
                            .build();
                    list.add(product);
                }
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<Product> getAllByCategoryID(int id) {
        String query = "SELECT * FROM product Where category_id = ?";
        List<Product> list = new ArrayList<>();
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = (ps != null) ? ps.executeQuery() : null;
                while (rs != null && rs.next()) {
                    Product product = Product.builder()
                            .id(rs.getInt(1))
                            .categoryId(rs.getInt(2))
                            .code(rs.getString(3))
                            .name(rs.getString(4))
                            .quantity(rs.getInt(5))
                            .price(rs.getDouble(6))
                            .description(rs.getString(7))
                            .image(rs.getString(8))
                            .createDate(rs.getString(9))
                            .status(rs.getInt(10))
                            .build();
                    list.add(product);
                }
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<Product> getAllForPaging(int id, int pageSize, int pageIndex) {
        String query = "SELECT * FROM \n"
                + " (SELECT *,ROW_NUMBER() OVER (ORDER BY ID ASC) as row_num FROM  Product WHERE category_id=?) \n"
                + " tblDummy WHERE row_num >= (? - 1)*? +1 AND row_num<= ? * ?";
        List<Product> list = new ArrayList<>();
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                ps.setObject(2, pageIndex);
                ps.setObject(3, pageSize);
                ps.setObject(4, pageIndex);
                ps.setObject(5, pageSize);
                ResultSet rs = (ps != null) ? ps.executeQuery() : null;
                while (rs != null && rs.next()) {
                    Product product = Product.builder()
                            .id(rs.getInt(1))
                            .categoryId(rs.getInt(2))
                            .code(rs.getString(3))
                            .name(rs.getString(4))
                            .quantity(rs.getInt(5))
                            .price(rs.getDouble(6))
                            .description(rs.getString(7))
                            .image(rs.getString(8))
                            .createDate(rs.getString(9))
                            .status(rs.getInt(10))
                            .build();
                    list.add(product);
                }
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product getOne(int id) {
        String query = "SELECT * FROM product WHERE id = ?";
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs != null && rs.next()) {
                    Product product = Product.builder()
                            .id(rs.getInt(1))
                            .categoryId(rs.getInt(2))
                            .code(rs.getString(3))
                            .name(rs.getString(4))
                            .quantity(rs.getInt(5))
                            .price(rs.getDouble(6))
                            .description(rs.getString(7))
                            .image(rs.getString(8))
                            .createDate(rs.getString(9))
                            .status(rs.getInt(10))
                            .build();
                    return product;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Product obj) {
        String query = "INSERT INTO product(category_id, code, name, quantity, price,"
                + " description, image, status)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        int check = 0;
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getCategoryId());
                ps.setObject(2, obj.getCode());
                ps.setObject(3, obj.getName());
                ps.setObject(4, obj.getQuantity());
                ps.setObject(5, obj.getPrice());
                ps.setObject(6, obj.getDescription());
                ps.setObject(7, obj.getImage());
                ps.setObject(8, obj.getStatus());
                check = ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(Product obj, int id) {
        String query = "UPDATE product SET category_id = ?, code = ?, name = ?,"
                + "quantity = ?, price = ?, description = ?, image = ?,"
                + "status = ? WHERE id = ?";
        int check = 0;
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getCategoryId());
                ps.setObject(2, obj.getCode());
                ps.setObject(3, obj.getName());
                ps.setObject(4, obj.getQuantity());
                ps.setObject(5, obj.getPrice());
                ps.setObject(6, obj.getDescription());
                ps.setObject(7, obj.getImage());
                ps.setObject(8, obj.getStatus());
                ps.setObject(9, id);
                check = ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(int id) {
        String query = "DELETE FROM product WHERE id = ?";
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

    public int count(int categoryid) {
        String query = "SELECT count(*) as count FROM product WHERE category_id = ?";
        try (Connection con = SQLconnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, categoryid);
                ResultSet rs = ps.executeQuery();
                while (rs != null && rs.next()) {
                    return rs.getInt("count");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new ProductDao().searchBySize("42"));

    }
}
