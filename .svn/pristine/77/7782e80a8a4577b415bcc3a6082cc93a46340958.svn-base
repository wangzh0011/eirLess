package io.renren.modules.miniapp.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.miniapp.dao.FormIdDaoc;
import io.renren.modules.miniapp.entity.FormIdc;
import io.renren.modules.miniapp.service.FormIdServicec;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by timmy.deng on 2018/9/12.
 */
@Service("formIdServicec")
public class FormIdServiceImplc extends ServiceImpl<FormIdDaoc, FormIdc> implements FormIdServicec {
    @Override
    public List<String> findDistinctOpenIdByPlate(String plate) {
        return baseMapper.findDistinctOpenIdByPlate(plate);
    }
}
