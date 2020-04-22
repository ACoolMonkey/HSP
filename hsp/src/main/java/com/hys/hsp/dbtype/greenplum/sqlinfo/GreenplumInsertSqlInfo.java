package com.hys.hsp.dbtype.greenplum.sqlinfo;

import com.hys.hsp.base.sqlinfo.BaseInsertSqlInfo;

import java.io.Serializable;

/**
 * Greenplum的insert开头sql信息类
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class GreenplumInsertSqlInfo extends BaseInsertSqlInfo implements Serializable {

    private static class GreenplumInsertSqlInfoLazyHolder {

        private static final GreenplumInsertSqlInfo INSTANCE = new GreenplumInsertSqlInfo();
    }

    private GreenplumInsertSqlInfo() {
    }

    public static GreenplumInsertSqlInfo getInstance() {
        return GreenplumInsertSqlInfoLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return GreenplumInsertSqlInfoLazyHolder.INSTANCE;
    }
}
