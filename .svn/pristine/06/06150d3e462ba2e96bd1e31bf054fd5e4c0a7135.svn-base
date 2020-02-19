package io.renren.modules.miniapp.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.miniapp.dao.WxFunctionDao;
import io.renren.modules.miniapp.entity.WxFunction;
import io.renren.modules.miniapp.service.WxFunctionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WxFunctionServiceImpl extends ServiceImpl<WxFunctionDao, WxFunction> implements WxFunctionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String paramKey = (String)params.get("key");
        List list = new ArrayList<WxFunction>();
        list.add("create_time");
        Page<WxFunction> page = this.selectPage(
                new Query<WxFunction>(params).getPage(),
                new EntityWrapper<WxFunction>()
                        .like(StringUtils.isNotBlank(paramKey),"id", paramKey)
                .orderDesc(list)
//
        );

        return new PageUtils(page);
    }
}
