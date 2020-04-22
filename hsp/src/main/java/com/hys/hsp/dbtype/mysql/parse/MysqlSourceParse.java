package com.hys.hsp.dbtype.mysql.parse;

import com.hys.hsp.base.parse.BaseSourceParse;

import java.io.Serializable;

/**
 * Mysql源解析
 *
 * @author Robert Hou
 * @date 2018年5月18日
 */
public class MysqlSourceParse extends BaseSourceParse implements Serializable {

    private static class MysqlSourceParseLazyHolder {

        private static final MysqlSourceParse INSTANCE = new MysqlSourceParse();
    }

    private MysqlSourceParse() {
    }

    public static MysqlSourceParse getInstance() {
        return MysqlSourceParseLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return MysqlSourceParseLazyHolder.INSTANCE;
    }
}
