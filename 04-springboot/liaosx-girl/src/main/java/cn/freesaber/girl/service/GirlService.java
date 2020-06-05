package cn.freesaber.girl.service;

import cn.freesaber.girl.Exception.GirlException;
import cn.freesaber.girl.domain.Girl;
import cn.freesaber.girl.enums.ResultEnum;
import cn.freesaber.girl.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class GirlService {
    @Autowired
    GirlRepository girlRepository;

    @Transactional
    public void insertTwo() {
        Girl girlA = new Girl();
        girlA.setCupSize("A");
        girlA.setAge(18);
        girlRepository.save(girlA);

        Girl girlB = new Girl();
        girlB.setCupSize("B");
        girlB.setAge(19);
        girlRepository.save(girlB);
    }

    public void getAge(Integer id) throws Exception {
        Optional<Girl> optionalGirl = girlRepository.findById(id);
        if (optionalGirl.isPresent()) {
            Girl girl = optionalGirl.get();
            Integer age = girl.getAge();
            if (age < 10) {
                throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
            } else if (age > 10 && age < 16) {
                throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
            }
        }
        throw new Exception("用户不存在");
    }
}
