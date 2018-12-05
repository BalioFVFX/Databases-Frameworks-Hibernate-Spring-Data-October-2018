package mostwanted.service;

import java.io.IOException;

public interface TownService {

    Boolean townsAreImported() throws IOException;

    String readTownsJsonFile() throws IOException;

    String importTowns(String townsFileContent);

    String exportRacingTowns();
}
