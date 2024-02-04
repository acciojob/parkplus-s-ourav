package com.driver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="PaymentInfo")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int paymentId;

    boolean paymentCompleted;
    PaymentMode paymentMode;

    @JoinColumn
    @OneToOne
    Reservation reservation;
}
