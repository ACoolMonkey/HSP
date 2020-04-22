package com.hys.hsp.dbtype.greenplum.factory.sqlinfo;

import com.hys.hsp.base.factory.sqlinfo.AbstractSqlInfoFactory;
import com.hys.hsp.base.factory.sqlinfo.AbstractSqlInfoFactoryProducer;
import com.hys.hsp.base.sqlinfo.AbstractSqlInfo;
import com.hys.hsp.utils.SqlInfo;

import java.io.Serializable;

/**
 * Greenplum的sqlinfo工厂生产者
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class GreenplumSqlInfoFactoryProducer extends AbstractSqlInfoFactoryProducer implements Serializable {

    private static class GreenplumSqlInfoFactoryProducerLazyHolder {

        private static final GreenplumSqlInfoFactoryProducer INSTANCE = new GreenplumSqlInfoFactoryProducer();
    }

    private GreenplumSqlInfoFactoryProducer() {
    }

    private static GreenplumSqlInfoFactoryProducer getInstance() {
        return GreenplumSqlInfoFactoryProducerLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return GreenplumSqlInfoFactoryProducerLazyHolder.INSTANCE;
    }

    @Override
    public AbstractSqlInfo getSqlInfoInstance(SqlInfo type) {
        AbstractSqlInfo sqlInfo;
        AbstractSqlInfoFactory greenplumSqlInfoFactory = GreenplumSqlInfoFactory.getInstance();
        switch (type) {
            case CREATE:
                sqlInfo = greenplumSqlInfoFactory.getCreateInstance();
                break;
            case INSERT:
                sqlInfo = greenplumSqlInfoFactory.getInsertInstance();
                break;
            case MERGE:
                sqlInfo = greenplumSqlInfoFactory.getMergeInstance();
                break;
            case UPDATE:
                sqlInfo = greenplumSqlInfoFactory.getUpdateInstance();
                break;
            default:
                throw new IllegalStateException("不兼容的类型：" + type);
        }
        return sqlInfo;
    }
}
