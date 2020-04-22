package com.hys.hsp.dbtype.db2.factory.sqlinfo;

import com.hys.hsp.base.factory.sqlinfo.AbstractSqlInfoFactory;
import com.hys.hsp.base.factory.sqlinfo.AbstractSqlInfoFactoryProducer;
import com.hys.hsp.base.sqlinfo.AbstractSqlInfo;
import com.hys.hsp.utils.SqlInfo;

import java.io.Serializable;

/**
 * Db2的sqlinfo工厂生产者
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class Db2SqlInfoFactoryProducer extends AbstractSqlInfoFactoryProducer implements Serializable {

    private static class Db2SqlInfoFactoryProducerLazyHolder {

        private static final Db2SqlInfoFactoryProducer INSTANCE = new Db2SqlInfoFactoryProducer();
    }

    private Db2SqlInfoFactoryProducer() {
    }

    private static Db2SqlInfoFactoryProducer getInstance() {
        return Db2SqlInfoFactoryProducerLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return Db2SqlInfoFactoryProducerLazyHolder.INSTANCE;
    }

    @Override
    public AbstractSqlInfo getSqlInfoInstance(SqlInfo type) {
        AbstractSqlInfo sqlInfo;
        AbstractSqlInfoFactory db2SqlInfoFactory = Db2SqlInfoFactory.getInstance();
        switch (type) {
            case CREATE:
                sqlInfo = db2SqlInfoFactory.getCreateInstance();
                break;
            case INSERT:
                sqlInfo = db2SqlInfoFactory.getInsertInstance();
                break;
            case MERGE:
                sqlInfo = db2SqlInfoFactory.getMergeInstance();
                break;
            case UPDATE:
                sqlInfo = db2SqlInfoFactory.getUpdateInstance();
                break;
            default:
                throw new IllegalStateException("不兼容的类型：" + type);
        }
        return sqlInfo;
    }
}
