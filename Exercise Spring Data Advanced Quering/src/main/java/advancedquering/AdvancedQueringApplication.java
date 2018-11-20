package advancedquering;

import advancedquering.service.AuthorService;
import advancedquering.service.BookService;
import advancedquering.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class AdvancedQueringApplication implements CommandLineRunner {
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;

    @Autowired
    public AdvancedQueringApplication(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... strings) throws Exception {
//        this.authorService.seedAuthors();
//        this.categoryService.seedCategories();
//        this.bookService.seedBooks();


        //Problem 01
        //this.bookService.booksTitlesByAgeRestriction("miNor").forEach(System.out::println);

        // Problem 02
        //this.bookService.goldenBooks(5000, "GOLD").forEach(System.out::println);

        //Problem 03
        //this.bookService.booksByPrice(new BigDecimal(5), new BigDecimal(40)).forEach(System.out::println);

        //Problem 04
        //this.bookService.notReleasedBooks(2000).forEach(System.out::println);

        //Problem 05
        //this.bookService.booksReleasedBeforeDate("12-04-1992").forEach(System.out::println);

        //Problem 06
        //this.authorService.authorsSearch("e").forEach(System.out::println);

        // Problem 07
        //this.bookService.booksSearch("sK").forEach(System.out::println);

        // Problem 08
        //this.bookService.bookTitlesSearch("R").forEach(System.out::println);

        // Problem 09
        //System.out.println("There are " + this.bookService.countBooks(40) + " books with longer title than 12 symbols");

        // Problem 10
        //this.authorService.totalBookCopies().forEach(System.out::println);

        // Problem 11
        //this.bookService.reducedBook("Thrones").forEach(System.out::println);

        // Problem 12
        //int result = this.bookService.increaseBookCopies("12 Oct 2005", 100);
        //System.out.println(result + " books are released after 12 Oct 2005, so total of" + result * 100 + " book copies were added");

        // Problem 13
        //System.out.println(this.bookService.removeBooks(300) + " books were deleted");

        // Problem 14
        //Integer writtenBooks = this.bookService.storedProcedure("Amanda", "Rice");
        //System.out.println("Amanda Rice has written " + writtenBooks + " books");


    }
}
