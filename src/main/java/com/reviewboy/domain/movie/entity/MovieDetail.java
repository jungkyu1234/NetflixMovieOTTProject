package com.reviewboy.domain.movie.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "movie_detail")
public class MovieDetail {

    @Column(name="movie_detail_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="price", precision = 13 , scale =  2)
    private BigDecimal price;

    @OneToOne(mappedBy = "movieDetail")
    Movie movie;

}
