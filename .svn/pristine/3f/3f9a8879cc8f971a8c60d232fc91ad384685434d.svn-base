package io.renren.modules.eir.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.eir.until.IssueTypeUtil;
import io.renren.modules.eir.until.SendTemplateMessageUtil;
import io.renren.modules.eir.until.SiteUtil;
import io.renren.modules.eir.until.StatusUtil;
import io.renren.modules.miniapp.entity.*;
import io.renren.modules.miniapp.service.*;
import io.renren.modules.miniapp.until.MessageTemplateUtils;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysConfigService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("eir/customer")
public class EirCustomerController {

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

    @Autowired
    MessageTemplateUtils messageTemplateUtils;

    @Autowired
    private SysConfigService sysConfigService;

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 意见建议列表
     * @param params
     * @return
     */
    @RequestMapping("/suggestList")
    public R getSuggestList(@RequestParam Map<String, Object> params){
        PageUtils page = suggestService.queryPage(params);
        for (Object o : page.getList()) {
            Suggest suggest = (Suggest)o;
            suggest.setStatus(StatusUtil.transition(suggest.getStatus()));
        }
        return R.ok().put("page", page);
    }

    /**
     * 堆场机械问题列表
     * @param params
     * @return
     */
    @RequestMapping("/yardPlanList")
    public R getYardPlanList(@RequestParam Map<String, Object> params){
        PageUtils page = yardPlanService.queryPage(params);
        for (Object o : page.getList()) {
            YardPlan yard = (YardPlan)o;
            if("other".equals(yard.getIssueType())){
                yard.setIssueType(yard.getIssue());
            }else{
                yard.setIssueType(IssueTypeUtil.transition(yard.getIssueType()));
            }
            yard.setSite(SiteUtil.transition(yard.getSite()));
            yard.setStatus(StatusUtil.transition(yard.getStatus()));
        }
        return R.ok().put("page", page);
    }

    /**
     * 操作堆场机械问题
     * @param progressing
     * @param id
     * @return
     */
    @RequestMapping("/showInfo")
    public R getInfoById(@RequestParam String id, String progressing){
        String operator = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
        YardPlan yard = yardPlanService.selectOne(new EntityWrapper<YardPlan>().like("id",id));
        if ("Y".equals(yard.getProgressing())){
            if ( !operator.equals(yard.getOperator())){
                return R.error(yard.getOperator()+"正在处理该机械问题");
            }
        }
        if("other".equals(yard.getIssueType())){
            yard.setIssueType(yard.getIssue());
        }else{
            yard.setIssueType(IssueTypeUtil.transition(yard.getIssueType()));
        }
        yard.setSite(SiteUtil.transition(yard.getSite()));
        yard.setStatus(StatusUtil.transition(yard.getStatus()));
        if("Y".equals(progressing)){
            //锁定同一个openid的待处理和挂起数据
            List<YardPlan> list = yardPlanService.selectList(new EntityWrapper<YardPlan>().eq("open_id",yard.getOpenId()).in("status",new Object[]{"0","2"}));
            for (YardPlan y: list) {
                y.setOperator(operator);
                y.setProgressing(progressing);
                if(!yardPlanService.updateById(y)){//锁定此数据，防止其他人同时操作
                    return R.error("锁定数据出错");
                }
            }
        }
        return R.ok().put("yard",yard);
    }

    /**
     * 查看用户的建议
     * @param id
     * @param progressing
     * @return
     */
    @RequestMapping("/showSuggestInfo")
    public R getSuggestInfoById(@RequestParam String id, String progressing){
        String operator = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
        Suggest suggest = suggestService.selectOne(new EntityWrapper<Suggest>().like("id",id));
        if ("Y".equals(suggest.getProgressing())){
            if ( !operator.equals(suggest.getOperator())){
                return R.error(suggest.getOperator()+"正在处理");
            }
        }
        suggest.setStatus(StatusUtil.transition(suggest.getStatus()));
        if("Y".equals(progressing)){
            //锁定同一个openid的待处理和挂起数据
            List<Suggest> list = suggestService.selectList(new EntityWrapper<Suggest>().eq("open_id",suggest.getOpenId()).in("status",new Object[]{"0"}));
            for (Suggest s: list) {
                s.setOperator(operator);
                s.setProgressing(progressing);
                if(!suggestService.updateById(s)){//锁定此数据，防止其他人同时操作
                    return R.error("锁定数据出错");
                }
            }
        }
        return R.ok().put("suggest",suggest);
    }

