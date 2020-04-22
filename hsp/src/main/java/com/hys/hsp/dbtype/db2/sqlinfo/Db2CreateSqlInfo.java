package com.hys.hsp.dbtype.db2.sqlinfo;

import com.hys.hsp.base.sqlinfo.BaseCreateSqlInfo;

import java.io.Serializable;

/**
 * Db2的create开头sql信息类
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class Db2CreateSqlInfo extends BaseCreateSqlInfo implements Serializable {

    private static final String TEMP_SPLIT_SQL = "DECLARE\\s*(GLOBAL|LOCAL)?\\s*(TEMPORARY|TEMP)\\s*TABLE(.*)\\s*(LIKE)?(.*)";
    private static final String SPLIT_KEY = "LIKE";

    private static class Db2CreateSqlInfoLazyHolder {

        private static final Db2CreateSqlInfo INSTANCE = new Db2CreateSqlInfo();
    }

    private Db2CreateSqlInfo() {
    }

    public static Db2CreateSqlInfo getInstance() {
        return Db2CreateSqlInfoLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return Db2CreateSqlInfoLazyHolder.INSTANCE;
    }

    @Override
    protected String getTempSplitSql() {
        return TEMP_SPLIT_SQL;
    }

    @Override
    protected String getSplitKey() {
        return SPLIT_KEY;
    }
}
