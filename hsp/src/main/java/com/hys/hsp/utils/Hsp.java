package com.hys.hsp.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.hys.hsp.base.factory.parse.AbstractParseFactoryProducer;
import com.hys.hsp.base.factory.sqlinfo.AbstractSqlInfoFactoryProducer;
import com.hys.hsp.base.sqlinfo.AbstractSqlInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * HSP解析器
 *
 * @author Robert Hou
 * @date 2018年5月22日
 */
@Slf4j
public class Hsp {

    /**
     * 存放本次脚本中所有sql的临时表集合
     */
    private static final Set<String> TEMP_SET = new HashSet<>();

    private Hsp() {
    }

    /**
     * HSP解析入口
     */
    public static List<AbstractSqlInfo> parse(String sql, String dbType, String defaultSchema) {
        List<AbstractSqlInfo> sqlInfoList = new ArrayList<>();
        AbstractSqlInfo sqlInfo;
        AbstractSqlInfoFactoryProducer sqlInfoFactoryProducer = null;
        AbstractParseFactoryProducer parseFactoryProducer = null;
        sql = sql.trim();
        //去掉换行符
        sql = sql.replaceAll("\r", " ");
        //获取sqlInfo和parse工厂
        FactoryProducer factoryProducer = ClasspathPackageScanner.getFactoryProducer(dbType);
        if (factoryProducer == null) {
            return null;
        }
        sqlInfoFactoryProducer = factoryProducer.getSqlInfoFactoryProducer();
        parseFactoryProducer = factoryProducer.getParseFactoryProducer();
        //获取sqlInfo
        if (sql.startsWith(SqlInfo.CREATE.toString()) || sql.startsWith(SqlInfo.DECLARE.toString())) {
            sqlInfo = sqlInfoFactoryProducer.getSqlInfoInstance(SqlInfo.CREATE);
        } else if (sql.startsWith(SqlInfo.INSERT.toString())) {
            sqlInfo = sqlInfoFactoryProducer.getSqlInfoInstance(SqlInfo.INSERT);
        } else if (sql.startsWith(SqlInfo.MERGE.toString())) {
            sqlInfo = sqlInfoFactoryProducer.getSqlInfoInstance(SqlInfo.MERGE);
        } else if (sql.startsWith(SqlInfo.UPDATE.toString())) {
            sqlInfo = sqlInfoFactoryProducer.getSqlInfoInstance(SqlInfo.UPDATE);
        } else {
            log.info("不属于create、insert、merge、update、declare(db2)关键字开头的sql不予解析");
            return null;
        }
        if (sqlInfo == null) {
            return null;
        }
        //清空sqlinfo属性值 
        sqlInfo.clearSqlInfo();
        sqlInfo.setSql(sql);
        if (!sqlInfo.splitSql()) {
            return null;
        }
        if (defaultSchema != null) {
            defaultSchema = defaultSchema + ".";
        }
        //解析
        ParseResultProducer parseResultProducer = parseSqlList(parseFactoryProducer, sqlInfo, defaultSchema);
        if (parseResultProducer == null) {
            return null;
        }
        sqlInfo.setSource(parseResultProducer.getSourceList());
        sqlInfo.setTarget(parseResultProducer.getTargetList());
        //判断源库表是否是临时表,不是则删掉
        if (!isSourceATemp(sqlInfo)) {
            log.info("源表为空，终止此次解析");
            return null;
        }
        //判断目标库表是否是临时表,不是则终止此次解析
        if (!isTargetATemp(sqlInfo)) {
            if (log.isInfoEnabled()) {
                log.info("无法解析出目标库名，同时该目标表:" + sqlInfo.getTarget() + "不是临时表，终止此次解析");
            }
            return null;
        }
        sqlInfoList.add(sqlInfo);
        AbstractSqlInfo db2WithTempSqlInfo = parseResultProducer.getDb2WithTempSqlInfo();
        if (db2WithTempSqlInfo != null) {
            sqlInfoList.add(db2WithTempSqlInfo);
        }
        return sqlInfoList;
    }

