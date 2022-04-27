package org.joksin.jpasamples.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "PHONE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Phone {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NUMBER")
    private String number;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_ID")
    private Person person;
}
