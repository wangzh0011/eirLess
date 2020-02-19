package io.renren.modules.miniapp.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.miniapp.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by timmy.deng on 2018/9/11.
 */
public interface OrderService extends IService<Order> {

     List<Order> findList();
     List<Order> findList(Object[] objects);
     PageUtils queryPage(Map<String, Object> params);
}
