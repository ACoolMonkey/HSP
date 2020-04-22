package com.hys.hsp.utils;

import com.hys.hsp.base.factory.parse.AbstractParseFactoryProducer;
import com.hys.hsp.base.factory.sqlinfo.AbstractSqlInfoFactoryProducer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * sqlinfo和parse工厂接收者
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
@Data
public class FactoryProducer implements Serializable {

    /**
     * sqlinfo工厂接收者
     */
    private AbstractSqlInfoFactoryProducer sqlInfoFactoryProducer;
    /**
     * parse工厂接收者
     */
    private AbstractParseFactoryProducer parseFactoryProducer;

    private static class FactoryProducerLazyHolder {

        private static final FactoryProducer INSTANCE = new FactoryProducer();
    }

    private FactoryProducer() {
    }

    static FactoryProducer getInstance() {
        return FactoryProducerLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return FactoryProducerLazyHolder.INSTANCE;
    }
}
