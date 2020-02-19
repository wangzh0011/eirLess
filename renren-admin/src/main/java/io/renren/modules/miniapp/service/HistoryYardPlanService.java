package io.renren.modules.miniapp.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.miniapp.entity.HistoryYardPlan;

import java.util.Map;

public interface HistoryYardPlanService extends IService<HistoryYardPlan> {
    PageUtils queryPage(Map<String, Object> params);
}
