/*
 * Java 2 Practical - HaNoi Aptech
 */
package dao;

import java.util.List;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * @param <T>
 */
public interface BaseDAO<T> {

    List<T> findAll() throws Exception;

    boolean create(T object) throws Exception;

    boolean update(T object) throws Exception;

    boolean delete(T object) throws Exception;

    boolean deleteById(int id) throws Exception;

    T findById(int id) throws Exception;
}
