package com.reviewboy.openapi.movie.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class MovieEntireResultDto {
    private MovieListResultDto movieListResult;
}
