package io.renren.modules.miniapp.controller;

import io.renren.datasources.DataSourceNames;
import io.renren.datasources.annotation.DataSource;
import io.renren.modules.miniapp.entity.cosido.CosIdoProcess;
import io.renren.modules.miniapp.service.CosIdoProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/miniApp")
public class CosIdoProcessController {

    @Autowired
    private CosIdoProcessService cosIdoProcessService;

    /**
     * 根据提单号查询进口重箱
     * @param docref
     * @return
     */
    @RequestMapping("/cosido")
    @DataSource(name = DataSourceNames.THIRD)
    public CosIdoProcess getCosIdoProcess(String docref) {
        CosIdoProcess cos = cosIdoProcessService.selectCosIdo(docref);
        return cos;
    }

}
