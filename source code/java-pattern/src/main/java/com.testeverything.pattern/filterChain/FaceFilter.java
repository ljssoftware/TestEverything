package com.testeverything.pattern.filterChain;

/**
 * 笑脸的 filter
 * Created by lijinsheng on 14-8-2.
 */
public class FaceFilter implements IFilter {
    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        request.setRequestString(request.getRequestString().replaceAll("笑脸", "^_^") + " FaceFilter request");
        filterChain.doFilter(request, response, filterChain);
        response.setResponseString(response.getResponseString() + " FaceFilter response ");
    }
}
