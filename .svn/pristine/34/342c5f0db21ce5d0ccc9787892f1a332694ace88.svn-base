package io.renren.modules.miniapp.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.miniapp.dao.CosIdoProcessDao;
import io.renren.modules.miniapp.entity.cosido.CosIdoProcess;
import io.renren.modules.miniapp.service.CosIdoProcessService;
import org.springframework.stereotype.Service;

@Service
public class CosIdoProcessServiceImpl extends ServiceImpl<CosIdoProcessDao, CosIdoProcess> implements CosIdoProcessService {


    @Override
    public CosIdoProcess selectCosIdo(String docref) {
        return baseMapper.selectCosIdo(docref);
    }
}
