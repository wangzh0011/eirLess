package io.renren.modules.miniapp.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.miniapp.entity.WxFunction;

import java.util.Map;

public interface WxFunctionService extends IService<WxFunction> {
    PageUtils queryPage(Map<String, Object> params);
}
