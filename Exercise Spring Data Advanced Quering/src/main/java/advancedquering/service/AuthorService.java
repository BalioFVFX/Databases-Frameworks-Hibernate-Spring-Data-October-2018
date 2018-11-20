package advancedquering.service;

import java.io.IOException;
import java.util.List;

public interface AuthorService {

    void seedAuthors() throws IOException;

    // Problem 06
    List<String> authorsSearch(String pattern);

    // Problem 10
    List<String> totalBookCopies();
}

