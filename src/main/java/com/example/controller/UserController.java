package com.example.controller;

import com.example.domain.request.TimeRequest;
import com.example.domain.user.User;
import com.example.dto.TimeDto;
import com.example.dto.UserAndTimeDto;
import com.example.dto.UserDto;
import com.example.service.TimeServiceImpl;
import com.example.service.UserServiceImpl;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
//    @PostMapping("register")
//    //@ApiOperation(value = "유저 회원등록", notes = "유저 정보를 입력받아 회원등록을 하고, 이미 등록된 유저이면 등록하지 않는다.")
//    public void register(@RequestBody UserDto user){
//        try{
//            System.out.println(user.toString());
//            userService.join(user);
//        }catch (DataIntegrityViolationException e){
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미 등록된 회원입니다.");
//        }
//    }

    @PostMapping("register")
    public boolean register(@RequestBody UserDto user){
        try{
            if (userService.checkUserDuplicate(user.getEmail())){
                //bindingResult.addError(new FieldError("SignUp Request", "email", "이미 등록된 회원입니다."));
                return true;
            }else{
                System.out.println(user.toString());
                userService.join(user);
                return false;
            }
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미 등록된 회원입니다.");
        }
    }


    //회원가입시 이미 가입한 회원인지 중복체크
    @GetMapping("register/{email}/exists")
    public ResponseEntity<Boolean> checkMemberDuplicate(@PathVariable String email){
        return ResponseEntity.ok(userService.checkUserDuplicate(email));
    }


    //time 저장
    @PostMapping("register/time")
    public void register_time(@RequestBody TimeRequest request) {
        String email = request.getEmail();
        List<String> times = request.getTimes();

        for (String time : times) {
            TimeDto timeDto = new TimeDto(time, email);

            try {
                System.out.println(timeDto.toString());
                timeService.save(timeDto);
            } catch (DataIntegrityViolationException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미 등록된 회원입니다.");
            }
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
    public UserAndTimeDto findTutorByEmail(@PathVariable String email){
        return userService.findTutorByEmail(email);
    }
  /*
    //tutor 필터링
    @GetMapping("/user")
    public List<User> getUsersByFilter(
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "locationsido", required = false) String locationsido,
            @RequestParam(value = "locationgu", required = false) String locationgu,
            @RequestParam(value = "tutoringmethod", required = false) String tutoringmethod) {

        return userService.getUsersByFilter(gender, locationsido, locationgu, tutoringmethod);
    }

   */

    @GetMapping("/filter")
    public List<UserAndTimeDto> getUsersByFilterWithTime(
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "locationsido", required = false) String locationsido,
            @RequestParam(value = "locationgu", required = false) String locationgu,
            @RequestParam(value = "tutoringmethod", required = false) String tutoringmethod,
            @RequestParam(value = "times", required = false) List<String> times) {

        return userService.getUsersByFilterWithTime(gender, locationsido, locationgu, tutoringmethod, times);
    }

    @GetMapping("tutors/time/{time}")
    public List<User> findTutorsByTime(@PathVariable String time){
        return userService.findTutorsByTime(time);
    }

    //유저 정보 검색
    @GetMapping("/{email}")
    public UserAndTimeDto findUserByEmail(@PathVariable String email){
        return userService.findUserByEmail(email);
    }

}
