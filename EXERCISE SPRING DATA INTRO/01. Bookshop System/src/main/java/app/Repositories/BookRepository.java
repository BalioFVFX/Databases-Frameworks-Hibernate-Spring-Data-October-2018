package app.Repositories;

import app.Entities.Author;
import app.Entities.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> getAllByReleaseDateAfter(Date date);
    List<Book> getAllByReleaseDateBefore(Date date);
    List<Book> getAllByAuthorOrderByReleaseDateDescTitleAsc(Author author);
}
