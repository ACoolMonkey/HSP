package com.hys.hsp.dbtype.greenplum.parse;

import com.hys.hsp.base.parse.BaseTargetParse;

import java.io.Serializable;

/**
 * Greenplum目标解析
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class GreenplumTargetParse extends BaseTargetParse implements Serializable {

    private static class GreenplumTargetParseLazyHolder {

        private static final GreenplumTargetParse INSTANCE = new GreenplumTargetParse();
    }

    private GreenplumTargetParse() {
    }

    public static GreenplumTargetParse getInstance() {
        return GreenplumTargetParseLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return GreenplumTargetParseLazyHolder.INSTANCE;
    }
}
