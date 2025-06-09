package com.reviewboy.domain.pay.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

//Spring 3.0부터는 @ConstrctorBinding 굳이 안 붙여도 됨
@ConfigurationProperties("iamport")
public record PayServiceProperties(String storeCd, String restApiKey, String restApiSecret){}
