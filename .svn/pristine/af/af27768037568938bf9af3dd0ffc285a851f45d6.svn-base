package io.renren.modules.miniapp.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.miniapp.dao.YardPlanDao;
import io.renren.modules.miniapp.entity.YardPlan;
import io.renren.modules.miniapp.service.YardPlanService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class YardPlanServiceImpl extends ServiceImpl<YardPlanDao, YardPlan> implements YardPlanService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String paramKey = (String)params.get("key");
        String site = (String)params.get("site");
        String status = (String)params.get("status");
        List list = new ArrayList<YardPlan>();
        list.add("create_time");
        Page<YardPlan> page = this.selectPage(
                new Query<YardPlan>(params).getPage(),
                new EntityWrapper<YardPlan>()
                        .like(StringUtils.isNotBlank(paramKey),"id", paramKey)
                        .like(StringUtils.isNotBlank(site),"site", site)
                        .like(StringUtils.isNotBlank(status),"status", status)
                .orderDesc(list)

        );

        return new PageUtils(page);
    }
}
