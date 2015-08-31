package dao;

import domain.Book;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by Lixiao on 8/31/2015.
 */
public class BookDao extends HibernateDaoSupport {

    public void save(Book book){
        this.getHibernateTemplate().save(book);
    }
    public Book getBookByIsbn(String isbn){
        Book book = this.getHibernateTemplate().get(Book.class,isbn);
        return book;
    }

}
