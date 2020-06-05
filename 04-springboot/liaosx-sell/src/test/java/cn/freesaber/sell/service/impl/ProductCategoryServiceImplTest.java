package cn.freesaber.sell.service.impl;

import cn.freesaber.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {
    @Autowired
    private ProductCategoryServiceImpl service;

    @Test
    @Transactional
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(4);
        productCategory.setCategoryName("男生最爱");
        ProductCategory result = service.save(productCategory);
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findOne() {
        ProductCategory productCategory = service.findOne(1);
        System.out.println(productCategory);
        Assert.assertEquals(new Integer(1), productCategory.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> result = service.findAll();
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> categoryTypes = Arrays.asList(1, 2, 3);
        List<ProductCategory> result = service.findByCategoryTypeIn(categoryTypes);

        Assert.assertNotEquals(0, result.size());
    }
}