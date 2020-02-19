package io.renren.modules.miniapp.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.miniapp.entity.WXUser;

import java.util.Map;

/**
 * Created by timmy.deng on 2018/9/12.
 */
public interface WXUserService extends IService<WXUser>{

    PageUtils queryPage(Map<String, Object> params);
}
