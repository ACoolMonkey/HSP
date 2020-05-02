package com.hys.hsp.base.sqlinfo;

import com.hys.hsp.utils.SqlInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 抽象sql信息类（存放sql相关信息）
 *
 * @author Robert Hou
 * @date 2018年5月18日
 */
@Data
public abstract class AbstractSqlInfo implements Cloneable {

    /**
     * sql语句
     */
    private String sql;
    /**
     * sql源代码片段
     */
    private List<String> sourceSql;
    /**
     * sql目标代码片段
     */
    private List<String> targetSql;
    /**
     * sql源库表名
     */
    private List<String> source;
    /**
     * sql目标库表名
     */
    private List<String> target;
    /**
     * 目标库表是否是临时表标志位
     */
    private boolean isTemp;

    /**
     * sql拆分出源和目标代码片段
     */
    public abstract boolean splitSql();

    /**
     * 获取sqlinfo标志位
     */
    protected abstract SqlInfo getSqlInfoType();

    /**
     * 获取SPLIT_SQL
     */
    protected abstract String getSplitSql();

    /**
     * 获取SPLIT_KEY
     */
    protected abstract String getSplitKey();

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        result = prime * result + ((target == null) ? 0 : target.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        AbstractSqlInfo other = (AbstractSqlInfo) obj;
        if (source == null) {
            if (other.source != null) {
                return false;
            }
        } else if (!source.equals(other.source)) {
            return false;
        }
        if (target == null) {
            return other.target == null;
        } else {
            return target.equals(other.target);
        }
    }

    @Override
    public AbstractSqlInfo clone() {
        AbstractSqlInfo sqlInfo;
        try {
            sqlInfo = (AbstractSqlInfo) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return sqlInfo;
    }

    /**
     * 清空sqlinfo属性值
     */
    public void clearSqlInfo() {
        setSql(null);
        setSourceSql(null);
        setTargetSql(null);
        setSource(null);
        setTarget(null);
        setTemp(false);
    }
}
