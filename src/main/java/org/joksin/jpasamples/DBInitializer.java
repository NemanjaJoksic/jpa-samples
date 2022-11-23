package org.joksin.jpasamples;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.joksin.jpasamples.entity.IDCard;
import org.joksin.jpasamples.entity.Person;
import org.joksin.jpasamples.entity.Phone;
import org.joksin.jpasamples.entity.Project;
import org.joksin.jpasamples.repository.PersonRepository;
import org.joksin.jpasamples.repository.ProjectRepository;
import org.joksin.jpasamples.repository.spec.PersonFilter;
import org.joksin.jpasamples.repository.spec.PersonSpecifications;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;

@Component
public class DBInitializer implements CommandLineRunner {

    private final PersonRepository personRepository;
    private final ProjectRepository projectRepository;

    public DBInitializer(PersonRepository personRepository, ProjectRepository projectRepository) {
        this.personRepository = personRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Person person1 = Person.builder()
                .id(1L)
                .firstName("Pera")
                .lastName("Peric")
                .born(1990)
                .favouriteProgrammingLanguage("Java")
                .idCard(new IDCard(1L, "123", new Date()))
                .build();
        person1.setPhones(new HashSet<>(Arrays.asList(new Phone(1L, "123", person1))));

        personRepository.save(person1);

        Person person2 = Person.builder()
                .id(2L)
                .firstName("Vasa")
                .lastName("Vasic")
                .born(1995)
                .favouriteProgrammingLanguage("Java")
                .idCard(new IDCard(2L, "345", new Date()))
                .build();
        person2.setPhones(new HashSet<>(Arrays.asList(new Phone(2L, "123", person2), new Phone(3L, "555", person2))));

        Person person3 = Person.builder()
                .id(3L)
                .firstName("Jovan")
                .lastName("Jovanovic")
                .born(1987)
                .favouriteProgrammingLanguage("Python")
                .idCard(new IDCard(3L, "567", new Date()))
                .build();

        personRepository.saveAll(Arrays.asList(person2, person3));

        List<Person> persons = personRepository.findAll();
        for (Person person : persons) {
            System.out.println(mapper.writeValueAsString(person));
        }

        System.out.println("--------------------------------");
        Set<Person> personsSet = personRepository.getAll();
        for (Person person : personsSet) {
            System.out.println(mapper.writeValueAsString(person));
        }

        Project project1 = Project.builder()
                .id(1L)
                .title("Project 1")
                .persons(new HashSet<>(Arrays.asList(person1, person2)))
                .build();

        Project project2 = Project.builder()
                .id(2L)
                .title("Project 2")
                .persons(new HashSet<>(Arrays.asList(person3)))
                .build();

        projectRepository.saveAll(Arrays.asList(project1, project2));

        System.out.println("--------------------------------");
        List<Project> projects = projectRepository.findAll();
        for (Project project : projects) {
            System.out.println(mapper.writeValueAsString(project));
        }
        System.out.println("--------------------------------");
        Set<Project> projectsSet = projectRepository.getAll();
        for (Project project : projectsSet) {
            System.out.println(mapper.writeValueAsString(project));
        }

        System.out.println("DB initialized");

        System.out.println("--------------------------------");
        persons = personRepository.findAll(PersonSpecifications.searchByNameAndProgLanguage("Jovan", "Python"));
        for (Person person : persons) {
            System.out.println(mapper.writeValueAsString(person));
        }
        persons = personRepository.findAll(new PersonFilter("Jovan", "Python"));
        for (Person person : persons) {
            System.out.println(mapper.writeValueAsString(person));
        }

        System.out.println("--------------------------------");
        persons = personRepository.findAll(PersonSpecifications.searchByNameAndProgLanguage("Jovan", null));
        for (Person person : persons) {
            System.out.println(mapper.writeValueAsString(person));
        }
        persons = personRepository.findAll(new PersonFilter("Jovan", null));
        for (Person person : persons) {
            System.out.println(mapper.writeValueAsString(person));
        }

        System.out.println("--------------------------------");
        persons = personRepository.findAll(PersonSpecifications.searchByNameAndProgLanguage(null, "Java"));
        for (Person person : persons) {
            System.out.println(mapper.writeValueAsString(person));
        }
        persons = personRepository.findAll(new PersonFilter(null, "Java"));
        for (Person person : persons) {
            System.out.println(mapper.writeValueAsString(person));
        }

        System.out.println("--------------------------------");
        persons = personRepository.findAll(PersonSpecifications.searchByNameAndProgLanguage(null, null));
        for (Person person : persons) {
            System.out.println(mapper.writeValueAsString(person));
        }
        persons = personRepository.findAll(new PersonFilter(null, null));
        for (Person person : persons) {
            System.out.println(mapper.writeValueAsString(person));
        }

    }

}
