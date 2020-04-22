package com.hys.hsp.base.parse;

import com.hys.hsp.utils.BracketsStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 源解析
 *
 * @author Robert Hou
 * @date 2018年5月18日
 */
public abstract class BaseSourceParse implements AbstractParse {

    private static final Pattern FROM = Pattern.compile("\\s*FROM\\s*(\\()?([0-9a-zA-Z_]*(\\.)?[0-9a-zA-Z_]+)");
    private static final Pattern MULTI_FROM = Pattern.compile("\\s*FROM\\s*(\\()?([0-9a-zA-Z_]*(\\.)?[0-9a-zA-Z_]+\\s*(AS)?\\s*[0-9a-zA-Z_]*(,\\s*[0-9a-zA-Z_]*(\\.)?[0-9a-zA-Z_]+\\s*(AS)?\\s*[0-9a-zA-Z_]*)+)");
    private static final Pattern JOIN = Pattern.compile("\\s*JOIN\\s*([0-9a-zA-Z_]*(\\.)?[0-9a-zA-Z_]+)");
    private static final Pattern USING = Pattern.compile("\\s*USING\\s*([0-9a-zA-Z_]*(\\.)?[0-9a-zA-Z_]+)");
    private static final Pattern WHERE = Pattern.compile("WHERE\\s*((\\()?\\s*[0-9a-zA-Z_]*(\\.)?[0-9a-zA-Z_]+\\.[0-9a-zA-Z_]+\\s*(,\\s*[0-9a-zA-Z_]*(\\.)?[0-9a-zA-Z_]+\\.[0-9a-zA-Z_]+)*(\\))?\\s*IN\\s*)?(\\(.*)");
    private static final char LEFT_BRACKET = '(';
    private static final char RIGHT_BRACKET = ')';

    @Override
    public final List<List<String>> parse(List<String> sqlList, String defaultSchema) {
        List<List<String>> returnList = new ArrayList<>();
        for (String sql : sqlList) {
            Set<String> sourceSet = new HashSet<>();
            if (sql == null) {
                return Collections.emptyList();
            }
            //去掉带from的where子句
            sql = truncWhere(sql);
            if (sql.startsWith("USING")) {
                //如果是merge into语法
                sourceSet = partParse(sourceSet, sql, defaultSchema, USING);
            } else if (sql.startsWith("LIKE")) {
                sourceSet = db2PartParse(sql, defaultSchema);
            } else {
                //FROM
                StringBuilder fromSqlStringBuilder = new StringBuilder();
                Matcher m = FROM.matcher(sql);
                while (m.find()) {
                    fromSqlStringBuilder.append(m.group(2));
                    //如果没有库名只有表名
                    if (!fromSqlStringBuilder.toString().contains(".") && defaultSchema != null) {
                        fromSqlStringBuilder.insert(0, defaultSchema);
                    }
                    sourceSet.add(fromSqlStringBuilder.toString());
                    fromSqlStringBuilder.delete(0, fromSqlStringBuilder.length());
                }
                //MULTI_FROM
                StringBuilder multiFromSqlStringBuilder = new StringBuilder();
                StringBuilder partMultiFromSqlStringBuilder = new StringBuilder();
                m = MULTI_FROM.matcher(sql);
                while (m.find()) {
                    multiFromSqlStringBuilder.append(m.group(2));
                    String[] multiFromSqlList = multiFromSqlStringBuilder.toString().split(",");
                    for (String sl : multiFromSqlList) {
                        sl = sl.trim();
                        int blankIndex = sl.indexOf(" ");
                        if (blankIndex == -1) {
                            partMultiFromSqlStringBuilder.append(sl);
                        } else {
                            partMultiFromSqlStringBuilder.append(sl.substring(0, blankIndex));
                        }
                        //如果没有库名只有表名
                        if (!partMultiFromSqlStringBuilder.toString().contains(".") && defaultSchema != null) {
                            partMultiFromSqlStringBuilder.insert(0, defaultSchema);
                        }
                        sourceSet.add(partMultiFromSqlStringBuilder.toString());
                        partMultiFromSqlStringBuilder.delete(0, partMultiFromSqlStringBuilder.length());
                    }
                    multiFromSqlStringBuilder.delete(0, multiFromSqlStringBuilder.length());
                }
                //JOIN
                sourceSet = partParse(sourceSet, sql, defaultSchema, JOIN);
            }
            returnList.add(new ArrayList<>(sourceSet));
        }
        return returnList;
    }

    /**
     * db2的DECLARE GLOBAL TEMPORARY TABLE解析
     */
    protected Set<String> db2PartParse(String sql, String defaultSchema) {
        return null;
    }

    /**
     * 源sql片段去掉带from的where子句
     */
    private String truncWhere(String sql) {
        BracketsStack bracketsStack = BracketsStack.getInstance();
        StringBuilder sqlStringBuilder = new StringBuilder(sql);
        while (true) {
            boolean whereFlag = false;
            String bracketSql = null;
            int startIndex = 0;
            Matcher m = WHERE.matcher(sqlStringBuilder);
            if (m.find()) {
                whereFlag = true;
                int endIndex = 0;
                bracketSql = m.group(7);
                startIndex = m.start();
                for (int i = 0; i < bracketSql.length(); i++) {
                    if (bracketSql.charAt(i) == LEFT_BRACKET) {
                        bracketsStack.push(LEFT_BRACKET);
                    } else if (bracketSql.charAt(i) == RIGHT_BRACKET) {
                        bracketsStack.pop();
                    }
                    if (bracketsStack.isEmpty()) {
                        endIndex = i + 1;
                        break;
                    }
                }
                bracketSql = bracketSql.substring(endIndex);
            }
            if (!whereFlag) {
                break;
            }
            String subSql = sqlStringBuilder.substring(0, startIndex);
            sqlStringBuilder.delete(0, sqlStringBuilder.length());
            sqlStringBuilder.append(subSql);
            sqlStringBuilder.append(bracketSql);
        }
        return sqlStringBuilder.toString();
    }

    /**
     * 子解析过程（根据key适配）
     */
    private Set<String> partParse(Set<String> sourceSet, String sql, String defaultSchema, final Pattern key) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        Matcher m = key.matcher(sql);
        while (m.find()) {
            sqlStringBuilder.append(m.group(1));
            //如果没有库名只有表名
            if (!sqlStringBuilder.toString().contains(".") && defaultSchema != null) {
                sqlStringBuilder.insert(0, defaultSchema);
            }
            sourceSet.add(sqlStringBuilder.toString());
            sqlStringBuilder.delete(0, sqlStringBuilder.length());
        }
        return sourceSet;
    }
}
