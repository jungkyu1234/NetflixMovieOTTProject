package com.reviewboy.domain.movie.service;

import com.reviewboy.domain.movie.dto.MovieDto;
import com.reviewboy.domain.movie.entity.Movie;
import com.reviewboy.domain.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;

    public Page<Movie> findAllMovies(Pageable pageable){
        return movieRepository.findAll(pageable);
    }

    @Override
    public  Movie findMovieById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if(movie.isPresent()){
            return movie.get();
        }else{
            throw new IllegalArgumentException("해당 아이디의 영화는 없습니다.");
        }
    }

    @Override
    public MovieDto findMovieDtoById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if(movie.isPresent()){
            return MovieDto.of(movie.get());
        }else{
            throw new IllegalArgumentException("해당 아이디의 영화는 없습니다.");
        }
    }
}
