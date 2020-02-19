package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.sys.dao.SysRoleDataDao;
import io.renren.modules.sys.entity.SysRoleDataEntity;
import io.renren.modules.sys.service.SysRoleDataService;
import org.springframework.stereotype.Service;

/**
 * Created by timmy.deng on 2019/2/26.
 */
@Service("sysRoleDataService")
public class SysRoleDataServiceImpl extends ServiceImpl<SysRoleDataDao,SysRoleDataEntity> implements SysRoleDataService {
}
