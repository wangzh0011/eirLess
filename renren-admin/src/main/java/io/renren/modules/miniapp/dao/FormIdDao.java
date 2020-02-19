package io.renren.modules.miniapp.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.miniapp.entity.FormId;

import java.util.List;

/**
 * Created by timmy.deng on 2018/9/12.
 */
public interface FormIdDao extends BaseMapper<FormId> {

    List<String> findDistinctOpenIdByPlate(String plate);
}
