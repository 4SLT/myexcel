package com.fslt.myexcel.service.impl;

import com.fslt.myexcel.model.SupplierModel;
import com.fslt.myexcel.service.SupplierService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: SupplierServiceImpl
 * @date: 2020/3/16 11:23
 * @author: 林宗雄
 * @version: 1.0
 */
@Service
public class SupplierServiceImpl implements SupplierService {

    @Override
    public List<List<SupplierModel>> groupSupplier(List<SupplierModel> list) {
        List<List<SupplierModel>> resultList = new ArrayList<>();
        List<SupplierModel> temp = new ArrayList<>();
        for (int i = 0; i < list.size(); ++i) {
            temp.add(list.get(i));
            if (i + 1 < list.size()) {
                //防止数组越界
                if (list.get(i).getSupplierName().equals(list.get(i + 1).getSupplierName())) {
                    continue;
                } else {
                    resultList.add(temp);
                    temp = new ArrayList<>();
                }
            } else {
                if (list.get(i).getSupplierName().equals(list.get(i - 1).getSupplierName())) {
                    resultList.add(temp);
                    temp = new ArrayList<>();
                } else {
                    continue;
                }
            }
        }
        return resultList;
    }

    @Override
    public List<SupplierModel> analysisGroup(List<SupplierModel> list) {
        for (SupplierModel model : list) {
            if (model.getTaxpayerType().equals("是") || model.getUnitType().equals("是")) {
                return list;
            }
        }
        return new ArrayList<SupplierModel>();
    }


}
