package app.serviceImpl;

import app.Map.CMapper;
import app.domain.dto.PersonCreateDto;
import app.domain.model.Person;
import app.repository.PersonRepository;
import app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public void create(PersonCreateDto person) {
        Person entity = CMapper.getMapper().map(person, Person.class);
        this.personRepository.saveAndFlush(entity);
    }

//    @Override
//    public Person findById(long id) {
//        //return this.personRepository.findOne(id);
//    }

    @Override
    public List<Person> findByCountry(String country) {
        return this.personRepository.findByCountry(country);
    }
}
