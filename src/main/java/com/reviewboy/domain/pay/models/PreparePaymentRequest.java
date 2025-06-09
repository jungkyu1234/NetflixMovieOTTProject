package com.reviewboy.domain.pay.models;

import com.siot.IamportRestClient.request.PrepareData;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class PreparePaymentRequest {
    //주문 영화 아이디
    private String movieId;

}
