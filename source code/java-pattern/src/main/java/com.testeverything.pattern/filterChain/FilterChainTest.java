package com.testeverything.pattern.filterChain;

/**
 * 过滤器 链表 的测试类
 * Created by lijinsheng on 14-8-2.
 */
public class FilterChainTest {
    public static void main(String[] args) {
        //组织请求数据
        Request request = new Request();
        request.setRequestString("<script> , 笑脸 , fuck");

        //组织返回数据
        Response response = new Response();
        response.setResponseString("");

        //组织过滤器
        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new HtmlFilter()).addFilter(new FaceFilter()).addFilter(new SensitiveFilter());
        //进行过滤
        filterChain.doFilter(request, response, filterChain);
        System.out.println(response.getResponseString());
    }
}
