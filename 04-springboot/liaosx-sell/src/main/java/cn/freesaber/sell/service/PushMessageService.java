package cn.freesaber.sell.service;

import cn.freesaber.sell.dto.OrderDTO;

public interface PushMessageService {
    //订单状态变更消息
    void orderStatus(OrderDTO orderDTO);
}
