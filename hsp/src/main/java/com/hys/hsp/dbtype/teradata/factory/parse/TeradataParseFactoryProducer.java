package com.hys.hsp.dbtype.teradata.factory.parse;

import com.hys.hsp.base.factory.parse.AbstractParseFactory;
import com.hys.hsp.base.factory.parse.AbstractParseFactoryProducer;
import com.hys.hsp.base.parse.AbstractParse;
import com.hys.hsp.utils.Parse;

import java.io.Serializable;

/**
 * Teradata的parse工厂生产者
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class TeradataParseFactoryProducer extends AbstractParseFactoryProducer implements Serializable {

    private static class TeradataParseFactoryProducerLazyHolder {

        private static final TeradataParseFactoryProducer INSTANCE = new TeradataParseFactoryProducer();
    }

    private TeradataParseFactoryProducer() {
    }

    private static TeradataParseFactoryProducer getInstance() {
        return TeradataParseFactoryProducerLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return TeradataParseFactoryProducerLazyHolder.INSTANCE;
    }

    @Override
    public AbstractParse getParseInstance(Parse type) {
        AbstractParse parse;
        AbstractParseFactory teradataParseFactory = TeradataParseFactory.getInstance();
        switch (type) {
            case SOURCE:
                parse = teradataParseFactory.getSourceInstance();
                break;
            case TARGET:
                parse = teradataParseFactory.getTargetInstance();
                break;
            default:
                throw new UnsupportedOperationException("不兼容的类型：" + type);
        }
        return parse;
    }
}
