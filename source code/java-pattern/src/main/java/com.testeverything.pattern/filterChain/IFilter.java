package com.testeverything.pattern.filterChain;

/**
 * 过滤器接口
 * Created by lijinsheng on 14-8-2.
 */
public interface IFilter {
    /**
     * 过滤器方法
     *
     * @param request     请求参数
     * @param response    响应参数
     * @param filterChain 过滤器链
     */
    void doFilter(Request request, Response response, FilterChain filterChain);
}
