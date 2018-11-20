package advancedquering.service;

import advancedquering.domain.entities.Author;
import advancedquering.repository.AuthorRepository;
import advancedquering.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final static String AUTHORS_FILE_PATH = "C:\\Users\\Erik\\Desktop\\Exercise Spring Data Advanced Quering\\src\\main\\resources\\files\\authors.txt";

    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    // Problem 06
    @Override
    public List<String> authorsSearch(String pattern) {
        return this.authorRepository.authorsSearch(pattern)
                .stream()
                .map(a -> String.format("%s %s", a.getFirstName(), a.getLastName()))
                .collect(Collectors.toList());
    }

    // Problem 10


    @Override
    public List<String> totalBookCopies() {
        List<String> result = new ArrayList<String>();
        for (Object[] objects: this.authorRepository.test()){
            result.add(objects[0] + " " + objects[1] + " - " + objects[2]);
        }

        return result;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (this.authorRepository.count() != 0) {
            return;
        }

        String[] authorFileContent = this.fileUtil.getFileContent(AUTHORS_FILE_PATH);
        for (String line : authorFileContent) {
            String[] names = line.split("\\s+");

            Author author = new Author();
            author.setFirstName(names[0]);
            author.setLastName(names[1]);

            this.authorRepository.saveAndFlush(author);
        }
    }
}