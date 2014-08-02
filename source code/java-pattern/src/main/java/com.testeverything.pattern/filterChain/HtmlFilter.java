package com.testeverything.pattern.filterChain;

/**
 * HTML 页面的Filter, 用于替换html标签
 * Created by lijinsheng on 14-8-2.
 */
public class HtmlFilter implements IFilter {

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        request.setRequestString(request.getRequestString().replaceAll("<", "[").replaceAll(">", "]") + " HTMLFilter Request ");
        filterChain.doFilter(request, response, filterChain);
        response.setResponseString(response.getResponseString() + " HTMLFilter Response");
    }
}
