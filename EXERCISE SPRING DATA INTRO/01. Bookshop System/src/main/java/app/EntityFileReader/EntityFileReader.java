package app.EntityFileReader;

import app.Entities.Author;
import app.Entities.Book;
import app.Entities.Category;
import app.Entities.Enums.AgeRestriction;
import app.Entities.Enums.EditionType;
import app.Repositories.AuthorRepository;
import app.Repositories.BookRepository;
import app.Services.AuthorServiceImpl;
import app.Services.CategoryServiceImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EntityFileReader {
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private String path;
    private AuthorRepository authorRepository;

    public EntityFileReader(String path) throws FileNotFoundException {
        setPath(path);
    }




    public List<String> readFile() throws IOException {
        String line;
        List<String> lines = new ArrayList<String>();
        while((line = bufferedReader.readLine()) != null){
            lines.add(line);
        }
        return lines;
    }

    public List<Author> getAuthors() throws IOException {
        List<Author> authors = new ArrayList<Author>();
        List<String> fileInformation = readFile();
        for (int i = 0; i < fileInformation.size(); i++){
            String[] currentLine = fileInformation.get(i).split(" ");
            Author author = new Author(currentLine[0], currentLine[1]);
            authors.add(author);
        }
        return authors;
    }

    public List<Book> getBooks(int authorsCount, AuthorServiceImpl authorService, CategoryServiceImpl categoryService) throws IOException, ParseException {
        List<Book> books = new ArrayList<Book>();
        List<String> fileInformation = readFile();
        Random random = new Random();
        int counter = 0;
        List<Category> categories = categoryService.getAllCategories();
        for (int i = 0; i < fileInformation.size(); i++){
            String[] currentLine = fileInformation.get(i).split("\\s+");

            int randomAuthorIndex = random.nextInt(authorsCount) + 1;
            Author author = authorService.getAuthorById(randomAuthorIndex);

            String editionTypeStr = currentLine[0].trim();
            Integer editionTypeVal = Integer.parseInt(editionTypeStr);
            EditionType editionType = EditionType.values()[editionTypeVal];

            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
            Date releaseDate = formatter.parse(currentLine[1]);

            int copies = Integer.parseInt(currentLine[2]);

            BigDecimal price = new BigDecimal(currentLine[3]);

            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(currentLine[4])];

            StringBuilder titleBuilder = new StringBuilder();
            for (int j = 5; j < currentLine.length; j++){
                titleBuilder.append(currentLine[j]).append(" ");
            }
            titleBuilder.delete(titleBuilder.lastIndexOf(" "), titleBuilder.lastIndexOf(" "));
            String title = titleBuilder.toString().trim();

            int randomCategoryNumber = random.nextInt(categories.size()) + 1;
            randomCategoryNumber -= 1;
            HashSet<Category> categoriesSet = new HashSet<Category>();
            categoriesSet.add(categories.get(randomCategoryNumber));

            Book book = new Book(author, editionType, releaseDate, copies, price, ageRestriction, title, categoriesSet);
            books.add(book);
            counter++;
            System.out.println(counter);
        }
        return books;
    }


    public List<Category> getCategories() throws IOException {
        List<Category> categories = new ArrayList<Category>();

        List<String> fileInformation = readFile();
        for (int i = 0; i < fileInformation.size(); i++){
            String[] currentLine = fileInformation.get(i).split(" ");
            Category category = new Category(currentLine[0]);
            categories.add(category);
        }

        return categories;
    }

    public void setPath(String path) throws FileNotFoundException {
        this.path = path;
        this.fileReader = new FileReader(path);
        this.bufferedReader = new BufferedReader(fileReader);
    }
}
