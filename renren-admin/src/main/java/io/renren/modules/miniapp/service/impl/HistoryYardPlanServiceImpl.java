package io.renren.modules.miniapp.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.miniapp.dao.HistoryYardPlanDao;
import io.renren.modules.miniapp.entity.HistoryYardPlan;
import io.renren.modules.miniapp.service.HistoryYardPlanService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HistoryYardPlanServiceImpl extends ServiceImpl<HistoryYardPlanDao, HistoryYardPlan> implements HistoryYardPlanService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String yardPlanId = (String)params.get("id");
        List list = new ArrayList<HistoryYardPlan>();
        list.add("update_time");
        Page<HistoryYardPlan> page = this.selectPage(
                new Query<HistoryYardPlan>(params).getPage(),
                new EntityWrapper<HistoryYardPlan>()
                        .like(StringUtils.isNotBlank(yardPlanId),"yard_plan_id", yardPlanId)
                .orderDesc(list)

        );

        return new PageUtils(page);
    }
}
