package com.hys.hsp.dbtype.db2.factory.parse;

import com.hys.hsp.base.factory.parse.AbstractParseFactory;
import com.hys.hsp.base.factory.parse.AbstractParseFactoryProducer;
import com.hys.hsp.base.parse.AbstractParse;
import com.hys.hsp.utils.Parse;

import java.io.Serializable;

/**
 * Db2的parse工厂生产者
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class Db2ParseFactoryProducer extends AbstractParseFactoryProducer implements Serializable {

    private static class Db2ParseFactoryProducerLazyHolder {

        private static final Db2ParseFactoryProducer INSTANCE = new Db2ParseFactoryProducer();
    }

    private Db2ParseFactoryProducer() {
    }

    private static Db2ParseFactoryProducer getInstance() {
        return Db2ParseFactoryProducerLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return Db2ParseFactoryProducerLazyHolder.INSTANCE;
    }

    @Override
    public AbstractParse getParseInstance(Parse type) {
        AbstractParse parse;
        AbstractParseFactory db2ParseFactory = Db2ParseFactory.getInstance();
        switch (type) {
            case SOURCE:
                parse = db2ParseFactory.getSourceInstance();
                break;
            case TARGET:
                parse = db2ParseFactory.getTargetInstance();
                break;
            default:
                throw new UnsupportedOperationException("不兼容的类型：" + type);
        }
        return parse;
    }
}
