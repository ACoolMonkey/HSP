package com.hys.hsp.dbtype.mysql.sqlinfo;

import com.hys.hsp.base.sqlinfo.BaseUpdateSqlInfo;

import java.io.Serializable;

/**
 * Mysql的update开头sql信息类
 *
 * @author Robert Hou
 * @date 2018年5月18日
 */
public class MysqlUpdateSqlInfo extends BaseUpdateSqlInfo implements Serializable {

    private static final String SPLIT_SQL = "UPDATE(.*)INNER\\s*JOIN(.*)";
    private static final String SPLIT_KEY = "INNER";

    private static class MysqlUpdateSqlInfoLazyHolder {

        private static final MysqlUpdateSqlInfo INSTANCE = new MysqlUpdateSqlInfo();
    }

    private MysqlUpdateSqlInfo() {
    }

    public static MysqlUpdateSqlInfo getInstance() {
        return MysqlUpdateSqlInfoLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return MysqlUpdateSqlInfoLazyHolder.INSTANCE;
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
