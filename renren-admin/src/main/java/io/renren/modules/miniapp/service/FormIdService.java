package io.renren.modules.miniapp.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.modules.miniapp.entity.FormId;

import java.util.List;

/**
 * Created by timmy.deng on 2018/9/12.
 */
public interface FormIdService extends IService<FormId> {
     List<String> findDistinctOpenIdByPlate(String plate);
}
