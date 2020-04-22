package com.hys.hsp.utils;

/**
 * sqlinfo标志位
 *
 * @author Robert Hou
 * @date 2018年5月24日
 */
public enum SqlInfo {

    /**
     * create开头标志位
     */
    CREATE,
    /**
     * insert开头标志位
     */
    INSERT,
    /**
     * merge开头标志位
     */
    MERGE,
    /**
     * update开头标志位
     */
    UPDATE,
    /**
     * declare开头标志位
     */
    DECLARE
}
