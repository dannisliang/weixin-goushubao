package service;

import dao.SalesBookDao;
import domain.SalesBook;

import java.util.List;

/**
 * Created by Lixiao on 8/31/2015.
 */
public class SalesBookService {
    private SalesBookDao salesBookDao;

    public List<SalesBook> getAllSBooks(int schoolId){
        return salesBookDao.getAllSBooks(schoolId);
    }

    public void setSalesBookDao(SalesBookDao salesBookDao) {
        this.salesBookDao = salesBookDao;
    }
}
