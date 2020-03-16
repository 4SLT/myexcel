package com.fslt.myexcel.service;

import com.fslt.myexcel.model.SupplierModel;

import java.util.List;

/**
 * @description: SupplierService
 * @date: 2020/3/16 11:23
 * @author: 林宗雄
 * @version: 1.0
 */
public interface SupplierService {

    /**
     * 根据供应商名称进行分组
     *
     * @param list 注意：list必须为按照供应商名称排好序
     * @return
     */
    List<List<SupplierModel>> groupSupplier(List<SupplierModel> list);


    /**
     * 按组分析数据，将一般纳税人和事业单位都为否/null的组筛除
     *
     * @param list
     * @return
     */
    List<SupplierModel> analysisGroup(List<SupplierModel> list);
}
