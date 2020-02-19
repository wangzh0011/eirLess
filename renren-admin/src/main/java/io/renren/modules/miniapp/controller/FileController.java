package io.renren.modules.miniapp.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.modules.miniapp.entity.BargeImage;
import io.renren.modules.miniapp.entity.Result;
import io.renren.modules.miniapp.service.BargeImageService;
import io.renren.modules.miniapp.until.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by timmy.deng on 2018/8/23.
 */
@RestController
@RequestMapping("/miniApp")
public class FileController {
    protected Logger log = LoggerFactory.getLogger(getClass());
    private ResultUtils resultUtils = new ResultUtils();

    @Value("${img.location}")
    private String location;

    @Autowired
    private BargeImageService bargeImageService;

    /**
     * 飞单图片上传
     * @param image
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveImage",method = RequestMethod.POST)
    public Object imageUpload(
            @RequestParam("image") MultipartFile image) {

        return uploadImage(image,location);

    }


    /**
     * 自主报到装船缴费清单上传
     * @param image
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveImageOfVessel",method = RequestMethod.POST)
    public Object imageUploadOfVessel(
            @RequestParam("image") MultipartFile image,String iVoyCd,String oVoyCd,String vesselName) {

        //上传并返回图片路径
        Result result = (Result) uploadImage(image,location + "vessel/");
        //解析图片路径
        JSON jsonData = (JSON) result.getData();
        Map map = JSON.parseObject(jsonData.toJSONString());
        String fileName = (String) map.get("filename");

        List<BargeImage> list = bargeImageService.selectList(new EntityWrapper<BargeImage>().eq("i_voy_cd",iVoyCd).eq("o_voy_cd",oVoyCd));
        BargeImage bargeImage = new BargeImage();
        if(list.size() == 0) {
            bargeImage.setiVoyCd(iVoyCd);
            bargeImage.setoVoyCd(oVoyCd);
            bargeImage.setVesselName(vesselName);
            bargeImage.setImagePath(fileName);
            bargeImage.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            bargeImageService.insert(bargeImage);
        } else {
            bargeImage = list.get(0);
            bargeImage.setImagePath(bargeImage.getImagePath() + "," + fileName);
            bargeImageService.updateById(bargeImage);
        }
        return result;
    }

    /**
     * 上传图片
     * @return
     */
    public Object uploadImage(MultipartFile image,String location) {
        if (image.isEmpty()){
            return resultUtils.Error(-1,"上传的文件为空");
        }
        //System.out.println(openid);
        // 获取文件名
        String fileName = image.getOriginalFilename();
        log.info("上传的文件名为：" + fileName);
        String newfilename = getNewFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        log.info("上传的后缀名为：" + suffixName);

        //日期文件夹
        Date date = new Date();
        String path = new SimpleDateFormat("yyyy/MM/dd/").format(date);

        // 文件上传后的路径
        String filePath = location+path;

        //如果不存在,创建文件夹
        File f = new File(filePath);
        if(!f.exists()){
            f.mkdirs();
        }

        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + newfilename+suffixName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        System.out.println(dest.getParentFile().canExecute());
        log.info("上传成功后的文件路径未：" + filePath + fileName);

        try {
            image.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map map = new HashMap();
        map.put("filename",path+newfilename+suffixName);

        return  resultUtils.Success(JSON.toJSON(map));

    }


    public synchronized String  getNewFilename(){
        SimpleDateFormat fmt = new SimpleDateFormat("HHmmssSSS");
        java.util.Date dt = new java.util.Date(System.currentTimeMillis());
        return fmt.format(dt);
    }
}
