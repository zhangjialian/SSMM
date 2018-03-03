package com.zjl.common.util;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

public class TradeCenterExcelHelp {
	public static SXSSFSheet getXSSFSheet(SXSSFWorkbook workbook, int listSize[], String title) {
		SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet(title);
		for (int i = 0; i < listSize.length; i++) {
			sheet.setColumnWidth(i, 300 * listSize[i]);
		}
		return sheet;
	}

	public static CellStyle getCellStyle(SXSSFWorkbook workbook, int type, boolean isNum) {
		// 生成一个样式
		CellStyle style = workbook.createCellStyle();
		// 设置样式
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		style.setLeftBorderColor(HSSFColor.GREY_25_PERCENT.index);
		style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.GREY_25_PERCENT.index);
		style.setBorderRight(XSSFCellStyle.BORDER_THIN);

		if (0 == type) {
			style.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		} else {
			if (isNum) {
				style.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
			} else {
				style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			}
			style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
			style.setBottomBorderColor(HSSFColor.GREY_25_PERCENT.index);
		}

		return style;
	}

	public static Font getXSSFFont(SXSSFWorkbook workbook, int color, int size, int type) {
		Font font = workbook.createFont();
		if (0 == color) {
			font.setColor(HSSFColor.BLACK.index);
		} else {
			font.setColor((short) color);
		}
		if (0 == size) {
			font.setFontHeightInPoints((short) 11);
		} else {
			font.setFontHeightInPoints((short) size);
		}
		if (0 == type) {
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		} else {
			font.setBoldweight((short) type);
		}
		return font;
	}
}
