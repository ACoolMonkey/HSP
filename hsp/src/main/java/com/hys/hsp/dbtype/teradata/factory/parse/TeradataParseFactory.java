package com.hys.hsp.dbtype.teradata.factory.parse;

import com.hys.hsp.base.factory.parse.AbstractParseFactory;
import com.hys.hsp.base.parse.AbstractParse;
import com.hys.hsp.dbtype.teradata.parse.TeradataSourceParse;
import com.hys.hsp.dbtype.teradata.parse.TeradataTargetParse;

import java.io.Serializable;

/**
 * Teradata工厂
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class TeradataParseFactory implements AbstractParseFactory, Serializable {

    private static class TeradataParseFactoryLazyHolder {

        private static final TeradataParseFactory INSTANCE = new TeradataParseFactory();
    }

    private TeradataParseFactory() {
    }

    static TeradataParseFactory getInstance() {
        return TeradataParseFactoryLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return TeradataParseFactoryLazyHolder.INSTANCE;
    }

    @Override
    public AbstractParse getSourceInstance() {
        return TeradataSourceParse.getInstance();
    }

    @Override
    public AbstractParse getTargetInstance() {
        return TeradataTargetParse.getInstance();
    }
}
