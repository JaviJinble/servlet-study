package com.korit.servletstudy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = "aaaa";
        System.out.println(req.getRequestURI());
        System.out.println(req.getParameter("aaa"));
        // resp : 응답 로부터 .getWriter() 작성
        resp.getWriter().println(username);
    }
}
