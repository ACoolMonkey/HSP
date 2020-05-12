package com.hys.hsp.dbtype.mysql.factory.parse;

import com.hys.hsp.base.factory.parse.AbstractParseFactory;
import com.hys.hsp.base.factory.parse.AbstractParseFactoryProducer;
import com.hys.hsp.base.parse.AbstractParse;
import com.hys.hsp.utils.Parse;

import java.io.Serializable;

/**
 * Mysql的parse工厂生产者
 *
 * @author Robert Hou
 * @date 2018年5月23日
 */
public class MysqlParseFactoryProducer extends AbstractParseFactoryProducer implements Serializable {

    private static class MysqlParseFactoryProducerLazyHolder {

        private static final MysqlParseFactoryProducer INSTANCE = new MysqlParseFactoryProducer();
    }

    private MysqlParseFactoryProducer() {
    }

    private static MysqlParseFactoryProducer getInstance() {
        return MysqlParseFactoryProducerLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return MysqlParseFactoryProducerLazyHolder.INSTANCE;
    }

    @Override
    public AbstractParse getParseInstance(Parse type) {
        AbstractParse parse;
        AbstractParseFactory mysqlParseFactory = MysqlParseFactory.getInstance();
        switch (type) {
            case SOURCE:
                parse = mysqlParseFactory.getSourceInstance();
                break;
            case TARGET:
                parse = mysqlParseFactory.getTargetInstance();
                break;
            default:
                throw new UnsupportedOperationException("不兼容的类型：" + type);
        }
        return parse;
    }
}
