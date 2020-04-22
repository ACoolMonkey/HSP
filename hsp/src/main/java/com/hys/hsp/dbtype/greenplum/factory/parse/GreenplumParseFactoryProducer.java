package com.hys.hsp.dbtype.greenplum.factory.parse;

import com.hys.hsp.base.factory.parse.AbstractParseFactory;
import com.hys.hsp.base.factory.parse.AbstractParseFactoryProducer;
import com.hys.hsp.base.parse.AbstractParse;
import com.hys.hsp.utils.Parse;

import java.io.Serializable;

/**
 * Greenplum的parse工厂生产者
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class GreenplumParseFactoryProducer extends AbstractParseFactoryProducer implements Serializable {

    private static class GreenplumParseFactoryProducerLazyHolder {

        private static final GreenplumParseFactoryProducer INSTANCE = new GreenplumParseFactoryProducer();
    }

    private GreenplumParseFactoryProducer() {
    }

    private static GreenplumParseFactoryProducer getInstance() {
        return GreenplumParseFactoryProducerLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return GreenplumParseFactoryProducerLazyHolder.INSTANCE;
    }

    @Override
    public AbstractParse getParseInstance(Parse type) {
        AbstractParse parse;
        AbstractParseFactory greenplumParseFactory = GreenplumParseFactory.getInstance();
        switch (type) {
            case SOURCE:
                parse = greenplumParseFactory.getSourceInstance();
                break;
            case TARGET:
                parse = greenplumParseFactory.getTargetInstance();
                break;
            default:
                throw new IllegalStateException("不兼容的类型：" + type);
        }
        return parse;
    }
}
