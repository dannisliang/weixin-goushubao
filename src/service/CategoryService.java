package service;

import dao.CategoryDao;
import domain.Category;
import domain.School;

import java.util.List;

/**
 * Created by Lixiao on 8/31/2015.
 */
public class CategoryService {
    private CategoryDao categoryDao;
    public List<Category> getCategories(int school){
        return categoryDao.getCategories(school);
    }

    public Category save(String name, School school){
        return categoryDao.save(name,school);
    }

    public void del(int cid,int sid){
        categoryDao.del(cid, sid);
    }

    public Category update(int cid,String name,int sid){
        return categoryDao.update(cid,name,sid);
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
}
