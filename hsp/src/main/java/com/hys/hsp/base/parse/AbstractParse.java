package com.hys.hsp.base.parse;

import java.util.List;

/**
 * 抽象解析类
 *
 * @author Robert Hou
 * @date 2018年5月18日
 */
public interface AbstractParse {

    /**
     * 正则解析
     */
    List<List<String>> parse(List<String> sqlList, String defaultSchema);
}
