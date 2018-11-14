package app.Run;

import app.Entities.Author;
import app.Entities.Book;
import app.Entities.Category;
import app.Entities.Enums.AgeRestriction;
import app.Entities.Enums.EditionType;
import app.EntityFileReader.EntityFileReader;
import app.Repositories.AuthorRepository;
import app.Services.AuthorServiceImpl;
import app.Services.BookServiceImpl;
import app.Services.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
@Component
public class Run implements CommandLineRunner {

    private AuthorServiceImpl authorService;
    private BookServiceImpl bookService;
    private CategoryServiceImpl categoryService;

    @Autowired
    public Run(AuthorServiceImpl authorService, BookServiceImpl bookService, CategoryServiceImpl categoryService){
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        // NOTE : CHANGE YOUR TXT FILE PATH!
        EntityFileReader fileReader = new EntityFileReader("C:\\Users\\Erik\\Desktop\\1\\01. Bookshop System\\src\\main\\resources\\files\\authors.txt");
        List<Author> authors = fileReader.getAuthors();
        authorService.persistAuthors(authors);
        authorService.getAllAuthors().forEach(a -> System.out.println(a.getFirstName()));

        // NOTE : CHANGE YOUR TXT FILE PATH!
        fileReader.setPath("C:\\Users\\Erik\\Desktop\\1\\01. Bookshop System\\src\\main\\resources\\files\\categories.txt");
        List<Category> categories = fileReader.getCategories();
        categoryService.persistCategories(categories);

        // NOTE : CHANGE YOUR TXT FILE PATH!
        fileReader.setPath("C:\\Users\\Erik\\Desktop\\1\\01. Bookshop System\\src\\main\\resources\\files\\books.txt");
        List<Book> books = fileReader.getBooks(authorService.getAllAuthors().size(), authorService, categoryService);
        bookService.persistBooks(books);


        System.out.println("-----------1. Get all books after the year 2000. Print only their titles.");
        bookService.getAllBooksAfter2000().forEach(book -> {
            System.out.println(book.getTitle());
        });

        System.out.println("-----------2. Get all authors with at least one book with release date before 1990. Print their first name and last name.");
        authorService.getBooksBefore1990().forEach(a -> {
            System.out.println(a.getFirstName() + " " + a.getLastName());
        });

        System.out.println("-----------3. Get all authors, ordered by the number of their books (descending). Print their first name, last name and book count.\t");
        authorService.getAuthorsOrderdByBooksDesc().forEach(a -> {
            System.out.println(a.getFirstName() + " " + a.getLastName() + " " + a.getBooks().size());
        });

        System.out.println("-----------4. Get all books from author George Powell, ordered by their release date (descending), then by book title (ascending). Print the book's title, release date and copies.");
        bookService.getAllBooksFromGeorgePowell().forEach(book -> {
            System.out.println(book.getTitle() + " " + book.getReleaseDate() + " " + book.getCopies());
        });
    }


}
