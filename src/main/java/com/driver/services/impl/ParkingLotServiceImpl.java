package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address) {
        ParkingLot parkingLot=ParkingLot.builder()
                .name(name)
                .address(address)
                .build();
        parkingLot=parkingLotRepository1.save(parkingLot);
        return parkingLot;
    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {
        Optional <ParkingLot> optionalParkingLot=parkingLotRepository1.findById(parkingLotId);
        if(!optionalParkingLot.isPresent()){
            return null;
        }
        ParkingLot parkingLot1=optionalParkingLot.get();

        Spot spot=Spot.builder()
                .parkingLot(parkingLot1)
                .pricePerHour(pricePerHour)
                .build();

        if(numberOfWheels==2){
            spot.setSpotType(SpotType.TWO_WHEELER);
        }
        else if (numberOfWheels==4){
            spot.setSpotType(SpotType.FOUR_WHEELER);
        }
        else if (numberOfWheels>4){
            spot.setSpotType(SpotType.OTHERS);
        }

        spot = spotRepository1.save(spot);
        //FK
        parkingLot1.getSpotList().add(spot);
        return spot;

    }

    @Override
    public void deleteSpot(int spotId) {
        if(!spotRepository1.existsById(spotId))
            return;
        Spot spot= spotRepository1.findById(spotId).get();
        ParkingLot parkingLot=spot.getParkingLot();

        for(Spot s: parkingLot.getSpotList()){
            if(s.getId()==spotId){
                parkingLot.getSpotList().remove(s);
            }
        }

        spotRepository1.save(spot);
        parkingLotRepository1.save(parkingLot);
    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
//            if(!spotRepository1.existsById(spotId))
//                return null;


                ParkingLot parkingLot1=parkingLotRepository1.findById(parkingLotId).get();
                Spot spot=null;
                for(Spot spot1 : parkingLot1.getSpotList()){
                    if(spot1.getId()==spotId){
                        spot1.setPricePerHour(pricePerHour);
                        spot=spot1;
                        spotRepository1.save(spot1);
                    }
                }

            return spot;

    }

    @Override
    public void deleteParkingLot(int parkingLotId) {

            parkingLotRepository1.deleteById(parkingLotId);

    }
}
