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
     * 防止多人同事下载包裹列表导致内存溢出
     */
    private Boolean inUse = false;

    /**
     * 导出用户批量导入的Excel文件
     * @param title
     * @return
     * @throws IOException
     * @throws Exception
     */
    @Override
    public SXSSFWorkbook downloadUserExcel(String title) throws IOException, Exception {
        String[] headers = {"姓名(*)", "用户名(*)", "邮箱(*)"};
        return this.downloadExcelModelCommon(title, headers);
    }

    /**
     * 导出Excel通用模板
     * @param title 标题
     * @param headers 列名
     * @return
     * @throws IOException
     * @throws Exception
     */
    private SXSSFWorkbook downloadExcelModelCommon(String title, String[] headers) throws IOException, Exception {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        // 每个单元格的大小

        if(headers == null || headers.length == 0){
            return workbook;
        }

        int listSize[] = new int[headers.length];
        for(int i = 0; i < listSize.length; i++){
            //设置默认列宽
            listSize[i] = DEFAULT_CELL_SIZE;
        }

        SXSSFSheet sheet = TradeCenterExcelHelp.getXSSFSheet(workbook, listSize, title);

        // 生成标题样式
        CellStyle titleStyle = TradeCenterExcelHelp.getCellStyle(workbook, TITLE, false);
        // 生成标题字体
        Font titleFont = TradeCenterExcelHelp.getXSSFFont(workbook, 0, 0, 0);
        // 把字体应用到当前的样式
        titleStyle.setFont(titleFont);

        // 生成正文样式
        CellStyle contextStyle = TradeCenterExcelHelp.getCellStyle(workbook, CONTEXT, false);
        // 生成正文字体
        Font contextFont = TradeCenterExcelHelp.getXSSFFont(workbook, 0, 0, XSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        contextStyle.setFont(contextFont);

        // 生成介绍行样式
        CellStyle introduceRowStyle = TradeCenterExcelHelp.getCellStyle(workbook, TITLE, false);
        // 生成标题字体
        Font introduceRowFont = TradeCenterExcelHelp.getXSSFFont(workbook, 0, 0, 0);
        // 把字体应用到当前的样式
        introduceRowStyle.setFont(introduceRowFont);
        // 填充背景色
        introduceRowStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        // 自动换行
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
