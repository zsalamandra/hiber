package com.example.hiber.services;

import com.example.hiber.model.entity.Policy;
import com.example.hiber.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public void doAction() {

        List<Policy> allByOrderId = orderRepository.findAllByOrderId(List.of(1111, 2222));
        allByOrderId.forEach(System.out::println);
    }
}
