package com.hys.hsp.dbtype.mysql.sqlinfo;

import com.hys.hsp.base.sqlinfo.BaseInsertSqlInfo;

import java.io.Serializable;

/**
 * Mysql的insert开头sql信息类
 *
 * @author Robert Hou
 * @date 2018年5月18日
 */
public class MysqlInsertSqlInfo extends BaseInsertSqlInfo implements Serializable {

    private static class MysqlInsertSqlInfoLazyHolder {

        private static final MysqlInsertSqlInfo INSTANCE = new MysqlInsertSqlInfo();
    }

    private MysqlInsertSqlInfo() {
    }

    public static MysqlInsertSqlInfo getInstance() {
        return MysqlInsertSqlInfoLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return MysqlInsertSqlInfoLazyHolder.INSTANCE;
    }
}
