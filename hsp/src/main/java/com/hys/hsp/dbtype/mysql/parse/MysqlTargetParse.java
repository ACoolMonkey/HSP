package com.hys.hsp.dbtype.mysql.parse;

import com.hys.hsp.base.parse.BaseTargetParse;

import java.io.Serializable;

/**
 * Mysql目标解析
 *
 * @author Robert Hou
 * @date 2018年5月18日
 */
public class MysqlTargetParse extends BaseTargetParse implements Serializable {

    private static class MysqlTargetParseLazyHolder {

        private static final MysqlTargetParse INSTANCE = new MysqlTargetParse();
    }

    private MysqlTargetParse() {
    }

    public static MysqlTargetParse getInstance() {
        return MysqlTargetParseLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return MysqlTargetParseLazyHolder.INSTANCE;
    }
}
