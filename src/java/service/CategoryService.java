/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CategoryDAO;
import dao.MethodDao;
import entity.Category;
import java.util.List;

/**
 *
 * @author NGUYEN KHANH TAI
 */
public class CategoryService implements MethodService<Category> {

    MethodDao<Category> mec = new CategoryDAO();

    @Override
    public List<Category> getAll() {
        return mec.getAll();
    }

    @Override
    public Category getOne(int id) {
        return mec.getOne(id);
    }

    @Override
    public boolean add(Category object) {
        return mec.add(object);
    }

    @Override
    public boolean update(Category object, int id) {
        return mec.update(object, id);
    }

    @Override
    public boolean remove(int id) {
        return mec.remove(id);
    }
   
}
