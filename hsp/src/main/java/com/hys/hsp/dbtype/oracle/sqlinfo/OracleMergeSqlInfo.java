package com.hys.hsp.dbtype.oracle.sqlinfo;

import com.hys.hsp.base.sqlinfo.BaseMergeSqlInfo;

import java.io.Serializable;

/**
 * Oracle的merge开头sql信息类
 *
 * @author Robert Hou
 * @date 2018年5月18日
 */
public class OracleMergeSqlInfo extends BaseMergeSqlInfo implements Serializable {

    private static class OracleMergeSqlInfoLazyHolder {

        private static final OracleMergeSqlInfo INSTANCE = new OracleMergeSqlInfo();
    }

    private OracleMergeSqlInfo() {
    }

    public static OracleMergeSqlInfo getInstance() {
        return OracleMergeSqlInfoLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return OracleMergeSqlInfoLazyHolder.INSTANCE;
    }
}
