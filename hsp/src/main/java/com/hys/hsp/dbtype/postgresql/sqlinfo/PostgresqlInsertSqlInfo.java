package com.hys.hsp.dbtype.postgresql.sqlinfo;

import com.hys.hsp.base.sqlinfo.BaseInsertSqlInfo;

import java.io.Serializable;

/**
 * Postgresql的insert开头sql信息类
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class PostgresqlInsertSqlInfo extends BaseInsertSqlInfo implements Serializable {

    private static class PostgresqlInsertSqlInfoLazyHolder {

        private static final PostgresqlInsertSqlInfo INSTANCE = new PostgresqlInsertSqlInfo();
    }

    private PostgresqlInsertSqlInfo() {
    }

    public static PostgresqlInsertSqlInfo getInstance() {
        return PostgresqlInsertSqlInfoLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return PostgresqlInsertSqlInfoLazyHolder.INSTANCE;
    }
}
