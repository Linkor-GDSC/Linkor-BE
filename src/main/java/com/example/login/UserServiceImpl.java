package com.example.login;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public Optional<User> findUserByUid(String uid) { return userRepository.findByUid(uid); }

    //email주소로 db에서 유저데이터 검색
    public Optional<User> findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public String findUserNickName(String uid) { return userRepository.findNickName(uid); }
}