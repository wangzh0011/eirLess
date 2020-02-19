package io.renren.modules.miniapp.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.miniapp.dao.HistorySuggestDao;
import io.renren.modules.miniapp.entity.HistorySuggest;
import io.renren.modules.miniapp.service.HistorySuggestService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HistorySuggestServiceImpl extends ServiceImpl<HistorySuggestDao, HistorySuggest> implements HistorySuggestService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String suggestId = (String)params.get("id");
        List list = new ArrayList<HistorySuggest>();
        list.add("update_time");
        Page<HistorySuggest> page = this.selectPage(
                new Query<HistorySuggest>(params).getPage(),
                new EntityWrapper<HistorySuggest>()
                        .like(StringUtils.isNotBlank(suggestId),"suggest_id", suggestId)
                .orderDesc(list)

        );

        return new PageUtils(page);
    }
}
