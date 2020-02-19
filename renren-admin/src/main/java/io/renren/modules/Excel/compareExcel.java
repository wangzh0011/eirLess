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
public class compareExcel {


    public List<StaffEntity> loadStaff() {
        List<StaffEntity> list = FileUtil.importExcel("C:\\Users\\timmy.deng.DCB1\\Desktop\\新账号\\active_staff.xlsx",
                0, 1, StaffEntity.class);
        return list;

    }

    public void handleExcel() {


        List<CustomEntity> newentities = FileUtil.importExcel("C:\\Users\\timmy.deng.DCB1\\Desktop\\excel1\\dcb1.xlsx",
                0, 1, CustomEntity.class);
        List<CustomEntity> oldentities = FileUtil.importExcel("C:\\Users\\timmy.deng.DCB1\\Desktop\\excel1\\ECN4.xlsx", 0, 1, CustomEntity.class);

        System.out.println("newentities共:" + newentities.size() + "条数据");
        System.out.println("oldentities.size() = " + oldentities.size());
        List<CustomEntity> list = new ArrayList<>();

        //比较新老excel
        for (CustomEntity newentity : newentities) {

            // System.out.println("newentity.getECN4_USER_ID() = " + newentity.getECN4_USER_ID());


            boolean flag = false;

            for (CustomEntity oldentity : oldentities) {

                if (newentity.getECN4_USER_ID().equals(oldentity.getECN4_USER_ID()) && newentity.getFACILITY_ID().equals(oldentity.getFACILITY_ID())) {
                    newentity.setDepartment(oldentity.getDepartment());
                    flag = true;
                }


            }

            if (flag == false) {
                //未找到与去年一样的账号
                list.add(newentity);
            }
        }


        for (CustomEntity entity : list) {


            System.out.println("账号:" + entity.getECN4_USER_ID() + "内外贸:" + entity.getFACILITY_ID() + "用户名:" + entity.getECN4_USER_NAME());
        }

        System.out.println("异常数据共:" + list.size() + "条.");

        //导出到文件

        try {
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("2019年新增账号", "ECN4"), CustomEntity.class, list);

            File outFile = new File("C:\\Users\\timmy.deng.DCB1\\Desktop\\excel1\\2019-ECN4新增账号.xls");
            outFile.createNewFile();

            FileOutputStream outputStream = new FileOutputStream(outFile);

            workbook.write(outputStream);

            outputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("2019年ECN4账号", "ECN4"), CustomEntity.class, newentities);

            File outFile = new File("C:\\Users\\timmy.deng.DCB1\\Desktop\\excel1\\2018-ECN4账号带部门.xls");
            outFile.createNewFile();

            FileOutputStream outputStream = new FileOutputStream(outFile);

            workbook.write(outputStream);

            outputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void  handleDepartment(){
        List<CustomEntity> target =  FileUtil.importExcel("C:\\Users\\timmy.deng.DCB1\\Desktop\\新账号\\2019-ECN4新增账号.xls",
                1, 1, CustomEntity.class);

        List<StaffEntity> staffEntities = loadStaff();


        for (CustomEntity entity : target) {

            for (StaffEntity staffEntity : staffEntities) {

                if (entity.getECN4_USER_ID().equals(staffEntity.getNo())){
                    System.out.println(entity.getECN4_USER_ID());
                    entity.setDepartment(staffEntity.getDepartment());
                }
            }

        }


        try {
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("2019年新增账号添加部门", "ECN4"), CustomEntity.class, target);

            File outFile = new File("C:\\Users\\timmy.deng.DCB1\\Desktop\\新账号\\2019-ECN4新增账号.xls");
            outFile.createNewFile();

            FileOutputStream outputStream = new FileOutputStream(outFile);

            workbook.write(outputStream);

            outputStream.close();
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }




