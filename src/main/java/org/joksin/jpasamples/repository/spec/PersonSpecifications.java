package org.joksin.jpasamples.repository.spec;

import org.springframework.data.jpa.domain.Specification;

public class PersonSpecifications {

    public static Specification searchByNameAndProgLanguage(String name, String progLanguage) {
        return Specification
                .where(name == null ? null : (root, query, builder) -> builder.equal(root.get("firstName"), name))
                .and(progLanguage == null ? null : (root, query, builder) -> builder.equal(root.get("favouriteProgrammingLanguage"), progLanguage));
    }

}
