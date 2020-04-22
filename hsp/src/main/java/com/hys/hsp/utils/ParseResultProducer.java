package com.hys.hsp.utils;

import com.hys.hsp.base.sqlinfo.AbstractSqlInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 解析结果接收者
 *
 * @author Robert Hou
 * @date 2018年6月28日
 */
@Data
public class ParseResultProducer implements Serializable {

    /**
     * 源解析结果
     */
    private List<String> sourceList;
    /**
     * 目标解析结果
     */
    private List<String> targetList;
    /**
     * db2解析结果
     */
    AbstractSqlInfo db2WithTempSqlInfo;

    private static class ParseResultProducerLazyHolder {

        private static final ParseResultProducer INSTANCE = new ParseResultProducer();
    }

    private ParseResultProducer() {
    }

    static ParseResultProducer getInstance() {
        return ParseResultProducerLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return ParseResultProducerLazyHolder.INSTANCE;
    }

    /**
     * 清空ParseResult属性值
     */
    void clearParseResult() {
        setSourceList(null);
        setTargetList(null);
        setDb2WithTempSqlInfo(null);
    }
}
