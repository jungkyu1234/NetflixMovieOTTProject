package com.reviewboy.domain.movie.service;

import com.reviewboy.domain.movie.dto.MovieDto;
import com.reviewboy.domain.movie.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MovieService {
    Page<Movie> findAllMovies(Pageable pageable);

    MovieDto findMovieDtoById(Long id);

    Movie findMovieById(Long id);
}
