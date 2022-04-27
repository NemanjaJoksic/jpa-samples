package org.joksin.jpasamples.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ID_CARD")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IDCard {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "ID_NUMBER")
    private String idNumber;

    @Column(name = "ISSUE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDate;

}
