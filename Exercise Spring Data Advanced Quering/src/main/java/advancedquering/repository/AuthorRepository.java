package advancedquering.repository;

import advancedquering.domain.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    // Problem 06
    @Query("select a FROM advancedquering.domain.entities.Author as a where a.firstName LIKE %:pattern")
    List<Author> authorsSearch(@Param(value = "pattern") String pattern);

    // Problem 10
    @Query(value = "select a.first_name, a.last_name, SUM(b.copies) AS total_book_copies from books as b JOIN authors as a ON a.id = b.author_id group by b.author_id order by total_book_copies desc", nativeQuery = true)
    List<Object[]> test();
}