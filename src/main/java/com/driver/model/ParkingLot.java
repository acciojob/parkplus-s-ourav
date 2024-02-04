package com.driver.model;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name="ParkingLotInfo")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParkingLot {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    int parkingLotId;
    String name;
    String address;

    @OneToMany (mappedBy = "parkingLot",cascade = CascadeType.ALL)
    List<Spot> SpotList=new ArrayList<>();
}
