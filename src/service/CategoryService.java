package service;

import dao.CategoryDao;
import domain.Category;

import java.util.List;

/**
 * Created by Lixiao on 8/31/2015.
 */
public class CategoryService {
    private CategoryDao categoryDao;
    public List<Category> getCategories(int school){
        return categoryDao.getCategories(school);
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
}
