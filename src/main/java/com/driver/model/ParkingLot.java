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
public class ParkingLot {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    int id;
    String name;
    String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Spot> getSpotList() {
        return SpotList;
    }

    public void setSpotList(List<Spot> spotList) {
        SpotList = spotList;
    }

    @OneToMany (mappedBy = "parkingLot",cascade = CascadeType.ALL)
    List<Spot> SpotList=new ArrayList<>();
}
