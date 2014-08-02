package com.testeverything.pattern.filterChain;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器链条
 * Created by lijinsheng on 14-8-2.
 */
public class FilterChain {
    private int index = 0;
    private List<IFilter> filterList = new ArrayList<IFilter>();

    public FilterChain addFilter(IFilter iFilter) {
        filterList.add(iFilter);
        return this;
    }

    public void doFilter(Request request, Response response, FilterChain filterChain) {
        if (index == filterList.size()) {
            return; // 所有的filter 都已经处理完成
        }

        filterList.get(index++).doFilter(request, response, filterChain);
    }
}
