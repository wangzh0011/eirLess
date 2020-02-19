package io.renren.modules.miniapp.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.miniapp.dao.WXUserDao;
import io.renren.modules.miniapp.entity.WXUser;
import io.renren.modules.miniapp.service.WXUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by timmy.deng on 2018/9/12.
 */
@Service("wxUserService")
public class WXUserServiceImpl  extends ServiceImpl<WXUserDao,WXUser> implements WXUserService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String key = (String)params.get("username");
        if (key != null){
            key=key.trim();
        }
        Page<WXUser> page = this.selectPage(
                new Query<WXUser>(params).getPage(),
                new EntityWrapper<WXUser>()
                        .like(StringUtils.isNotBlank(key),"id", key).or()
                        .like(StringUtils.isNotBlank(key),"user_name",key).or()
                        .like(StringUtils.isNotBlank(key),"plate",key).or()
                        .like(StringUtils.isNotBlank(key),"PHONE",key).or()
                        .like(StringUtils.isNotBlank(key),"CREATE_TIME",key).or()
                        .like(StringUtils.isNotBlank(key),"openid",key)
        );
        return new PageUtils(page);
    }
}
