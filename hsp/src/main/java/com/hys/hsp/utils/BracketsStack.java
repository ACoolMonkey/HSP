package com.hys.hsp.utils;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * 匹配左右括号的堆栈实现
 *
 * @author Robert Hou
 * @date 2018年5月31日
 */
@Slf4j
public class BracketsStack implements Serializable {

    /**
     * 堆栈用List实现（Stack已过时，不建议使用）
     */
    private static final List<Character> BRACKETS_LIST = new LinkedList<>();

    private static class BracketsStackLazyHolder {

        private static final BracketsStack INSTANCE = new BracketsStack();
    }

    private BracketsStack() {
    }

    public static BracketsStack getInstance() {
        return BracketsStackLazyHolder.INSTANCE;
    }

    private Object readResolve() {
        return BracketsStackLazyHolder.INSTANCE;
    }

    /**
     * 在堆栈顶部插入一个新元素
     */
    public void push(char bracket) {
        BRACKETS_LIST.add(bracket);
    }

    /**
     * 删除堆栈顶部元素并返回
     */
    public Character pop() {
        if (!isEmpty()) {
            return BRACKETS_LIST.remove(BRACKETS_LIST.size() - 1);
        } else {
            log.error("堆栈元素为空，删除失败");
            return null;
        }
    }

    /**
     * 判断堆栈是否为空
     */
    public boolean isEmpty() {
        return BRACKETS_LIST.isEmpty();
    }
}
