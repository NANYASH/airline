package com.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PASSENGER")
@EqualsAndHashCode(exclude = {"flights"})
@ToString(exclude = {"flights"})
public class Passenger {
    @SequenceGenerator(name = "PASSENGER_SEQ", sequenceName = "PASSENGER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PASSENGER_SEQ")
    @Id
    @Getter
    @Setter
    private Long id;

    @Column(name = "LAST_NAME")
    @Getter
    @Setter
    private String lastName;

    @Column(name = "NATIONALITY")
    @Getter
    @Setter
    private String nationality;

    @Column(name = "DATE_OF_BIRTH")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Getter
    @Setter
    private Date dateOfBirth;

    @Column(name = "PASSPORT_CODE")
    @Getter
    @Setter
    private String passportCode;

    @ManyToMany(mappedBy = "passengers",fetch = FetchType.EAGER)
    @Getter
    @Setter
    private List<Flight> flights;
}
