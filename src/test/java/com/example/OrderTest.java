package com.example;

import com.example.hiber.services.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ContextConfiguration(classes = AutotestConfig.class)
@ExtendWith(SpringExtension.class)
public class OrderTest {

    @Autowired
    private OrderService orderService;

    @Test
    void doIt() {

        orderService.doAction();

    }
}
