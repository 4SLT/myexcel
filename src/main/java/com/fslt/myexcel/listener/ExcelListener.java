package com.fslt.myexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: ExcelListener
 * @date: 2020/3/15 16:44
 * @author: zongxiong.lin
 * @version: 1.0
 */
public class ExcelListener extends AnalysisEventListener {

    public List<Object> datas = new ArrayList<>();


    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        datas.add(o);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    public List<Object> getDatas() {
        return datas;
    }

    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }
}
