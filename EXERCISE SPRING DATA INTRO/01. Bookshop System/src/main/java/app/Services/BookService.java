package app.Services;

import app.Entities.Author;
import app.Entities.Book;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

public interface BookService {
    List<Book> getAllBooksAfter2000() throws ParseException;
    List<Book> getAllBooksFromGeorgePowell();
    Set<Book> getAllBooks();
    void persistBook(Book book);
    void persistBooks(Iterable<Book> books);
}
