package cn.freesaber.sell.service.impl;

import cn.freesaber.sell.dataobject.SellerInfo;
import cn.freesaber.sell.repository.SellerInfoRepository;
import cn.freesaber.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
