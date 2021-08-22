/*
 * Java 2 Practical - HaNoi Aptech
 */
package dao;

import java.util.List;
import model.Product;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public interface ProductDAO extends BaseDAO<Product> {

    List<Product> findByName(String name) throws Exception;

    List<Product> findByPrice(double fromPrice, double toPrice) throws Exception;
    
    boolean existName(String name) throws Exception;
}
