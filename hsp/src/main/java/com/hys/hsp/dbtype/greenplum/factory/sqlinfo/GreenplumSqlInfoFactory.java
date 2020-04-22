package com.hys.hsp.dbtype.greenplum.factory.sqlinfo;

import com.hys.hsp.base.factory.sqlinfo.AbstractSqlInfoFactory;
import com.hys.hsp.base.sqlinfo.AbstractSqlInfo;
import com.hys.hsp.dbtype.greenplum.sqlinfo.GreenplumCreateSqlInfo;
import com.hys.hsp.dbtype.greenplum.sqlinfo.GreenplumInsertSqlInfo;
import com.hys.hsp.dbtype.greenplum.sqlinfo.GreenplumMergeSqlInfo;
import com.hys.hsp.dbtype.greenplum.sqlinfo.GreenplumUpdateSqlInfo;

import java.io.Serializable;

/**
 * Greenplum工厂
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class GreenplumSqlInfoFactory implements AbstractSqlInfoFactory, Serializable {

    private static class GreenplumSqlInfoFactoryLazyHolder {

        private static final GreenplumSqlInfoFactory INSTANCE = new GreenplumSqlInfoFactory();
    }

    private GreenplumSqlInfoFactory() {
    }

    static GreenplumSqlInfoFactory getInstance() {
        return GreenplumSqlInfoFactoryLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return GreenplumSqlInfoFactoryLazyHolder.INSTANCE;
    }

    @Override
    public AbstractSqlInfo getCreateInstance() {
        return GreenplumCreateSqlInfo.getInstance();
    }

    @Override
    public AbstractSqlInfo getInsertInstance() {
        return GreenplumInsertSqlInfo.getInstance();
    }

    @Override
    public AbstractSqlInfo getMergeInstance() {
        return GreenplumMergeSqlInfo.getInstance();
    }

    @Override
    public AbstractSqlInfo getUpdateInstance() {
        return GreenplumUpdateSqlInfo.getInstance();
    }
}
