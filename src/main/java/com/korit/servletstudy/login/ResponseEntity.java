package com.korit.servletstudy.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Data
@Builder
@AllArgsConstructor
public class ResponseEntity<T> {
    private int status;
    private  T body;

    public void response(ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(status);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().println(JsonParserUtil.stringify(this));

    }
}
