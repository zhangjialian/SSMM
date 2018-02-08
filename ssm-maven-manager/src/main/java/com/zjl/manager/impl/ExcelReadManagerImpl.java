package com.zjl.manager.impl;

import com.zjl.common.exception.ErrorCodeException;
import com.zjl.common.systemEnum.ErrorCodeEnum;
import com.zjl.common.user.UserBO;
import com.zjl.common.util.DateUtils;
import com.zjl.manager.ExcelReadManager;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjf on 2017/11/15.
 */
@Service
public class ExcelReadManagerImpl implements ExcelReadManager {

    Logger logger = LoggerFactory.getLogger(ExcelReadManagerImpl.class);

    /**
     * 批量读取上传文件的用户信息
     * @param inputStream
     * @return
     * @throws Exception
     */
    @Override
    public List<UserBO> batchReadUser(InputStream inputStream) throws Exception {
        //存放读取到的记录
        List<UserBO> userBOList = new ArrayList<>();

        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);// 这种方式
        } catch (Exception e) {
            logger.error("@batchReadUser解析Excel文件出错, 传入参数{}, 异常信息{}", inputStream, e);
            throw new ErrorCodeException(ErrorCodeEnum.FI01);
        }

        if (null == workbook) {
            return new ArrayList<>();
        }

        // 默认获取第一个工作表
        Sheet sheet = workbook.getSheetAt(0);
        int rowCount = sheet.getPhysicalNumberOfRows();
        Row row = null;

        // 数据起始行，跳过标题(第0行)
        int startRowNo = 1;
        for (int i = startRowNo; i < rowCount; i++) {
            row = (Row) sheet.getRow(i);

            String name = this.getCellValue(row.getCell(0), null);
            String username = this.getCellValue(row.getCell(1), null);
            String email = this.getCellValue(row.getCell(2), null);

            //如果当前行全部为空，则不再往下读取数据
            if(StringUtils.isEmpty(username) && StringUtils.isEmpty(name) && StringUtils.isEmpty(email)){
                break;
            }
            UserBO userBO = new UserBO();
            userBO.setName(name);
            userBO.setUsername(username);
            userBO.setEmail(email);
            userBO.setRowNo(i);
            userBO.setValid(true);
            userBOList.add(userBO);
        }
        return userBOList;
    }

    private String getCellValue(Cell cell, String pattern) {
        if (null == cell) {
            return "";
        }
        DecimalFormat df;
        if (StringUtils.isEmpty(pattern)) {
            df = new DecimalFormat("#0.##");
        } else {
            df = new DecimalFormat(pattern);
        }
        String cellValue = null;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING: // 文本
                cellValue = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    try {
                        cellValue = DateUtils.chanageToDayString(cell.getDateCellValue());
                    } catch (ParseException e) {
                        logger.error("解析转换日期型单元格出错,默认为空,@getCellValue,传入参数{},异常信息", cell.getDateCellValue(), e);
                        cellValue = "";
                    } // 日期型
                } else {
                    cellValue = df.format(cell.getNumericCellValue()); // 数字
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN: // 布尔型
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_BLANK: // 空白
                cellValue = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_ERROR: // 错误
                cellValue = "错误";
                break;
            case Cell.CELL_TYPE_FORMULA: // 公式
                cellValue = "错误";
                break;
            default:
                cellValue = "错误";
        }
        return cellValue.trim();
    }

}
