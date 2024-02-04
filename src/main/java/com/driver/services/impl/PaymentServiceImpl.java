package com.driver.services.impl;

import com.driver.model.Payment;
import com.driver.model.PaymentMode;
import com.driver.model.Reservation;
import com.driver.repository.PaymentRepository;
import com.driver.repository.ReservationRepository;
import com.driver.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ReservationRepository reservationRepository2;
    @Autowired
    PaymentRepository paymentRepository2;

    @Override
    public Payment pay(Integer reservationId, int amountSent, String mode) throws Exception {
//            if(!reservationRepository2.existsById(reservationId)){
//                return null;
//            }

            Reservation reservation=reservationRepository2.findById(reservationId).get();
            int bill= reservation.getSpot().getPricePerHour()*reservation.getNumberOfHours();

            if(amountSent<bill){
                throw new Exception("Insufficient Amount");
            }

            if(mode.equalsIgnoreCase("cash") || mode.equalsIgnoreCase("card") || mode.equalsIgnoreCase("UPI")){
                Payment payment=Payment.builder()
                        .paymentCompleted(Boolean.TRUE)
                        .reservation(reservation)
                        .build();

                if(mode.equalsIgnoreCase("cash")){
                    payment.setPaymentMode(PaymentMode.CASH);
                }
                else if (mode.equalsIgnoreCase(("card"))){
                    payment.setPaymentMode(PaymentMode.CARD);
                }
                else if (mode.equalsIgnoreCase("UPI")){
                    payment.setPaymentMode(PaymentMode.UPI);
                }

                reservation.setPayment(payment);
                payment.setReservation(reservation);


                paymentRepository2.save(payment);
                return payment;

            }
            else throw new Exception("Payment mode not detected");
    }
}
