package com.testeverything.generic.util;

import java.util.Collections;
import java.util.List;

/**
 * 排序的工具类
 * Created by lijinsheng on 14-6-25.
 */
public class SortUtil {
    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        Collections.sort(list);
    }
}
