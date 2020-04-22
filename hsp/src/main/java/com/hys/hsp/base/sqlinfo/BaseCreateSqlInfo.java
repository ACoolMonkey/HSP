package com.hys.hsp.base.sqlinfo;

import com.hys.hsp.utils.SqlInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * create开头sql信息类
 *
 * @author Robert Hou
 * @date 2018年5月18日
 */
@Slf4j
public abstract class BaseCreateSqlInfo extends AbstractSqlInfo {

    private static final String SPLIT_SQL = "CREATE\\s*(OR\\s*REPLACE)?\\s*(TABLE|FILE|LOG|MACRO|PROCEDURE|VIEW)(.*)\\s*AS\\s*SELECT(.*)";
    private static final String TEMP_SPLIT_SQL = "CREATE\\s*(GLOBAL|LOCAL)?\\s*(TEMPORARY|TEMP)\\s*TABLE(.*)\\s*(AS\\s*SELECT)?(.*)";
    private static final String SPLIT_KEY = "SELECT";

    @Override
    public final boolean splitSql() {
        List<String> targetSqlList = new ArrayList<>();
        List<String> sourceSqlList = new ArrayList<>();
        boolean splitSqlFlag = false;
        //拆分目标sql片段
        Pattern r = Pattern.compile(getSplitSql());
        Matcher m = r.matcher(getSql());
        if (m.find()) {
            splitSqlFlag = true;
            targetSqlList.add(m.group(3));
            setTargetSql(targetSqlList);
        }
        //处理创建临时表的情况
        r = Pattern.compile(getTempSplitSql());
        m = r.matcher(getSql());
        if (m.find()) {
            splitSqlFlag = true;
            setTemp(true);
            targetSqlList.add(m.group(3));
            setTargetSql(targetSqlList);
        }
        //拆分源sql片段
        if (splitSqlFlag) {
            int indexOfSplitKey = getSql().indexOf(getSplitKey());
            String sourceSql;
            if (indexOfSplitKey == -1) {
                sourceSql = null;
            } else {
                sourceSql = getSql().substring(indexOfSplitKey);
            }
            sourceSqlList.add(sourceSql);
            setSourceSql(sourceSqlList);
            return true;
        }
        log.info("不兼容的create开头语句匹配");
        return false;
    }

    @Override
    protected SqlInfo getSqlInfoType() {
        return SqlInfo.CREATE;
    }

    @Override
    protected String getSplitSql() {
        return SPLIT_SQL;
    }

    @Override
    protected String getSplitKey() {
        return SPLIT_KEY;
    }

    /**
     * 获取TEMP_SPLIT_SQL
     */
    protected String getTempSplitSql() {
        return TEMP_SPLIT_SQL;
    }
}
