package com.erobic.springit.web.filters;

import com.erobic.springit.utils.RequestContextUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * This filter is applied to all the web requests. It initializes username for current thread.
 * Created by robik on 1/1/17.
 */
@Component
public class RequestContextFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        RequestContextUtil.init();
        chain.doFilter(request, response);
    }
}
