package com.reviewboy.domain.movie.dto;

import com.reviewboy.domain.movie.entity.Movie;
import com.reviewboy.domain.movie.entity.MovieDetail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
public class MovieDto {

    private Long id;

    private String movieCd;

    private String movieNm;

    private String prdtYear;

    private LocalDate openDt;

    private String typeNm;

    private String repGenreNm;

    private String repNationNm;

    private String prdtStatNm;

    private LocalDateTime createAt;

    private LocalDateTime updatedAt;

    private MovieDetailDto movieDetailDto;

    //TODO 지금은 결제 모듈개발을 위해 결제에 필요한 데이터만을 넣어준다.
    public static MovieDto of(Movie movie){
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setMovieCd(movie.getMovieCd());
        movieDto.setMovieNm(movie.getMovieNm());
        movieDto.setMovieDetailDto(MovieDetailDto.of(movie.getMovieDetail()));
        return movieDto;
    }
}
