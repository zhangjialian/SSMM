package com.zjl.manager;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.IOException;

/**
 * Created by zjf on 2017/11/15.
 */
public interface ExcelWriteManager {

    /**
     * �����û����������Excel�ļ�
     * @param title
     * @return
     * @throws IOException
     * @throws Exception
     */
    SXSSFWorkbook downloadUserExcel(String title) throws IOException, Exception;

}
