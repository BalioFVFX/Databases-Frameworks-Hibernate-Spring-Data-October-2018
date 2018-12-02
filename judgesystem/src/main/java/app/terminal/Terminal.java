package app.terminal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Terminal implements CommandLineRunner {
    @Override
    public void run(String... args) {
        System.out.println("no vs e to4no ;)");
    }
}
