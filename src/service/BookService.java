package service;

        import dao.BookDao;
        import domain.Book;
        import utils.GetBookFromDouBan;

/**
 * Created by Lixiao on 8/31/2015.
 */
public class BookService {
    private BookDao bookDao;

    public void save(Book book){
        bookDao.save(book);
    }

    public Book getBookByIsbn(String isbn) throws Exception{
        Book book = null;
        book =  bookDao.getBookByIsbn(isbn);
        if(book==null){
            book = GetBookFromDouBan.getISBNInfo(isbn);
            if (book!=null){
                bookDao.save(book);
            }
        }
        return book;
    }
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
