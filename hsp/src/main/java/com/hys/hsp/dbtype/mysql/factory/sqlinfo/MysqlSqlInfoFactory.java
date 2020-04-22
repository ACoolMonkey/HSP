package com.hys.hsp.dbtype.mysql.factory.sqlinfo;

import com.hys.hsp.base.factory.sqlinfo.AbstractSqlInfoFactory;
import com.hys.hsp.base.sqlinfo.AbstractSqlInfo;
import com.hys.hsp.dbtype.mysql.sqlinfo.MysqlCreateSqlInfo;
import com.hys.hsp.dbtype.mysql.sqlinfo.MysqlInsertSqlInfo;
import com.hys.hsp.dbtype.mysql.sqlinfo.MysqlUpdateSqlInfo;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * Mysql工厂
 *
 * @author Robert Hou
 * @date 2018年5月18日
 */
@Slf4j
public class MysqlSqlInfoFactory implements AbstractSqlInfoFactory, Serializable {

    private static class MysqlSqlInfoFactoryLazyHolder {

        private static final MysqlSqlInfoFactory INSTANCE = new MysqlSqlInfoFactory();
    }

    private MysqlSqlInfoFactory() {
    }

    static MysqlSqlInfoFactory getInstance() {
        return MysqlSqlInfoFactoryLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return MysqlSqlInfoFactoryLazyHolder.INSTANCE;
    }

    @Override
    public AbstractSqlInfo getCreateInstance() {
        return MysqlCreateSqlInfo.getInstance();
    }

    @Override
    public AbstractSqlInfo getInsertInstance() {
        return MysqlInsertSqlInfo.getInstance();
    }

    @Override
    public AbstractSqlInfo getMergeInstance() {
        log.info("Mysql不支持merge into语法，终止此次解析");
        return null;
    }

    @Override
    public AbstractSqlInfo getUpdateInstance() {
        return MysqlUpdateSqlInfo.getInstance();
    }
}
