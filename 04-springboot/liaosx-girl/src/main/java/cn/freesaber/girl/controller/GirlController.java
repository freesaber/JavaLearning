package cn.freesaber.girl.controller;

import cn.freesaber.girl.domain.Girl;
import cn.freesaber.girl.domain.Result;
import cn.freesaber.girl.repository.GirlRepository;
import cn.freesaber.girl.service.GirlService;
import cn.freesaber.girl.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class GirlController {
    private final static Logger logger = LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlRepository girlRepository;
    @Autowired
    private GirlService girlService;

    /**
     * 查询所有女生列表
     */
    @GetMapping("/girls")
    public Result<List<Girl>> girlList() {
        return ResultUtil.success(girlRepository.findAll());
    }

    /**
     * 添加一个女生
     */
    @PostMapping("/girls")
    public Result<Girl> girlAdd(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(girlRepository.save(girl));
    }

    /**
     * 查询一个女生
     */
    @GetMapping("/girls/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id) {
        Optional<Girl> optionalGirl = girlRepository.findById(id);
        if (optionalGirl.isPresent()) {
            return optionalGirl.get();
        }
        return null;
    }

    /**
     * 更新
     */
    @PutMapping("/girls/{id}")
    public Girl girlUpdate(@PathVariable("id") Integer id,
                           @RequestParam("cupSize") String cupSize,
                           @RequestParam("age") Integer age) {
        Optional<Girl> optionalGirl = girlRepository.findById(id);
        if (optionalGirl.isPresent()) {
            Girl girl = optionalGirl.get();
            girl.setCupSize(cupSize);
            girl.setAge(age);
            return girlRepository.save(girl);
        }
        return null;
    }

    // 删除
    @DeleteMapping("/girls/{id}")
    public Result<Girl> girlDelete(@PathVariable("id") Integer id){
        girlRepository.deleteById(id);
        return ResultUtil.success();
    }

    // 通过年龄查询女生列表
    @GetMapping("/girls/age/{age}")
    public List<Girl> girlListByAge(@PathVariable("age") Integer age){
        return girlRepository.findByAge(age);
    }

    // 一次性添加两个女生
    @PostMapping("/girls/two")
    public void girlTwo(){
        girlService.insertTwo();
    }

    // 获取年龄
    @GetMapping("/girls/getAge/{id}")
    public void gerAge(@PathVariable("id") Integer id) throws Exception{
        girlService.getAge(id);
    }

}
