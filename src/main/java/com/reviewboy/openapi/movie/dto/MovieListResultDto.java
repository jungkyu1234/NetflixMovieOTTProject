package com.reviewboy.openapi.movie.dto;

import com.reviewboy.domain.movie.entity.Movie;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@ToString
@Getter @Setter
public class MovieListResultDto {
    private int totCnt;
    private String source;
    private List<MovieResultDto> movieList;
}
