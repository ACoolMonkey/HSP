package com.hys.hsp.dbtype.oracle.sqlinfo;

import com.hys.hsp.base.sqlinfo.BaseCreateSqlInfo;

import java.io.Serializable;

/**
 * Oracle的create开头sql信息类
 *
 * @author Robert Hou
 * @date 2018年5月18日
 */
public class OracleCreateSqlInfo extends BaseCreateSqlInfo implements Serializable {

    private static class OracleCreateSqlInfoLazyHolder {

        private static final OracleCreateSqlInfo INSTANCE = new OracleCreateSqlInfo();
    }

    private OracleCreateSqlInfo() {
    }

    public static OracleCreateSqlInfo getInstance() {
        return OracleCreateSqlInfoLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return OracleCreateSqlInfoLazyHolder.INSTANCE;
    }
}
