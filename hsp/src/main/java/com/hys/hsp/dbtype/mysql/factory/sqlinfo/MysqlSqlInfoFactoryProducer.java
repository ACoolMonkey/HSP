package com.hys.hsp.dbtype.mysql.factory.sqlinfo;

import com.hys.hsp.base.factory.sqlinfo.AbstractSqlInfoFactory;
import com.hys.hsp.base.factory.sqlinfo.AbstractSqlInfoFactoryProducer;
import com.hys.hsp.base.sqlinfo.AbstractSqlInfo;
import com.hys.hsp.utils.SqlInfo;

import java.io.Serializable;

/**
 * Mysql的sqlinfo工厂生产者
 *
 * @author Robert Hou
 * @date 2018年5月23日
 */
public class MysqlSqlInfoFactoryProducer extends AbstractSqlInfoFactoryProducer implements Serializable {

    private static class MysqlSqlInfoFactoryProducerLazyHolder {

        private static final MysqlSqlInfoFactoryProducer INSTANCE = new MysqlSqlInfoFactoryProducer();
    }

    private MysqlSqlInfoFactoryProducer() {
    }

    private static MysqlSqlInfoFactoryProducer getInstance() {
        return MysqlSqlInfoFactoryProducerLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return MysqlSqlInfoFactoryProducerLazyHolder.INSTANCE;
    }

    @Override
    public AbstractSqlInfo getSqlInfoInstance(SqlInfo type) {
        AbstractSqlInfo sqlInfo;
        AbstractSqlInfoFactory mysqlSqlInfoFactory = MysqlSqlInfoFactory.getInstance();
        switch (type) {
            case CREATE:
                sqlInfo = mysqlSqlInfoFactory.getCreateInstance();
                break;
            case INSERT:
                sqlInfo = mysqlSqlInfoFactory.getInsertInstance();
                break;
            case MERGE:
                sqlInfo = mysqlSqlInfoFactory.getMergeInstance();
                break;
            case UPDATE:
                sqlInfo = mysqlSqlInfoFactory.getUpdateInstance();
                break;
            default:
                throw new UnsupportedOperationException("不兼容的类型：" + type);
        }
        return sqlInfo;
    }
}
