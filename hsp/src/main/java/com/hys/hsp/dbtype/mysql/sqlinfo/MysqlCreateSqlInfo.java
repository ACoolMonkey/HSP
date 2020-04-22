package com.hys.hsp.dbtype.mysql.sqlinfo;

import com.hys.hsp.base.sqlinfo.BaseCreateSqlInfo;

import java.io.Serializable;

/**
 * Mysql的create开头sql信息类
 *
 * @author Robert Hou
 * @date 2018年5月18日
 */
public class MysqlCreateSqlInfo extends BaseCreateSqlInfo implements Serializable {

    private static class MysqlCreateSqlInfoLazyHolder {

        private static final MysqlCreateSqlInfo INSTANCE = new MysqlCreateSqlInfo();
    }

    private MysqlCreateSqlInfo() {
    }

    public static MysqlCreateSqlInfo getInstance() {
        return MysqlCreateSqlInfoLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return MysqlCreateSqlInfoLazyHolder.INSTANCE;
    }
}
