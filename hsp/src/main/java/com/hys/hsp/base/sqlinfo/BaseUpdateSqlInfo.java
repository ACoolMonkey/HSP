package com.hys.hsp.base.sqlinfo;

import com.hys.hsp.utils.SqlInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * update开头sql信息类
 *
 * @author Robert Hou
 * @date 2018年5月18日
 */
@Slf4j
public abstract class BaseUpdateSqlInfo extends AbstractSqlInfo {

    private static final String SPLIT_SQL = "UPDATE(.*)SET(.*)";
    private static final String SPLIT_KEY = "SET";

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
            targetSqlList.add(m.group(1));
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
        log.info("不兼容的update开头语句匹配");
        return false;
    }

    @Override
    protected SqlInfo getSqlInfoType() {
        return SqlInfo.UPDATE;
    }

    @Override
    protected String getSplitSql() {
        return SPLIT_SQL;
    }

    @Override
    protected String getSplitKey() {
        return SPLIT_KEY;
    }
}
