package cn.freesaber.sell.repository;

import cn.freesaber.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository repository;

    @Test
    @Transactional
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("111222333");
        orderMaster.setBuyerName("saber");
        orderMaster.setBuyerPhone("13999999999");
        orderMaster.setBuyerAddress("nanjing");
        orderMaster.setBuyerOpenid("110110");
        orderMaster.setOrderAmount(new BigDecimal(2.5));

        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest request = PageRequest.of(0, 1);
        Page<OrderMaster> result = repository.findByBuyerOpenid("110110", request);
        System.out.println("总元素个数：" + result.getTotalElements());
        System.out.println("总页数：" + result.getTotalPages());
        Assert.assertNotEquals(0, result.getTotalElements());
    }
}