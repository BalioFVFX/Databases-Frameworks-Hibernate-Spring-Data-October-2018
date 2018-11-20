package advancedquering.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

public interface BookService {

    void seedBooks() throws IOException;

    List<String> getAllBooksTitlesAfter();

    Set<String> getAllAuthorsWithBookBefore();

    // Problem 01
    List<String> booksTitlesByAgeRestriction(String ageRestriction);

    // Problem 02
    List<String> goldenBooks(int copies, String editionType);

    // Problem 03
    List<String> booksByPrice(BigDecimal lowerPrice, BigDecimal higherPrice);

    // Problem 04
    List<String> notReleasedBooks(Integer year);

    // Problem 05
    List<String> booksReleasedBeforeDate(String date);

    //Problem 07
    List<String> booksSearch(String pattern);

    // Problem 08
    List<String> bookTitlesSearch(String pattern);

    // Problem 09
    Integer countBooks(Integer number);

    // Problem 11
    List<String> reducedBook(String title);

    // Problem 12
    Integer increaseBookCopies(String date, Integer copies) throws ParseException;

    // Problem 13
    Integer removeBooks(Integer copies);

    // Problem 14
    Integer storedProcedure(String firstName, String lastName);
}