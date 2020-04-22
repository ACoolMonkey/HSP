package com.hys.hsp.dbtype.teradata.factory.sqlinfo;

import com.hys.hsp.base.factory.sqlinfo.AbstractSqlInfoFactory;
import com.hys.hsp.base.factory.sqlinfo.AbstractSqlInfoFactoryProducer;
import com.hys.hsp.base.sqlinfo.AbstractSqlInfo;
import com.hys.hsp.utils.SqlInfo;

import java.io.Serializable;

/**
 * Teradata的sqlinfo工厂生产者
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class TeradataSqlInfoFactoryProducer extends AbstractSqlInfoFactoryProducer implements Serializable {

    private static class TeradataSqlInfoFactoryProducerLazyHolder {

        private static final TeradataSqlInfoFactoryProducer INSTANCE = new TeradataSqlInfoFactoryProducer();
    }

    private TeradataSqlInfoFactoryProducer() {
    }

    private static TeradataSqlInfoFactoryProducer getInstance() {
        return TeradataSqlInfoFactoryProducerLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return TeradataSqlInfoFactoryProducerLazyHolder.INSTANCE;
    }

    @Override
    public AbstractSqlInfo getSqlInfoInstance(SqlInfo type) {
        AbstractSqlInfo sqlInfo;
        AbstractSqlInfoFactory teradataSqlInfoFactory = TeradataSqlInfoFactory.getInstance();
        switch (type) {
            case CREATE:
                sqlInfo = teradataSqlInfoFactory.getCreateInstance();
                break;
            case INSERT:
                sqlInfo = teradataSqlInfoFactory.getInsertInstance();
                break;
            case MERGE:
                sqlInfo = teradataSqlInfoFactory.getMergeInstance();
                break;
            case UPDATE:
                sqlInfo = teradataSqlInfoFactory.getUpdateInstance();
                break;
            default:
                throw new IllegalStateException("不兼容的类型：" + type);
        }
        return sqlInfo;
    }
}
