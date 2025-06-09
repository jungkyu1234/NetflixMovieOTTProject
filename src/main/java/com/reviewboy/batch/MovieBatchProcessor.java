package com.reviewboy.batch;

import com.reviewboy.domain.movie.entity.Movie;
import com.reviewboy.domain.movie.entity.MovieDetail;
import com.reviewboy.domain.movie.repository.MovieRepository;
import com.reviewboy.openapi.movie.MovieOpenApiService;
import com.reviewboy.openapi.movie.dto.MovieEntireResultDto;
import com.reviewboy.openapi.movie.dto.MovieListResultDto;
import com.reviewboy.openapi.movie.dto.MovieResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Component
@RequiredArgsConstructor
public class MovieBatchProcessor {

    private final MovieOpenApiService movieOpenApiService;

    private final JdbcTemplate jdbcTemplate;

    private final MovieRepository movieRepository;

    @Transactional
    @Scheduled(cron = "0 0 0 1 * *")
    public void runMonthlyTask() {
        System.out.println("한 달에 한 번 실행됨: " + LocalDateTime.now());
        // 실제 작업 로직 작성
        int page = 1;
        int k = 0;
        while(true){
            MovieEntireResultDto entireResultDto =
                    movieOpenApiService.retrieveAllMovies("2025", page, 100);

            MovieListResultDto movieListResultDto = entireResultDto.getMovieListResult();
            List<MovieResultDto> movieResultDtoList = movieListResultDto.getMovieList();
            if (!CollectionUtils.isEmpty(movieResultDtoList)) {

                DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;

                for (MovieResultDto movieResultDto : movieResultDtoList) {

                    Movie movie = new Movie();
                    movie.setMovieCd(movieResultDto.getMovieCd());
                    movie.setMovieNm(movieResultDto.getMovieNm());
                    movie.setPrdtYear(movieResultDto.getPrdtYear());
                    movie.setTypeNm(movieResultDto.getTypeNm());
                    movie.setOpenDt(LocalDate.parse(movieResultDto.getOpenDt(),formatter));
                    movie.setRepGenreNm(movieResultDto.getRepGenreNm());
                    movie.setRepNationNm(movieResultDto.getRepNationNm());
                    movie.setPrdtStatNm(movieResultDto.getPrdtStatNm());

                    MovieDetail movieDetail = new MovieDetail();
                    if(k % 2 == 0){
                        movieDetail.setPrice(BigDecimal.valueOf(500L));
                    }else{
                        movieDetail.setPrice(BigDecimal.valueOf(1000L));
                    }
                    movie.setMovieDetail(movieDetail);
                    movieRepository.save(movie);
                }
                page +=1;
                k++;
            }else{
                break;
            }



        }


    }
}
