package app.Services;

import app.Entities.Author;
import app.Entities.Book;
import app.Repositories.AuthorRepository;
import app.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> getAllBooksAfter2000() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
        Date releaseDate = formatter.parse("1/1/2000");
        return this.bookRepository.getAllByReleaseDateAfter(releaseDate);
    }

    @Override
    public List<Book> getAllBooksFromGeorgePowell() {
        Author author = this.authorRepository.getAuthorByFirstNameAndLastName("George", "Powell");
        return this.bookRepository.getAllByAuthorOrderByReleaseDateDescTitleAsc(author);
    }

    @Override
    public Set<Book> getAllBooks() {
        return null;
    }

    @Override
    public void persistBook(Book book) {
        this.bookRepository.save(book);
    }

    @Override
    public void persistBooks(Iterable<Book> books) {
        this.bookRepository.saveAll(books);
    }
}