    public void  XpsHandleDepartment(){
        List<XpsEntity> target =  FileUtil.importExcel("C:\\Users\\timmy.deng.DCB1\\Desktop\\新账号\\2019-XPS新增账号.xls",
                1, 1, XpsEntity.class);

        List<StaffEntity> staffEntities = loadStaff();


        for (XpsEntity entity : target) {

            for (StaffEntity staffEntity : staffEntities) {

                if (entity.getUSER_ID().equals(staffEntity.getNo())){
                    System.out.println(entity.getUSER_ID());
                    entity.setDepartment(staffEntity.getDepartment());
                }
            }

        }


        try {
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("2019年新增账号添加部门", "ECN4"), XpsEntity.class, target);

            File outFile = new File("C:\\Users\\timmy.deng.DCB1\\Desktop\\新账号\\2019-XPS新增账号.xls");
            outFile.createNewFile();

            FileOutputStream outputStream = new FileOutputStream(outFile);

            workbook.write(outputStream);

            outputStream.close();
            System.out.println();


        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    public void  N4bHandleDepartment(){
        List<N4bEntity> target =  FileUtil.importExcel("C:\\Users\\timmy.deng.DCB1\\Desktop\\新账号\\2018-N4b新增账号.xls",
                1, 1, N4bEntity.class);

        List<StaffEntity> staffEntities = loadStaff();


        for (N4bEntity entity : target) {

            for (StaffEntity staffEntity : staffEntities) {

                if (entity.getUSER_ID().equals(staffEntity.getNo())){
                    System.out.println(entity.getUSER_ID());
                    entity.setDepartment(staffEntity.getDepartment());
                }
            }

        }


        try {
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("2018-N4b新增账号", "N4B"), N4bEntity.class, target);

            File outFile = new File("C:\\Users\\timmy.deng.DCB1\\Desktop\\新账号\\2018-N4b新增账号.xls");
            outFile.createNewFile();

            FileOutputStream outputStream = new FileOutputStream(outFile);

            workbook.write(outputStream);

            outputStream.close();

            System.out.println();


        } catch (IOException e) {
            e.printStackTrace();
        }



    }


