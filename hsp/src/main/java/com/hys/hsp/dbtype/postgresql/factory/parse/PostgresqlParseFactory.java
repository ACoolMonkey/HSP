package com.hys.hsp.dbtype.postgresql.factory.parse;

import com.hys.hsp.base.factory.parse.AbstractParseFactory;
import com.hys.hsp.base.parse.AbstractParse;
import com.hys.hsp.dbtype.postgresql.parse.PostgresqlSourceParse;
import com.hys.hsp.dbtype.postgresql.parse.PostgresqlTargetParse;

import java.io.Serializable;

/**
 * Postgresql工厂
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class PostgresqlParseFactory implements AbstractParseFactory, Serializable {

    private static class PostgresqlParseFactoryLazyHolder {

        private static final PostgresqlParseFactory INSTANCE = new PostgresqlParseFactory();
    }

    private PostgresqlParseFactory() {
    }

    static PostgresqlParseFactory getInstance() {
        return PostgresqlParseFactoryLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return PostgresqlParseFactoryLazyHolder.INSTANCE;
    }

    @Override
    public AbstractParse getSourceInstance() {
        return PostgresqlSourceParse.getInstance();
    }

    @Override
    public AbstractParse getTargetInstance() {
        return PostgresqlTargetParse.getInstance();
    }
}
