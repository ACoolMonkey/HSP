package com.hys.hsp.dbtype.oracle.factory.parse;

import com.hys.hsp.base.factory.parse.AbstractParseFactory;
import com.hys.hsp.base.parse.AbstractParse;
import com.hys.hsp.dbtype.oracle.parse.OracleSourceParse;
import com.hys.hsp.dbtype.oracle.parse.OracleTargetParse;

import java.io.Serializable;

/**
 * Oracle工厂
 *
 * @author Robert Hou
 * @date 2018年5月18日
 */
public class OracleParseFactory implements AbstractParseFactory, Serializable {

    private static class OracleParseFactoryLazyHolder {

        private static final OracleParseFactory INSTANCE = new OracleParseFactory();
    }

    private OracleParseFactory() {
    }

    static OracleParseFactory getInstance() {
        return OracleParseFactoryLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return OracleParseFactoryLazyHolder.INSTANCE;
    }

    @Override
    public AbstractParse getSourceInstance() {
        return OracleSourceParse.getInstance();
    }

    @Override
    public AbstractParse getTargetInstance() {
        return OracleTargetParse.getInstance();
    }
}
