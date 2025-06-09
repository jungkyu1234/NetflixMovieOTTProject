package com.reviewboy.domain.pay.presentation;


import com.reviewboy.domain.pay.config.PayServiceProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PayViewController {

    private final PayServiceProperties payServiceProperties;

    @GetMapping("/pay")
    public String payPage(Model model){

        System.out.println(payServiceProperties.storeCd());
        model.addAttribute("storeCd", payServiceProperties.storeCd());
        return "pay";
    }
}
