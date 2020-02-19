package io.renren.modules.eir.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.miniapp.entity.WxFunction;
import io.renren.modules.miniapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 用于控制小程序首页功能的开关
 */
@RestController
@RequestMapping("wx")
public class AppController {
    @Autowired
    private WXUserService wxUserService;

    @Autowired
    private WxFunctionService wxFunctionService;

    /**
     * 用户列表
     * @param params
     * @return
     */
    @RequestMapping("/user/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = wxUserService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 小程序功能列表
     * @param params
     * @return
     */
    @RequestMapping("/system/wxFunctionList")
    public R wxFunctionList(@RequestParam Map<String, Object> params){
        return R.ok().put("page",wxFunctionService.queryPage(params));
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @RequestMapping("/system/getDetail")
    public R getDetail(@RequestParam String id){
        WxFunction detail = wxFunctionService.selectOne(new EntityWrapper<WxFunction>().eq("id",id));
        if(detail != null){
            return R.ok().put("wxFunction",detail);
        }
        return R.error();
    }

    /**
     * 新增
     * @param wxFunction
     * @return
     */
    @RequestMapping("/system/save")
    public R save(@RequestBody WxFunction wxFunction){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        wxFunction.setCreateTime(sdf.format(new Date()));
        wxFunction.setUpdateTime(sdf.format(new Date()));
        if(wxFunctionService.insert(wxFunction)){
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     * @param wxFunction
     * @return
     */
    @RequestMapping("system/update")
    public R update(@RequestBody WxFunction wxFunction){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        wxFunction.setUpdateTime(sdf.format(new Date()));
        if(wxFunctionService.insertOrUpdate(wxFunction)){
            return R.ok();
        }
        return R.error();
    }

}
