package com.hys.hsp.dbtype.oracle.factory.parse;

import com.hys.hsp.base.factory.parse.AbstractParseFactory;
import com.hys.hsp.base.factory.parse.AbstractParseFactoryProducer;
import com.hys.hsp.base.parse.AbstractParse;
import com.hys.hsp.utils.Parse;

import java.io.Serializable;

/**
 * Oracle的parse工厂生产者
 *
 * @author Robert Hou
 * @date 2018年5月23日
 */
public class OracleParseFactoryProducer extends AbstractParseFactoryProducer implements Serializable {

    private static class OracleParseFactoryProducerLazyHolder {

        private static final OracleParseFactoryProducer INSTANCE = new OracleParseFactoryProducer();
    }

    private OracleParseFactoryProducer() {
    }

    private static OracleParseFactoryProducer getInstance() {
        return OracleParseFactoryProducerLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return OracleParseFactoryProducerLazyHolder.INSTANCE;
    }

    @Override
    public AbstractParse getParseInstance(Parse type) {
        AbstractParse parse;
        AbstractParseFactory oracleParseFactory = OracleParseFactory.getInstance();
        switch (type) {
            case SOURCE:
                parse = oracleParseFactory.getSourceInstance();
                break;
            case TARGET:
                parse = oracleParseFactory.getTargetInstance();
                break;
            default:
                throw new UnsupportedOperationException("不兼容的类型：" + type);
        }
        return parse;
    }
}
