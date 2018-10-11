package com.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PLANE")
@EqualsAndHashCode
@ToString
public class Plane {
    @SequenceGenerator(name = "PLANE_SEQ", sequenceName = "PLANE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLANE_SEQ")
    @Id
    @Getter
    @Setter
    private Long id;

    @Column(name = "PLANE_MODEL")
    @Getter
    @Setter
    private String model;

    @Column(name = "CODE")
    @Getter
    @Setter
    private String code;

    @Column(name = "YEAR_PRODUCED")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Getter
    @Setter
    private Date yearProduced;

    @Column(name = "AVG_FUEL_CONSUMPTION")
    @Getter
    @Setter
    private Double avgFuelConsumption;
}
