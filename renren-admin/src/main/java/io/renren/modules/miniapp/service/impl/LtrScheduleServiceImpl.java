package io.renren.modules.miniapp.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.miniapp.dao.LtrScheduleDao;
import io.renren.modules.miniapp.entity.bargelink.LtrSchedule;
import io.renren.modules.miniapp.service.LtrScheduleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class LtrScheduleServiceImpl extends ServiceImpl<LtrScheduleDao, LtrSchedule> implements LtrScheduleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String paramKey = (String)params.get("key");
        String status = (String)params.get("status");
        List list = new ArrayList<LtrSchedule>();
        Page<LtrSchedule> page = null;
        if("ata".equals(status)){
            list.add("i_ata");
            page = this.selectPage(
                    new Query<LtrSchedule>(params).getPage(),
                    new EntityWrapper<LtrSchedule>()
                            .like(StringUtils.isNotBlank(paramKey),"voy_cd", paramKey)
                            .and()
                            .ge("i_ata",new SimpleDateFormat("yyyyMMddHHmmss").format(new Date().getTime()-1000*60*60*24*10))
                            .orderDesc(list)
            );
        }
        if("eta".equals(status)){
            list.add("eta");
            page = this.selectPage(
                    new Query<LtrSchedule>(params).getPage(),
                    new EntityWrapper<LtrSchedule>()
                            .like(StringUtils.isNotBlank(paramKey),"voy_cd", paramKey)
                            .and()
                            .ge("eta",new SimpleDateFormat("yyyyMMddHHmmss").format(new Date().getTime()-1000*60*60*24*1))
                            .and()
                            .isNull("i_ata")
                            .orderDesc(list)
            );
        }

        return new PageUtils(page);
    }
}
