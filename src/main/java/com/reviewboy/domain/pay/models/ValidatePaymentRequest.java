package com.reviewboy.domain.pay.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ValidatePaymentRequest {
    //아임포트원 결제 고유 번호
    private String impUid;
    //주문 번호
    private String merchantUid;
}
