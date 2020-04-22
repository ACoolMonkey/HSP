package com.hys.hsp.dbtype.db2.sqlinfo;

import com.hys.hsp.base.sqlinfo.BaseUpdateSqlInfo;

import java.io.Serializable;

/**
 * Db2的update开头sql信息类
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class Db2UpdateSqlInfo extends BaseUpdateSqlInfo implements Serializable {

    private static class Db2UpdateSqlInfoLazyHolder {

        private static final Db2UpdateSqlInfo INSTANCE = new Db2UpdateSqlInfo();
    }

    private Db2UpdateSqlInfo() {
    }

    public static Db2UpdateSqlInfo getInstance() {
        return Db2UpdateSqlInfoLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return Db2UpdateSqlInfoLazyHolder.INSTANCE;
    }
}
