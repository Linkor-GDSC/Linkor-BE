package com.example.controller;

import com.example.domain.user.User;
import com.example.dto.TimeDto;
import com.example.dto.UserDto;
import com.example.service.TimeServiceImpl;
import com.example.service.UserServiceImpl;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserServiceImpl userService;
    private final TimeServiceImpl timeService;

    public UserController(UserServiceImpl userService, TimeServiceImpl timeService) {
        this.userService = userService;
        this.timeService = timeService;
    }
    @PostMapping("register")
    //@ApiOperation(value = "유저 회원등록", notes = "유저 정보를 입력받아 회원등록을 하고, 이미 등록된 유저이면 등록하지 않는다.")
    public void register(@RequestBody UserDto user){
        try{
            System.out.println(user.toString());
            userService.join(user);
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미 등록된 회원입니다.");
        }
    }

    //time 저장
    @PostMapping("register/time")
    public void register_time(@RequestBody TimeDto time){
        try{
            System.out.println(time.toString());
            timeService.save(time);
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미 등록된 회원입니다.");
        }
    }
    @GetMapping("all")
    //@ApiOperation(value = "전체 유저 정보 찾기", notes = "전체 유저 데이터를 찾아 리턴한다.")
    public List<User> findAll(){
        return userService.findUsers();
    }

    @GetMapping("tutors")
    public List<User> findTutors(String role){
        return userService.findTutors(role);
    }

    //@PathVariable : 경로 변수
    @GetMapping("tutors/{email}")
    public List<User> findTutorsByEmail(@PathVariable String email){
        return userService.findTutorByEmail(email);
    }

    @GetMapping("tutors/time/{time}")
    public List<User> findTutorsByTime(@PathVariable String time){
        return userService.findTutorsByTime(time);
    }

}
