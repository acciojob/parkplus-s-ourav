package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository3;
    @Autowired
    SpotRepository spotRepository3;
    @Autowired
    ReservationRepository reservationRepository3;
    @Autowired
    ParkingLotRepository parkingLotRepository3;
    @Override
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {
        ParkingLot parkingLot;
        try {
            parkingLot = parkingLotRepository3.findById(parkingLotId).get();
        }
        catch (Exception e){
            throw new Exception("Cannot make reservation");
        }

        User user;
        try {
            user = userRepository3.findById(userId).get();
        }
        catch (Exception e){
            throw new Exception("Cannot make reservation");
        }

            List<Spot> potentialSpotList=new ArrayList<>();

            for(Spot spot1 : parkingLot.getSpotList()){
                int wheels=0;

                if(spot1.getOccupied())
                    continue;

                if(spot1.getSpotType().equals("TWO_WHEELER")){
                    wheels=2;
                }
                else if(spot1.getSpotType().equals("FOUR_WHEELER")){
                    wheels=4;
                }
                else wheels=Integer.MAX_VALUE;

                if(wheels>=numberOfWheels){
                    potentialSpotList.add(spot1);
                }

            }

            if(potentialSpotList.size()==0){
                throw new Exception("Cannot make reservation");
            }

            Collections.sort(potentialSpotList,(Spot a,Spot b)-> (a.getPricePerHour()-b.getPricePerHour()));
            Spot spot = potentialSpotList.get(0);

            Reservation reservation= Reservation.builder()
                    .numberOfHours(timeInHours)
                    .user(user)
                    .spot(spot)
                    .build();

            user.getReservationList().add(reservation);
            spot.getReservationList().add(reservation);

            reservationRepository3.save(reservation);
            userRepository3.save(user);
            spotRepository3.save(spot);


            return reservation;
    }
}
