/*
 * Java 2 Practical - HaNoi Aptech
 */
package service;

import model.Category;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public interface CategoryService {
    Category findById(int id) throws Exception;
}
