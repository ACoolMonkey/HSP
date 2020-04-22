package com.hys.hsp.dbtype.greenplum.sqlinfo;

import com.hys.hsp.base.sqlinfo.BaseCreateSqlInfo;

import java.io.Serializable;

/**
 * Greenplum的create开头sql信息类
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class GreenplumCreateSqlInfo extends BaseCreateSqlInfo implements Serializable {

    private static class GreenplumCreateSqlInfoLazyHolder {

        private static final GreenplumCreateSqlInfo INSTANCE = new GreenplumCreateSqlInfo();
    }

    private GreenplumCreateSqlInfo() {
    }

    public static GreenplumCreateSqlInfo getInstance() {
        return GreenplumCreateSqlInfoLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return GreenplumCreateSqlInfoLazyHolder.INSTANCE;
    }
}
