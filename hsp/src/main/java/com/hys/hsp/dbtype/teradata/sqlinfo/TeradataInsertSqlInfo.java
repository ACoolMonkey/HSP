package com.hys.hsp.dbtype.teradata.sqlinfo;

import com.hys.hsp.base.sqlinfo.BaseInsertSqlInfo;

import java.io.Serializable;

/**
 * Teradata的insert开头sql信息类
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class TeradataInsertSqlInfo extends BaseInsertSqlInfo implements Serializable {

    private static class TeradataInsertSqlInfoLazyHolder {

        private static final TeradataInsertSqlInfo INSTANCE = new TeradataInsertSqlInfo();
    }

    private TeradataInsertSqlInfo() {
    }

    public static TeradataInsertSqlInfo getInstance() {
        return TeradataInsertSqlInfoLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return TeradataInsertSqlInfoLazyHolder.INSTANCE;
    }
}
