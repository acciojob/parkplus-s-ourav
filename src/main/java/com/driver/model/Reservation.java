package com.driver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="ReservationInfo")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int reservationId;

    int numberOfHours;

    @JoinColumn
    @ManyToOne
    User user;

    @JoinColumn
    @ManyToOne
    Spot spot;

    @JoinColumn
    @OneToOne
    Payment payment;
}
