package com.hys.hsp.dbtype.greenplum.sqlinfo;

import com.hys.hsp.base.sqlinfo.BaseUpdateSqlInfo;

import java.io.Serializable;

/**
 * Greenplum的update开头sql信息类
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class GreenplumUpdateSqlInfo extends BaseUpdateSqlInfo implements Serializable {

    private static class GreenplumUpdateSqlInfoLazyHolder {

        private static final GreenplumUpdateSqlInfo INSTANCE = new GreenplumUpdateSqlInfo();
    }

    private GreenplumUpdateSqlInfo() {
    }

    public static GreenplumUpdateSqlInfo getInstance() {
        return GreenplumUpdateSqlInfoLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return GreenplumUpdateSqlInfoLazyHolder.INSTANCE;
    }
}
