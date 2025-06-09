package com.reviewboy.domain.movie.models;

import lombok.Data;

@Data
public class MovieRequest {
    int page = 1;

    Long id;

    String movieCd;

    String movieNm;
}
