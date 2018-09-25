package com.entity;


import com.fasterxml.jackson.annotation.JsonFormat;;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PLANE")
public class Plane {
    private Long id;
    private String model;
    private String code;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date yearProduced;
    private Double avgFuelConsumption;

    @SequenceGenerator(name = "PLANE_SEQ", sequenceName = "PLANE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLANE_SEQ")
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "PLANE_MODEL")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "YEAR_PRODUCED")
    public Date getYearProduced() {
        return yearProduced;
    }

    public void setYearProduced(Date yearProduced) {
        this.yearProduced = yearProduced;
    }

    @Column(name = "AVG_FUEL_CONSUMPTION")
    public Double getAvgFuelConsumption() {
        return avgFuelConsumption;
    }

    public void setAvgFuelConsumption(Double avgFuelConsumption) {
        this.avgFuelConsumption = avgFuelConsumption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plane plane = (Plane) o;

        if (id != null ? !id.equals(plane.id) : plane.id != null) return false;
        if (model != null ? !model.equals(plane.model) : plane.model != null) return false;
        if (code != null ? !code.equals(plane.code) : plane.code != null) return false;
        if (yearProduced != null ? !yearProduced.equals(plane.yearProduced) : plane.yearProduced != null) return false;
        return avgFuelConsumption != null ? avgFuelConsumption.equals(plane.avgFuelConsumption) : plane.avgFuelConsumption == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (yearProduced != null ? yearProduced.hashCode() : 0);
        result = 31 * result + (avgFuelConsumption != null ? avgFuelConsumption.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", code='" + code + '\'' +
                ", yearProduced=" + yearProduced +
                ", avgFuelConsumption=" + avgFuelConsumption +
                '}';
    }
}
