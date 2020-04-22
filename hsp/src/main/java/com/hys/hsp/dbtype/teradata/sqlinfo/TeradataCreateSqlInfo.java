package com.hys.hsp.dbtype.teradata.sqlinfo;

import com.hys.hsp.base.sqlinfo.BaseCreateSqlInfo;

import java.io.Serializable;

/**
 * Teradata的create开头sql信息类
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class TeradataCreateSqlInfo extends BaseCreateSqlInfo implements Serializable {

    private static class TeradataCreateSqlInfoLazyHolder {

        private static final TeradataCreateSqlInfo INSTANCE = new TeradataCreateSqlInfo();
    }

    private TeradataCreateSqlInfo() {
    }

    public static TeradataCreateSqlInfo getInstance() {
        return TeradataCreateSqlInfoLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return TeradataCreateSqlInfoLazyHolder.INSTANCE;
    }
}
