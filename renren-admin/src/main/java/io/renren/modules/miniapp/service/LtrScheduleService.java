package io.renren.modules.miniapp.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.miniapp.entity.bargelink.LtrSchedule;

import java.util.Map;

public interface LtrScheduleService extends IService<LtrSchedule> {
    PageUtils queryPage(Map<String, Object> params);
}
