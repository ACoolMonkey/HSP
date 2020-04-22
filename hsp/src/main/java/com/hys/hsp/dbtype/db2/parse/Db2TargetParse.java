package com.hys.hsp.dbtype.db2.parse;

import com.hys.hsp.base.parse.BaseTargetParse;

import java.io.Serializable;

/**
 * Db2目标解析
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class Db2TargetParse extends BaseTargetParse implements Serializable {

    private static class Db2TargetParseLazyHolder {

        private static final Db2TargetParse INSTANCE = new Db2TargetParse();
    }

    private Db2TargetParse() {
    }

    public static Db2TargetParse getInstance() {
        return Db2TargetParseLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return Db2TargetParseLazyHolder.INSTANCE;
    }
}
