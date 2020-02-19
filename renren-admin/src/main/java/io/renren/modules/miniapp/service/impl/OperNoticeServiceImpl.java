package io.renren.modules.miniapp.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.miniapp.dao.OperNoticeDao;
import io.renren.modules.miniapp.entity.OperNotice;
import io.renren.modules.miniapp.service.OperNoticeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OperNoticeServiceImpl extends ServiceImpl<OperNoticeDao, OperNotice> implements OperNoticeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String paramKey = (String)params.get("key");
        List list = new ArrayList<OperNotice>();
        list.add("update_time");
        Page<OperNotice> page = this.selectPage(
                new Query<OperNotice>(params).getPage(),
                new EntityWrapper<OperNotice>()
                        .like(StringUtils.isNotBlank(paramKey),"id", paramKey)
                .orderDesc(list)
        );

        return new PageUtils(page);
    }
}
