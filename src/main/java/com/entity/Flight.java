package com.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "FLIGHT")
public class Flight {
    private Long id;
    private Plane plane;
    private List<Passenger> passengers;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateFlight;
    private String cityFrom;
    private String cityTo;

    @SequenceGenerator(name = "FLIGHT_SEQ", sequenceName = "FLIGHT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FLIGHT_SEQ")
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(targetEntity = Plane.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PLANE")
    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    @ManyToMany(targetEntity = Passenger.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "FLIGHT_PASSENGER",
            joinColumns = @JoinColumn(name = "ID_FLIGHT"),
            inverseJoinColumns = @JoinColumn(name = "ID_PASSENGER")
    )
    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    @Column(name = "DATE_FLIGHT")
    public Date getDateFlight() {
        return dateFlight;
    }

    public void setDateFlight(Date dateFlight) {
        this.dateFlight = dateFlight;
    }

    @Column(name = "CITY_FROM")
    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    @Column(name = "CITY_TO")
    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (id != null ? !id.equals(flight.id) : flight.id != null) return false;
        if (plane != null ? !plane.equals(flight.plane) : flight.plane != null) return false;
        if (passengers != null ? !passengers.equals(flight.passengers) : flight.passengers != null) return false;
        if (dateFlight != null ? !dateFlight.equals(flight.dateFlight) : flight.dateFlight != null) return false;
        if (cityFrom != null ? !cityFrom.equals(flight.cityFrom) : flight.cityFrom != null) return false;
        return cityTo != null ? cityTo.equals(flight.cityTo) : flight.cityTo == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (plane != null ? plane.hashCode() : 0);
        result = 31 * result + (passengers != null ? passengers.hashCode() : 0);
        result = 31 * result + (dateFlight != null ? dateFlight.hashCode() : 0);
        result = 31 * result + (cityFrom != null ? cityFrom.hashCode() : 0);
        result = 31 * result + (cityTo != null ? cityTo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", plane=" + plane +
                ", dateFlight=" + dateFlight +
                ", cityFrom='" + cityFrom + '\'' +
                ", cityTo='" + cityTo + '\'' +
                '}';
    }
}
