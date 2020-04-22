package com.hys.hsp.base.factory.parse;

import com.hys.hsp.base.parse.AbstractParse;
import com.hys.hsp.utils.Parse;

/**
 * parse工厂生产者
 *
 * @author Robert Hou
 * @date 2018年5月23日
 */
public abstract class AbstractParseFactoryProducer {

    /**
     * 获取parse实例
     */
    public abstract AbstractParse getParseInstance(Parse type);
}