    /**
     * 解除锁定状态
     * @param id
     * @param type 分为suggest和yard两种类型
     * @return
     */
    @RequestMapping("/unLock")
    public R unLock(String id, String type){
        if("yard".equals(type)){
            YardPlan yard = yardPlanService.selectOne(new EntityWrapper<YardPlan>().eq("id",id));
            List<YardPlan> list = yardPlanService.selectList(new EntityWrapper<YardPlan>().eq("open_id",yard.getOpenId()).in("status",new Object[]{"0","2"}));
            if(list.size() == 0) return R.ok();
            for (YardPlan y: list) {
                y.setProgressing("N");
            }
            if(!yardPlanService.updateBatchById(list)) return R.error("解锁程序异常");
            return R.ok();
        }else if("suggest".equals(type)){
            Suggest suggest = suggestService.selectOne(new EntityWrapper<Suggest>().eq("id",id));
            List<Suggest> list = suggestService.selectList(new EntityWrapper<Suggest>().eq("open_id",suggest.getOpenId()).in("status",new Object[]{"0"}));
            if(list.size() == 0) return R.ok();
            for (Suggest s: list) {
                s.setProgressing("N");
            }
            if(!suggestService.updateBatchById(list)) return R.error("解锁程序异常");
            return R.ok();
        }
        return R.error();
    }

    /**
     * 回复、挂起
     * @param id
     * @param remark
     * @param status
     * @return
     */
    @RequestMapping("/handle")
    public R handleSomething(String id,String remark,String status,String type){
        if("yard".equals(type)){
            YardPlan yard = new YardPlan();
            yard.setId(id);
            yard.setRemark(remark);
            yard.setStatus(status);
            yard.setHandleTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            //解锁
            unLock(id,"yard");
            if(yardPlanService.updateById(yard)){
                /**推送**/
                YardPlan yardMessage = yardPlanService.selectOne(new EntityWrapper<>(yard));
                //设置堆场机械问题
                if("other".equals(yardMessage.getIssueType())){
                    yardMessage.setIssueType(yardMessage.getIssue());
                }else{
                    yardMessage.setIssueType(IssueTypeUtil.transition(yardMessage.getIssueType()));
                }

                if(status.equals("2")) { //挂起
                    yardMessage.setRemark("码头已为您升级处理，稍后会推出最新处理结果，请耐心等候！");
                }

                //发送模板消息
                Map map = SendTemplateMessageUtil.sendWxMsg(yardMessage,sysConfigService.getValue("yardPlan_template_id"));
                String result = messageTemplateUtils.sendMessageTemplate(map);
                log.info("推送结果： " + result);

                //保存历史记录
                if(saveHistoryInfo(yardPlanService.selectOne(new EntityWrapper<YardPlan>().eq("id",yard.getId()))))
                    return R.ok();
            }
            return R.error("系统错误");
        } else if("suggest".equals(type)){
            Suggest suggest = new Suggest();
            suggest.setId(id);
            suggest.setRemark(remark);
            suggest.setStatus(status);
            suggest.setHandleTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            //解锁
            unLock(id,"suggest");
            if(suggestService.updateById(suggest)){
               Suggest suggestMessage =  suggestService.selectOne(new EntityWrapper<>(suggest));
                if(status.equals("1")) {
                    //发送模板消息
                    Map map = SendTemplateMessageUtil.sendWxMsg(suggestMessage,sysConfigService.getValue("suggest_template_id"));
                    String result = messageTemplateUtils.sendMessageTemplate(map);
                    log.info("推送结果： " + result);
                }
                if(saveSuggestHistoryInfo(suggestService.selectOne(new EntityWrapper<Suggest>().eq("id",suggest.getId()))))
                    return R.ok();
            }
            return R.error("系统错误");
        }
        return R.error();
    }


