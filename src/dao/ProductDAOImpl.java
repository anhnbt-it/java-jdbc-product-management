/*
 * Java 2 Practical - HaNoi Aptech
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;

public class ProductDAOImpl implements ProductDAO {

    private final Connection conn;
    private final String SQL_INSERT = "INSERT INTO Product (pname, quantity, price, cid) VALUES (?, ?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE Product SET pname = ?, quantity = ?, price = ?, cid = ? WHERE pid = ?";
    private final String SQL_SEARCH_BY_NAME = "SELECT p.*, c.cname FROM Product p LEFT JOIN category c ON c.cid = p.cid WHERE LOWER(p.pname) LIKE ?";
    private final String SQL_SEARCH_BY_PRICE = "SELECT p.*, c.cname FROM Product p LEFT JOIN category c ON c.cid = p.cid WHERE p.price BETWEEN ? AND ?";
    private final String SQL_SELECT_ONE = "SELECT p.*, c.cname FROM Product p LEFT JOIN category c ON c.cid = p.cid WHERE pid = ?";
    private final String SQL_SELECT_ALL = "SELECT p.*, c.cname FROM Product p LEFT JOIN category c ON c.cid = p.cid";

    public ProductDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean create(Product object) throws Exception {
        PreparedStatement pstmt = null;
        int rowAffected = -1;
        try {
            pstmt = conn.prepareStatement(SQL_INSERT);
            pstmt.setString(1, object.getName());
            pstmt.setInt(2, object.getQuantity());
            pstmt.setDouble(3, object.getPrice());
            pstmt.setInt(4, object.getCategory().getId());
            rowAffected = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return rowAffected > 0;
    }

    @Override
    public boolean update(Product object) throws Exception {
        PreparedStatement pstmt = null;
        int rowAffected = -1;
        try {
            pstmt = conn.prepareStatement(SQL_UPDATE);
            pstmt.setString(1, object.getName());
            pstmt.setInt(2, object.getQuantity());
            pstmt.setDouble(3, object.getPrice());
            pstmt.setInt(4, object.getCategory().getId());
            pstmt.setInt(5, object.getId());
            rowAffected = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return rowAffected > 0;
    }

    @Override
    public boolean delete(Product object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteById(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product findById(int id) throws Exception {
        PreparedStatement pstmt = null;
        Product product = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SELECT_ONE);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("pid"));
                product.setName(rs.getString("pname"));
                product.setQuantity(rs.getInt("quantity"));
                product.setPrice(rs.getDouble("price"));
                product.getCategory().setId(rs.getInt("cid"));
                product.getCategory().setName(rs.getString("cname"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return product;
    }

    @Override
    public List<Product> findByName(String name) throws Exception {
        PreparedStatement pstmt = null;
        List<Product> products = new ArrayList<>();
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SEARCH_BY_NAME);
            pstmt.setString(1, "%" + name.toLowerCase() + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("pid"));
                product.setName(rs.getString("pname"));
                product.setQuantity(rs.getInt("quantity"));
                product.setPrice(rs.getDouble("price"));
                product.getCategory().setId(rs.getInt("cid"));
                product.getCategory().setName(rs.getString("cname"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return products;
    }

    @Override
    public List<Product> findByPrice(double fromPrice, double toPrice) throws Exception {
        PreparedStatement pstmt = null;
        List<Product> products = new ArrayList<>();
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SEARCH_BY_PRICE);
            pstmt.setDouble(1, fromPrice);
            pstmt.setDouble(2, toPrice);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("pid"));
                product.setName(rs.getString("pname"));
                product.setQuantity(rs.getInt("quantity"));
                product.setPrice(rs.getDouble("price"));
                product.getCategory().setId(rs.getInt("cid"));
                product.getCategory().setName(rs.getString("cname"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return products;
    }

    @Override
    public boolean existName(String name) throws Exception {
        boolean exists = false;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SEARCH_BY_NAME);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return exists;
    }

    @Override
    public List<Product> findAll() throws Exception {
        PreparedStatement pstmt = null;
        List<Product> products = new ArrayList<>();
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("pid"));
                product.setName(rs.getString("pname"));
                product.setQuantity(rs.getInt("quantity"));
                product.setPrice(rs.getDouble("price"));
                product.getCategory().setId(rs.getInt("cid"));
                product.getCategory().setName(rs.getString("cname"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return products;
    }

}
