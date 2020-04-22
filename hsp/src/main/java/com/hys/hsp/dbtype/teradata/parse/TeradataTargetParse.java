package com.hys.hsp.dbtype.teradata.parse;

import com.hys.hsp.base.parse.BaseTargetParse;

import java.io.Serializable;

/**
 * Teradata目标解析
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class TeradataTargetParse extends BaseTargetParse implements Serializable {

    private static class TeradataTargetParseLazyHolder {

        private static final TeradataTargetParse INSTANCE = new TeradataTargetParse();
    }

    private TeradataTargetParse() {
    }

    public static TeradataTargetParse getInstance() {
        return TeradataTargetParseLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return TeradataTargetParseLazyHolder.INSTANCE;
    }
}
