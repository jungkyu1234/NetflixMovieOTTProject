package com.reviewboy.domain.movie.presentation;


import com.reviewboy.domain.movie.models.MovieDetailResponse;
import com.reviewboy.domain.movie.models.MovieResponse;
import com.reviewboy.domain.movie.service.MovieService;
import com.reviewboy.openapi.movie.MovieOpenApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MovieController {

    private final MovieService movieService;

    private final MovieOpenApiService movieOpenApiService;


    @GetMapping("/api/movie/{page}")
    public Page<MovieResponse> getMovieList(@PathVariable int page){
        Pageable pageable = PageRequest.of(page - 1, 10,
                Sort.by(Sort.Order.asc("movieCd")));

        return movieService.findAllMovies(pageable)
                .map(MovieResponse::of);
    }

    @GetMapping("/api/movie/content/{movieCd}")
    public Map<String, Object> getMovieContentByMovieCd(@PathVariable String movieCd){
        return movieOpenApiService.retrieveMovieContentByMovieCd(movieCd);
    }


}
