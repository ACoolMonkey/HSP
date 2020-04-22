package com.hys.hsp.dbtype.db2.factory.parse;

import com.hys.hsp.base.factory.parse.AbstractParseFactory;
import com.hys.hsp.base.parse.AbstractParse;
import com.hys.hsp.dbtype.db2.parse.Db2SourceParse;
import com.hys.hsp.dbtype.db2.parse.Db2TargetParse;

import java.io.Serializable;

/**
 * Db2工厂
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class Db2ParseFactory implements AbstractParseFactory, Serializable {

    private static class Db2ParseFactoryLazyHolder {

        private static final Db2ParseFactory INSTANCE = new Db2ParseFactory();
    }

    private Db2ParseFactory() {
    }

    static Db2ParseFactory getInstance() {
        return Db2ParseFactoryLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return Db2ParseFactoryLazyHolder.INSTANCE;
    }

    @Override
    public AbstractParse getSourceInstance() {
        return Db2SourceParse.getInstance();
    }

    @Override
    public AbstractParse getTargetInstance() {
        return Db2TargetParse.getInstance();
    }
}
