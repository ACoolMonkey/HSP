package com.hys.hsp.dbtype.oracle.parse;

import com.hys.hsp.base.parse.BaseSourceParse;

import java.io.Serializable;

/**
 * Oracle源解析
 *
 * @author Robert Hou
 * @date 2018年5月18日
 */
public class OracleSourceParse extends BaseSourceParse implements Serializable {

    private static class OracleSourceParseLazyHolder {

        private static final OracleSourceParse INSTANCE = new OracleSourceParse();
    }

    private OracleSourceParse() {
    }

    public static OracleSourceParse getInstance() {
        return OracleSourceParseLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return OracleSourceParseLazyHolder.INSTANCE;
    }
}
