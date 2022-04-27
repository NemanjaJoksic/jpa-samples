package org.joksin.jpasamples.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PERSON")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Person {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "BORN")
    private Integer born;

    @Column(name = "FAV_PROGRAMMING_LANGUAGE")
    private String favouriteProgrammingLanguage;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CARD_ID")
    private IDCard idCard;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Phone> phones = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "persons", fetch = FetchType.LAZY)
    private Set<Project> projects = new HashSet<>();

}
