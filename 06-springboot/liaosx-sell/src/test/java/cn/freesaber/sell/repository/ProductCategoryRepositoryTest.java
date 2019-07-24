package cn.freesaber.sell.repository;

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
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;

    @Test
    @Transactional // 回滚测试添加的数据
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("男生最爱");
        productCategory.setCategoryType(4);
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);
        // Assert.assertNotEquals(null, result);
    }

    @Test
    public void findOneTest() {
        ProductCategory productCategory = repository.findById(1).get();
        System.out.println(productCategory);
    }

    @Test
    @Transactional
    public void updateTest() {
        ProductCategory productCategory = repository.findById(1).get();
        productCategory.setCategoryType(10);

        repository.save(productCategory);
    }

    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<ProductCategory> productCategoryList = repository.findByCategoryTypeIn(list);

        Assert.assertNotEquals(0, productCategoryList.size());
    }
}