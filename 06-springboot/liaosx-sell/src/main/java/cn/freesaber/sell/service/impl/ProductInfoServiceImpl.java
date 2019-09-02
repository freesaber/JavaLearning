package cn.freesaber.sell.service.impl;

import cn.freesaber.sell.dataobject.ProductInfo;
import cn.freesaber.sell.dto.CartDTO;
import cn.freesaber.sell.enums.ProductStatusEnum;
import cn.freesaber.sell.enums.ResultEnum;
import cn.freesaber.sell.exception.SellException;
import cn.freesaber.sell.repository.ProductInfoRepository;
import cn.freesaber.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public ProductInfo findOne(String productId) {
        // 查询商品是否存在
        Optional<ProductInfo> optionalProductInfo = repository.findById(productId);
        if (!optionalProductInfo.isPresent()) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        return optionalProductInfo.get();
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    /**
     * 商品上架
     */
    @Override
    public ProductInfo onSale(String productId) {
        // 查询商品
        ProductInfo productInfo = findOne(productId);
        // 判断状态
        if (productInfo.getProductStatus() == ProductStatusEnum.UP.getCode()) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        // 修改状态
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        // 保存
        return repository.save(productInfo);
    }

    /**
     * 商品下架
     */
    @Override
    public ProductInfo offSale(String productId) {
        // 查询商品
        ProductInfo productInfo = findOne(productId);
        // 判断状态
        if (productInfo.getProductStatus() == ProductStatusEnum.DOWN.getCode()) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        // 修改状态
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        // 保存
        return repository.save(productInfo);
    }
}
