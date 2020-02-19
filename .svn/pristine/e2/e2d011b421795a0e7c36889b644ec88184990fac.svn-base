package io.renren.modules.miniapp.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.miniapp.dao.ChangeLogDao;
import io.renren.modules.miniapp.entity.bargelink.ChangeLog;
import io.renren.modules.miniapp.service.ChangeLogService;
import org.springframework.stereotype.Service;

@Service
public class ChangeLogServiceImpl extends ServiceImpl<ChangeLogDao, ChangeLog> implements ChangeLogService {

    @Override
    public void insertValue(String logType, String logDtl, String logStatus, String updTime, String voyCd) {
        baseMapper.insertValue(logType, logDtl, logStatus, updTime, voyCd);
    }
}
