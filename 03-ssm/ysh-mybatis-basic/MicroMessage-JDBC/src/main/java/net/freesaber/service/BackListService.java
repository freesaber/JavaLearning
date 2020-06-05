package net.freesaber.service;

import net.freesaber.bean.Message;
import net.freesaber.dao.MessageDao;

import java.util.List;

/**
 * 后台列表相关业务功能
 */
public class BackListService {
    public List<Message> queryMessageList(String command, String description) {
        MessageDao messageDao = new MessageDao();
        return messageDao.queryMessageList(command, description);
    }
}
