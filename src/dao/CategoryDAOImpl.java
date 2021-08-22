/*
 * Java 2 Practical - HaNoi Aptech
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Category;

public class CategoryDAOImpl implements CategoryDAO {

    private final Connection conn;
    private final String SQL_SELECT_ONE = "SELECT * FROM category WHERE cid = ?";

    public CategoryDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Category findById(int id) throws Exception {
        Category category = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SELECT_ONE);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                category = new Category();
                category.setId(rs.getInt("cid"));
                category.setName(rs.getString("cname"));
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
        return category;
    }

}
