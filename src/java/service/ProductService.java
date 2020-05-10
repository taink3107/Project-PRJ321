/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.MethodDao;
import dao.ProductDao;
import entity.Product;
import java.util.List;

/**
 *
 * @author NGUYEN KHANH TAI
 */
public class ProductService implements MethodService<Product> {
    MethodDao<Product> med = new ProductDao();
    /*
     *    { @inheritDoc }
     */
    @Override
    public List<Product> getAll() {
        return med.getAll();
    }

    @Override
    public Product getOne(int id) {
        return med.getOne(id);
    }

    @Override
    public boolean add(Product object) {
        return med.add(object);
    }

    @Override
    public boolean update(Product object, int id) {
        return med.update(object, id);
    }

    @Override
    public boolean remove(int id) {
        return med.remove(id);
    }
    public List<Product> getAllByCategoryId(int id){
        return new ProductDao().getAllByCategoryID(id);
    }
    public static void main(String[] args) {
        System.out.println(new ProductService().getAllByCategoryId(1));
    }
}
