package io.renren.modules.miniapp.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.miniapp.dao.WXUserDaoc;
import io.renren.modules.miniapp.entity.WXUserc;
import io.renren.modules.miniapp.service.WXUserServicec;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by timmy.deng on 2018/9/12.
 */
@Service("wxUserServicec")
public class WXUserServiceImplc extends ServiceImpl<WXUserDaoc,WXUserc> implements WXUserServicec {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String key = (String)params.get("key");
        List<String> ls = new ArrayList();

        Page<WXUserc> page = this.selectPage(
                new Query<WXUserc>(params).getPage(),
                new EntityWrapper<WXUserc>().like(StringUtils.isNotBlank(key),"id", key).or()
                        .like(StringUtils.isNotBlank(key),"user_name",key).or()
                        .like(StringUtils.isNotBlank(key),"plate",key).or()
                        .like(StringUtils.isNotBlank(key),"openid",key)
        );
        return new PageUtils(page);
    }
}
