package com.hys.hsp.base.factory.sqlinfo;

import com.hys.hsp.base.sqlinfo.AbstractSqlInfo;

/**
 * sqlinfo抽象工厂
 *
 * @author Robert Hou
 * @date 2018年5月18日
 */
public interface AbstractSqlInfoFactory {

    /**
     * 获取create实例
     */

    AbstractSqlInfo getCreateInstance();

    /**
     * 获取insert实例
     */
    AbstractSqlInfo getInsertInstance();

    /**
     * 获取merge实例
     */
    AbstractSqlInfo getMergeInstance();

    /**
     * 获取update实例
     */
    AbstractSqlInfo getUpdateInstance();
}
