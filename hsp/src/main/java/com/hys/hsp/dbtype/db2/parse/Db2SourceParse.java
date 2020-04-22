package com.hys.hsp.dbtype.db2.parse;

import com.hys.hsp.base.parse.BaseSourceParse;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Db2源解析
 *
 * @author Robert Hou
 * @date 2018年5月30日
 */
public class Db2SourceParse extends BaseSourceParse implements Serializable {

    private static final Pattern LIKE = Pattern.compile("\\s*LIKE\\s*([0-9a-zA-Z_]*(\\.)?[0-9a-zA-Z_]+)");

    private static class Db2SourceParseLazyHolder {

        private static final Db2SourceParse INSTANCE = new Db2SourceParse();
    }

    private Db2SourceParse() {
    }

    public static Db2SourceParse getInstance() {
        return Db2SourceParseLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return Db2SourceParseLazyHolder.INSTANCE;
    }

    @Override
    protected Set<String> db2PartParse(String sql, String defaultSchema) {
        //如果是db2的DECLARE GLOBAL TEMPORARY TABLE语法
        Set<String> sourceSet = new HashSet<>();
        StringBuilder fromSqlStringBuilder = new StringBuilder();
        Matcher m = LIKE.matcher(sql);
        if (m.find()) {
            fromSqlStringBuilder.append(m.group(1));
            //如果没有库名只有表名
            if (!fromSqlStringBuilder.toString().contains(".") && defaultSchema != null) {
                fromSqlStringBuilder.insert(0, defaultSchema);
            }
            sourceSet.add(fromSqlStringBuilder.toString());
            fromSqlStringBuilder.delete(0, fromSqlStringBuilder.length());
        }
        return sourceSet;
    }
}
