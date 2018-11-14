package app.Services;

import app.Entities.Author;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Set;
@Service
public interface AuthorService {
    Iterable<Author> getAllAuthors();
    void persistAuthor(Author author);
    void persistAuthors(Iterable<Author> authors);
    Author getAuthorById(int id);
    Set<Author> getBooksBefore1990() throws ParseException;
    Author getAuthorByFirstAndLastName(String firstName, String lastName);
    List<Author> getAuthorsOrderdByBooksDesc();
}
