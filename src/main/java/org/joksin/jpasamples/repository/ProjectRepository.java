package org.joksin.jpasamples.repository;

import org.joksin.jpasamples.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    /*
     *   java.util.Set must be used to avoid duplicates with left join fetch
     *   java.util.List contains duplicates
     */
    @Query(value = "from Project proj left join fetch proj.persons persons left join fetch persons.phones left join fetch persons.idCard")
    Set<Project> getAll();

}
