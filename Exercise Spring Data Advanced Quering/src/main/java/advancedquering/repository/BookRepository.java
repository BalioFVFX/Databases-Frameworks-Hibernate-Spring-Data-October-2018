package advancedquering.repository;
import advancedquering.domain.entities.AgeRestriction;
import advancedquering.domain.entities.Book;
import advancedquering.domain.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByReleaseDateAfter(LocalDate date);

    List<Book> findAllByReleaseDateBefore(LocalDate date);


    // Problem 01
    @Query(value = "SELECT b FROM advancedquering.domain.entities.Book AS b where b.ageRestriction = :ageRestriction")
    List<Book> findAllByAgeRestriction(@Param(value = "ageRestriction")AgeRestriction ageRestriction);

    // Problem 02
    @Query(value = "SELECT b FROM advancedquering.domain.entities.Book AS b where b.editionType = :editionType and b.copies < :copies")
    List<Book> goldenBooks(@Param(value = "copies") int copies, @Param(value = "editionType") EditionType editionType);

    // Problem 03
    @Query(value = "select b FROM advancedquering.domain.entities.Book as b where b.price < :lowerPrice OR b.price > :higherPrice")
    List<Book> booksByPrice(@Param(value = "lowerPrice") BigDecimal lowerPrice, @Param(value = "higherPrice") BigDecimal higherPrice);

    //Problem 04
    @Query(value = "select b FROM advancedquering.domain.entities.Book as b where year(b.releaseDate) <> :year")
    List<Book> notReleasedBooks(@Param(value = "year") Integer year);

    //Problem 05
    @Query(value = "select b FROM advancedquering.domain.entities.Book as b where b.releaseDate < :date")
    List<Book> booksReleasedBeforeDate(@Param(value = "date") LocalDate date);

    // Problem 07
    @Query(value = "select b FROM advancedquering.domain.entities.Book as b where b.title like %:pattern%")
    List<Book> booksSearch(@Param(value = "pattern") String pattern);

    // Problem 08
    @Query(value = "select b from advancedquering.domain.entities.Book as b inner join b.author as a where a.lastName like 'r%'")
    List<Book> bookTitleSearch(@Param(value = "pattern") String pattern);

    // Problem 09
    @Query(value = "select count(b) from advancedquering.domain.entities.Book as b where length(b.title) > :number")
    Integer countBooks(@Param(value = "number") Integer number);

    // Problem 11
    @Query(value = "select b.title, b.edition_type, b.age_restriction, b.price from books as b where b.title = :title", nativeQuery = true)
    List<Object[]> reducedBook(@Param(value = "title") String title);

    // Problem 12
    @Transactional
    @Modifying
    @Query(value = "update advancedquering.domain.entities.Book as b set b.copies = b.copies + :copies where b.releaseDate > :date")
    Integer increaseBookCopies(@Param(value = "date") LocalDate date, @Param(value = "copies") Integer copies);

    // Problem 13
    @Transactional
    @Modifying
    @Query(value = "delete from advancedquering.domain.entities.Book as b where b.copies < :copies")
    Integer removeBooks(@Param(value = "copies") Integer copies);

    // Problem 14
    @Query(value = "call books_by_author(:firstName, :lastName)", nativeQuery = true)
    Object[] storedProcedure(@Param(value = "firstName") String firstName, @Param(value = "lastName") String lastName);


}
