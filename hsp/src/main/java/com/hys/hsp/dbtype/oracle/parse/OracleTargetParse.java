package com.hys.hsp.dbtype.oracle.parse;

import com.hys.hsp.base.parse.BaseTargetParse;

import java.io.Serializable;

/**
 * Oracle目标解析
 *
 * @author Robert Hou
 * @date 2018年5月18日
 */
public class OracleTargetParse extends BaseTargetParse implements Serializable {

    private static class OracleTargetParseLazyHolder {

        private static final OracleTargetParse INSTANCE = new OracleTargetParse();
    }

    private OracleTargetParse() {
    }

    public static OracleTargetParse getInstance() {
        return OracleTargetParseLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return OracleTargetParseLazyHolder.INSTANCE;
    }
}
