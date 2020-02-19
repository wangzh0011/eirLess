package io.renren.modules.miniapp.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.miniapp.dao.SuggestDao;
import io.renren.modules.miniapp.entity.Suggest;
import io.renren.modules.miniapp.service.SuggestService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SuggestServiceImpl extends ServiceImpl<SuggestDao, Suggest> implements SuggestService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String paramKey = (String)params.get("key");
        String status = (String)params.get("status");
        List list = new ArrayList<Suggest>();
        list.add("create_time");
        Page<Suggest> page = this.selectPage(
                new Query<Suggest>(params).getPage(),
                new EntityWrapper<Suggest>()
                        .like(StringUtils.isNotBlank(paramKey),"id", paramKey)
                        .like(StringUtils.isNotBlank(status),"status", status)
                .orderDesc(list)
//
        );

        return new PageUtils(page);
    }
}
