package com.reviewboy.domain.pay.service;


import com.reviewboy.domain.movie.entity.Movie;
import com.reviewboy.domain.movie.service.MovieService;
import com.reviewboy.domain.pay.config.PayServiceProperties;
import com.reviewboy.domain.pay.entity.Order;
import com.reviewboy.domain.pay.entity.enums.PayStatus;
import com.reviewboy.domain.pay.models.PreparePaymentRequest;
import com.reviewboy.domain.pay.models.ValidatePaymentRequest;
import com.reviewboy.domain.pay.repository.OrderRepository;
import com.reviewboy.domain.users.entity.User;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.request.PrepareData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayService {

    private final OrderRepository orderRepository;

    private final PayServiceProperties payServiceProperties;

    private IamportClient api;

    @PostConstruct
    public void initApi(){
        api = new IamportClient(payServiceProperties.restApiKey(), payServiceProperties.restApiSecret());
    }

    public Order preparePayment(User user, Movie movie, BigDecimal price) throws IamportResponseException, IOException {
        Order order = new Order();
        order.setUsername(user.getUsername());
        order.setPayStatus(PayStatus.PREPARED);
        order.setMovie(movie);
        order.setUser(user);
        order.setPreparePrice(price);

        Order savedOrder = orderRepository.save(order);
        log.info("1. 오더 생성 완료 - PREPARE");

        PrepareData prepareData = new PrepareData(String.valueOf(savedOrder.getId()), savedOrder.getPreparePrice());
        var result = api.postPrepare(prepareData);
        log.info("preparePaymentResult:{}, message: {}", result.getCode(), result.getMessage());
        log.info("2. 오더 API 전송 완료 - merchantUID:{}", savedOrder.getId());
        return savedOrder;
    }

    public boolean validatePayment(Long orderId, String orderUid) throws IamportResponseException, IOException {
        //검증 과정을 거친다.
        //1.API 결제 결과 조회
        IamportResponse<Payment> iamportResponse = api.paymentByImpUid(orderUid);
        BigDecimal paidAmount = iamportResponse.getResponse().getAmount();
        log.info("3.API 결제 시 결제된 금액 : {}", paidAmount.longValue());
        if(!paidAmount.equals(BigDecimal.valueOf(1000))){
            CancelData cancelData = new CancelData(iamportResponse.getResponse().getImpUid(), true);
            api.cancelPaymentByImpUid(cancelData);
        }
        //2.DB 조회
        Order order = orderRepository.findById(orderId).orElseGet(()->null);
        log.info("4.DB에 적재 시 확인된 금액: {}", Objects.requireNonNull(order).getPreparePrice().longValue());

        if(paidAmount.compareTo(order.getPreparePrice()) == 0){
            order.setPayStatus(PayStatus.FINISHED);
        }
        return paidAmount.compareTo(order.getPreparePrice()) == 0;
    }
}
