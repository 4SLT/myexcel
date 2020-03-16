package com.fslt.myexcel.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.fslt.myexcel.listener.ExcelListener;
import com.fslt.myexcel.model.SupplierModel;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @description: ExcelUtil
 * @date: 2020/3/16 9:41
 * @author: 林宗雄
 * @version: 1.0
 */
@Slf4j
public class ExcelUtil {

    /**
     * 解析Excel数据
     *
     * @param filePath 待导入文件路径
     * @param head     Excel对应实体类
     * @param listener
     * @return
     */
    public static List<SupplierModel> readExcel(String filePath, Class head, ExcelListener listener) {
        log.info("文件：【{}】开始解析", filePath);
        ExcelReader excelReader = EasyExcel.read(filePath, head, listener).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        //读取信息
        excelReader.read(readSheet);
        //读取excel时会创建临时文件，必须关闭
        excelReader.finish();
        return listener.getDatas();
    }

    /**
     * @param filePath 待导出文件路径
     * @param list     导出内容
     * @param head     导出Excel对应实体类
     */
    public static void writeExcel(String filePath, List list, Class head) {
        log.info("文件：【{}】开始导出", filePath);
        ExcelWriter excelWriter = EasyExcel.write(filePath, head).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("sheet1").build();
        excelWriter.write(list, writeSheet);
        // 别忘记finish
        excelWriter.finish();
    }

}
