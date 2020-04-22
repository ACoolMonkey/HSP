package com.hys.hsp.base.parse;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 目标解析
 *
 * @author Robert Hou
 * @date 2018年5月18日
 */
public abstract class BaseTargetParse implements AbstractParse {

    private static final Pattern TARGET = Pattern.compile("\\s*([0-9a-zA-Z_]*(\\.)?[0-9a-zA-Z_]+)(\\()?");

    @Override
    public final List<List<String>> parse(List<String> sqlList, String defaultSchema) {
        List<List<String>> returnList = new ArrayList<>();
        for (String sql : sqlList) {
            List<String> targetList = new ArrayList<>();
            StringBuilder targetSqlStringBuilder = new StringBuilder();
            Matcher m = TARGET.matcher(sql);
            if (m.find()) {
                targetSqlStringBuilder.append(m.group(1));
                //如果没有库名只有表名
                if (!targetSqlStringBuilder.toString().contains(".") && defaultSchema != null) {
                    targetSqlStringBuilder.insert(0, defaultSchema);
                }
                targetList.add(targetSqlStringBuilder.toString());
                targetSqlStringBuilder.delete(0, targetSqlStringBuilder.length());
            }
            returnList.add(targetList);
        }
        return returnList;
    }
}