    /**
     * 判断源表是否是临时表
     */
    private static boolean isSourceATemp(AbstractSqlInfo sqlInfo) {
        if (sqlInfo.isTemp()) {
            TEMP_SET.addAll(sqlInfo.getTarget());
        }
        Iterator<String> iterator = sqlInfo.getSource().iterator();
        while (iterator.hasNext()) {
            String source = iterator.next();
            if (source.contains(".")) {
                continue;
            }
            boolean isTemp = false;
            for (String temp : TEMP_SET) {
                if (temp.equals(source)) {
                    isTemp = true;
                    break;
                }
            }
            //遍历临时表集合，如果本次sql解析的源不是临时表，则删掉该源
            if (!isTemp) {
                if (log.isInfoEnabled()) {
                    log.info("无法解析出源库名，同时该源表:" + source + "不是临时表，执行删除该源表操作");
                }
                iterator.remove();
            }
        }
        return !sqlInfo.getSource().isEmpty();
    }

    /**
     * 判断目标表是否是临时表
     */
    private static boolean isTargetATemp(AbstractSqlInfo sqlInfo) {
        if (!sqlInfo.isTemp() && !sqlInfo.getTarget().get(0).contains(".")) {
            boolean isTemp = false;
            for (String temp : TEMP_SET) {
                if (temp.equals(sqlInfo.getTarget().get(0))) {
                    isTemp = true;
                    break;
                }
            }
            return isTemp;
        }
        return true;
    }

    /**
     * 清空临时表集合
     */
    public static void tempSetClear() {
        TEMP_SET.clear();
    }

    /**
     * 获取一个空的sqlInfo对象（默认为create类型）
     */
    public static AbstractSqlInfo getSqlInfo(String dbType) {
        FactoryProducer factoryProducer = ClasspathPackageScanner.getFactoryProducer(dbType);
        AbstractSqlInfoFactoryProducer sqlInfoFactoryProducer = factoryProducer.getSqlInfoFactoryProducer();
        AbstractSqlInfo sqlInfo = sqlInfoFactoryProducer.getSqlInfoInstance(SqlInfo.CREATE).clone();
        sqlInfo.clearSqlInfo();
        return sqlInfo;
    }

    /**
     * 获取db2insert开头sql中with语法临时表创建的sqlInfo
     */
    private static AbstractSqlInfo getDb2WithTempSqlInfo(List<String> db2WithSourceList, List<String> db2WithTargetList) {
        AbstractSqlInfo sqlInfo = getSqlInfo("dbvdb2");
        sqlInfo.setTemp(true);
        sqlInfo.setSource(db2WithSourceList);
        sqlInfo.setTarget(db2WithTargetList);
        TEMP_SET.addAll(sqlInfo.getTarget());
        return sqlInfo;
    }

    /**
     * 解析sql源和目标片段
     */
    private static ParseResultProducer parseSqlList(AbstractParseFactoryProducer parseFactoryProducer, AbstractSqlInfo sqlInfo, String defaultSchema) {
        ParseResultProducer parseResultProducer = ParseResultProducer.getInstance();
        parseResultProducer.clearParseResult();
        List<List<String>> sourceList = parseFactoryProducer.getParseInstance(Parse.SOURCE).parse(sqlInfo.getSourceSql(), defaultSchema);
        List<List<String>> targetList = parseFactoryProducer.getParseInstance(Parse.TARGET).parse(sqlInfo.getTargetSql(), defaultSchema);
        boolean commonFlag = (sourceList.size() == 1 && targetList.size() == 1);
        boolean db2WithFlag = (sourceList.size() == 2 && targetList.size() == 2);
        if (!(commonFlag || db2WithFlag)) {
            log.info("不识别的语法结构，终止此次解析");
            return null;
        }
        if (db2WithFlag) {
            //处理db2insert开头语句中with语法的临时表情况
            AbstractSqlInfo db2WithTempSqlInfo = getDb2WithTempSqlInfo(sourceList.get(1), targetList.get(1));
            parseResultProducer.setDb2WithTempSqlInfo(db2WithTempSqlInfo);
        }
        //没有分析出源或目标
        if (!sqlInfo.isTemp()) {
            for (List<String> source : sourceList) {
                if (source.isEmpty()) {
                    log.info("无法解析出源库表，终止此次解析");
                    return null;
                }
            }
            for (List<String> target : targetList) {
                if (target.isEmpty()) {
                    log.info("无法解析出目标库表，终止此次解析");
                    return null;
                }
            }
        }
        parseResultProducer.setSourceList(sourceList.get(0));
        parseResultProducer.setTargetList(targetList.get(0));
        return parseResultProducer;
    }
}
