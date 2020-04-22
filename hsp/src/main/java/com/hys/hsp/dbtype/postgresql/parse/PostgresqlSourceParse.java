package com.hys.hsp.dbtype.postgresql.parse;

import com.hys.hsp.base.parse.BaseSourceParse;

import java.io.Serializable;

/**
 * Postgresql源解析
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class PostgresqlSourceParse extends BaseSourceParse implements Serializable {

    private static class PostgresqlSourceParseLazyHolder {

        private static final PostgresqlSourceParse INSTANCE = new PostgresqlSourceParse();
    }

    private PostgresqlSourceParse() {
    }

    public static PostgresqlSourceParse getInstance() {
        return PostgresqlSourceParseLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return PostgresqlSourceParseLazyHolder.INSTANCE;
    }
}
