package com.hys.hsp.dbtype.db2.sqlinfo;

import com.hys.hsp.base.sqlinfo.BaseInsertSqlInfo;
import com.hys.hsp.utils.SpecialFlag;

import java.io.Serializable;

/**
 * Db2的insert开头sql信息类
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class Db2InsertSqlInfo extends BaseInsertSqlInfo implements Serializable {

    private static final String SPLIT_SQL = "INSERT\\s+INTO(.*?)\\s*(WITH\\s*(.*?)\\)\\s+AS\\s+\\((.*?)\\))?\\s*(SELECT\\s+.*)";

    private static class Db2InsertSqlInfoLazyHolder {

        private static final Db2InsertSqlInfo INSTANCE = new Db2InsertSqlInfo();
    }

    private Db2InsertSqlInfo() {
    }

    public static Db2InsertSqlInfo getInstance() {
        return Db2InsertSqlInfoLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return Db2InsertSqlInfoLazyHolder.INSTANCE;
    }

    @Override
    protected String getSplitSql() {
        return SPLIT_SQL;
    }

    @Override
    protected SpecialFlag getSpecialFlag() {
        return SpecialFlag.db2Insert;
    }
}
