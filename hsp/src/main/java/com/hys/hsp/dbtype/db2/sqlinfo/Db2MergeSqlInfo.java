package com.hys.hsp.dbtype.db2.sqlinfo;

import com.hys.hsp.base.sqlinfo.BaseMergeSqlInfo;

import java.io.Serializable;

/**
 * Db2的merge开头sql信息类
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class Db2MergeSqlInfo extends BaseMergeSqlInfo implements Serializable {

    private static class Db2MergeSqlInfoLazyHolder {

        private static final Db2MergeSqlInfo INSTANCE = new Db2MergeSqlInfo();
    }

    private Db2MergeSqlInfo() {
    }

    public static Db2MergeSqlInfo getInstance() {
        return Db2MergeSqlInfoLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return Db2MergeSqlInfoLazyHolder.INSTANCE;
    }
}
