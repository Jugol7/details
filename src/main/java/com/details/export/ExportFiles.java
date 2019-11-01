package com.details.export;

import com.details.entity.Student;
import com.details.utils.FilesUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 目的：为什么会出现乱码，在什么地方出现
 * 没有从页面取数据 而是指定excel文件
 * <p>
 * <p>
 * 官方文档：
 *
 * @author zlp
 * @RestController is a stereotype annotation that combines @ResponseBody and @Controller.
 * 意思是：
 * @RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用。
 * 如果只用@RestController，那么无法返回jsp页面。使用视图解析器InternalResourceViewResolver也不行
 * 如果需要返回指定的页面，则需要@Controller配合视图解析器InternalResourceViewResolver才行
 * 如果需要返回特定的json、xnl类型，则需要@RequestBody注解该方法
 * @date 2019-10-31 10:50
 */
@RestController
@RequestMapping(value = "export")
public class ExportFiles {

    @GetMapping(value = "export")
    public void exportFiles() {
        this.exportServer();
//        return "success";
    }

    private void exportServer() {
        //指定数据位置
        String path = "C:\\Users\\aisino\\Desktop\\订单列表_2019-10-31\\订单列表_2019-10-31_1.xls";
        //指定生产位置
        String pathTo = "C:\\Users\\aisino\\Desktop\\student";
        //文件名
        String exName = "测试数据__export";
        //表头，数据list，文件名，地址
        String[] headName = {"name", "like"};
        List<List<String>> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        list1.add("zlp");
        list1.add("音乐");
        list2.add("jugol");
        list2.add("电影");
        list3.add("Jugol7");
        list3.add("工作");
        list.add(list1);
        list.add(list2);
        list.add(list3);
        File fileExcel = createExcel(headName, list, exName, pathTo);
        //压缩文件
    }

    private File createExcel(String[] headName, List<List<String>> list, String fileName, String path) {
        File file = new File(path+"/"+fileName+".xls");
        FilesUtil.deleteInsideFile(file);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet1 = hssfWorkbook.createSheet("sheet1");
        //表头
        HSSFRow row = sheet1.createRow(0);
        HSSFCell cell = row.createCell(0);
        //设置表头
        for (int i = 0; i < headName.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(headName[i]);
        }
        for (int j = 0; j < list.size(); j++) {
            row = sheet1.createRow(j + 1);
            List<String> stringList = list.get(j);
            for (int k = 0; k < stringList.size(); k++){
                cell = row.createCell(k);
                cell.setCellValue(stringList.get(k));
            }
        }
        FileOutputStream fileOutputStream = null;
        //如果目录文件不存在
        if(!new File(path).exists()){
            new File(path).mkdirs();
        }
        try {
            fileOutputStream = new FileOutputStream(file);
            hssfWorkbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
