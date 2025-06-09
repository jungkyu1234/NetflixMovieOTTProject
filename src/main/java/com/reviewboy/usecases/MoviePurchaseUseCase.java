package com.reviewboy.usecases;

import com.reviewboy.domain.movie.dto.MovieDto;
import com.reviewboy.domain.movie.entity.Movie;
import com.reviewboy.domain.movie.repository.MovieRepository;
import com.reviewboy.domain.movie.service.MovieService;
import com.reviewboy.domain.pay.entity.Order;
import com.reviewboy.domain.pay.repository.OrderRepository;
import com.reviewboy.domain.pay.service.PayService;
import com.reviewboy.domain.users.entity.User;
import com.reviewboy.domain.users.service.UserService;
import com.reviewboy.security.utils.SecurityUtils;
import com.siot.IamportRestClient.exception.IamportResponseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MoviePurchaseUseCase {
    private final MovieService movieService;
    private final PayService payService;
    private final UserService userService;

    @Transactional(rollbackFor = {IamportResponseException.class, IOException.class})
    public Order preparePayProcess(Long movieId) throws IamportResponseException, IOException{
        //1.유저 정보를 조회한다.
        String username = Objects.requireNonNull(SecurityUtils.getCurrentUser()).getUsername();
        User user = userService.findByUsername(username);
        //2.유저가 구매하려는 영화를 찾는다.
        Movie movie = movieService.findMovieById(movieId);
        //3.구매 준비단계를 저장한다.
        return payService.preparePayment(user, movie, movie.getMovieDetail().getPrice());
    }

    @Transactional(rollbackFor = {IamportResponseException.class, IOException.class})
    public boolean validatePayProcess(Long orderId, String orderUid) throws IamportResponseException, IOException{

       return payService.validatePayment(orderId, orderUid);
    }



}
