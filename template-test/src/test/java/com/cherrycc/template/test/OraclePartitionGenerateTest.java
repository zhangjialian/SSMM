package com.cherrycc.template.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author BG349176
 * @date 2018/12/20 17:11
 */
public class OraclePartitionGenerateTest {

    @Test
    public void test1(){
        List<String> tableNameList = Arrays.asList(
                "GT_BALANCE_DETAIL",
                "GT_V8_FEE_RESULT",
                "GT_SITE_CARGO",
                "GT_SEARCH_LOG",
                "GT_ADDRESS_PARSE_RESULT",
                "GT_CB_ROUTE",
                "GT_ORDER_AUTO_INSURED_INFO",
                "GT_BALANCE_DETAIL_BACK",
                "GT_ACCEPT_ADDRESS_PARSE_RESULT",
                "GT_V8_FEE_RESULT_NEW_ADD",
                "GT_TRUCK_CARGO",
                "GT_BILL_DETAIL",
                "GT_PROBLEM_NEW",
                "HADOOP_GT_SCAN_FORECAST",
                "GT_SITE_CHECK_BILL",
                //"GT_ACCEPT_TIME_EFFECTIVE",
                "GT_SCAN"
        );
        for(String tableName : tableNameList){
            this.printTablePartitionByTableName(tableName, 2019);
            System.out.println("");
        }
    }

    private void printTablePartitionByTableName(String tableName, int year){
        String sql = null;
        for(int i = 1; i <= 12; i++){
            if(i != 1){
                sql = this.getSqlByParams(tableName, String.valueOf(year), String.format("%02d", i), "01");
                System.out.println(sql);
            }
            sql = this.getSqlByParams(tableName, String.valueOf(year), String.format("%02d", i), "11");
            System.out.println(sql);
            sql = this.getSqlByParams(tableName, String.valueOf(year), String.format("%02d", i), "21");
            System.out.println(sql);
        }
        sql = this.getSqlByParams(tableName, String.valueOf(year + 1), "01", "01");
        System.out.println(sql);
    }

    private String getSqlByParams(String tableName, String year, String month, String day){
        String sqlModal = "ALTER TABLE %s ADD PARTITION %s VALUES LESS THAN (TIMESTAMP' %s-%s-%s 00:00:00');";
        return String.format(sqlModal, tableName, tableName + "_" + year + month + day, year, month, day);
    }

    @Test
    public void test2(){
        String tableName = "HADOOP_GT_SCAN_FORECAST";
        String partitionName = "SCAN_FORECAST";
        this.printTablePartitionByTableName(tableName, partitionName, 2019);
        System.out.println("");
    }

    private void printTablePartitionByTableName(String tableName, String partitionName, int year){
        String sql = null;
        for(int i = 1; i <= 12; i++){
            if(i != 1){
                sql = this.getSqlByParams(tableName, partitionName, String.valueOf(year), String.format("%02d", i), "01");
                System.out.println(sql);
            }
            sql = this.getSqlByParams(tableName, partitionName, String.valueOf(year), String.format("%02d", i), "11");
            System.out.println(sql);
            sql = this.getSqlByParams(tableName, partitionName, String.valueOf(year), String.format("%02d", i), "21");
            System.out.println(sql);
        }
        sql = this.getSqlByParams(tableName, partitionName, String.valueOf(year + 1), "01", "01");
        System.out.println(sql);
    }

    private String getSqlByParams(String tableName, String partitionName, String year, String month, String day){
        String sqlModal = "ALTER TABLE %s ADD PARTITION %s VALUES LESS THAN (TIMESTAMP' %s-%s-%s 00:00:00');";
        return String.format(sqlModal, tableName, partitionName + "_P" + year.substring(year.length() - 2, year.length()) + month + day, year, month, day);
    }

}
