package com.hys.hsp.base.sqlinfo;

import com.hys.hsp.utils.SpecialFlag;
import com.hys.hsp.utils.SqlInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * insert开头sql信息类
 *
 * @author Robert Hou
 * @date 2018年5月18日
 */
@Slf4j
public abstract class BaseInsertSqlInfo extends AbstractSqlInfo {

    private static final String SPLIT_SQL = "INSERT\\s*INTO(.*)\\s*SELECT\\s*(.*)";
    private static final String SPLIT_KEY = "SELECT";

    @Override
    public final boolean splitSql() {
        List<String> targetSqlList = new ArrayList<>();
        List<String> sourceSqlList = new ArrayList<>();
        String db2WithSourceTempSql = null;
        String db2WithSourceSql = null;
        boolean splitSqlFlag = false;
        //拆分目标sql片段
        Pattern r = Pattern.compile(getSplitSql());
        Matcher m = r.matcher(getSql());
        if (m.find()) {
            splitSqlFlag = true;
            if (SpecialFlag.db2Insert.equals(getSpecialFlag())) {
                //处理db2insert开头语句中with语法的临时表情况
                targetSqlList.add(m.group(1));
                if (m.group(3) != null) {
                    targetSqlList.add(m.group(3));
                }
                if (m.group(4) != null) {
                    db2WithSourceTempSql = m.group(4);
                }
                db2WithSourceSql = m.group(5);
            } else {
                targetSqlList.add(m.group(1));
            }
            setTargetSql(targetSqlList);
        }
        //拆分源sql片段
        if (SpecialFlag.db2Insert.equals(getSpecialFlag())) {
            //处理db2insert开头语句中with语法的临时表情况
            sourceSqlList.add(db2WithSourceSql);
            if (db2WithSourceTempSql != null) {
                sourceSqlList.add(db2WithSourceTempSql);
            }
            setSourceSql(sourceSqlList);
            return true;
        } else if (splitSqlFlag) {
            int indexOfSplitKey = getSql().indexOf(getSplitKey());
            String sourceSql = null;
            if (indexOfSplitKey != -1) {
                sourceSql = getSql().substring(indexOfSplitKey);
            }
            sourceSqlList.add(sourceSql);
            setSourceSql(sourceSqlList);
            return true;
        }
        log.info("不兼容的insert开头语句匹配");
        return false;
    }

    @Override
    protected SqlInfo getSqlInfoType() {
        return SqlInfo.INSERT;
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
     * 获取特殊标志位
     */
    protected SpecialFlag getSpecialFlag() {
        return null;
    }
}
