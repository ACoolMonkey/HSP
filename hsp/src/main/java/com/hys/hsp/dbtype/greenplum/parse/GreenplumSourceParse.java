package com.hys.hsp.dbtype.greenplum.parse;

import com.hys.hsp.base.parse.BaseSourceParse;

import java.io.Serializable;

/**
 * Greenplum源解析
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class GreenplumSourceParse extends BaseSourceParse implements Serializable {

    private static class GreenplumSourceParseLazyHolder {

        private static final GreenplumSourceParse INSTANCE = new GreenplumSourceParse();
    }

    private GreenplumSourceParse() {
    }

    public static GreenplumSourceParse getInstance() {
        return GreenplumSourceParseLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return GreenplumSourceParseLazyHolder.INSTANCE;
    }
}
