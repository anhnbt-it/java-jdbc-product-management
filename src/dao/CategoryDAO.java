/*
 * Java 2 Practical - HaNoi Aptech
 */
package dao;

import model.Category;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public interface CategoryDAO {

    Category findById(int id) throws Exception;
}
