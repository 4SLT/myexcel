package com.fslt.myexcel.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @description: SupplierModel
 * @date: 2020/3/15 18:13
 * @author: zongxiong.lin
 * @version: 1.0
 */
@Data
public class SupplierModel extends BaseRowModel {

    @ExcelProperty(value = "主键id", index = 0)
    private Integer id;

    @ExcelProperty(value = "供应商编号", index = 1)
    private String supplierNo;

    @ExcelProperty(value = "供应商名称", index = 2)
    private String supplierName;

    @ExcelProperty(value = "认证状态", index = 3)
    private String authenticationStatus;

    @ExcelProperty(value = "供应商类型", index = 4)
    private String supplierType;

    @ExcelProperty(value = "提供产品", index = 5)
    private String productName;

    @ExcelProperty(value = "供货类型", index = 6)
    private String supplyType;

    @ExcelProperty(value = "一般纳税人", index = 7)
    private String taxpayerType;

    @ExcelProperty(value = "事业单位", index = 8)
    private String unitType;

    @ExcelProperty(value = "统一社会信用代码", index = 9)
    private String taxpayerNum;

    @ExcelProperty(value = "营业执照号", index = 10)
    private String businessLicenseNo;

    @ExcelProperty(value = "板块使用权限", index = 11)
    private String dataAuthority;

    @ExcelProperty(value = "联系人", index = 12)
    private String contactName;

    @ExcelProperty(value = "联系人电话", index = 13)
    private String contactMobile;

    @ExcelProperty(value = "创建人姓名", index = 14)
    private String createUser;

    @ExcelProperty(value = "创建人工号", index = 15)
    private String createId;

    @ExcelProperty(value = "创建时间", index = 16)
    private String createTime;


}
