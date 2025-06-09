package com.reviewboy.domain.pay.entity;

import com.reviewboy.domain.movie.entity.Movie;
import com.reviewboy.domain.pay.entity.enums.PayStatus;
import com.reviewboy.domain.users.entity.User;
import io.lettuce.core.dynamic.annotation.CommandNaming;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Column(name = "order_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private BigDecimal preparePrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "pay_status")
    private PayStatus payStatus = PayStatus.PREPARED;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name= "movie_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

}