    public void  N4THandleDepartment(){
        List<N4bEntity> target =  FileUtil.importExcel("C:\\Users\\timmy.deng.DCB1\\Desktop\\新账号\\2018-N4T新增账号.xls",
                1, 1, N4bEntity.class);

        List<StaffEntity> staffEntities = loadStaff();


        for (N4bEntity entity : target) {

            for (StaffEntity staffEntity : staffEntities) {

                if (entity.getUSER_ID().replace("d","").equals(staffEntity.getNo())){
                    System.out.println(entity.getUSER_ID()+" ");
                    entity.setDepartment(staffEntity.getDepartment());
                }
            }

        }


        try {
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("2018-N4T新增账号", "N4T"), N4bEntity.class, target);

            File outFile = new File("C:\\Users\\timmy.deng.DCB1\\Desktop\\新账号\\2018-N4T新增账号.xls");
            outFile.createNewFile();

            FileOutputStream outputStream = new FileOutputStream(outFile);

            workbook.write(outputStream);

            outputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public void deleteAccount(){
        List<N4bEntity> target =  FileUtil.importExcel("C:\\Users\\timmy.deng.DCB1\\Desktop\\excel1\\2018-N4T账号带部门.xls",
                1, 1, N4bEntity.class);

        List<AccountEntity> account =  FileUtil.importExcel("C:\\Users\\timmy.deng.DCB1\\Desktop\\新账号\\no_active.xlsx",
                0, 1, AccountEntity.class);

        System.out.println("原始数据:"+target.size());
        System.out.println("account:"+account.size());
     /*   for (AccountEntity entity : account) {
            System.out.println(entity.getUid());
        }*/

        List<N4bEntity> newEntities = new ArrayList<>();

        for (int i = 0; i < target.size(); i++) {
            boolean flag=false;
            N4bEntity n4bEntity = target.get(i);
            for (AccountEntity accountEntity : account) {
                if (n4bEntity.getUSER_ID().equals(accountEntity.getUid())){
                    //target.remove(n4bEntity);
                    System.out.println(n4bEntity.getUSER_ID());
                    flag=true;
                }
            }

            if (!flag){
                newEntities.add(n4bEntity);
            }

        }

        System.out.println("加工后数据:"+newEntities.size());

        try {
            System.out.println();
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("2018-N4T新增账号", "N4T"), N4bEntity.class, newEntities);

            File outFile = new File("C:\\Users\\timmy.deng.DCB1\\Desktop\\excel1\\2018-N4T账号带部门.xls");
            outFile.createNewFile();

            FileOutputStream outputStream = new FileOutputStream(outFile);

            workbook.write(outputStream);

            outputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void N4tCombine(){
        List<N4bEntity> target =  FileUtil.importExcel("C:\\Users\\timmy.deng.DCB1\\Desktop\\excel1\\2018-N4T账号带部门.xls",
                1, 1, N4bEntity.class);

        List<N4bEntity> data =  FileUtil.importExcel("C:\\Users\\timmy.deng.DCB1\\Desktop\\aaaa\\2018-N4T新增账号.xls",
                1, 1, N4bEntity.class);

        System.out.println("原始数据:"+target.size());
        System.out.println("data:"+data.size());

        int i=0;
        for (N4bEntity n4bEntity : target) {
            for (N4bEntity datum : data) {
                if (n4bEntity.getUSER_ID().equals(datum.getUSER_ID()) && n4bEntity.getDepartment()==null){
                    i=+1;
                    System.out.println("第"+i+"数据:"+datum.getUSER_ID());
                    n4bEntity.setDepartment(datum.getDepartment());
                }
            }
        }




        System.out.println("加工后数据:"+target.size());

        try {
            System.out.println();
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("2018-N4T新增账号", "N4T"), N4bEntity.class, target);
            System.out.println();
            File outFile = new File("C:\\Users\\timmy.deng.DCB1\\Desktop\\excel1\\2018-N4T账号带部门.xls");
            outFile.createNewFile();

            FileOutputStream outputStream = new FileOutputStream(outFile);

            workbook.write(outputStream);

            outputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void ECN4Combine(){
        List<CustomEntity> target =  FileUtil.importExcel("C:\\Users\\timmy.deng.DCB1\\Desktop\\excel1\\2018-ECN4账号带部门.xls",
                1, 1, CustomEntity.class);

        List<CustomEntity> data =  FileUtil.importExcel("C:\\Users\\timmy.deng.DCB1\\Desktop\\aaaa\\2019-ECN4新增账号.xls",
                1, 1, CustomEntity.class);

        System.out.println("原始数据:"+target.size());
        System.out.println("data: "+data.size());

        int i=0;
        for (CustomEntity n4bEntity : target) {
            for (CustomEntity datum : data) {
                if (n4bEntity.getDepartment()==null && n4bEntity.getECN4_USER_ID().equals(datum.getECN4_USER_ID()) &&
                        n4bEntity.getFACILITY_ID().equals(datum.getFACILITY_ID()) ){
                    i+=1;
                    System.out.println("第"+i+"数据:"+datum.getECN4_USER_ID());
                    n4bEntity.setDepartment(datum.getDepartment());
                }
            }
        }




        System.out.println("加工后数据:"+target.size());

        try {
            System.out.println();
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("2018-N4T新增账号", "N4T"), CustomEntity.class, target);
            System.out.println();
            File outFile = new File("C:\\Users\\timmy.deng.DCB1\\Desktop\\excel1\\2018-ECN4账号带部门.xls");
            outFile.createNewFile();

            FileOutputStream outputStream = new FileOutputStream(outFile);

            workbook.write(outputStream);

            outputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void XpsCombine(){
        List<XpsEntity> target =  FileUtil.importExcel("C:\\Users\\timmy.deng.DCB1\\Desktop\\excel1\\2018-XPS账号带部门.xls",
                1, 1, XpsEntity.class);

        List<XpsEntity> data =  FileUtil.importExcel("C:\\Users\\timmy.deng.DCB1\\Desktop\\aaaa\\2019-XPS新增账号.xls",
                1, 1, XpsEntity.class);

        System.out.println("原始数据:"+target.size());
        System.out.println("data:"+data.size());

        int i=0;
        for (XpsEntity n4bEntity : target) {
            for (XpsEntity datum : data) {
                if (n4bEntity.getDepartment()==null && n4bEntity.getUSER_ID().equals(datum.getUSER_ID()) ){
                    i+=1;
                    System.out.println("第"+i+"数据:"+datum.getUSER_ID());
                    n4bEntity.setDepartment(datum.getDepartment());
                }
            }
        }




        System.out.println("加工后数据:"+target.size());

        try {
            System.out.println();
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("2018-XPS账号带部门.xls", "N4T"), XpsEntity.class, target);
            System.out.println();
            File outFile = new File("C:\\Users\\timmy.deng.DCB1\\Desktop\\excel1\\2018-XPS账号带部门.xls");
            outFile.createNewFile();

            FileOutputStream outputStream = new FileOutputStream(outFile);

            workbook.write(outputStream);

            outputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }




    public static void main(String[] args) {
        compareExcel excel = new compareExcel();
        excel.XpsCombine();
        // excel.ECN4Combine();
        // excel.N4tCombine();
        //excel.N4THandleDepartment();
       // excel.deleteAccount();
        //excel.N4bHandleDepartment();
        //excel.XpsHandleDepartment();
        //excel.handleDepartment();
        //excel.handleExcel();
    }
}
