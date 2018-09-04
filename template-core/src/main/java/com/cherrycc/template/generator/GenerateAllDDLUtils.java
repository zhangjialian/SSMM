package com.cherrycc.template.generator;

import javax.persistence.*;
import java.io.*;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenerateAllDDLUtils {

    private static final String VO_SUFFIX = "VO";

    private static final String SO_SUFFIX = "Query";

    private static final String PACK_VO_PREFIX = "Pack";

    private static final String PACK_VO_SUFFIX = "VO";

    private static final String MAPPER_SUFFIX = "Mapper";

    private static final String TEST_CLASS_SUFFIX = "ServiceTest";

    private static String PO_CLASS_PATH;

    private static String VO_CLASS_PATH;

    private static String DAO_CLASS_PATH;

    private static String SERVICE_INTERFACE_CLASS_PATH;

    private static String SERVICE_IMPL_CLASS_PATH;

    private static String EJB_CLASS_PATH;

    private final static String DIR_SPLIT = System.getProperty("file.separator");

    private final static String DEFAULT_OUTPUT_DIR = "D:" + DIR_SPLIT + "DDLFiles";

    private static String OUTPUT_DIR;

    private static final String DAO_INTERFACE_SUFFIX = "DAO";

    private static final String DAO_IMPL_SUFFIX = "DAOImpl";

    private static final String SERVICE_INTERFACE_SUFFIX = "Service";

    private static final String SERVICE_IMPL_SUFFIX = "ServiceImpl";

    private static final String WS_PREFIX = "WS";

    private static final String WS_INTERFACE_SUFFIX = "Service";

    private static final String WS_IMPL_SUFFIX = "ServiceBean";

    private static String PO_CLASS_SIMPLE_NAME;

    private static String VO_CLASS_SIMPLE_NAME;

    private static String PACK_VO_CLASS_SIMPLE_NAME;

    private static String SO_CLASS_SIMPLE_NAME;

    private static String DAO_INTERFACE_CLASS_NAME;

    private static String DAO_IMPL_CLASS_NAME;

    private static String SERVICE_INTERFACE_CLASS_NAME;

    private static String SERVICE_IMPL_CLASS_NAME;

    private static String WS_INTERFACE_CLASS_NAME;

    private static String WS_IMPL_CLASS_NAME;

    private static String BLANK_FOR_METHOD = "    ";

    private static String BLANK_FOR_METHOD_BODY = "        ";

    private static String BLANK_FOR_PARAM = " ";

    private static final String CREATE_METHOD_NAME_PREFIX = "create";

    private static final String DELETE_METHOD_NAME_PREFIX = "delete";

    private static final String GET_METHOD_NAME_PREFIX = "get";

    private static final String SEARCH_METHOD_NAME_PREFIX = "search";

    private static final String UPDATE_METHOD_NAME_PREFIX = "update";

    private static final String JAVA_FILE_SUFFIX = ".java";

    private static final String SQL_SUFFIX = ".sql";

    private static final String XML_SUFFIX = ".xml";

    private static final String TEST_ANNOTATION = "@Test";

    private static final String REMARK_UPDATED_STR = "remark was updated";

    private static final String LINE_SPLIT = System.getProperty("line.separator");

    private static final String COMMA = " , ";

    public static void main(String[] args) throws Exception {

        inputAllParam();

        Class clazz = Class.forName(PO_CLASS_PATH);

        initAllComponentNames(clazz);

        printAllComponent(clazz);

    }

    private static void inputAllParam() throws IOException, ClassNotFoundException {

        PO_CLASS_PATH = getInputPath("请输入PO类的全路径包含类名:").trim();

        Class clazz = Class.forName(PO_CLASS_PATH);

        OUTPUT_DIR = getInputPath("请输入输出文件路径:").trim();

        if (OUTPUT_DIR == null || OUTPUT_DIR.isEmpty()) {
            OUTPUT_DIR = DEFAULT_OUTPUT_DIR;
        }

        OUTPUT_DIR += "/" + clazz.getSimpleName();

        File outPutDir = new File(OUTPUT_DIR);
        if (!outPutDir.exists()) {
            boolean createDirSuccess = outPutDir.mkdirs();
            if (createDirSuccess) {
                System.out.println("创建目录成功: " + outPutDir);
            } else {
                System.out.println("创建目录失败: " + outPutDir);
            }
        }
        System.out.println();
    }

    private static void initAllComponentNames(Class clazz) {
        String className = clazz.getSimpleName();

        PO_CLASS_SIMPLE_NAME = className;

        VO_CLASS_SIMPLE_NAME = PO_CLASS_SIMPLE_NAME + VO_SUFFIX;

        SO_CLASS_SIMPLE_NAME = PO_CLASS_SIMPLE_NAME + SO_SUFFIX;

        PACK_VO_CLASS_SIMPLE_NAME = PACK_VO_PREFIX + PO_CLASS_SIMPLE_NAME + PACK_VO_SUFFIX;

        DAO_INTERFACE_CLASS_NAME = PO_CLASS_SIMPLE_NAME + DAO_INTERFACE_SUFFIX;

        SERVICE_INTERFACE_CLASS_NAME = PO_CLASS_SIMPLE_NAME + SERVICE_INTERFACE_SUFFIX;

        WS_INTERFACE_CLASS_NAME = WS_PREFIX + PO_CLASS_SIMPLE_NAME + WS_INTERFACE_SUFFIX;

        DAO_IMPL_CLASS_NAME = PO_CLASS_SIMPLE_NAME + DAO_IMPL_SUFFIX;

        SERVICE_IMPL_CLASS_NAME = PO_CLASS_SIMPLE_NAME + SERVICE_IMPL_SUFFIX;

        WS_IMPL_CLASS_NAME = WS_PREFIX + PO_CLASS_SIMPLE_NAME + WS_IMPL_SUFFIX;
    }

    private static void outputTheStringToFile(String outputDir, String fileName, String content) {
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            String outputPath = outputDir + DIR_SPLIT + fileName;
            fw = new FileWriter(new File(outputPath));
            bw = new BufferedWriter(fw);
            bw.write(content);
            System.out.println("====successFull write the file:" + fileName + " to path:" + outputPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static String getInputPath(String info) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(info);
        StringBuilder buffer = new StringBuilder();

        int c;
        while ((c = br.read()) != '\n') {
            buffer.append((char) c);
        }
        return buffer.toString();
    }

    static class Model {
        public String column;

        public String prop;

        public String types;
    }

    @SuppressWarnings("unchecked")
    private static String getTypesByField(Field field) {
        String res = "VARCHAR";
        Class type = field.getType();
        if (type == String.class) {
            res = "VARCHAR";
        } else if (type == Integer.class || type == Long.class || type == int.class || type == long.class || type == Double.class
                || type == double.class || type == Float.class || type == float.class) {
            res = "NUMERIC";
        } else if (type == Date.class) {
            res = "TIMESTAMP";
        } else if (type == Boolean.class || type == boolean.class) {
            res = "BIT";
        }
        return res;
    }

    @SuppressWarnings("unchecked")
    private static List<Model> getColumnsByType(Class clazz) {
        List<Model> ret = new ArrayList<Model>();
        for (Field field : clazz.getDeclaredFields()) {
            if ("serialVersionUID".equalsIgnoreCase(field.getName())) {
                continue;
            }
            Model model = new Model();
            if (field.isAnnotationPresent(Column.class)) {
                model.column = field.getAnnotation(Column.class).name().toLowerCase();
            } else {
                model.column = field.getName();
            }
            model.prop = field.getName();
            model.types = getTypesByField(field);
            ret.add(model);
        }

        String[] strs = { "domain_id", "domainId", "NUMERIC", "created_time", "createdTime", "TIMESTAMP", "updated_time",
                "updatedTime", "TIMESTAMP", "creator_id", "creatorId", "NUMERIC", "updator_id", "updatorId", "NUMERIC",
                "lock_version", "lockVersion", "NUMERIC", "creator_name", "creatorName", "VARCHAR", "updator_name", "updatorName",
                "VARCHAR", "domain_name", "domainName", "VARCHAR", "re_mark", "reMark", "VARCHAR" };
        for (int k = 0; k < strs.length; k = k + 3) {
            Model model = new Model();
            model.column = strs[k];
            model.prop = strs[k + 1];
            model.types = strs[k + 2];
            ret.add(model);
        }
        return ret;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @SuppressWarnings("unchecked")
    private static String printMapper(Class clazz) {
        String tableName = JPAHelper.getTableName(clazz);
        List<Model> list = getColumnsByType(clazz);
        String seqName = JPAHelper.getSequenceName(clazz);

        String typeName = clazz.getSimpleName();
        // typeAlias
        System.out.println("<typeAlias alias=\"" + typeName + "\" type=\"" + clazz.getName() + "\"/>");
        System.out.println();

        // typeAlias for Vo
        // System.out.println("<typeAlias alias=\"" + typeName + VO_SUFFIX +
        // "\" type=\"" + VO_CLASS_PATH + "\"/>");
        // System.out.println();

        String indentation1 = "    ";
        String indentation2 = "        ";
        String indentation3 = "            ";
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + LINE_SPLIT);
        sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" " + LINE_SPLIT);
        sb.append("\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">" + LINE_SPLIT);
        sb.append("<mapper namespace=\"" + typeName + "\">" + LINE_SPLIT);
        sb.append("" + LINE_SPLIT);
        // insert
        sb.append(indentation1 + "<insert id=\"insert\" parameterType=\"" + typeName + "\">" + LINE_SPLIT);
        sb.append(indentation2 + "<selectKey keyProperty=\"id\" resultType=\"long\" order=\"BEFORE\">" + LINE_SPLIT);
        sb.append(indentation3 + "select " + seqName + ".nextval from dual" + LINE_SPLIT);
        sb.append(indentation2 + "</selectKey>" + LINE_SPLIT);

        sb.append(indentation2 + "insert into " + tableName + "(\n");
        for (int k = 0; k < list.size(); k++) {
            Model model = list.get(k);
            if (k == list.size() - 1) {
                sb.append(indentation2 + "<!-- " + k + "-->" + model.column + LINE_SPLIT);
            } else {
                sb.append(indentation2 + "<!-- " + k + "-->" + model.column + ",\n");
            }
        }
        sb.append(indentation2 + ")\n");
        sb.append(indentation2 + "values(\n");
        for (int k = 0; k < list.size(); k++) {
            Model model = list.get(k);
            if (k == list.size() - 1) {
                sb.append(indentation2 + "<!-- " + k + "-->#{" + model.prop + ":" + model.types + "}\n");
            } else {
                sb.append(indentation2 + "<!-- " + k + "-->#{" + model.prop + ":" + model.types + "},\n");
            }
        }
        sb.append(indentation2 + ")\n");
        sb.append(indentation1 + "</insert>" + LINE_SPLIT);
        sb.append("" + LINE_SPLIT);

        // update
        sb.append(indentation1 + "<update id=\"update\" parameterType=\"" + typeName + "\">" + LINE_SPLIT);
        sb.append(indentation2 + "update " + tableName + " set\n");
        for (int k = 0; k < list.size(); k++) {
            Model model = list.get(k);
            if (!model.column.equals("lock_version") && !model.column.equals("id")) {
                sb.append(indentation3 + model.column + "=#{" + model.prop + ":" + model.types + "},\n");
            }
        }
        sb.append(indentation3 + "LOCK_VERSION = LOCK_VERSION+1\n");
        sb.append(indentation3 + "where ID=#{id} and LOCK_VERSION=#{lockVersion} \n");
        sb.append(indentation1 + "</update>" + LINE_SPLIT);
        sb.append("" + LINE_SPLIT);

        // updatePOSelective
        printUpdatePOSelective(sb, tableName, typeName, list);

        // selectByPrimaryKey
        sb.append(indentation1 + "<select id=\"selectByPrimaryKey\" parameterType=\"long\" resultType=\"" + typeName + "\">"
                + LINE_SPLIT);
        sb.append(indentation2 + "select * from " + tableName + " where id=#{id}" + LINE_SPLIT);
        sb.append(indentation1 + "</select>" + LINE_SPLIT);
        sb.append("" + LINE_SPLIT);

        // searchBySo
        /*
         * sb.append(indentation1+"<select id=\"searchBySo\" resultType=\""+typeName
         * +"\">" + LINE_SPLIT); sb.append(indentation2+"select t.* from "+ tableName
         * + " t\n");
         * sb.append(indentation2+"<include refid=\"SO_Where_Clause\" />" +
         * LINE_SPLIT); sb.append(indentation1+"</select>" + LINE_SPLIT); sb.append("" +
         * LINE_SPLIT);
         */

        // searchVosBySo
        sb.append(indentation1 + "<select id=\"searchVosBySo\" resultType=\"" + typeName + "Vo" + "\">" + LINE_SPLIT);
        sb.append(indentation2 + "select t.* from " + tableName + " t\n");
        sb.append(indentation2 + "<include refid=\"SO_Where_Clause\" />" + LINE_SPLIT);
        sb.append(indentation1 + "</select>" + LINE_SPLIT);
        sb.append("" + LINE_SPLIT);

        // searchCountBySo
        sb.append(indentation1 + "<select id=\"searchCountBySo\" resultType=\"long\">" + LINE_SPLIT);
        sb.append(indentation2 + "select count(t.id) from " + tableName + " t\n");
        sb.append(indentation2 + "<include refid=\"SO_Where_Clause\" />" + LINE_SPLIT);
        sb.append(indentation1 + "</select>" + LINE_SPLIT);
        sb.append("" + LINE_SPLIT);

        // SO_Where_Clause
        sb.append(indentation1 + "<sql id=\"SO_Where_Clause\">" + LINE_SPLIT);
        sb.append(indentation2 + "<where>" + LINE_SPLIT);
        sb.append("" + LINE_SPLIT);
        sb.append(indentation2 + "</where>" + LINE_SPLIT);
        sb.append(indentation2 + "<include refid=\"Base.Order_By_Clause\" />" + LINE_SPLIT);
        sb.append(indentation1 + "</sql>" + LINE_SPLIT);

        // deleteByPrimaryKey
        sb.append(indentation1 + "<delete id=\"deleteByPrimaryKey\" parameterType=\"long\">" + LINE_SPLIT);
        sb.append(indentation2 + "delete from " + tableName + " where id=#{id}" + LINE_SPLIT);
        sb.append(indentation1 + "</delete>" + LINE_SPLIT);

        // updateStatus
        sb.append(indentation1 + "<update id=\"updateStatus\">" + LINE_SPLIT);
        sb.append(indentation2 + "update " + tableName + " t set t.status='DISABLE' where id=#{id}" + LINE_SPLIT);
        sb.append(indentation1 + "</update>" + LINE_SPLIT);

        sb.append("</mapper>" + LINE_SPLIT);
        System.out.println(sb.toString());
        return sb.toString();
    }

    private static void printUpdatePOSelective(StringBuilder stringBuilder, String tableName, String typeName,
            List<Model> colList) {
        stringBuilder
                .append(MessageFormat.format("    <update id=\"updatePOSelective\" parameterType=\"{0}\">", typeName))
                .append(LINE_SPLIT);
        stringBuilder.append("        update ").append(tableName).append(LINE_SPLIT);
        stringBuilder.append("        <set>").append(LINE_SPLIT);

        for (int index = 0, size = colList.size(); index < size; index++) {
            Model model = colList.get(index);

            if (!model.column.equals("lock_version") && !model.column.equals("id")) {
                stringBuilder.append("            ").append(MessageFormat.format("<if test=\"{0} != null \">", model.prop))
                        .append(LINE_SPLIT);
                stringBuilder.append("                ").append(model.column).append("=#{").append(model.prop).append(":")
                        .append(model.types).append("}").append(COMMA)
                        .append(LINE_SPLIT);
                stringBuilder.append("            ").append("</if>").append(LINE_SPLIT);
            }
        }

        stringBuilder.append("LOCK_VERSION = LOCK_VERSION+1\n");
        stringBuilder.append("</set>").append(LINE_SPLIT);
        stringBuilder.append("where ID=#{id} and LOCK_VERSION=#{lockVersion} \n");

        stringBuilder.append("</update>").append(LINE_SPLIT);
        stringBuilder.append("").append(LINE_SPLIT);

    }

    @SuppressWarnings("unchecked")
    private static String table(Class clazz) {
        StringBuilder sql = new StringBuilder();
        if (clazz.isAnnotationPresent(Table.class)) {
            // 该class存在Table类型的注解，获取指定的表名
            Table table = (Table) clazz.getAnnotation(Table.class);
            String tableName = table.name();
            sql.append("--create table " + tableName + LINE_SPLIT);
            sql.append("create table " + tableName + " (");
        }
        Field[] fArr = clazz.getDeclaredFields();
        List<String> columnList = getColumns(fArr);
        // 拼接解析后的成员变量信息成创建表语句
        for (int i = 0; i < columnList.size(); i++) {
            if (i == (columnList.size() - 1)) {
                sql.append(LINE_SPLIT + columnList.get(i) + ")");
            } else {
                sql.append(LINE_SPLIT + columnList.get(i) + ",");
            }
        }
        sql.append(";");
        sql.append(LINE_SPLIT);
        System.out.println(sql.toString());
        return sql.toString();
    }

    /**
     * 用来解析所有成员变量的方法
     */
    @SuppressWarnings("unchecked")
    public static List<String> getColumns(Field[] fArr) {
        List<String> result = new ArrayList<String>();
        String columnName = "";
        String columnLength = "";
        String columnType = "";
        for (int i = 0; i < fArr.length; i++) {
            Field f = fArr[i];
            columnName = f.getName();
            if ("serialVersionUID".equalsIgnoreCase(columnName)) {
                continue;
            }
            boolean haveLength = true;
            if (f.isAnnotationPresent(Id.class)) {
                // columnName = f.getName();
                columnLength = "19";
                String str = columnName + " number" + "(" + columnLength + ")";
                result.add(str);
            } else {
                if (f.isAnnotationPresent(Column.class)) {
                    columnName = f.getAnnotation(Column.class).name();
                }/*
                  * else{ columnName = f.getName(); }
                  */
                Class type = f.getType();
                if (Integer.class == type || Long.class == type) {
                    columnLength = "19";
                    columnType = "number";
                } else if (Float.class == type || Double.class == type) {
                    columnLength = "19,4";
                    columnType = "number";
                } else if (Boolean.class == type) {
                    columnLength = "1";
                    columnType = "number";
                } else if (Date.class == type) {
                    if (f.isAnnotationPresent(Temporal.class)) {
                        if ("TIMESTAMP".equalsIgnoreCase(f.getAnnotation(Temporal.class).value().toString())) {
                            columnLength = "6";
                            columnType = "timestamp";
                        } else if ("DATE".equalsIgnoreCase(f.getAnnotation(Temporal.class).value().toString())) {
                            columnType = "DATE";
                            haveLength = false;
                        }
                    } else {
                        columnLength = "6";
                        columnType = "timestamp";
                    }

                } else {
                    columnLength = "255";
                    columnType = "varchar2";
                }
                String strColumnLength = "";
                if (haveLength) {
                    strColumnLength = " ( " + columnLength + " ) ";
                }
                String str = columnName + " " + columnType + strColumnLength;
                result.add(str);
            }
        }
        result.add("DOMAIN_ID NUMBER(19) ");
        result.add("CREATED_TIME TIMESTAMP(6) ");
        result.add("UPDATED_TIME TIMESTAMP(6) ");
        result.add("CREATOR_ID NUMBER(19) ");
        result.add("UPDATOR_ID NUMBER(19) ");
        result.add("LOCK_VERSION NUMBER(19) default 0 ");
        result.add("CREATOR_NAME VARCHAR2(255 CHAR) ");
        result.add("UPDATOR_NAME VARCHAR2(255 CHAR) ");
        result.add("DOMAIN_NAME VARCHAR2(255 CHAR) ");
        result.add("RE_MARK VARCHAR2(1024)");
        return result;
    }

    @SuppressWarnings("unchecked")
    private static String seq(Class clazz) {
        StringBuilder sequence = new StringBuilder();
        if (clazz.isAnnotationPresent(SequenceGenerator.class)) {
            // 该class存在Table类型的注解，获取指定的表名
            SequenceGenerator seq = (SequenceGenerator) clazz.getAnnotation(SequenceGenerator.class);
            String seqName = seq.name();
            sequence.append("--create sequence " + seqName + LINE_SPLIT);
            sequence.append("create sequence " + seqName);
            sequence.append(" minvalue 1 ");
            sequence.append(" maxvalue 999999999999999999999999999 ");
            sequence.append(" start with 60001 ");
            sequence.append(" increment by 1 ");
            sequence.append(" cache 20; ");
            sequence.append(LINE_SPLIT);
        }
        System.out.println(sequence.toString());
        return sequence.toString();
    }

    @SuppressWarnings("unchecked")
    private static String primaryKey(Class clazz) {
        StringBuilder key = new StringBuilder();

        if (clazz.isAnnotationPresent(Table.class)) {
            // 该class存在Table类型的注解，获取指定的表名
            Table table = (Table) clazz.getAnnotation(Table.class);
            String tableName = table.name();
            key.append("--create key " + key + LINE_SPLIT);
            key.append("alter table " + tableName + " add primary key (ID);");
            key.append(LINE_SPLIT);
        }
        System.out.println(key.toString());
        return key.toString();
    }

    private static void printAllComponent(Class clazz) {

        //table
        //primaryKey
        //seq
        String sqlContent = getSqlContent(clazz);

        outputTheStringToFile(OUTPUT_DIR, PO_CLASS_SIMPLE_NAME + SQL_SUFFIX, sqlContent);

        // 生成mapper文件
        String mapperContent = printMapper(clazz);

        outputTheStringToFile(OUTPUT_DIR, PO_CLASS_SIMPLE_NAME + MAPPER_SUFFIX + XML_SUFFIX, mapperContent);

        String serviceInterfaceContent = getServiceInterfaceContent();

        outputTheStringToFile(OUTPUT_DIR, SERVICE_INTERFACE_CLASS_NAME + JAVA_FILE_SUFFIX, serviceInterfaceContent);

        String serviceImplContent = getServiceImplContent();

        outputTheStringToFile(OUTPUT_DIR, SERVICE_IMPL_CLASS_NAME + JAVA_FILE_SUFFIX, serviceImplContent);

        String wsInterfaceContent = getWebServiceInterfaceContent(clazz);

        outputTheStringToFile(OUTPUT_DIR, WS_INTERFACE_CLASS_NAME + JAVA_FILE_SUFFIX, wsInterfaceContent);

        String wsImplContent = getWebServiceImplContent(clazz);

        outputTheStringToFile(OUTPUT_DIR, WS_IMPL_CLASS_NAME + JAVA_FILE_SUFFIX, wsImplContent);

        String packVoContent = getPackVoContent(clazz);

        outputTheStringToFile(OUTPUT_DIR, PACK_VO_CLASS_SIMPLE_NAME + JAVA_FILE_SUFFIX, packVoContent);

        String DAOInterfaceContent = getDAOInterfaceContent();

        outputTheStringToFile(OUTPUT_DIR, DAO_INTERFACE_CLASS_NAME + JAVA_FILE_SUFFIX, DAOInterfaceContent);

        String DAOImplContent = getDAOImplContent();

        outputTheStringToFile(OUTPUT_DIR, DAO_IMPL_CLASS_NAME + JAVA_FILE_SUFFIX, DAOImplContent);

        String soContent = getSearchSoContent();

        outputTheStringToFile(OUTPUT_DIR, SO_CLASS_SIMPLE_NAME + JAVA_FILE_SUFFIX, soContent);

        String testClassContent = getTestClassContent();

        outputTheStringToFile(OUTPUT_DIR, PO_CLASS_SIMPLE_NAME + TEST_CLASS_SUFFIX + JAVA_FILE_SUFFIX, testClassContent);

    }

    private static String getSqlContent(Class clazz) {
        StringBuilder sql = new StringBuilder();

        sql.append(table(clazz));
        sql.append(primaryKey(clazz));
        sql.append(seq(clazz));

        return sql.toString();
    }

    private static String getTestClassContent() {
        StringBuilder buffer = new StringBuilder();

        String testAnnotationStartStr = TEST_ANNOTATION;

        // start
        buffer.append(String.format("public class %sServiceTest extends SpringBaseTest{", PO_CLASS_SIMPLE_NAME));
        buffer.append(LINE_SPLIT);

        buffer.append(fillBlankForString("@Autowired"));
        buffer.append(LINE_SPLIT);
        buffer.append(fillBlankForString(String.format("private %s %s;", SERVICE_INTERFACE_CLASS_NAME,
                convertFirstLetterToLower(SERVICE_INTERFACE_CLASS_NAME))));
        buffer.append(LINE_SPLIT);

        // create entry method
        buffer.append(LINE_SPLIT);
        String createEntryMethodHeader = generateMehtodHeader(VO_CLASS_SIMPLE_NAME, "create", null, null);
        String createEntryMethodBody = getCreateEntryMethodBodyForTest();
        buffer.append(generateEntireMethod(createEntryMethodHeader, createEntryMethodBody));
        buffer.append(LINE_SPLIT);

        // testCreate method
        buffer.append(LINE_SPLIT);
        buffer.append(BLANK_FOR_METHOD + testAnnotationStartStr + LINE_SPLIT);
        String createMethodHeader = generateMehtodHeader("void", "testCreate", null, null);
        String createMethodBody = getCreateMethodBodyForTest();
        buffer.append(generateEntireMethod(createMethodHeader, createMethodBody));
        buffer.append(LINE_SPLIT);

        // testSearch method
        buffer.append(BLANK_FOR_METHOD + testAnnotationStartStr + LINE_SPLIT);
        String testSearchMethodHeader = generateMehtodHeader("void", "testSearch", null, null);
        String testSearchMethodBody = getTestSearchMethodBodyForTest();
        buffer.append(generateEntireMethod(testSearchMethodHeader, testSearchMethodBody));
        buffer.append(LINE_SPLIT);

        // testSearchCount method
        buffer.append(BLANK_FOR_METHOD + testAnnotationStartStr + LINE_SPLIT);
        String testSearchCountMethodHeader = generateMehtodHeader("void", "testSearchCount", null, null);
        String testSearchCountMethodBody = getTestSearchCountMethodBodyForTest();
        buffer.append(generateEntireMethod(testSearchCountMethodHeader, testSearchCountMethodBody));
        buffer.append(LINE_SPLIT);

        // testUpdate method
        buffer.append(BLANK_FOR_METHOD + testAnnotationStartStr + LINE_SPLIT);
        String testUpdateMethodHeader = generateMehtodHeader("void", "testUpdate", null, null);
        String testUpdateMethodBody = getUpdateMethodBodyForTest();
        buffer.append(generateEntireMethod(testUpdateMethodHeader, testUpdateMethodBody));
        buffer.append(LINE_SPLIT);
        buffer.append(LINE_SPLIT);

        // end
        buffer.append("}");
        buffer.append(LINE_SPLIT);
        return buffer.toString();
    }

    private static String getUpdateMethodBodyForTest() {
        StringBuilder buffer = new StringBuilder();

        buffer.append(getMethodBodyLineByFormatContent(String.format("%s vo = this.create();", VO_CLASS_SIMPLE_NAME)));

        buffer.append(getMethodBodyLineByFormatContent(String.format("%s voTemp = %s.get%s(vo.getId()).getVo();",
                VO_CLASS_SIMPLE_NAME, convertFirstLetterToLower(SERVICE_INTERFACE_CLASS_NAME), PO_CLASS_SIMPLE_NAME)));

        buffer.append(getMethodBodyLineByFormatContent(String.format("voTemp.setReMark(\"%s\");", REMARK_UPDATED_STR)));

        buffer.append(getMethodBodyLineByFormatContent(String.format("%s updateRes = %s.update%s(voTemp).getVo();",
                VO_CLASS_SIMPLE_NAME, convertFirstLetterToLower(SERVICE_INTERFACE_CLASS_NAME), PO_CLASS_SIMPLE_NAME)));

        buffer.append(getMethodBodyLineByFormatContent(String.format(
                "Assert.assertTrue(updateRes !=null && \"%s\".equals(updateRes.getReMark()));", REMARK_UPDATED_STR)));

        return buffer.toString();
    }

    private static String getTestSearchCountMethodBodyForTest() {
        StringBuilder buffer = new StringBuilder();

        buffer.append(getMethodBodyLineByFormatContent(String.format("%s vo = this.create();", VO_CLASS_SIMPLE_NAME)));

        buffer.append(getMethodBodyLineByFormatContent(String.format("%s so = new %s();", SO_CLASS_SIMPLE_NAME,
                SO_CLASS_SIMPLE_NAME)));

        buffer.append(getMethodBodyLineByFormatContent(String.format("long count = %s.search%sCount(so).getUdf1();",
                convertFirstLetterToLower(SERVICE_INTERFACE_CLASS_NAME), PO_CLASS_SIMPLE_NAME)));

        buffer.append(getMethodBodyLineByFormatContent("Assert.assertTrue(count > 0);"));

        return buffer.toString();
    }

    private static String getTestSearchMethodBodyForTest() {
        StringBuilder buffer = new StringBuilder();

        buffer.append(getMethodBodyLineByFormatContent(String.format("%s vo = this.create();", VO_CLASS_SIMPLE_NAME)));

        buffer.append(getMethodBodyLineByFormatContent(String.format("%s so = new %s();", SO_CLASS_SIMPLE_NAME,
                SO_CLASS_SIMPLE_NAME)));

        buffer.append(getMethodBodyLineByFormatContent(String.format("List<%s> voList = %s.search%s(so).getVoList();",
                VO_CLASS_SIMPLE_NAME, convertFirstLetterToLower(SERVICE_INTERFACE_CLASS_NAME), PO_CLASS_SIMPLE_NAME)));

        buffer.append(getMethodBodyLineByFormatContent("Assert.assertTrue(voList != null && voList.size() > 0);"));

        return buffer.toString();
    }

    private static String getMethodBodyLineByFormatContent(String s) {
        String res = fillBlankForMethodBody(s);
        res += LINE_SPLIT;
        return res;
    }

    private static String getCreateMethodBodyForTest() {
        StringBuilder buffer = new StringBuilder();

        buffer.append(fillBlankForMethodBody(String.format("%s vo = this.create();", VO_CLASS_SIMPLE_NAME)));
        buffer.append(LINE_SPLIT);

        buffer.append(fillBlankForMethodBody("Assert.assertTrue(vo!= null);"));
        return buffer.toString();
    }

    private static String getCreateEntryMethodBodyForTest() {
        StringBuilder buffer = new StringBuilder();

        buffer.append(fillBlankForMethodBody(String.format("%s vo = MockTestUtil.getJavaBean(%s.class);", VO_CLASS_SIMPLE_NAME,
                VO_CLASS_SIMPLE_NAME)));
        buffer.append(LINE_SPLIT);

        buffer.append(fillBlankForMethodBody(String.format("return %s.%s(vo).getVo();",
                convertFirstLetterToLower(SERVICE_INTERFACE_CLASS_NAME), CREATE_METHOD_NAME_PREFIX + PO_CLASS_SIMPLE_NAME)));
        return buffer.toString();
    }

    private static String getDAOInterfaceContent() {
        StringBuilder buffer = new StringBuilder();

        // start
        buffer.append(String.format("public interface %s extends DAO<%s>{", DAO_INTERFACE_CLASS_NAME, PO_CLASS_SIMPLE_NAME));
        buffer.append(LINE_SPLIT);
        buffer.append(LINE_SPLIT);

        // end
        buffer.append("}");
        buffer.append(LINE_SPLIT);

        return buffer.toString();
    }

    private static String getDAOImplContent() {
        StringBuilder buffer = new StringBuilder();

        // start
        buffer.append("@Repository");
        buffer.append(LINE_SPLIT);
        buffer.append(String.format("public class %s extends BaseDAO<%s> implements %s{", DAO_IMPL_CLASS_NAME,
                PO_CLASS_SIMPLE_NAME, DAO_INTERFACE_CLASS_NAME));
        buffer.append(LINE_SPLIT);
        buffer.append(LINE_SPLIT);

        // end
        buffer.append("}");
        buffer.append(LINE_SPLIT);

        return buffer.toString();
    }

    private static String getSearchSoContent() {
        StringBuilder buffer = new StringBuilder();

        // start
        buffer.append("@Repository");
        buffer.append(LINE_SPLIT);
        buffer.append(String.format("public class %s extends SearchObject {", SO_CLASS_SIMPLE_NAME));
        buffer.append(LINE_SPLIT);

        buffer.append(fillBlankForString("private static final long serialVersionUID = 1L;"));

        buffer.append(LINE_SPLIT);
        buffer.append(LINE_SPLIT);

        // end
        buffer.append("}");
        buffer.append(LINE_SPLIT);

        return buffer.toString();
    }

    private static String getServiceInterfaceContent() {

        StringBuilder buffer = new StringBuilder();

        // start
        buffer.append(String.format("public interface %s {", SERVICE_INTERFACE_CLASS_NAME));
        buffer.append(LINE_SPLIT);
        buffer.append(LINE_SPLIT);

        // create
        buffer.append(
                fillBlankForString(generateMehtodHeader(PACK_VO_CLASS_SIMPLE_NAME, CREATE_METHOD_NAME_PREFIX + PO_CLASS_SIMPLE_NAME,
                        VO_CLASS_SIMPLE_NAME, "vo") + ";"));
        buffer.append(LINE_SPLIT);
        buffer.append(LINE_SPLIT);

        // get
        buffer.append(fillBlankForString(
                generateMehtodHeader(PACK_VO_CLASS_SIMPLE_NAME, GET_METHOD_NAME_PREFIX + PO_CLASS_SIMPLE_NAME, "Long",
                        "primaryKey") + ";"));
        buffer.append(LINE_SPLIT);
        buffer.append(LINE_SPLIT);

        // update
        buffer.append(
                fillBlankForString(generateMehtodHeader(PACK_VO_CLASS_SIMPLE_NAME, UPDATE_METHOD_NAME_PREFIX + PO_CLASS_SIMPLE_NAME,
                        VO_CLASS_SIMPLE_NAME, "vo") + ";"));
        buffer.append(LINE_SPLIT);
        buffer.append(LINE_SPLIT);

        // delete
        buffer.append(fillBlankForString(
                generateMehtodHeader(PACK_VO_CLASS_SIMPLE_NAME, DELETE_METHOD_NAME_PREFIX + PO_CLASS_SIMPLE_NAME, "Long",
                        "primaryKey") + ";"));
        buffer.append(LINE_SPLIT);
        buffer.append(LINE_SPLIT);

        // search
        buffer.append(
                fillBlankForString(generateMehtodHeader(PACK_VO_CLASS_SIMPLE_NAME, SEARCH_METHOD_NAME_PREFIX + PO_CLASS_SIMPLE_NAME,
                        SO_CLASS_SIMPLE_NAME, "so") + ";"));
        buffer.append(LINE_SPLIT);
        buffer.append(LINE_SPLIT);

        // searchCount
        buffer.append(fillBlankForString(
                generateMehtodHeader(PACK_VO_CLASS_SIMPLE_NAME, SEARCH_METHOD_NAME_PREFIX + PO_CLASS_SIMPLE_NAME + "Count",
                        SO_CLASS_SIMPLE_NAME, "so") + ";"));
        buffer.append(LINE_SPLIT);
        buffer.append(LINE_SPLIT);

        // end
        buffer.append("}");
        buffer.append(LINE_SPLIT);

        return buffer.toString();
    }

    private static String getServiceImplContent() {

        StringBuilder buffer = new StringBuilder();

        buffer.append(String.format("@Service(\"%sService\")", convertFirstLetterToLower(PO_CLASS_SIMPLE_NAME)));
        buffer.append(LINE_SPLIT);

        // start
        buffer.append(String.format("public class %s implements %s {", SERVICE_IMPL_CLASS_NAME, SERVICE_INTERFACE_CLASS_NAME));
        buffer.append(LINE_SPLIT);
        buffer.append(LINE_SPLIT);

        // autowire some class
        fillAutowireClassForServiceImpl(buffer);

        // create
        buffer.append(LINE_SPLIT);
        String createMethodHeader = generateMehtodHeader(PACK_VO_CLASS_SIMPLE_NAME, CREATE_METHOD_NAME_PREFIX
                + PO_CLASS_SIMPLE_NAME, VO_CLASS_SIMPLE_NAME, "vo");
        String createMethodBody = getCreateMethodBodyForServiceImpl();
        buffer.append(generateEntireMethod(createMethodHeader, createMethodBody));
        buffer.append(LINE_SPLIT);
        buffer.append(LINE_SPLIT);

        // get
        String getMethodHeader = generateMehtodHeader(PACK_VO_CLASS_SIMPLE_NAME, GET_METHOD_NAME_PREFIX + PO_CLASS_SIMPLE_NAME,
                "Long", "primaryKey");
        String getMethodBody = getGetMethodBodyForServiceImpl();
        buffer.append(generateEntireMethod(getMethodHeader, getMethodBody));
        buffer.append(LINE_SPLIT);
        buffer.append(LINE_SPLIT);

        // update
        String updateMethodHeader = generateMehtodHeader(PACK_VO_CLASS_SIMPLE_NAME, UPDATE_METHOD_NAME_PREFIX
                + PO_CLASS_SIMPLE_NAME, VO_CLASS_SIMPLE_NAME, "vo");
        String updateMethodBody = getUpdateMethodBodyForServiceImpl();
        buffer.append(generateEntireMethod(updateMethodHeader, updateMethodBody));
        buffer.append(LINE_SPLIT);
        buffer.append(LINE_SPLIT);

        // delete
        String deleteMethodHeader = generateMehtodHeader(PACK_VO_CLASS_SIMPLE_NAME, DELETE_METHOD_NAME_PREFIX
                + PO_CLASS_SIMPLE_NAME, "Long", "primaryKey");
        String deleteMethodBody = getDeleteMethodBodyForServiceImpl();
        buffer.append(generateEntireMethod(deleteMethodHeader, deleteMethodBody));
        buffer.append(LINE_SPLIT);
        buffer.append(LINE_SPLIT);

        // search
        String searchMethodHeader = generateMehtodHeader(PACK_VO_CLASS_SIMPLE_NAME, SEARCH_METHOD_NAME_PREFIX
                + PO_CLASS_SIMPLE_NAME, SO_CLASS_SIMPLE_NAME, "so");
        String searchMethodBody = getSearchMethodBodyForServiceImpl();
        buffer.append(generateEntireMethod(searchMethodHeader, searchMethodBody));

        buffer.append(LINE_SPLIT);
        buffer.append(LINE_SPLIT);

        // searchCount
        String searchCountMethodHeader = generateMehtodHeader(PACK_VO_CLASS_SIMPLE_NAME, SEARCH_METHOD_NAME_PREFIX
                + PO_CLASS_SIMPLE_NAME + "Count", SO_CLASS_SIMPLE_NAME, "so");
        String searchCountMethodBody = getSearchCountMethodBodyForServiceImpl();
        buffer.append(generateEntireMethod(searchCountMethodHeader, searchCountMethodBody));

        buffer.append(LINE_SPLIT);
        buffer.append(LINE_SPLIT);

        // end
        buffer.append("}");
        buffer.append(LINE_SPLIT);

        // System.out.println();
        // System.out.println(serviceInterface.toString());
        // System.out.println();
        return buffer.toString();

    }

    private static String getCreateMethodBodyForServiceImpl() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(fillBlankForMethodBody(String.format("%s packVo = new %s();", PACK_VO_CLASS_SIMPLE_NAME,
                PACK_VO_CLASS_SIMPLE_NAME)));
        buffer.append(LINE_SPLIT);

        buffer.append(fillBlankForMethodBody(String.format("%s entity = dozerBeanUtil.convert(vo, %s.class);", PO_CLASS_SIMPLE_NAME,
                PO_CLASS_SIMPLE_NAME)));
        buffer.append(LINE_SPLIT);

        buffer.append(fillBlankForMethodBody(String.format("%s returnEntity = %s.createPOReturnObj(entity);", PO_CLASS_SIMPLE_NAME,
                convertFirstLetterToLower(DAO_INTERFACE_CLASS_NAME))));
        buffer.append(LINE_SPLIT);

        buffer.append(fillBlankForMethodBody(String.format("packVo.setVo(dozerBeanUtil.convert(returnEntity, %s.class));",
                VO_CLASS_SIMPLE_NAME)));
        buffer.append(LINE_SPLIT);
        buffer.append(fillBlankForMethodBody("return packVo;"));
        return buffer.toString();
    }

    private static String getGetMethodBodyForServiceImpl() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(fillBlankForMethodBody(String.format("%s packVo = new %s();", PACK_VO_CLASS_SIMPLE_NAME,
                PACK_VO_CLASS_SIMPLE_NAME)));
        buffer.append(LINE_SPLIT);

        buffer.append(fillBlankForMethodBody(String.format("%s returnEntity = %s.getPO(primaryKey);", PO_CLASS_SIMPLE_NAME,
                convertFirstLetterToLower(DAO_INTERFACE_CLASS_NAME))));
        buffer.append(LINE_SPLIT);

        buffer.append(fillBlankForMethodBody(String.format("packVo.setVo(dozerBeanUtil.convert(returnEntity, %s.class));",
                VO_CLASS_SIMPLE_NAME)));
        buffer.append(LINE_SPLIT);
        buffer.append(fillBlankForMethodBody("return packVo;"));
        return buffer.toString();
    }

    private static String getSearchMethodBodyForServiceImpl() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(fillBlankForMethodBody(String.format("%s packVo = new %s();", PACK_VO_CLASS_SIMPLE_NAME,
                PACK_VO_CLASS_SIMPLE_NAME)));
        buffer.append(LINE_SPLIT);

        buffer.append(fillBlankForMethodBody(String.format("List<%s> voList = %s.searchVOs(so);", VO_CLASS_SIMPLE_NAME,
                convertFirstLetterToLower(DAO_INTERFACE_CLASS_NAME))));
        buffer.append(LINE_SPLIT);

        buffer.append(fillBlankForMethodBody("packVo.setVoList(voList);"));
        buffer.append(LINE_SPLIT);
        buffer.append(fillBlankForMethodBody("return packVo;"));
        return buffer.toString();
    }

    private static String getSearchCountMethodBodyForServiceImpl() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(fillBlankForMethodBody(String.format("%s packVo = new %s();", PACK_VO_CLASS_SIMPLE_NAME,
                PACK_VO_CLASS_SIMPLE_NAME)));
        buffer.append(LINE_SPLIT);

        buffer.append(fillBlankForMethodBody(String.format("Long count = %s.searchPOsCount(so);",
                convertFirstLetterToLower(DAO_INTERFACE_CLASS_NAME))));
        buffer.append(LINE_SPLIT);

        buffer.append(fillBlankForMethodBody("packVo.setUdf1(count);"));
        buffer.append(LINE_SPLIT);
        buffer.append(fillBlankForMethodBody("return packVo;"));
        return buffer.toString();
    }

    private static String getUpdateMethodBodyForServiceImpl() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(fillBlankForMethodBody(String.format("%s packVo = new %s();", PACK_VO_CLASS_SIMPLE_NAME,
                PACK_VO_CLASS_SIMPLE_NAME)));
        buffer.append(LINE_SPLIT);

        buffer.append(fillBlankForMethodBody(String.format("%s po = dozerBeanUtil.convert(vo, %s.class);", PO_CLASS_SIMPLE_NAME,
                PO_CLASS_SIMPLE_NAME)));
        buffer.append(LINE_SPLIT);

        buffer.append(fillBlankForMethodBody(String.format("%s returnEntity = %s.updatePOReturnObj(po);", PO_CLASS_SIMPLE_NAME,
                convertFirstLetterToLower(DAO_INTERFACE_CLASS_NAME))));
        buffer.append(LINE_SPLIT);

        buffer.append(fillBlankForMethodBody(String.format("packVo.setVo(dozerBeanUtil.convert(returnEntity, %s.class));",
                VO_CLASS_SIMPLE_NAME)));
        buffer.append(LINE_SPLIT);
        buffer.append(fillBlankForMethodBody("return packVo;"));
        return buffer.toString();
    }

    private static String fillBlankForString(String s) {
        return BLANK_FOR_METHOD + s;
    }

    private static String fillBlankForMethodBody(String s) {
        return BLANK_FOR_METHOD_BODY + s;
    }

    private static String getDeleteMethodBodyForServiceImpl() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(fillBlankForMethodBody(String.format("%s packVo = new %s();", PACK_VO_CLASS_SIMPLE_NAME,
                PACK_VO_CLASS_SIMPLE_NAME)));
        buffer.append(LINE_SPLIT);

        buffer.append(fillBlankForMethodBody(String.format("%s.deletePOById(primaryKey);",
                convertFirstLetterToLower(DAO_INTERFACE_CLASS_NAME))));
        buffer.append(LINE_SPLIT);

        buffer.append(fillBlankForMethodBody("return packVo;"));
        return buffer.toString();
    }

    private static void fillAutowireClassForServiceImpl(StringBuilder buffer) {
        buffer.append(fillBlankForString(String.format("private Logger logger = LoggerFactory.getLogger(%s.class);",
                SERVICE_IMPL_CLASS_NAME)));
        buffer.append(LINE_SPLIT);
        buffer.append(LINE_SPLIT);

        buffer.append(fillBlankForString("@Autowired"));
        buffer.append(LINE_SPLIT);
        buffer.append(fillBlankForString(String.format("private %s %s;", DAO_INTERFACE_CLASS_NAME,
                convertFirstLetterToLower(DAO_INTERFACE_CLASS_NAME))));
        buffer.append(LINE_SPLIT);
        buffer.append(LINE_SPLIT);

        buffer.append(fillBlankForString("@Autowired"));
        buffer.append(LINE_SPLIT);
        buffer.append(fillBlankForString("private DozerBeanUtil dozerBeanUtil;"));
        buffer.append(LINE_SPLIT);
        buffer.append(LINE_SPLIT);
    }

    /**
     * 产生完整方法 methodHeader为方法签名部分，如:public String getSite(Long id)
     * methodBody为方法实现内容
     *
     * @param methodHeader
     * @param methodBody
     * @return
     */
    private static String generateEntireMethod(String methodHeader, String methodBody) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(fillBlankForString(methodHeader));
        buffer.append("{");
        buffer.append(LINE_SPLIT);
        buffer.append(methodBody);
        buffer.append(LINE_SPLIT);
        buffer.append(fillBlankForString("}"));
        return buffer.toString();
    }

    /**
     * 产生无方法体部分
     *
     * @param returnName 返回值
     * @param methodName 方法名
     * @param paramType  方法参数类型
     * @param paramName  方法参数名称
     * @return
     */
    private static String generateMehtodHeader(String returnName, String methodName, String paramType, String paramName) {
        String firstPart = "public" + BLANK_FOR_PARAM + returnName + BLANK_FOR_PARAM + methodName + "(";
        if (paramType != null && paramName != null) {
            firstPart += paramType + BLANK_FOR_PARAM + paramName;
        }
        firstPart += ")";
        return firstPart;
    }

    /*
     * 首字母小写
     */
    private static String convertFirstLetterToLower(String className) {
        String lowerString = null;
        String firstLetter = className.substring(0, 1);
        String leftLetters = className.substring(1);

        lowerString = firstLetter.toLowerCase() + leftLetters;

        return lowerString;
    }

    private static String getWebServiceInterfaceContent(Class clazz) {
        String className = clazz.getSimpleName();

        StringBuilder webServiceInterface = new StringBuilder();

        // header
        webServiceInterface.append("@WebService");
        webServiceInterface.append(LINE_SPLIT);
        webServiceInterface.append("@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)");
        webServiceInterface.append(LINE_SPLIT);

        // start
        webServiceInterface.append(String.format("public interface WS%sService {", className));
        webServiceInterface.append(LINE_SPLIT);
        webServiceInterface.append(LINE_SPLIT);

        // create
        webServiceInterface.append(String.format("    Pack%sVo create%s(ClientInfo clientInfo, %sVo vo);", className, className,
                className));
        webServiceInterface.append(LINE_SPLIT);
        webServiceInterface.append(LINE_SPLIT);

        // get
        webServiceInterface.append(String.format("    Pack%sVo get%s(ClientInfo clientInfo, Long primaryKey);", className,
                className));
        webServiceInterface.append(LINE_SPLIT);
        webServiceInterface.append(LINE_SPLIT);

        // delete
        webServiceInterface.append(String.format("    Pack%sVo delete%s(ClientInfo clientInfo, Long primaryKey);", className,
                className));
        webServiceInterface.append(LINE_SPLIT);
        webServiceInterface.append(LINE_SPLIT);

        // update
        webServiceInterface.append(String.format("    Pack%sVo update%s(ClientInfo clientInfo, %sVo vo);", className, className,
                className));
        webServiceInterface.append(LINE_SPLIT);
        webServiceInterface.append(LINE_SPLIT);

        // search
        webServiceInterface.append(String.format("    Pack%sVo search%s(ClientInfo clientInfo, %sSo so);", className, className,
                className));
        webServiceInterface.append(LINE_SPLIT);
        webServiceInterface.append(LINE_SPLIT);

        // searchCount
        webServiceInterface.append(String.format("    Pack%sVo search%sCount(ClientInfo clientInfo, %sSo so);", className,
                className, className));
        webServiceInterface.append(LINE_SPLIT);
        webServiceInterface.append(LINE_SPLIT);

        // end
        webServiceInterface.append("}");
        webServiceInterface.append(LINE_SPLIT);

        return webServiceInterface.toString();
    }

    private static String getWebServiceImplContent(Class clazz) {
        String className = clazz.getSimpleName();

        StringBuilder webServiceImpl = new StringBuilder();

        String webServiceTemplate = String.format("%sService", convertFirstLetterToLower(className));

        // header
        webServiceImpl.append(String.format("@Stateless(name = \"%sService\")", className));
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append(String.format("@Remote(WS%sService.class)", className));
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append(String.format(
                "@WebService(endpointInterface = \"com.best.oasis.ltlv5.ejb3.complaintnew.WS%sService\", name = \"%sService\")",
                className, className));
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append("@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)");
        webServiceImpl.append(LINE_SPLIT);

        // start
        webServiceImpl.append(String.format("public class WS%sServiceBean implements WS%sService {", className, className));
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append(LINE_SPLIT);

        // webservice
        webServiceImpl.append("    @Autowired");
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append(String.format("    private WS%sService %s;", className, webServiceTemplate));
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append(LINE_SPLIT);

        // create
        webServiceImpl.append("    @Override");
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append(String.format("    public Pack%sVo create%s(ClientInfo clientInfo, %sVo vo) {", className, className,
                className));
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append(String.format("        return %s.create%s(clientInfo, vo);", webServiceTemplate, className));
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append("    }");
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append(LINE_SPLIT);

        // get
        webServiceImpl.append("    @Override");
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append(String.format("    public Pack%sVo get%s(ClientInfo clientInfo, Long primaryKey) {", className,
                className));
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append(String.format("        return %s.get%s(clientInfo, primaryKey);", webServiceTemplate, className));
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append("    }");
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append(LINE_SPLIT);

        // delete
        webServiceImpl.append("    @Override");
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append(String.format("    public Pack%sVo delete%s(ClientInfo clientInfo, Long primaryKey) {", className,
                className));
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append(String.format("        return %s.delete%s(clientInfo, primaryKey);", webServiceTemplate, className));
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append("    }");
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append(LINE_SPLIT);

        // update
        webServiceImpl.append("    @Override");
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append(String.format("    public Pack%sVo update%s(ClientInfo clientInfo, %sVo vo) {", className, className,
                className));
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append(String.format("        return %s.update%s(clientInfo, vo);", webServiceTemplate, className));
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append("    }");
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append(LINE_SPLIT);

        // search
        webServiceImpl.append("    @Override");
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append(String.format("    public Pack%sVo search%s(ClientInfo clientInfo, %sSo so) {", className, className,
                className));
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append(String.format("        return %s.search%s(clientInfo, so);", webServiceTemplate, className));
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append("    }");
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append(LINE_SPLIT);

        // searchCount
        webServiceImpl.append("    @Override");
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append(String.format("    public Pack%sVo search%sCount(ClientInfo clientInfo, %sSo so) {", className,
                className, className));
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append(String.format("        return %s.search%sCount(clientInfo, so);", webServiceTemplate, className));
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append("    }");
        webServiceImpl.append(LINE_SPLIT);
        webServiceImpl.append(LINE_SPLIT);

        // end
        webServiceImpl.append("}");
        webServiceImpl.append(LINE_SPLIT);

        // System.out.println();
        // System.out.println(webServiceImpl.toString());
        // System.out.println();
        return webServiceImpl.toString();

    }

    private static String getPackVoContent(Class clazz) {
        String className = clazz.getSimpleName();

        StringBuilder packVo = new StringBuilder();

        // start
        packVo.append(String.format("public class Pack%sVo extends AbstractPackVo{", className));
        packVo.append(LINE_SPLIT);

        // serialVersionUID
        packVo.append(String.format("    private static final long serialVersionUID = 1L;", className));
        packVo.append(LINE_SPLIT);
        packVo.append(LINE_SPLIT);

        // vo
        packVo.append(String.format("    private %sVo vo;", className));
        packVo.append(LINE_SPLIT);
        packVo.append(LINE_SPLIT);

        // voList
        packVo.append(String.format("    private List<%sVo> voList;", className));
        packVo.append(LINE_SPLIT);
        packVo.append(LINE_SPLIT);

        // getVo
        packVo.append(String.format("    public %sVo getVo() {", className));
        packVo.append(LINE_SPLIT);
        packVo.append("        return vo;");
        packVo.append(LINE_SPLIT);
        packVo.append("    }");
        packVo.append(LINE_SPLIT);
        packVo.append(LINE_SPLIT);

        // setVo
        packVo.append(String.format("    public void setVo(%sVo vo) {", className));
        packVo.append(LINE_SPLIT);
        packVo.append("        this.vo = vo;");
        packVo.append(LINE_SPLIT);
        packVo.append("    }");
        packVo.append(LINE_SPLIT);
        packVo.append(LINE_SPLIT);

        // getVoList
        packVo.append(String.format("    public List<%sVo> getVoList() {", className));
        packVo.append(LINE_SPLIT);
        packVo.append("        return voList;");
        packVo.append(LINE_SPLIT);
        packVo.append("    }");
        packVo.append(LINE_SPLIT);
        packVo.append(LINE_SPLIT);

        // setVoList
        packVo.append(String.format("    public void setVoList(List<%sVo> voList) {", className));
        packVo.append(LINE_SPLIT);
        packVo.append("        this.voList = voList;");
        packVo.append(LINE_SPLIT);
        packVo.append("    }");
        packVo.append(LINE_SPLIT);
        packVo.append(LINE_SPLIT);

        // end
        packVo.append("}");

        // System.out.println();
        return packVo.toString();
        // System.out.println();

    }

}