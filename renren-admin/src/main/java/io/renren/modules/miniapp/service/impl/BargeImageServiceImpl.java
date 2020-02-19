package io.renren.modules.miniapp.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.miniapp.dao.BargeImageDao;
import io.renren.modules.miniapp.entity.BargeImage;
import io.renren.modules.miniapp.service.BargeImageService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BargeImageServiceImpl extends ServiceImpl<BargeImageDao, BargeImage> implements BargeImageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String keyWord = (String)params.get("keyWord");

        List list = new ArrayList<BargeImage>();
        list.add("create_time");
        Page<BargeImage> page = this.selectPage(
                new Query<BargeImage>(params).getPage(),
                new EntityWrapper<BargeImage>()
                        .like(StringUtils.isNotBlank(keyWord),"vessel_name", keyWord)
                        .or()
                        .eq(StringUtils.isNotBlank(keyWord),"i_voy_cd", keyWord)
                        .or()
                        .eq(StringUtils.isNotBlank(keyWord),"o_voy_cd", keyWord)

                .orderDesc(list)

        );

        return new PageUtils(page);
    }
}
