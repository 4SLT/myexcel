package com.fslt.myexcel.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.fslt.myexcel.listener.ExcelListener;
import com.fslt.myexcel.pojo.SupplierModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: HelloController
 * @date: 2020/3/15 15:02
 * @author: zongxiong.lin
 * @version: 1.0
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "hello";
    }

    @RequestMapping("/testExcel")
    @ResponseBody
    public List<List<Object>> testExcel() {

        String filePath = "C:\\Users\\lin\\Desktop\\5.8重复供应商\\重复供应商（B）.xlsx";
        Sheet sheet = new Sheet(1, 0);
        List<List<Object>> resultList = new ArrayList<>();
        try {
            InputStream in = new FileInputStream(new File(filePath));
            List<Object> result = EasyExcelFactory.read(in, sheet);
            resultList.add(result);
            System.out.println(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @RequestMapping("/importExcel")
    @ResponseBody
    public String importExcel() throws Exception {
        String filePath = "C:\\Users\\lin\\Desktop\\5.8重复供应商\\重复供应商（B）.xlsx";
        InputStream in = new FileInputStream(new File(filePath));
        ExcelListener listener = new ExcelListener();
        ExcelReader excelReader = new ExcelReader(in, ExcelTypeEnum.XLSX, null, listener);
        //读取信息
        excelReader.read(new Sheet(1, 1, SupplierModel.class));

        //获取数据
        List<Object> list = listener.getDatas();
        List<SupplierModel> supplierModels = new ArrayList<>();
        SupplierModel supplierModel = new SupplierModel();

        for (int i = 0; i < list.size(); ++i) {
            supplierModel = (SupplierModel) list.get(i);
            supplierModels.add(supplierModel);
        }
        this.analysisGroup(supplierModels);
        return "导入完成";
    }

    private List<SupplierModel> analysisGroup(List<SupplierModel> list) {
        List<SupplierModel> result = new ArrayList<>();
        List<SupplierModel> unDo = new ArrayList<>();
        for (int i = 0; i < list.size() - 1; ++i) {
            unDo.add(list.get(i));
            if (list.get(i).getSupplierName().equals(list.get(i + 1).getSupplierName())) {
                continue;
            } else {
                //调用方法
                result.addAll(this.analysis(unDo));
                //清空undo
                unDo = new ArrayList<>();
            }
        }
        this.exportExcel(result);
        return result;
    }

    private List<SupplierModel> analysis(List<SupplierModel> list) {
        List<SupplierModel> result = new ArrayList<>();
        for (SupplierModel model : list) {
            if (model.getTaxpayerType().equals("是") || model.getUnitType().equals("是")) {
                return list;
            }
        }
        return new ArrayList<SupplierModel>();
    }

    private void exportExcel(List<SupplierModel> list) {
        ExcelWriter writer = null;
        try {
            OutputStream outputStream = new FileOutputStream("C:\\Users\\lin\\Desktop\\5.8重复供应商\\导出供应商.xlsx");
            writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX, true);
            Sheet sheet = new Sheet(1, 0, SupplierModel.class);
            sheet.setSheetName("目录");

            writer.write(list, sheet);
            writer.finish();
            outputStream.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
