package io.renren.modules.miniapp.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.miniapp.entity.FormIdc;

import java.util.List;

/**
 * Created by timmy.deng on 2018/9/12.
 */
public interface FormIdDaoc extends BaseMapper<FormIdc> {

    List<String> findDistinctOpenIdByPlate(String plate);
}
