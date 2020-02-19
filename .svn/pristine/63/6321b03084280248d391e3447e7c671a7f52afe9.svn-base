package io.renren.modules.miniapp.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.utils.R;
import io.renren.modules.miniapp.entity.*;
import io.renren.modules.miniapp.service.*;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/miniApp")
public class CustomerController {

    @Autowired
    private SuggestService suggestService;

    @Autowired
    private YardPlanService yardPlanService;

    @Autowired
    private HistoryYardPlanService historyYardPlanService;

    @Autowired
    private HistorySuggestService historySuggestService;

    @Autowired
    private OperNoticeService operNoticeService;


    /**
     * 保存用户提交的意见
     * @param suggest
     * @return
     */
    @RequestMapping("/suggest")
    public R getSuggest(Suggest suggest){
        if(suggestService.insert(suggest)){
            if(saveSuggestHistoryInfo(suggest))
            return R.ok();
        }
        return R.error("系统异常");
    }

    /**
     * 堆场机械问题
     * @param yardPlan
     * @return
     */
    @RequestMapping("/yardPlan")
    public R getYardPlan(YardPlan yardPlan){
        if(yardPlanService.insert(yardPlan)){
            if(saveHistoryInfo(yardPlan))
            return R.ok();
        }
        return R.error("系统异常");
    }

    /**
     * 操作通知列表
     * @return
     */
    @RequestMapping("/getAllNotice")
    public List<OperNotice> getAllNotice(){
        List<OperNotice> list = operNoticeService.selectList(new EntityWrapper<OperNotice>().eq("is_show","1").orderBy("create_time",false));
        return list;
    }

    /**
     * 记录浏览量  ----后续可以考虑使用序列
     * @param id
     * @return
     */
    @RequestMapping("/updateViews")
    public R updateViews(String id) {
        OperNotice notice = operNoticeService.selectOne(new EntityWrapper<OperNotice>().eq("id",id));
        notice.setViews(notice.getViews()+1);
        operNoticeService.updateById(notice);
        return R.ok();
    }

    @RequestMapping("/getNoticeNum")
    public int getNoticeNum() {
        return operNoticeService.selectCount(new EntityWrapper<OperNotice>().eq("is_show","1"));
    }

    /**
     * 保存历史记录
     * @param yardPlan
     * @return
     */
    public boolean saveHistoryInfo(YardPlan yardPlan){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        try {
            HistoryYardPlan historyYardPlan = new HistoryYardPlan();
            //将yardPlan复制到historyYardPlan
            BeanUtils.copyProperties(historyYardPlan, yardPlan);
            historyYardPlan.setYardPlanId(yardPlan.getId());
            historyYardPlan.setUpdateTime(format.format(new Date()));

            historyYardPlan.setOperator("司机");
            return historyYardPlanService.insert(historyYardPlan);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 保存历史记录 --suggest
     * @param suggest
     * @return
     */
    public boolean saveSuggestHistoryInfo(Suggest suggest){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        try {
            HistorySuggest historySuggest = new HistorySuggest();
            //将suggest复制到historySuggest
            BeanUtils.copyProperties(historySuggest, suggest);
            historySuggest.setSuggestId(suggest.getId());
            historySuggest.setUpdateTime(format.format(new Date()));

            historySuggest.setOperator("司机");
            return historySuggestService.insert(historySuggest);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }
}
