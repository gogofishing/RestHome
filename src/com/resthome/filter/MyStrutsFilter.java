package com.resthome.filter;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

public class MyStrutsFilter extends StrutsPrepareAndExecuteFilter {
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        //过滤url，html编辑器的路径不进行struts2拦截
        String url = request.getRequestURI();
        // System.out.println(url);
        if (url.contains("/ueditor/jsp/")) {
//        	System.out.println("不进行struts2的拦截");
        	req.setCharacterEncoding("UTF-8");
        	res.setCharacterEncoding("UTF-8");
            chain.doFilter(req, res);
        }else{
//        	System.out.println("使用struts2默认的拦截器");
        	req.setCharacterEncoding("UTF-8");
        	res.setCharacterEncoding("UTF-8");
            super.doFilter(req, res, chain);
        }
    }
}
