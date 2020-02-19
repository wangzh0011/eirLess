package io.renren.modules.miniapp.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.miniapp.entity.WXUserc;

import java.util.Map;

/**
 * Created by timmy.deng on 2018/9/12.
 */
public interface WXUserServicec extends IService<WXUserc>{

    PageUtils queryPage(Map<String, Object> params);
}
