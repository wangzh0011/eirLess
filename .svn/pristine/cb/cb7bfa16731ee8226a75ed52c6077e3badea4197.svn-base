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
public class compareExcelN4T {

    public List<StaffEntity> loadStaff() {
        List<StaffEntity> list = FileUtil.importExcel("C:\\Users\\timmy.deng.DCB1\\Desktop\\新账号\\active_staff.xlsx",
                0, 1, StaffEntity.class);
        return list;

    }

    public static void main(String[] args) {

        compareExcelN4T excelN4T = new compareExcelN4T();



       List<N4bEntity> newentities =  FileUtil.importExcel("C:\\Users\\timmy.deng.DCB1\\Desktop\\excel1\\N4-2018.xlsx",
               0,1,N4bEntity.class);
       List<N4bEntity> oldentities =  FileUtil.importExcel("C:\\Users\\timmy.deng.DCB1\\Desktop\\excel1\\N4-2017.xlsx",0,1,N4bEntity.class);

       System.out.println("newentities共:"+newentities.size()+"条数据");
       System.out.println("oldentities.size() = " + oldentities.size());
       List<N4bEntity> list = new ArrayList<>();


       List<StaffEntity> staffEntities = excelN4T.loadStaff();

       //比较新老excel
        for (N4bEntity newentity : newentities) {

           // System.out.println("newentity.getECN4_USER_ID() = " + newentity.getECN4_USER_ID());

            System.out.println();
            boolean flag=false;

            //先在部门员工表找
            for (StaffEntity staffEntity : staffEntities) {

                if (newentity.getUSER_ID().equals(staffEntity.getNo())){
                    newentity.setDepartment(staffEntity.getDepartment());
                    flag=true;
                    break;
                }
            }
           if (!flag) {  // 员工表未找到部门,在去年的表找
               for (N4bEntity oldentity : oldentities) {


                   if (newentity.getUSER_ID().equals(oldentity.getUSER_ID())
                           //&&
                       //newentity.getROLE_NAME().equals(oldentity.getROLE_NAME())
                           ) {
                       newentity.setDepartment(oldentity.getDepartment());

                       flag = true;
                       break;
                   }

               }
           }
           newentity.setUSER_NAME(newentity.getUSER_LAST_NAME()+" "+newentity.getUSER_FIRST_NAME());

            if (flag==false){ //未找到与去年一样的账号

                list.add(newentity);

            }
        }


        for (N4bEntity entity : list) {


            System.out.println("账号:"+entity.getUSER_ID()+"系统名:"+entity.getROLE_NAME()+"用户名:"+entity.getUSER_LAST_NAME()+" "+entity.getUSER_FIRST_NAME());
        }

        System.out.println("异常数据共:"+list.size()+"条.");

        //导出到文件

        try {
            System.out.println();
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("2018年新增账号","N4T"),N4bEntity.class,list);

            File outFile  = new File("C:\\Users\\timmy.deng.DCB1\\Desktop\\excel1\\2018-N4T新增账号.xls");
            outFile.createNewFile();

            FileOutputStream outputStream = new FileOutputStream(outFile);

            workbook.write(outputStream);

            outputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }



        try {
            System.out.println();
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("2018年账号带部门","N4T"),N4bEntity.class,newentities);

            File outFile  = new File("C:\\Users\\timmy.deng.DCB1\\Desktop\\excel1\\2018-N4T账号带部门.xls");
            outFile.createNewFile();

            FileOutputStream outputStream = new FileOutputStream(outFile);

            workbook.write(outputStream);

            outputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
