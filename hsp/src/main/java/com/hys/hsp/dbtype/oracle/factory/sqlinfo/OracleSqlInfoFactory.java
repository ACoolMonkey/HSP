package com.hys.hsp.dbtype.oracle.factory.sqlinfo;

import com.hys.hsp.base.factory.sqlinfo.AbstractSqlInfoFactory;
import com.hys.hsp.base.sqlinfo.AbstractSqlInfo;
import com.hys.hsp.dbtype.oracle.sqlinfo.OracleCreateSqlInfo;
import com.hys.hsp.dbtype.oracle.sqlinfo.OracleInsertSqlInfo;
import com.hys.hsp.dbtype.oracle.sqlinfo.OracleMergeSqlInfo;
import com.hys.hsp.dbtype.oracle.sqlinfo.OracleUpdateSqlInfo;

import java.io.Serializable;

/**
 * Oracle工厂
 *
 * @author Robert Hou
 * @date 2018年5月18日
 */
public class OracleSqlInfoFactory implements AbstractSqlInfoFactory, Serializable {

    private static class OracleSqlInfoFactoryLazyHolder {

        private static final OracleSqlInfoFactory INSTANCE = new OracleSqlInfoFactory();
    }

    private OracleSqlInfoFactory() {
    }

    static OracleSqlInfoFactory getInstance() {
        return OracleSqlInfoFactoryLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return OracleSqlInfoFactoryLazyHolder.INSTANCE;
    }

    @Override
    public AbstractSqlInfo getCreateInstance() {
        return OracleCreateSqlInfo.getInstance();
    }

    @Override
    public AbstractSqlInfo getInsertInstance() {
        return OracleInsertSqlInfo.getInstance();
    }

    @Override
    public AbstractSqlInfo getMergeInstance() {
        return OracleMergeSqlInfo.getInstance();
    }

    @Override
    public AbstractSqlInfo getUpdateInstance() {
        return OracleUpdateSqlInfo.getInstance();
    }
}
