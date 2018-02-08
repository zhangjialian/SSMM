package com.zjl.manager.impl;

import com.zjl.common.util.TradeCenterExcelHelp;
import com.zjl.manager.ExcelWriteManager;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by zjf on 2017/11/15.
 */
@Service
public class ExcelWriteManagerImpl implements ExcelWriteManager {

    public final static int DEFAULT_CELL_SIZE = 13;

    public final static int TITLE = 0;

    public final static int CONTEXT = 1;

    public final static int CELL_SIZE_20 = 20;

    public final int PACKORDER_LIST_SIZE[] = { DEFAULT_CELL_SIZE, CELL_SIZE_20, CELL_SIZE_20, CELL_SIZE_20,
            CELL_SIZE_20, DEFAULT_CELL_SIZE, DEFAULT_CELL_SIZE, };

    /**
     * ��ֹ����ͬ�����ذ����б����ڴ����
     */
    private Boolean inUse = false;

    /**
     * �����û����������Excel�ļ�
     * @param title
     * @return
     * @throws IOException
     * @throws Exception
     */
    @Override
    public SXSSFWorkbook downloadUserExcel(String title) throws IOException, Exception {
        String[] headers = {"����(*)", "�û���(*)", "����(*)"};
        return this.downloadExcelModelCommon(title, headers);
    }

    /**
     * ����Excelͨ��ģ��
     * @param title ����
     * @param headers ����
     * @return
     * @throws IOException
     * @throws Exception
     */
    private SXSSFWorkbook downloadExcelModelCommon(String title, String[] headers) throws IOException, Exception {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        // ÿ����Ԫ��Ĵ�С

        if(headers == null || headers.length == 0){
            return workbook;
        }

        int listSize[] = new int[headers.length];
        for(int i = 0; i < listSize.length; i++){
            //����Ĭ���п�
            listSize[i] = DEFAULT_CELL_SIZE;
        }

        SXSSFSheet sheet = TradeCenterExcelHelp.getXSSFSheet(workbook, listSize, title);

        // ���ɱ�����ʽ
        CellStyle titleStyle = TradeCenterExcelHelp.getCellStyle(workbook, TITLE, false);
        // ���ɱ�������
        Font titleFont = TradeCenterExcelHelp.getXSSFFont(workbook, 0, 0, 0);
        // ������Ӧ�õ���ǰ����ʽ
        titleStyle.setFont(titleFont);

        // ����������ʽ
        CellStyle contextStyle = TradeCenterExcelHelp.getCellStyle(workbook, CONTEXT, false);
        // ������������
        Font contextFont = TradeCenterExcelHelp.getXSSFFont(workbook, 0, 0, XSSFFont.BOLDWEIGHT_NORMAL);
        // ������Ӧ�õ���ǰ����ʽ
        contextStyle.setFont(contextFont);

        // ���ɽ�������ʽ
        CellStyle introduceRowStyle = TradeCenterExcelHelp.getCellStyle(workbook, TITLE, false);
        // ���ɱ�������
        Font introduceRowFont = TradeCenterExcelHelp.getXSSFFont(workbook, 0, 0, 0);
        // ������Ӧ�õ���ǰ����ʽ
        introduceRowStyle.setFont(introduceRowFont);
        // ��䱳��ɫ
        introduceRowStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        // �Զ�����
        introduceRowStyle.setWrapText(true);
        introduceRowStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);
        introduceRowStyle.setBorderBottom(XSSFCellStyle.BORDER_NONE);

        int rowNum = 0;
		/*createIntroduceRow(sheet, rowNum, introduceRowStyle);
		rowNum += 1;*/
        SXSSFRow row = (SXSSFRow) sheet.createRow(rowNum);
        for (int i = 0; i < listSize.length; i++) {
            SXSSFCell cell = (SXSSFCell) row.createCell(i);
            cell.setCellStyle(titleStyle);
            cell.setCellValue(headers[i]);
        }


        return workbook;

    }
}
