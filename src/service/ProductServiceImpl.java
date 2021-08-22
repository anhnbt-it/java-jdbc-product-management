/*
 * Java 2 Practical - HaNoi Aptech
 */
package service;

import dao.ProductDAO;
import dao.ProductDAOImpl;
import java.sql.Connection;
import java.util.List;
import model.Product;

public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    public ProductServiceImpl(Connection conn) {
        this.productDAO = new ProductDAOImpl(conn);
    }

    @Override
    public boolean create(Product object) throws Exception {
        return productDAO.create(object);
    }

    @Override
    public boolean update(Product object) throws Exception {
        return productDAO.update(object);
    }

    @Override
    public boolean delete(Product object) throws Exception {
        return productDAO.delete(object);
    }

    @Override
    public boolean deleteById(int id) throws Exception {
        return productDAO.deleteById(id);
    }

    @Override
    public Product findById(int id) throws Exception {
        return productDAO.findById(id);
    }

    @Override
    public List<Product> findByName(String name) throws Exception {
        return productDAO.findByName(name);
    }

    @Override
    public List<Product> findByPrice(double fromPrice, double toPrice) throws Exception {
        if (fromPrice > toPrice) {
            throw new Exception("To price must be greater from price");
        }
        return productDAO.findByPrice(fromPrice, toPrice);
    }

    @Override
    public boolean existName(String name) throws Exception {
        return productDAO.existName(name);
    }

    @Override
    public List<Product> findAll() throws Exception {
        return productDAO.findAll();
    }

}
