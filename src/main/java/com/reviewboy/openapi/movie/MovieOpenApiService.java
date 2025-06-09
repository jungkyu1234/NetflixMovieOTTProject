package com.reviewboy.openapi.movie;

import com.reviewboy.openapi.movie.dto.MovieEntireResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Service
public class MovieOpenApiService {
    @Value("${openapi.movie.key}")
    private String key;
    @Value("${openapi.movie.url}")
    private String url;

    public MovieEntireResultDto retrieveAllMovies(String year, int curPage, int pageSize){
        RestTemplate restTemplate = new RestTemplate();

        String requestUrl = url + "/movie/searchMovieList.json?key=" + key + "&openStartDt=" + year + "&repNationCd=22041011&itemPerPage=" + pageSize + "&curPage=" + curPage;
        System.out.println(requestUrl);
        MovieEntireResultDto entireDto =
        restTemplate.getForObject(requestUrl,
                MovieEntireResultDto.class);

        System.out.println(entireDto);
        return entireDto;
    }

    public Map<String, Object> retrieveMovieContentByMovieCd(String movieCd){
        log.info("영화진흥위원회 오픈 API 호출 시작 - 영화상세정보{movieCd:" + movieCd + "}");
        RestTemplate restTemplate = new RestTemplate();
        var result = restTemplate.exchange(url + "/movie/searchMovieInfo.json?key=" + key + "&movieCd=" + movieCd,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, Object>>() {});
        log.info("영화진흥위원회 오픈 API 호출 완료 - 영화상세정보");
        log.info("영화진흥위원회 오픈 API 호출 결과 - {}", result.getStatusCode().toString());
        return result.getBody();
    }
}
