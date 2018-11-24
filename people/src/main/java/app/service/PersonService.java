package app.service;

import app.domain.dto.PersonCreateDto;
import app.domain.model.Person;

import java.util.List;

public interface PersonService {

    void create(PersonCreateDto person);

//    Person findById(long id);

    List<Person> findByCountry(String country);
}
