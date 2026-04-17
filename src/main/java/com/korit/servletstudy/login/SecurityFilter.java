package com.korit.servletstudy.login;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter("/*")
public class SecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        // 다운캐스팅함. ServletRequest이 상위 추상화 객체라   HttpServletRequest에 있는 기능을 가져옴.   Http프로토콜

        String uri = httpReq.getRequestURI();
        String projectNameIgnoreUri = uri.substring(uri.indexOf("/", 1));

        if (projectNameIgnoreUri.startsWith("/api/auth")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpSession session = httpReq.getSession();
        Object authentication = session.getAttribute("authentication");
        //        ((HttpServletRequest) servletRequest).getSession();
        if (authentication == null) { // authAttribute가 null이면 로그인이 안된 상태!
            ResponseEntity.builder()
                    .status(403)
                    .body("로그인 후 이용가능합니다.")
                    .build()
                    .response(servletResponse);
            return; // 재귀 탈출
        }


        filterChain.doFilter(servletRequest, servletResponse);
    }
}
