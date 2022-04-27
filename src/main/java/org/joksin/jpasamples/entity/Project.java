package org.joksin.jpasamples.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PROJECT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Project {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "PERSON_PROJECT",
            joinColumns = {
                    @JoinColumn(name = "PROJECT_I", referencedColumnName = "ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
            }
    )
    private Set<Person> persons = new HashSet<>();

}
