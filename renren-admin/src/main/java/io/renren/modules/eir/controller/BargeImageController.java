package io.renren.modules.eir.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.miniapp.entity.BargeImage;
import io.renren.modules.miniapp.service.BargeImageService;
import io.renren.modules.sys.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("eir/bargeImage")
public class BargeImageController {

    @Autowired
    private BargeImageService bargeImageService;

    @Autowired
    private SysConfigService sysConfigService;

    @RequestMapping("/getBargeMessage")
    public R getBargeMessage(@RequestParam Map<String, Object> params){
        String uploadUrl = sysConfigService.getValue("uploadUrl");
        PageUtils page = bargeImageService.queryPage(params);
        List<BargeImage> list = (List<BargeImage>) page.getList();
        for (BargeImage barge : list) {
            String[] path = barge.getImagePath().split(",");
            //重新组装图片路径
            StringBuffer paths = new StringBuffer("");
            for (int i = 0; i < path.length; i++) {
                if(i == path.length-1) {
                    barge.setImagePath(paths + uploadUrl + "vessel/" + path[i]);
                } else {
                    paths = paths.append(uploadUrl + "vessel/" + path[i] + ",");
                }
            }
        }
        return R.ok().put("page",page);
    }

}
