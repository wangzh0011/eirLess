package io.renren.modules.miniapp.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.utils.R;
import io.renren.modules.miniapp.entity.WxFunction;
import io.renren.modules.miniapp.service.WxFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/miniApp")
public class WxFunctionController {

    @Autowired
    private WxFunctionService wxFunctionService;

    /**
     * 功能列表
     * @return
     */
    @RequestMapping("/getFunctionList")
    public List<WxFunction> getFunctionList(){
        List<WxFunction> list = wxFunctionService.selectList(new EntityWrapper<>());
        return list;
    }

    @RequestMapping("/wxSwitch")
    public R wxSwitch(String functionCode){
        WxFunction wxFunction = wxFunctionService.selectOne(new EntityWrapper<WxFunction>().eq("function_code",functionCode));
        return R.ok().put("wxFunction",wxFunction);
    }
}
