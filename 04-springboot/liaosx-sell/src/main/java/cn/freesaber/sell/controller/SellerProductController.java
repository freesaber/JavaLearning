package cn.freesaber.sell.controller;

import cn.freesaber.sell.dataobject.ProductCategory;
import cn.freesaber.sell.dataobject.ProductInfo;
import cn.freesaber.sell.enums.ProductStatusEnum;
import cn.freesaber.sell.enums.ResultEnum;
import cn.freesaber.sell.form.ProductForm;
import cn.freesaber.sell.service.ProductCategoryService;
import cn.freesaber.sell.service.ProductInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {
    private final Logger logger = LoggerFactory.getLogger(SellerProductController.class);

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "5") Integer size,
                             Map<String, Object> map) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(pageRequest);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);
    }

    @RequestMapping("on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        try {
            ProductInfo productInfo = productInfoService.onSale(productId);
            map.put("msg", ResultEnum.PRODUCT_UP_SUCCESS.getMessage());
            map.put("url", "/seller/product/list");
            return new ModelAndView("common/success", map);
        } catch (Exception ex) {
            logger.error("【卖家端商品上架】发生异常");
            map.put("msg", ex.getMessage());
            map.put("url", "/seller/product/list");
            return new ModelAndView("common/error", map);
        }
    }

    @RequestMapping("off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                                Map<String, Object> map) {
        try {
            ProductInfo productInfo = productInfoService.offSale(productId);
            map.put("msg", ResultEnum.PRODUCT_DOWN_SUCCESS.getMessage());
            map.put("url", "/seller/product/list");
            return new ModelAndView("common/success", map);
        } catch (Exception ex) {
            logger.error("【卖家端商品下架】发生异常");
            map.put("msg", ex.getMessage());
            map.put("url", "/seller/product/list");
            return new ModelAndView("common/error", map);
        }
    }

    // 创建或编辑商品
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productInfoService.findOne(productId);
            map.put("productInfo", productInfo);
        }

        // 查询所有类目
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        map.put("categoryList", productCategoryList);

        return new ModelAndView("product/index", map);
    }

    // 保存或更新商品
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        try {
            ProductInfo productInfo = new ProductInfo();
            if (!StringUtils.isEmpty(form.getProductId())) {
                productInfo = productInfoService.findOne(form.getProductId());
                BeanUtils.copyProperties(form, productInfo);
            } else {
                BeanUtils.copyProperties(form, productInfo);
                productInfo.setProductId(UUID.randomUUID().toString());
                productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
            }

            productInfoService.save(productInfo);
        } catch (Exception ex) {
            logger.error("【卖家端添加或修改商品】发生异常");
            map.put("msg", ex.getMessage());
            map.put("url", "/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        if (StringUtils.isEmpty(form.getProductId())) {
            map.put("msg", ResultEnum.PRODUCT_ADD_SUCCESS.getMessage());
        } else {
            map.put("msg", ResultEnum.PRODUCT_UPDATE_SUCCESS.getMessage());
        }
        map.put("url", "/seller/product/list");
        return new ModelAndView("common/success", map);
    }
}
