package com.driver.model;

import com.sun.org.apache.xpath.internal.operations.Bool;
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
@Data
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int spotId;
    @Enumerated(value = EnumType.STRING)
    SpotType spotType;
    int pricePerHour;
    boolean occupied;

    @JoinColumn
    @ManyToOne
    ParkingLot parkingLot;

    @OneToMany (mappedBy = "spot",cascade = CascadeType.ALL)
    List<Reservation> reservationList=new ArrayList<>();

}
