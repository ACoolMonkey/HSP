package com.hys.hsp.dbtype.postgresql.factory.sqlinfo;

import com.hys.hsp.base.factory.sqlinfo.AbstractSqlInfoFactory;
import com.hys.hsp.base.factory.sqlinfo.AbstractSqlInfoFactoryProducer;
import com.hys.hsp.base.sqlinfo.AbstractSqlInfo;
import com.hys.hsp.utils.SqlInfo;

import java.io.Serializable;

/**
 * Postgresql的sqlinfo工厂生产者
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class PostgresqlSqlInfoFactoryProducer extends AbstractSqlInfoFactoryProducer implements Serializable {

    private static class PostgresqlSqlInfoFactoryProducerLazyHolder {

        private static final PostgresqlSqlInfoFactoryProducer INSTANCE = new PostgresqlSqlInfoFactoryProducer();
    }

    private PostgresqlSqlInfoFactoryProducer() {
    }

    private static PostgresqlSqlInfoFactoryProducer getInstance() {
        return PostgresqlSqlInfoFactoryProducerLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return PostgresqlSqlInfoFactoryProducerLazyHolder.INSTANCE;
    }

    @Override
    public AbstractSqlInfo getSqlInfoInstance(SqlInfo type) {
        AbstractSqlInfo sqlInfo;
        AbstractSqlInfoFactory postgresqlSqlInfoFactory = PostgresqlSqlInfoFactory.getInstance();
        switch (type) {
            case CREATE:
                sqlInfo = postgresqlSqlInfoFactory.getCreateInstance();
                break;
            case INSERT:
                sqlInfo = postgresqlSqlInfoFactory.getInsertInstance();
                break;
            case MERGE:
                sqlInfo = postgresqlSqlInfoFactory.getMergeInstance();
                break;
            case UPDATE:
                sqlInfo = postgresqlSqlInfoFactory.getUpdateInstance();
                break;
            default:
                throw new IllegalStateException("不兼容的类型：" + type);
        }
        return sqlInfo;
    }
}
