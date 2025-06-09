package com.reviewboy.domain.movie.models;


import com.reviewboy.domain.movie.entity.Movie;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieResponse {
    private Long id;
    //영화코드
    private String movieCd;
    //영화명
    private String movieNm;
    //제작연도
    private String prdtYear;
    //개봉일
    private LocalDate openDt;
    //유형명
    private String typeNm;
    //장르명
    private String repGenreNm;
    //대표제작국가
    private String repNationNm;
    //제작상태명
    private String prdtStatNm;

    public static MovieResponse of(Movie movie){
       MovieResponse movieResponse = new MovieResponse();
       movieResponse.setId(movie.getId());
       movieResponse.setMovieNm(movie.getMovieNm());
       movieResponse.setMovieCd(movie.getMovieCd());
       movieResponse.setPrdtYear(movie.getPrdtYear());
       movieResponse.setOpenDt(movie.getOpenDt());
       movieResponse.setTypeNm(movie.getTypeNm());
       movieResponse.setRepGenreNm(movie.getRepGenreNm());
       movieResponse.setRepNationNm(movie.getRepNationNm());
       movieResponse.setPrdtStatNm(movie.getPrdtStatNm());
       return movieResponse;
    }
}
