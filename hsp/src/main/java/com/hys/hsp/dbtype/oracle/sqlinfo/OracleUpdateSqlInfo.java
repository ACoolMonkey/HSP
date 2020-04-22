package com.hys.hsp.dbtype.oracle.sqlinfo;

import com.hys.hsp.base.sqlinfo.BaseUpdateSqlInfo;

import java.io.Serializable;

/**
 * Oracle的update开头sql信息类
 *
 * @author Robert Hou
 * @date 2018年5月18日
 */
public class OracleUpdateSqlInfo extends BaseUpdateSqlInfo implements Serializable {

    private static class OracleUpdateSqlInfoLazyHolder {

        private static final OracleUpdateSqlInfo INSTANCE = new OracleUpdateSqlInfo();
    }

    private OracleUpdateSqlInfo() {
    }

    public static OracleUpdateSqlInfo getInstance() {
        return OracleUpdateSqlInfoLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return OracleUpdateSqlInfoLazyHolder.INSTANCE;
    }
}
