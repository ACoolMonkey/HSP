package com.hys.hsp.dbtype.oracle.factory.sqlinfo;

import com.hys.hsp.base.factory.sqlinfo.AbstractSqlInfoFactory;
import com.hys.hsp.base.factory.sqlinfo.AbstractSqlInfoFactoryProducer;
import com.hys.hsp.base.sqlinfo.AbstractSqlInfo;
import com.hys.hsp.utils.SqlInfo;

import java.io.Serializable;

/**
 * Oracle的sqlinfo工厂生产者
 *
 * @author Robert Hou
 * @date 2018年5月23日
 */
public class OracleSqlInfoFactoryProducer extends AbstractSqlInfoFactoryProducer implements Serializable {

    private static class OracleSqlInfoFactoryProducerLazyHolder {

        private static final OracleSqlInfoFactoryProducer INSTANCE = new OracleSqlInfoFactoryProducer();
    }

    private OracleSqlInfoFactoryProducer() {
    }

    private static OracleSqlInfoFactoryProducer getInstance() {
        return OracleSqlInfoFactoryProducerLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return OracleSqlInfoFactoryProducerLazyHolder.INSTANCE;
    }

    @Override
    public AbstractSqlInfo getSqlInfoInstance(SqlInfo type) {
        AbstractSqlInfo sqlInfo;
        AbstractSqlInfoFactory oracleSqlInfoFactory = OracleSqlInfoFactory.getInstance();
        switch (type) {
            case CREATE:
                sqlInfo = oracleSqlInfoFactory.getCreateInstance();
                break;
            case INSERT:
                sqlInfo = oracleSqlInfoFactory.getInsertInstance();
                break;
            case MERGE:
                sqlInfo = oracleSqlInfoFactory.getMergeInstance();
                break;
            case UPDATE:
                sqlInfo = oracleSqlInfoFactory.getUpdateInstance();
                break;
            default:
                throw new UnsupportedOperationException("不兼容的类型：" + type);
        }
        return sqlInfo;
    }
}
