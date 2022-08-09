package top.mylady.api.config.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//@WebFilter(filterName = "CorsFilter")
//@Configuration
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        res.setHeader("Access-Control-Allow-Origin",req.getHeader("origin"));
        res.setHeader("Access-Control-Allow-Credentials","true");
        res.setHeader("Access-Control-Allow-Methods","POST,GET,DELETE,PUT,PATCH");
        res.setHeader("Access-Control-Max-Age","3600");
        res.setHeader("Access-Control-Allow-Headers","Origin,X-Requested-With,Content-Type,Accept,Authorization");
        res.addHeader("x-frame-options","ALLOWALL");
        //add_header X-Frame-Options ALLOWALL;       # 允许所有域名iframe
        //add_header X-Frame-Options DENY;           # 不允许任何域名iframe,包括相同的域名
        //add_header X-Frame-Options SANEORIGIN;     # 允许相同域名iframe,如a.test.com允许b.test.com
        //add_header X-Frame-Options ALLOW-FROM uri; # 允许指定域名iframe,
        chain.doFilter(request,response);
    }

}
