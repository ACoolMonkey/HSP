package com.hys.hsp.dbtype.postgresql.parse;

import com.hys.hsp.base.parse.BaseTargetParse;

import java.io.Serializable;

/**
 * Postgresql目标解析
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class PostgresqlTargetParse extends BaseTargetParse implements Serializable {

    private static class PostgresqlTargetParseLazyHolder {

        private static final PostgresqlTargetParse INSTANCE = new PostgresqlTargetParse();
    }

    private PostgresqlTargetParse() {
    }

    public static PostgresqlTargetParse getInstance() {
        return PostgresqlTargetParseLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return PostgresqlTargetParseLazyHolder.INSTANCE;
    }
}
