package app.Repositories;

import app.Entities.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Author getAuthorByFirstNameAndLastName(String firstName, String lastName);
    List<Author> getAllByOrderByBooksDesc();
}
