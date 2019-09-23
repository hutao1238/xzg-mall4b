package com.xzg.mall.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xzg.mall.common.xss.XssWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 一些简单的安全过滤：
 * xss
 * @author lgh
 */
public class XssFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(getClass().getName());
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        
        logger.info("uri:{}",req.getRequestURI());
        // xss 过滤
		chain.doFilter(new XssWrapper(req), resp);
    }

    @Override
    public void destroy() {

    }
}
