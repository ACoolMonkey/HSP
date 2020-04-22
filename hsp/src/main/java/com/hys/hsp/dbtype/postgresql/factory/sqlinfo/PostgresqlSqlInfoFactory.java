package com.hys.hsp.dbtype.postgresql.factory.sqlinfo;

import com.hys.hsp.base.factory.sqlinfo.AbstractSqlInfoFactory;
import com.hys.hsp.base.sqlinfo.AbstractSqlInfo;
import com.hys.hsp.dbtype.postgresql.sqlinfo.PostgresqlCreateSqlInfo;
import com.hys.hsp.dbtype.postgresql.sqlinfo.PostgresqlInsertSqlInfo;
import com.hys.hsp.dbtype.postgresql.sqlinfo.PostgresqlUpdateSqlInfo;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * Postgresql工厂
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
@Slf4j
public class PostgresqlSqlInfoFactory implements AbstractSqlInfoFactory, Serializable {

    private static class PostgresqlSqlInfoFactoryLazyHolder {

        private static final PostgresqlSqlInfoFactory INSTANCE = new PostgresqlSqlInfoFactory();
    }

    private PostgresqlSqlInfoFactory() {
    }

    static PostgresqlSqlInfoFactory getInstance() {
        return PostgresqlSqlInfoFactoryLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return PostgresqlSqlInfoFactoryLazyHolder.INSTANCE;
    }

    @Override
    public AbstractSqlInfo getCreateInstance() {
        return PostgresqlCreateSqlInfo.getInstance();
    }

    @Override
    public AbstractSqlInfo getInsertInstance() {
        return PostgresqlInsertSqlInfo.getInstance();
    }

    @Override
    public AbstractSqlInfo getMergeInstance() {
        log.info("Postgresql不支持merge into语法，终止此次解析");
        return null;
    }

    @Override
    public AbstractSqlInfo getUpdateInstance() {
        return PostgresqlUpdateSqlInfo.getInstance();
    }
}
