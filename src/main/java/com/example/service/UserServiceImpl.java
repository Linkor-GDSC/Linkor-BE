package com.example.service;

import com.example.domain.user.User;
import com.example.domain.user.UserRepository;
import com.example.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl {
    private final UserRepository userRepository;

    //user데이터 DB에 저장
    public void join(UserDto userDto){
        userRepository.save(userDto.toEntity());
    }
    /*
        //중복데이터 저장 방지
        private void validateDuplicateMember(User user){
            userRepository.findByEmail(user.getEmail())
                    .ifPresent(e -> {
                        throw new IllegalStateException("이미 존재하는 회원입니다.");
                    });
        }
    */


    //이메일로 이미 가입된 회원인지 판별
    public boolean checkUserDuplicate(String email){
        return userRepository.existsByEmail(email);
    }

    //email주소로 db에서 유저데이터 검색
    public List<User> findTutorByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public List<User> findTutors(String role){
        return userRepository.findTutors(role);
    }


    //public String findUserNickName(String uid) { return userRepository.findNickName(uid); }

    //튜터 필터링
    public List<User> getUsersByFilter(String gender, String locationsido, String locationgu, String tutoringmethod) {
        return userRepository.findUsersByFilter(gender, locationsido, locationgu, tutoringmethod);
    }

    public List<User> findTutorsByTime(String time){
        return userRepository.findTutorsByTime(time);
    }
}