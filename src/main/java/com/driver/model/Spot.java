package com.driver.model;

//import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="SpotInfo")
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    public Boolean getOccupied() {
        return occupied;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public void setSpotType(SpotType spotType) {
        this.spotType = spotType;
    }

    public int getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(int pricePerHour) {
        this.pricePerHour = pricePerHour;
    }



    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    @Enumerated(value = EnumType.STRING)
    SpotType spotType;
    int pricePerHour;
    Boolean occupied;

    @JoinColumn
    @ManyToOne
    ParkingLot parkingLot;

    @OneToMany (mappedBy = "spot",cascade = CascadeType.ALL)
    List<Reservation> reservationList=new ArrayList<>();

}
