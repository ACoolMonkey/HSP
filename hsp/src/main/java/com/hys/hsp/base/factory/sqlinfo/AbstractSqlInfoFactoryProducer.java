package com.hys.hsp.base.factory.sqlinfo;

import com.hys.hsp.base.sqlinfo.AbstractSqlInfo;
import com.hys.hsp.utils.SqlInfo;

/**
 * sqlinfo工厂生产者
 *
 * @author Robert Hou
 * @date 2018年5月23日
 */
public abstract class AbstractSqlInfoFactoryProducer {

    /**
     * 获取sqlinfo实例
     */
    public abstract AbstractSqlInfo getSqlInfoInstance(SqlInfo type);
}
