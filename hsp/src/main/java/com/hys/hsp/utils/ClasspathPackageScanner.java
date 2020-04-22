package com.hys.hsp.utils;

import com.hys.hsp.base.factory.parse.AbstractParseFactoryProducer;
import com.hys.hsp.base.factory.sqlinfo.AbstractSqlInfoFactoryProducer;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * 读取指定包结构
 *
 * @author Robert Hou
 * @date 2018年6月25日
 */
@Slf4j
public class ClasspathPackageScanner implements Serializable {

    private ClassLoader cl;
    private static String prefix;
    private static List<String> dbTypeList;
    private static final String SQLINFO_FACTORY_PRODUCER = "SqlInfoFactoryProducer";
    private static final String PARSE_FACTORY_PRODUCER = "ParseFactoryProducer";
    private static final String GET_INSTANCE_METHOD_NAME = "getInstance";
    private static final String UTILS = "utils";
    private static final String DBTYPE = "dbtype.";
    private static final String FACTORY = "factory.";
    private static final String SQLINFO = "sqlinfo.";
    private static final String PARSE = "parse.";

    private static class ClasspathPackageScannerLazyHolder {

        private static final ClasspathPackageScanner INSTANCE = new ClasspathPackageScanner();
    }

    private ClasspathPackageScanner() {
        String hgspUtilsPackageName = Hsp.class.getPackage().getName();
        prefix = hgspUtilsPackageName.substring(0, hgspUtilsPackageName.lastIndexOf(UTILS));
        this.cl = getClass().getClassLoader();
    }

    private static ClasspathPackageScanner getInstance() {
        return ClasspathPackageScannerLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return ClasspathPackageScannerLazyHolder.INSTANCE;
    }

    private static List<String> getDbTypeList() {
        return dbTypeList;
    }

    private static void setDbTypeList(List<String> dbTypeList) {
        ClasspathPackageScanner.dbTypeList = dbTypeList;
    }

    /**
     * 获取指定包下面的文件
     */
    private List<String> doScan() {
        String splashPath = (prefix + DBTYPE).replaceAll("\\.", "/");
        URL url = cl.getResource(splashPath);
        String filePath = url.getFile();
        File file = new File(filePath);
        String[] names = file.list();
        return Arrays.asList(names);
    }

    /**
     * 获取sqlInfo和parse工厂
     */
    static FactoryProducer getFactoryProducer(String dbType) {
        dbType = dbType.replaceAll("dbv", "");
        AbstractSqlInfoFactoryProducer sqlInfoFactoryProducer = null;
        AbstractParseFactoryProducer parseFactoryProducer = null;
        boolean dbflag = false;
        try {
            if (dbTypeList == null || dbTypeList.isEmpty()) {
                ClasspathPackageScanner scan = ClasspathPackageScanner.getInstance();
                List<String> dbTypeList = scan.doScan();
                setDbTypeList(dbTypeList);
            }
            for (String dt : getDbTypeList()) {
                if (dt.equals(dbType)) {
                    dbflag = true;
                    String dbTypeFU = dbType.substring(0, 1).toUpperCase() + dbType.substring(1);
                    //sqlinfo
                    @SuppressWarnings("unchecked")
                    Class<? extends AbstractSqlInfoFactoryProducer> sqlInfoClazz = (Class<? extends AbstractSqlInfoFactoryProducer>) Class.forName(prefix + DBTYPE + dbType + "." + FACTORY + SQLINFO + dbTypeFU + SQLINFO_FACTORY_PRODUCER);
                    Method sqlInfoMethod = sqlInfoClazz.getDeclaredMethod(GET_INSTANCE_METHOD_NAME);
                    sqlInfoMethod.setAccessible(true);
                    sqlInfoFactoryProducer = (AbstractSqlInfoFactoryProducer) sqlInfoMethod.invoke(sqlInfoClazz);
                    //parse
                    @SuppressWarnings("unchecked")
                    Class<? extends AbstractParseFactoryProducer> parseClazz = (Class<? extends AbstractParseFactoryProducer>) Class.forName(prefix + DBTYPE + dbType + "." + FACTORY + PARSE + dbTypeFU + PARSE_FACTORY_PRODUCER);
                    Method parseMethod = parseClazz.getDeclaredMethod(GET_INSTANCE_METHOD_NAME);
                    parseMethod.setAccessible(true);
                    parseFactoryProducer = (AbstractParseFactoryProducer) parseMethod.invoke(parseClazz);
                    break;
                }
            }
            if (!dbflag) {
                log.error("不支持的数据库类型！");
                return null;
            }
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        FactoryProducer factoryProducer = FactoryProducer.getInstance();
        factoryProducer.setSqlInfoFactoryProducer(sqlInfoFactoryProducer);
        factoryProducer.setParseFactoryProducer(parseFactoryProducer);
        return factoryProducer;
    }
}
