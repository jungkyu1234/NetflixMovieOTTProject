package com.reviewboy.domain.movie.repository;

import com.reviewboy.domain.movie.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    //페이징 처리
    @Override
    Page<Movie> findAll(Pageable pageable);

}
