package cn.freesaber.sell.service;

import cn.freesaber.sell.domain.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    // 卖家端管理用
    ProductCategory save(ProductCategory productCategory);

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    // 买家端用
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
