package com.reviewboy.domain.movie.dto;

import com.reviewboy.domain.movie.entity.MovieDetail;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MovieDetailDto {
    private Long price;

    public static MovieDetailDto of(MovieDetail movieDetail){
        MovieDetailDto dto = new MovieDetailDto();
        dto.setPrice(movieDetail.getPrice().longValue());
        return dto;
    }
}
