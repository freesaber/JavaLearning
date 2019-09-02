package cn.freesaber.sell.service.impl;

import cn.freesaber.sell.dataobject.ProductInfo;
import cn.freesaber.sell.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {
    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    @Transactional
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("111222333");
        productInfo.setProductName("皮皮虾");
        productInfo.setProductPrice(new BigDecimal(3));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好吃的虾");
        productInfo.setProductIcon("http://xxoo.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(3);
        productInfoService.save(productInfo);
        System.out.println(productInfo);
        Assert.assertNotEquals(null, productInfo);
    }

    @Test
    public void findOne() {
        ProductInfo productInfo = productInfoService.findOne("123456");
        Assert.assertEquals("123456", productInfo.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfos = productInfoService.findUpAll();
        Assert.assertNotEquals(0, productInfos.size());
    }

    @Test
    public void findAll() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(pageable);
        System.out.println("所有元素个数:" + productInfoPage.getTotalElements());
        System.out.println("总页数:" + productInfoPage.getTotalPages());
    }

    @Test
    public void onSale() {
        ProductInfo productInfo = productInfoService.onSale("123456");
        Assert.assertEquals(ProductStatusEnum.UP.getCode(), productInfo.getProductStatus());
    }

    @Test
    public void offSale() {
        ProductInfo productInfo = productInfoService.offSale("123456");
        Assert.assertEquals(ProductStatusEnum.DOWN.getCode(), productInfo.getProductStatus());
    }
}