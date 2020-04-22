package com.hys.hsp.dbtype.teradata.parse;

import com.hys.hsp.base.parse.BaseSourceParse;

import java.io.Serializable;

/**
 * Teradata源解析
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class TeradataSourceParse extends BaseSourceParse implements Serializable {

    private static class TeradataSourceParseLazyHolder {

        private static final TeradataSourceParse INSTANCE = new TeradataSourceParse();
    }

    private TeradataSourceParse() {
    }

    public static TeradataSourceParse getInstance() {
        return TeradataSourceParseLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return TeradataSourceParseLazyHolder.INSTANCE;
    }
}
