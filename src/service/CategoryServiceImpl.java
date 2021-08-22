/*
 * Java 2 Practical - HaNoi Aptech
 */
package service;

import dao.CategoryDAO;
import dao.CategoryDAOImpl;
import java.sql.Connection;
import model.Category;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;

    public CategoryServiceImpl(Connection conn) {
        this.categoryDAO = new CategoryDAOImpl(conn);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Category findById(int id) throws Exception {
        return categoryDAO.findById(id);
    }

}
