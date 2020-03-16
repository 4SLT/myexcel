package com.fslt.myexcel.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
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

}
