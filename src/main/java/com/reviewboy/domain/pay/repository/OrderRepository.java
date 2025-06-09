package com.reviewboy.domain.pay.repository;


import com.reviewboy.domain.pay.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
