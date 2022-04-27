package org.joksin.jpasamples.repository;

import org.joksin.jpasamples.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {

    /*
    *   java.util.Set must be used to avoid duplicates with left join fetch
    *   java.util.List contains duplicates
     */
    @Query(value = "from Person p left join fetch p.phones left join fetch p.idCard")
    Set<Person> getAll();

}
