package advancedquering.service;

import advancedquering.domain.entities.*;
import advancedquering.repository.AuthorRepository;
import advancedquering.repository.BookRepository;
import advancedquering.repository.CategoryRepository;
import advancedquering.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final static String BOOKS_FILE_PATH = "C:\\Users\\Erik\\Desktop\\Exercise Spring Data Advanced Quering\\src\\main\\resources\\files\\books.txt";

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }


    // Problem 01
    @Override
    public List<String> booksTitlesByAgeRestriction(String ageRestriction) {
        return this.bookRepository.findAllByAgeRestriction(AgeRestriction.valueOf(ageRestriction.toUpperCase()))
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());

    }

    // Problem 02
    @Override
    public List<String> goldenBooks(int copies, String editionType) {
        return this.bookRepository.goldenBooks(copies, EditionType.valueOf(editionType.toUpperCase()))
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    // Problem 03
    @Override
    public List<String> booksByPrice(BigDecimal lowerPrice, BigDecimal higherPrice) {
        return this.bookRepository.booksByPrice(lowerPrice, higherPrice)
                .stream()
                .map(b -> String.format("%s - $%.2f", b.getTitle(), b.getPrice()))
                .collect(Collectors.toList());
    }

    // Problem 04
    @Override
    public List<String> notReleasedBooks(Integer year) {
        return this.bookRepository.notReleasedBooks(year)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    // Problem 05
    @Override
    public List<String> booksReleasedBeforeDate(String date) {
        String[] formattedDate = date.split("-");
        return this.bookRepository.booksReleasedBeforeDate(LocalDate.of(Integer.parseInt(formattedDate[2]), Integer.parseInt(formattedDate[1].replace("0", "").trim()), Integer.parseInt(formattedDate[0].replace("0", "").trim())))
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    // Problem 07
    @Override
    public List<String> booksSearch(String pattern) {
        return this.bookRepository.booksSearch(pattern)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    // Problem 08
    @Override
    public List<String> bookTitlesSearch(String pattern) {
        return this.bookRepository.bookTitleSearch(pattern).stream().map(b -> String.format("%s (%s %s)", b.getTitle(), b.getAuthor().getFirstName(), b.getAuthor().getLastName())).collect(Collectors.toList());
    }

    // Problem 09
    @Override
    public Integer countBooks(Integer number) {
        return this.bookRepository.countBooks(number);
    }

    // Problem 11
    @Override
    public List<String> reducedBook(String title) {
        List<String> result = new ArrayList<String>();
        for (Object[] objects: this.bookRepository.reducedBook(title)){
            result.add(String.format("%s %s %s %s", objects[0], EditionType.values()[Integer.parseInt(objects[1].toString())], AgeRestriction.values()[Integer.parseInt(objects[2].toString())], objects[3]));
        }

        return result;

    }

    // Problem 12
    @Override
    public Integer increaseBookCopies(String date, Integer copies) throws ParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return this.bookRepository.increaseBookCopies(LocalDate.parse(date, dateTimeFormatter), copies);
    }

    // Problem 13
    @Override
    public Integer removeBooks(Integer copies) {
        return this.bookRepository.removeBooks(copies);
    }

    @Override
    public Integer storedProcedure(String firstName, String lastName) {
        return Integer.parseInt(this.bookRepository.storedProcedure(firstName, lastName)[0].toString());
    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepository.count() != 0) {
            return;
        }

        String[] booksFileContent = this.fileUtil.getFileContent(BOOKS_FILE_PATH);
        for (String line : booksFileContent) {
            String[] lineParams = line.split("\\s+");

            Book book = new Book();
            book.setAuthor(this.getRandomAuthor());

            EditionType editionType = EditionType.values()[Integer.parseInt(lineParams[0])];
            book.setEditionType(editionType);

            LocalDate releaseDate = LocalDate.parse(lineParams[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
            book.setReleaseDate(releaseDate);

            int copies = Integer.parseInt(lineParams[2]);
            book.setCopies(copies);

            BigDecimal price = new BigDecimal(lineParams[3]);
            book.setPrice(price);

            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(lineParams[4])];
            book.setAgeRestriction(ageRestriction);

            StringBuilder title = new StringBuilder();
            for (int i = 5; i < lineParams.length; i++) {
                title.append(lineParams[i]).append(" ");
            }

            book.setTitle(title.toString().trim());

            Set<Category> categories = this.getRandomCategories();
            book.setCategories(categories);

            this.bookRepository.saveAndFlush(book);
        }
    }

    @Override
    public List<String> getAllBooksTitlesAfter() {
        List<Book> books = this.bookRepository.findAllByReleaseDateAfter(LocalDate.parse("2000-12-31"));

        return books.stream().map(b -> b.getTitle()).collect(Collectors.toList());
    }

    @Override
    public Set<String> getAllAuthorsWithBookBefore() {
        List<Book> books = this.bookRepository.findAllByReleaseDateBefore(LocalDate.parse("1990-01-01"));

        return books.stream().map(b -> String.format("%s %s", b.getAuthor().getFirstName(), b.getAuthor().getLastName())).collect(Collectors.toSet());
    }

    private Author getRandomAuthor() {
        Random random = new Random();

        int randomId = random.nextInt((int) (this.authorRepository.count() - 1)) + 1;

        return this.authorRepository.findById(randomId).orElse(null);
    }

    private Set<Category> getRandomCategories() {
        Set<Category> categories = new LinkedHashSet<>();

        Random random = new Random();
        int length = random.nextInt(5);

        for (int i = 0; i < length; i++) {
            Category category = this.getRandomCategory();

            categories.add(category);
        }

        return categories;
    }

    private Category getRandomCategory() {
        Random random = new Random();

        int randomId = random.nextInt((int) (this.categoryRepository.count() - 1)) + 1;

        return this.categoryRepository.findById(randomId).orElse(null);
    }
}