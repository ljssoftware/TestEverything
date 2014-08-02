package com.testeverything.pattern.filterChain;

/**
 * 敏感词的Filter
 * Created by lijinsheng on 14-8-2.
 */
public class SensitiveFilter implements IFilter {
    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        request.setRequestString(request.getRequestString().replaceAll("fuck", "hello") + " SensitiveFilter request ");
        filterChain.doFilter(request, response, filterChain);
        response.setResponseString(response.getResponseString() + "SensitiveFilter Response ");
    }
}
