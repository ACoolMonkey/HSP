package com.hys.hsp.base.factory.parse;

import com.hys.hsp.base.parse.AbstractParse;

/**
 * parse抽象工厂
 *
 * @author Robert Hou
 * @date 2018年5月18日
 */
public interface AbstractParseFactory {

    /**
     * 获取源实例
     */
    AbstractParse getSourceInstance();

    /**
     * 获目标实例
     */
    AbstractParse getTargetInstance();
}
