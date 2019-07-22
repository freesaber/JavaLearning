package cn.freesaber.sell.repository;

import cn.freesaber.sell.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    // 通过类别List获取类目列表
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
