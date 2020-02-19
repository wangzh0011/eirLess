package io.renren.modules.Excel;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import io.renren.modules.eir.until.FileUtil;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by timmy.deng on 2018/11/30.
 */
public class compareExcelXps {

    public static void main(String[] args) {



       List<XpsEntity> newentities =  FileUtil.importExcel("C:\\Users\\timmy.deng.DCB1\\Desktop\\excel1\\xps-2018.xlsx",
               0,1,XpsEntity.class);
       List<XpsEntity> oldentities =  FileUtil.importExcel("C:\\Users\\timmy.deng.DCB1\\Desktop\\excel1\\xps-2017.xlsx",0,1,XpsEntity.class);

       System.out.println("newentities共:"+newentities.size()+"条数据");
        System.out.println("oldentities.size() = " + oldentities.size());
       List<XpsEntity> list = new ArrayList<>();

       //比较新老excel
        for (XpsEntity newentity : newentities) {

           // System.out.println("newentity.getECN4_USER_ID() = " + newentity.getECN4_USER_ID());


            boolean flag=false;

            for (XpsEntity oldentity : oldentities) {

                if (newentity.getUSER_ID().equals(oldentity.getUSER_ID())&&newentity.getFacility().equals(oldentity.getFacility())){
                    newentity.setDepartment(oldentity.getDepartment());
                    flag=true;
                }


            }
            
            if (flag==false){ //未找到与去年一样的账号

                newentity.setUSER_NAME(newentity.getUSER_LAST_NAME()+" "+newentity.getUSER_FIRST_NAME());
                list.add(newentity);
            }
        }


        for (XpsEntity entity : list) {


            System.out.println("账号:"+entity.getUSER_ID()+"内外贸:"+entity.getFacility()+"用户名:"+entity.getUSER_NAME());
        }

        System.out.println("异常数据共:"+list.size()+"条.");

        //导出到文件

        try {
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("2019年新增账号","Xps"),XpsEntity.class,list);

            File outFile  = new File("C:\\Users\\timmy.deng.DCB1\\Desktop\\excel1\\2019-Xpl新增账号.xls");
            outFile.createNewFile();

            FileOutputStream outputStream = new FileOutputStream(outFile);

            workbook.write(outputStream);

            outputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println();
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("2018年XPS账号带部门","XPS"),XpsEntity.class,newentities);

            File outFile  = new File("C:\\Users\\timmy.deng.DCB1\\Desktop\\excel1\\2018-XPS账号带部门.xls");
            outFile.createNewFile();

            FileOutputStream outputStream = new FileOutputStream(outFile);

            workbook.write(outputStream);

            outputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
