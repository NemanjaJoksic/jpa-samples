package org.joksin.jpasamples.repository.spec;

import lombok.AllArgsConstructor;
import org.joksin.jpasamples.entity.Person;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class PersonFilter implements Specification<Person> {

    private final String firstName;
    private final String favouriteProgrammingLanguage;

    @Override
    public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new LinkedList<>();

        if (firstName != null) {
            Predicate firstNamePredicate = criteriaBuilder.equal(root.get("firstName"), firstName);
            predicates.add(firstNamePredicate);
        }
        if (favouriteProgrammingLanguage != null) {
            Predicate favouriteProgrammingLanguagePredicate = criteriaBuilder.equal(root.get("favouriteProgrammingLanguage"), favouriteProgrammingLanguage);
            predicates.add(favouriteProgrammingLanguagePredicate);
        }

        return predicates.isEmpty() ? null : criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

}
