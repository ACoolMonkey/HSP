package com.hys.hsp.dbtype.oracle.sqlinfo;

import com.hys.hsp.base.sqlinfo.BaseInsertSqlInfo;

import java.io.Serializable;

/**
 * Oracle的insert开头sql信息类
 *
 * @author Robert Hou
 * @date 2018年5月18日
 */
public class OracleInsertSqlInfo extends BaseInsertSqlInfo implements Serializable {

    private static class OracleInsertSqlInfoLazyHolder {

        private static final OracleInsertSqlInfo INSTANCE = new OracleInsertSqlInfo();
    }

    private OracleInsertSqlInfo() {
    }

    public static OracleInsertSqlInfo getInstance() {
        return OracleInsertSqlInfoLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return OracleInsertSqlInfoLazyHolder.INSTANCE;
    }
}
