package com.hys.hsp.dbtype.teradata.factory.sqlinfo;

import com.hys.hsp.base.factory.sqlinfo.AbstractSqlInfoFactory;
import com.hys.hsp.base.sqlinfo.AbstractSqlInfo;
import com.hys.hsp.dbtype.teradata.sqlinfo.TeradataCreateSqlInfo;
import com.hys.hsp.dbtype.teradata.sqlinfo.TeradataInsertSqlInfo;
import com.hys.hsp.dbtype.teradata.sqlinfo.TeradataMergeSqlInfo;
import com.hys.hsp.dbtype.teradata.sqlinfo.TeradataUpdateSqlInfo;

import java.io.Serializable;

/**
 * Teradata工厂
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class TeradataSqlInfoFactory implements AbstractSqlInfoFactory, Serializable {

    private static class TeradataSqlInfoFactoryLazyHolder {

        private static final TeradataSqlInfoFactory INSTANCE = new TeradataSqlInfoFactory();
    }

    private TeradataSqlInfoFactory() {
    }

    static TeradataSqlInfoFactory getInstance() {
        return TeradataSqlInfoFactoryLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return TeradataSqlInfoFactoryLazyHolder.INSTANCE;
    }

    @Override
    public AbstractSqlInfo getCreateInstance() {
        return TeradataCreateSqlInfo.getInstance();
    }

    @Override
    public AbstractSqlInfo getInsertInstance() {
        return TeradataInsertSqlInfo.getInstance();
    }

    @Override
    public AbstractSqlInfo getMergeInstance() {
        return TeradataMergeSqlInfo.getInstance();
    }

    @Override
    public AbstractSqlInfo getUpdateInstance() {
        return TeradataUpdateSqlInfo.getInstance();
    }
}
