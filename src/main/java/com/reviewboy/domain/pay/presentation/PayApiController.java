package com.reviewboy.domain.pay.presentation;


import com.reviewboy.domain.pay.entity.Order;
import com.reviewboy.domain.pay.models.PreparePaymentRequest;
import com.reviewboy.domain.pay.models.ValidatePaymentRequest;
import com.reviewboy.usecases.MoviePurchaseUseCase;
import com.siot.IamportRestClient.exception.IamportResponseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PayApiController {

   private final MoviePurchaseUseCase moviePurchaseUseCase;

    //사전 결제 준비
    @PostMapping("/pay/prepare_payment")
    public ResponseEntity<?> preparePayment(@RequestBody PreparePaymentRequest preparePaymentRequest){
        try{
           Order order = moviePurchaseUseCase.preparePayProcess(Long.valueOf(preparePaymentRequest.getMovieId()));
           return ResponseEntity.ok(order);
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    //사후 결제 검증
    @PostMapping("/pay/validate_payment")
    public boolean validatePayment(@RequestBody ValidatePaymentRequest validatePaymentRequest) throws IamportResponseException, IOException {
        return moviePurchaseUseCase.validatePayProcess(Long.valueOf(validatePaymentRequest.getMerchantUid()), validatePaymentRequest.getImpUid());
    }
}
