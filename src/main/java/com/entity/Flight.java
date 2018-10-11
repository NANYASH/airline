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
@Table(name = "FLIGHT")
@EqualsAndHashCode(exclude = {"passengers"})
@ToString(exclude = {"passengers"})
public class Flight {
    @SequenceGenerator(name = "FLIGHT_SEQ", sequenceName = "FLIGHT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FLIGHT_SEQ")
    @Id
    @Getter
    @Setter
    private Long id;

    @OneToOne(targetEntity = Plane.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PLANE")
    @Getter
    @Setter
    private Plane plane;

    @ManyToMany(targetEntity = Passenger.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "FLIGHT_PASSENGER",
            joinColumns = @JoinColumn(name = "ID_FLIGHT"),
            inverseJoinColumns = @JoinColumn(name = "ID_PASSENGER"))
    @Getter
    @Setter
    private List<Passenger> passengers;

    @Column(name = "DATE_FLIGHT")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Getter
    @Setter
    private Date dateFlight;

    @Column(name = "CITY_FROM")
    @Getter
    @Setter
    private String cityFrom;

    @Column(name = "CITY_TO")
    @Getter
    @Setter
    private String cityTo;
}
