package io.renren.modules.miniapp.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.miniapp.dao.FormIdDao;
import io.renren.modules.miniapp.entity.FormId;
import io.renren.modules.miniapp.service.FormIdService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by timmy.deng on 2018/9/12.
 */
@Service("formIdService")
public class FormIdServiceImpl  extends ServiceImpl<FormIdDao, FormId> implements FormIdService {
    @Override
    public List<String> findDistinctOpenIdByPlate(String plate) {
        return baseMapper.findDistinctOpenIdByPlate(plate);
    }
}
