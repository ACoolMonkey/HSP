package com.hys.hsp.dbtype.postgresql.factory.parse;

import com.hys.hsp.base.factory.parse.AbstractParseFactory;
import com.hys.hsp.base.factory.parse.AbstractParseFactoryProducer;
import com.hys.hsp.base.parse.AbstractParse;
import com.hys.hsp.utils.Parse;

import java.io.Serializable;

/**
 * Postgresql的parse工厂生产者
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class PostgresqlParseFactoryProducer extends AbstractParseFactoryProducer implements Serializable {

    private static class PostgresqlParseFactoryProducerLazyHolder {

        private static final PostgresqlParseFactoryProducer INSTANCE = new PostgresqlParseFactoryProducer();
    }

    private PostgresqlParseFactoryProducer() {
    }

    private static PostgresqlParseFactoryProducer getInstance() {
        return PostgresqlParseFactoryProducerLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return PostgresqlParseFactoryProducerLazyHolder.INSTANCE;
    }

    @Override
    public AbstractParse getParseInstance(Parse type) {
        AbstractParse parse;
        AbstractParseFactory postgresqlParseFactory = PostgresqlParseFactory.getInstance();
        switch (type) {
            case SOURCE:
                parse = postgresqlParseFactory.getSourceInstance();
                break;
            case TARGET:
                parse = postgresqlParseFactory.getTargetInstance();
                break;
            default:
                throw new UnsupportedOperationException("不兼容的类型：" + type);
        }
        return parse;
    }
}
