package com.hys.hsp.dbtype.greenplum.sqlinfo;

import com.hys.hsp.base.sqlinfo.BaseMergeSqlInfo;

import java.io.Serializable;

/**
 * Greenplum的merge开头sql信息类
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class GreenplumMergeSqlInfo extends BaseMergeSqlInfo implements Serializable {

    private static class GreenplumMergeSqlInfoLazyHolder {

        private static final GreenplumMergeSqlInfo INSTANCE = new GreenplumMergeSqlInfo();
    }

    private GreenplumMergeSqlInfo() {
    }

    public static GreenplumMergeSqlInfo getInstance() {
        return GreenplumMergeSqlInfoLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return GreenplumMergeSqlInfoLazyHolder.INSTANCE;
    }
}
