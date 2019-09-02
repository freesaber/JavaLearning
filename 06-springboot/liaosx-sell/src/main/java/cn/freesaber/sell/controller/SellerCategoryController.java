package cn.freesaber.sell.controller;

import cn.freesaber.sell.dataobject.ProductCategory;
import cn.freesaber.sell.enums.ResultEnum;
import cn.freesaber.sell.form.CategoryForm;
import cn.freesaber.sell.service.ProductCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {
    private final Logger logger = LoggerFactory.getLogger(SellerCategoryController.class);

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("list")
    public ModelAndView list(Map<String, Object> map) {
        List<ProductCategory> categoryList = productCategoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("category/list", map);
    }

    @GetMapping("index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {
        if (categoryId != null && categoryId > 0) {
            ProductCategory productCategory = productCategoryService.findOne(categoryId);
            map.put("category", productCategory);
        }

        return new ModelAndView("category/index", map);
    }

    @PostMapping("save")
    public ModelAndView save(@Valid CategoryForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/list");
            return new ModelAndView("common/error", map);
        }

        try {
            ProductCategory productCategory = new ProductCategory();
            if (form.getCategoryId() != null) {
                productCategory = productCategoryService.findOne(form.getCategoryId());
            }

            BeanUtils.copyProperties(form, productCategory);
            productCategoryService.save(productCategory);
        } catch (Exception ex) {
            logger.error("【卖家端添加或修改类目】发生异常");
            map.put("msg", ex.getMessage());
            map.put("url", "/sell/seller/category/list");
            return new ModelAndView("common/error", map);
        }
        if (form.getCategoryId() != null) {
            map.put("msg", ResultEnum.CATEGORY_ADD_SUCCESS.getMessage());
        } else {
            map.put("msg", ResultEnum.CATEGORY_UPDATE_SUCCESS.getMessage());
        }
        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/success", map);
    }
}
