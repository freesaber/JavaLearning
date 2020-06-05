package net.freesaber.service;

import net.freesaber.dao.MessageDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 维护相关的业务功能
 */
public class MaintainService {
    // 单条删除
    public void deleteOne(String id) {
        if (id != null && !"".equals(id)) {
            MessageDao messageDao = new MessageDao();
            messageDao.deleteOne(Integer.valueOf(id));
        }
    }

    // 批量删除
    public void deleteBatch(String[] ids){
        List<Integer> idsParam = new ArrayList<>();
        for (String id : ids){
            idsParam.add(Integer.valueOf(id));
        }
        MessageDao messageDao = new MessageDao();
        messageDao.deleteBatch(idsParam);
    }
}
