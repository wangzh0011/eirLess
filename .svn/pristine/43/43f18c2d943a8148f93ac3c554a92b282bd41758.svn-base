package io.renren.modules.miniapp.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.miniapp.dao.HistoryOrderDao;
import io.renren.modules.miniapp.entity.HistoryOrder;
import io.renren.modules.miniapp.service.HistoryOrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by timmy.deng on 2018/9/11.
 */
@Service("HistoryOrderService")
public class HistoryOrderServiceImpl extends ServiceImpl<HistoryOrderDao, HistoryOrder> implements HistoryOrderService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String key = (String)params.get("key");
        List<String> ls = new ArrayList();
        ls.add("order_id");
        ls.add("update_time");

        if (key != null) {
            key = key.trim();
        }
        Page<HistoryOrder> page = this.selectPage(
                new Query<HistoryOrder>(params).getPage(),
                new EntityWrapper<HistoryOrder>()
                        .eq(StringUtils.isNotBlank(key),"order_id", key)
/*                      .like(StringUtils.isNotBlank(key),"order_id", key).or()
                        .like(StringUtils.isNotBlank(key),"state", key).or()
                        .like(StringUtils.isNotBlank(key),"user_name", key).or()

                        .like(StringUtils.isNotBlank(key),"plate", key) */
                        .orderDesc(ls)
        );
        return new PageUtils(page);
    }
}
