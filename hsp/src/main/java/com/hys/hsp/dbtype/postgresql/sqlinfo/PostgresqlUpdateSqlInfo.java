package com.hys.hsp.dbtype.postgresql.sqlinfo;

import com.hys.hsp.base.sqlinfo.BaseUpdateSqlInfo;

import java.io.Serializable;

/**
 * Postgresql的update开头sql信息类
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class PostgresqlUpdateSqlInfo extends BaseUpdateSqlInfo implements Serializable {

    private static class PostgresqlUpdateSqlInfoLazyHolder {

        private static final PostgresqlUpdateSqlInfo INSTANCE = new PostgresqlUpdateSqlInfo();
    }

    private PostgresqlUpdateSqlInfo() {
    }

    public static PostgresqlUpdateSqlInfo getInstance() {
        return PostgresqlUpdateSqlInfoLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return PostgresqlUpdateSqlInfoLazyHolder.INSTANCE;
    }
}
