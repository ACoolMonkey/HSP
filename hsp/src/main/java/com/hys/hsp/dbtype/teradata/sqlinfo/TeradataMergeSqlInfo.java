package com.hys.hsp.dbtype.teradata.sqlinfo;

import com.hys.hsp.base.sqlinfo.BaseMergeSqlInfo;

import java.io.Serializable;

/**
 * Teradata的merge开头sql信息类
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class TeradataMergeSqlInfo extends BaseMergeSqlInfo implements Serializable {

    private static class TeradataMergeSqlInfoLazyHolder {

        private static final TeradataMergeSqlInfo INSTANCE = new TeradataMergeSqlInfo();
    }

    private TeradataMergeSqlInfo() {
    }

    public static TeradataMergeSqlInfo getInstance() {
        return TeradataMergeSqlInfoLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return TeradataMergeSqlInfoLazyHolder.INSTANCE;
    }
}
