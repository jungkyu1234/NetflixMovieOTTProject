package com.reviewboy.domain.movie.entity;

import com.reviewboy.domain.pay.entity.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * "movieCd": "20254931",
 * "movieNm": "인피니트 15주년 콘서트 리미티드 에디션 더 무비",
 * "movieNmEn": "",
 * "prdtYear": "2025",
 * "openDt": "20250611",
 * "typeNm": "장편",
 * "prdtStatNm": "개봉예정",
 * "nationAlt": "한국",
 * "genreAlt": "공연",
 * "repNationNm": "한국",
 * "repGenreNm": "공연",
 */
@Entity
@Getter @Setter
@Table(name = "movie")
public class Movie {
    @Column(name = "movie_id")
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    //영화코드
    @Column(name="movie_cd")
    private String movieCd;

    //영화명
    @Column(name="movie_nm")
    private String movieNm;

    //제작연도
    @Column(name="prdt_year")
    private String prdtYear;

    //개봉일
    @Column(name="open_dt")
    private LocalDate openDt;

    //유형명
    @Column(name="type_nm")
    private String typeNm;

    //장르명
    @Column(name="rep_genre_nm")
    private String repGenreNm;

    //대표제작국가
    @Column(name="rep_nation_nm")
    private String repNationNm;

    //제작상태명
    @Column(name="prd_stat_nm")
    private String prdtStatNm;

    @Column(name ="created_at")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name ="updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "movie_detail_id") //외래키가 영화에 있고
    private MovieDetail movieDetail;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<Order> orderList = new ArrayList<>();

}