    /**
     * 查看历史记录
     * @param params
     * @return
     */
    @RequestMapping("/showHistoryInfo")
    public R getHistoryInfoById(@RequestParam Map<String, Object> params){
        PageUtils page = historyYardPlanService.queryPage(params);

        for (Object o : page.getList()) {
            HistoryYardPlan y = (HistoryYardPlan) o;
            if("other".equals(y.getIssueType())){
                y.setIssueType(y.getIssue());
            }else{
                y.setIssueType(IssueTypeUtil.transition(y.getIssueType()));
            }
            y.setSite(SiteUtil.transition(y.getSite()));
            y.setStatus(StatusUtil.transition(y.getStatus()));
        }

        return R.ok().put("page", page);
    }

    /**
     * 查看历史记录
     * @param params
     * @return
     */
    @RequestMapping("/showSuggestHistoryInfo")
    public R getSuggestHistoryInfoById(@RequestParam Map<String, Object> params){
        PageUtils page = historySuggestService.queryPage(params);
        for (Object o : page.getList()) {
            HistorySuggest s = (HistorySuggest) o;
            s.setStatus(StatusUtil.transition(s.getStatus()));
        }

        return R.ok().put("page", page);
    }

    /**
     * 操作通知列表
     * @param params
     * @return
     */
    @RequestMapping("/operNoticeList")
    public R getOperNoticeList(@RequestParam Map<String, Object> params){

        return R.ok().put("page", operNoticeService.queryPage(params));
    }

    /**
     * 新增操作通知
     * @param notice
     * @return
     */
    @RequestMapping("/addNotice")
    public R addNotice(@RequestBody OperNotice notice){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //补全notice信息
        notice.setAuthor(((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername());
        notice.setEditor(((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername());
        notice.setCreateTime(sdf.format(new Date()));
        notice.setUpdateTime(sdf.format(new Date()));
        if(operNoticeService.insert(notice)) return R.ok();
        return R.error();
    }

    /**
     * 更新操作通知页面展示数据
     * @param id
     * @return
     */
    @RequestMapping("/modifyNotice")
    public R modifyNotice(String id){
        OperNotice notice = operNoticeService.selectOne(new EntityWrapper<OperNotice>().eq("id",id));
        if(notice != null) return R.ok().put("notice",notice);
        return R.error("系统数据异常");
    }

    /**
     * 更新操作通知
     * @param notice
     * @return
     */
    @RequestMapping("/updateNotice")
    public R updateNotice(@RequestBody OperNotice notice){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        notice.setEditor(((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername());
        notice.setUpdateTime(sdf.format(new Date()));
        if(operNoticeService.updateById(notice)) return R.ok();
        return R.error();
    }

    /**
     * 获取堆场问题回复模板
     * @return
     */
    @RequestMapping("/getTemplate")
    public R getTemplate(){
        String data = sysConfigService.getValue("template_yardPlan_text");

        return R.ok(data);
    }

    /**
     * 保存历史记录 --yard
     * @param yardPlan
     * @return
     */
    public boolean saveHistoryInfo(YardPlan yardPlan){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String userName = null;
        try {
            HistoryYardPlan historyYardPlan = new HistoryYardPlan();
            //将yardPlan复制到historyYardPlan
            BeanUtils.copyProperties(historyYardPlan, yardPlan);
            historyYardPlan.setYardPlanId(yardPlan.getId());
            historyYardPlan.setUpdateTime(format.format(new Date()));

            ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getRoleIdList();
            userName = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
            if(userName == null){
                historyYardPlan.setOperator("司机");
            }else{
                historyYardPlan.setOperator(userName);
            }
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
        String userName = null;
        try {
            HistorySuggest historySuggest = new HistorySuggest();
            //将suggest复制到historySuggest
            BeanUtils.copyProperties(historySuggest, suggest);
            historySuggest.setSuggestId(suggest.getId());
            historySuggest.setUpdateTime(format.format(new Date()));

            ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getRoleIdList();
            userName = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
            if(userName == null){
                historySuggest.setOperator("司机");
            }else{
                historySuggest.setOperator(userName);
            }
            return historySuggestService.insert(historySuggest);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }

}
