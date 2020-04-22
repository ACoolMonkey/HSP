package com.hys.hsp.dbtype.db2.factory.sqlinfo;

import com.hys.hsp.base.factory.sqlinfo.AbstractSqlInfoFactory;
import com.hys.hsp.base.sqlinfo.AbstractSqlInfo;
import com.hys.hsp.dbtype.db2.sqlinfo.Db2CreateSqlInfo;
import com.hys.hsp.dbtype.db2.sqlinfo.Db2InsertSqlInfo;
import com.hys.hsp.dbtype.db2.sqlinfo.Db2MergeSqlInfo;
import com.hys.hsp.dbtype.db2.sqlinfo.Db2UpdateSqlInfo;

import java.io.Serializable;

/**
 * Db2工厂
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class Db2SqlInfoFactory implements AbstractSqlInfoFactory, Serializable {

    private static class Db2SqlInfoFactoryLazyHolder {

        private static final Db2SqlInfoFactory INSTANCE = new Db2SqlInfoFactory();
    }

    private Db2SqlInfoFactory() {
    }

    static Db2SqlInfoFactory getInstance() {
        return Db2SqlInfoFactoryLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return Db2SqlInfoFactoryLazyHolder.INSTANCE;
    }

    @Override
    public AbstractSqlInfo getCreateInstance() {
        return Db2CreateSqlInfo.getInstance();
    }

    @Override
    public AbstractSqlInfo getInsertInstance() {
        return Db2InsertSqlInfo.getInstance();
    }

    @Override
    public AbstractSqlInfo getMergeInstance() {
        return Db2MergeSqlInfo.getInstance();
    }

    @Override
    public AbstractSqlInfo getUpdateInstance() {
        return Db2UpdateSqlInfo.getInstance();
    }
}
