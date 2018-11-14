package app.Services;

import app.Entities.Author;
import app.Repositories.AuthorRepository;
import app.Repositories.BookRepository;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Component
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }



    @Override
    public List<Author> getAllAuthors() {
        Iterable<Author> authors = this.authorRepository.findAll();
        List<Author> authorsList = new ArrayList<Author>();
        authors.forEach(authorsList::add);
        return authorsList;
    }

    @Override
    public void persistAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void persistAuthors(Iterable<Author> authors) {
        authorRepository.saveAll(authors);
    }

    @Override
    public Author getAuthorById(int id) {
        Author author = this.authorRepository.findById((long)id).orElse(null);
        if(author == null){
            throw new IllegalArgumentException("Invalid author id!");
        }
        return author;
    }

    @Override
    public Set<Author> getBooksBefore1990() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
        Date releaseDate = formatter.parse("1/1/1990");
        Set<Author> authors = new HashSet<Author>();
        this.bookRepository.getAllByReleaseDateAfter(releaseDate).forEach(book -> {
            authors.add(book.getAuthor());
        });
        return authors;
    }

    @Override
    public Author getAuthorByFirstAndLastName(String firstName, String lastName) {
        return this.authorRepository.getAuthorByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Author> getAuthorsOrderdByBooksDesc() {
        List<Author> authors = new ArrayList<Author>();
        authors = this.authorRepository.getAllByOrderByBooksDesc();
        authors.sort( (a,b) -> b.getBooks().size() - a.getBooks().size());
        return authors;
    }
}
