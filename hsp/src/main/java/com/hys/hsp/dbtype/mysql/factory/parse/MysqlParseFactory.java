package com.hys.hsp.dbtype.mysql.factory.parse;

import com.hys.hsp.base.factory.parse.AbstractParseFactory;
import com.hys.hsp.base.parse.AbstractParse;
import com.hys.hsp.dbtype.mysql.parse.MysqlSourceParse;
import com.hys.hsp.dbtype.mysql.parse.MysqlTargetParse;

import java.io.Serializable;

/**
 * Mysql工厂
 *
 * @author Robert Hou
 * @date 2018年5月18日
 */
public class MysqlParseFactory implements AbstractParseFactory, Serializable {

    private static class MysqlParseFactoryLazyHolder {

        private static final MysqlParseFactory INSTANCE = new MysqlParseFactory();
    }

    private MysqlParseFactory() {
    }

    static MysqlParseFactory getInstance() {
        return MysqlParseFactoryLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return MysqlParseFactoryLazyHolder.INSTANCE;
    }

    @Override
    public AbstractParse getSourceInstance() {
        return MysqlSourceParse.getInstance();
    }

    @Override
    public AbstractParse getTargetInstance() {
        return MysqlTargetParse.getInstance();
    }
}
