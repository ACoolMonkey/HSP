package com.hys.hsp.dbtype.greenplum.factory.parse;

import com.hys.hsp.base.factory.parse.AbstractParseFactory;
import com.hys.hsp.base.parse.AbstractParse;
import com.hys.hsp.dbtype.greenplum.parse.GreenplumSourceParse;
import com.hys.hsp.dbtype.greenplum.parse.GreenplumTargetParse;

import java.io.Serializable;

/**
 * Greenplum工厂
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class GreenplumParseFactory implements AbstractParseFactory, Serializable {

    private static class GreenplumParseFactoryLazyHolder {

        private static final GreenplumParseFactory INSTANCE = new GreenplumParseFactory();
    }

    private GreenplumParseFactory() {
    }

    static GreenplumParseFactory getInstance() {
        return GreenplumParseFactoryLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return GreenplumParseFactoryLazyHolder.INSTANCE;
    }

    @Override
    public AbstractParse getSourceInstance() {
        return GreenplumSourceParse.getInstance();
    }

    @Override
    public AbstractParse getTargetInstance() {
        return GreenplumTargetParse.getInstance();
    }
}
