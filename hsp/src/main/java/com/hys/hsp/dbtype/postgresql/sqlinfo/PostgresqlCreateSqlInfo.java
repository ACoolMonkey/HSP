package com.hys.hsp.dbtype.postgresql.sqlinfo;

import com.hys.hsp.base.sqlinfo.BaseCreateSqlInfo;

import java.io.Serializable;

/**
 * Postgresql的create开头sql信息类
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class PostgresqlCreateSqlInfo extends BaseCreateSqlInfo implements Serializable {

    private static class PostgresqlCreateSqlInfoLazyHolder {

        private static final PostgresqlCreateSqlInfo INSTANCE = new PostgresqlCreateSqlInfo();
    }

    private PostgresqlCreateSqlInfo() {
    }

    public static PostgresqlCreateSqlInfo getInstance() {
        return PostgresqlCreateSqlInfoLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return PostgresqlCreateSqlInfoLazyHolder.INSTANCE;
    }
}
