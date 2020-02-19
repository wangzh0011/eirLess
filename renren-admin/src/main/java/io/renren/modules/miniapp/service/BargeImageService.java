package io.renren.modules.miniapp.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.miniapp.entity.BargeImage;

import java.util.Map;

public interface BargeImageService extends IService<BargeImage> {
    PageUtils queryPage(Map<String, Object> params);
}
