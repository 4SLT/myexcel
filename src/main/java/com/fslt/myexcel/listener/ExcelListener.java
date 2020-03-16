package com.fslt.myexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.fslt.myexcel.model.SupplierModel;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: ExcelListener
 * @date: 2020/3/15 16:44
 * @author: zongxiong.lin
 * @version: 1.0
 */
@Slf4j
public class ExcelListener extends AnalysisEventListener<SupplierModel> {

    private List<SupplierModel> datas = new ArrayList<>();

    /**
     * 每一条数据解析都会调用此方法
     *
     * @param data
     * @param analysisContext
     */
    @Override
    public void invoke(SupplierModel data, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", data);
        //如果数据量太大需要在此做相应处理，以防OOM
        datas.add(data);
    }


    /**
     * 数据解析完成后调用
     *
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("数据解析完成");
    }

    public List<SupplierModel> getDatas() {
        return datas;
    }

    public void setDatas(List<SupplierModel> datas) {
        this.datas = datas;
    }
}
