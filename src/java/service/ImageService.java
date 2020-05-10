/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ImageDao;
import dao.MethodDao;
import entity.Image;
import java.util.List;

/**
 *
 * @author NGUYEN KHANH TAI
 */
public class ImageService implements MethodService<Image> {

    MethodDao<Image> imageDao = new ImageDao();

    @Override
    public List<Image> getAll() {
        return imageDao.getAll();
    }

    @Override
    public Image getOne(int id) {
        return imageDao.getOne(id);
    }

    @Override
    public boolean add(Image object) {
        return imageDao.add(object);
    }

    @Override
    public boolean update(Image object, int id) {
        return imageDao.update(object, id);
    }

    @Override
    public boolean remove(int id) {
        return imageDao.remove(id);
    }
    public List<Image> getAllByProductId(int id){
        ImageDao dao = new ImageDao();
        return dao.getAllByProductId(id);
    }
    public static void main(String[] args) {
        System.out.println(new ImageService().getAllByProductId(1));
    }
}
