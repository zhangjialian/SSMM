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
     * ������ȡ�ϴ��ļ����û���Ϣ
     * @param inputStream
     * @return
     * @throws Exception
     */
    @Override
    public List<UserBO> batchReadUser(InputStream inputStream) throws Exception {
        //��Ŷ�ȡ���ļ�¼
        List<UserBO> userBOList = new ArrayList<>();

        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);// ���ַ�ʽ
        } catch (Exception e) {
            logger.error("@batchReadUser����Excel�ļ�����, �������{}, �쳣��Ϣ{}", inputStream, e);
            throw new ErrorCodeException(ErrorCodeEnum.FI01);
        }

        if (null == workbook) {
            return new ArrayList<>();
        }

        // Ĭ�ϻ�ȡ��һ��������
        Sheet sheet = workbook.getSheetAt(0);
        int rowCount = sheet.getPhysicalNumberOfRows();
        Row row = null;

        // ������ʼ�У���������(��0��)
        int startRowNo = 1;
        for (int i = startRowNo; i < rowCount; i++) {
            row = (Row) sheet.getRow(i);

            String name = this.getCellValue(row.getCell(0), null);
            String username = this.getCellValue(row.getCell(1), null);
            String email = this.getCellValue(row.getCell(2), null);

            //�����ǰ��ȫ��Ϊ�գ��������¶�ȡ����
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
            case Cell.CELL_TYPE_STRING: // �ı�
                cellValue = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    try {
                        cellValue = DateUtils.chanageToDayString(cell.getDateCellValue());
                    } catch (ParseException e) {
                        logger.error("����ת�������͵�Ԫ�����,Ĭ��Ϊ��,@getCellValue,�������{},�쳣��Ϣ", cell.getDateCellValue(), e);
                        cellValue = "";
                    } // ������
                } else {
                    cellValue = df.format(cell.getNumericCellValue()); // ����
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN: // ������
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_BLANK: // �հ�
                cellValue = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_ERROR: // ����
                cellValue = "����";
                break;
            case Cell.CELL_TYPE_FORMULA: // ��ʽ
                cellValue = "����";
                break;
            default:
                cellValue = "����";
        }
        return cellValue.trim();
    }

}
