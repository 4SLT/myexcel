package com.fslt.myexcel.controller;

import com.fslt.myexcel.listener.ExcelListener;
import com.fslt.myexcel.model.SupplierModel;
import com.fslt.myexcel.service.SupplierService;
import com.fslt.myexcel.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: SupplierController
 * @date: 2020/3/16 11:32
 * @author: 林宗雄
 * @version: 1.0
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        String inputFilePath = "C:\\Users\\Administrator\\Desktop\\5.8重复供应商\\重复供应商（A）.xlsx";
        String outputFilePath = "C:\\Users\\Administrator\\Desktop\\5.8重复供应商\\重复供应商（B）.xlsx";
        List<SupplierModel> list = ExcelUtil.readExcel(inputFilePath, SupplierModel.class, new ExcelListener());
        List<List<SupplierModel>> groups = supplierService.groupSupplier(list);
        List<SupplierModel> resultList = new ArrayList<>();
        for (int i = 0; i < groups.size(); ++i) {
            resultList.addAll(supplierService.analysisGroup(groups.get(i)));
        }
        ExcelUtil.writeExcel(outputFilePath, resultList, SupplierModel.class);
        return "ok,重复供应商（B）已导出";
    }

}
