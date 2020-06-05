package cn.freesaber.sell.service.impl;

import cn.freesaber.sell.dto.OrderDTO;
import cn.freesaber.sell.service.OrderService;
import cn.freesaber.sell.service.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceImplTest {
    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = orderService.findOne("cfccdb5f-b5b2-40df-9faf-d62e8da3fd8b");
        payService.create(orderDTO);
    }
}