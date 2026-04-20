package com.korit.servletstudy.login;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/users")
public class UserController extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) { // 이 컨트롤러가 동작하기 전에 필요한 것들을 한 번 세팅하는 메서드
        UserRepository userRepository = new UserRepository(config.getServletContext());
        userService = new UserService(userRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 사용자 전체 조회
        // 클라이언트 요청을 처리하는 메서드
        List<User> users = userService.getAll(); //  받은 모든 사용자 리스트 응답
        ResponseEntity.builder()
                .status(200)
                .body(users)
                .build()
                .response(resp);
    }

}
