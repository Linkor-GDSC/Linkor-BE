//package com.example.login;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletInputStream;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.StreamUtils;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//
//@WebServlet(name = "userDataServlet", urlPatterns = "/user/register")
//public class UserDataServlet extends HttpServlet {
//
//    @Autowired
//    private UserServiceImpl userService;
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ServletInputStream inputStream = request.getInputStream();
//        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
//        System.out.println("messageBody = " + messageBody);
//
//        User user = objectMapper.readValue(messageBody, User.class);
//
//        response.getWriter().write("ok");
//
//        //db에 저장
//        userService.join(user);
//
//    }
//}
