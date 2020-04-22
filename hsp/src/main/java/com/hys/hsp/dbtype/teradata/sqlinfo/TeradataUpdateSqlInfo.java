package com.hys.hsp.dbtype.teradata.sqlinfo;

import com.hys.hsp.base.sqlinfo.BaseUpdateSqlInfo;

import java.io.Serializable;

/**
 * Teradata的update开头sql信息类
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class TeradataUpdateSqlInfo extends BaseUpdateSqlInfo implements Serializable {

    private static class TeradataUpdateSqlInfoLazyHolder {

        private static final TeradataUpdateSqlInfo INSTANCE = new TeradataUpdateSqlInfo();
    }

    private TeradataUpdateSqlInfo() {
    }

    public static TeradataUpdateSqlInfo getInstance() {
        return TeradataUpdateSqlInfoLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return TeradataUpdateSqlInfoLazyHolder.INSTANCE;
    }
}
