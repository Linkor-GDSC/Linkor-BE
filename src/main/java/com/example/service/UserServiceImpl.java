package com.example.service;

import com.example.domain.time.Time;
import com.example.domain.time.TimeRepository;
import com.example.domain.user.User;
import com.example.domain.user.UserRepository;
import com.example.dto.UserAndTimeDto;
import com.example.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final TimeRepository timeRepository;

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

    /*
    //튜터 필터링
    public List<User> getUsersByFilter(String gender, String locationsido, String locationgu, String tutoringmethod) {
        return userRepository.findUsersByFilter(gender, locationsido, locationgu, tutoringmethod);
    }

     */

    public List<UserAndTimeDto> getUsersByFilterWithTime(String gender, String locationsido, String locationgu, String tutoringmethod,
                                               List<String> times) {
        //return userRepository.findUsersByFilterWithTime(gender, locationsido, locationgu, tutoringmethod, times);
        // 사용자 필터링된 목록 가져오기
        List<Object[]> usersAndTimes = userRepository.findUsersByFilterWithTime(gender, locationsido, locationgu, tutoringmethod, times);

        // 사용자 이메일 목록 추출
        List<String> userEmails = usersAndTimes.stream()
                .map(userAndTime -> ((User) userAndTime[0]).getEmail())
                .collect(Collectors.toList());

        // 각 사용자에 대한 시간 정보 가져오기
        Map<String, List<Time>> userEmailToTimesMap = new HashMap<>();
        for (String userEmail : userEmails) {
            List<Time> times1 = timeRepository.findTimesByUserEmail(userEmail);
            userEmailToTimesMap.put(userEmail, times1);
        }

        // 결과 조합
        List<UserAndTimeDto> result = new ArrayList<>();
        for (Object[] userAndTime : usersAndTimes) {
            User user = (User) userAndTime[0];
            List<Time> times2 = userEmailToTimesMap.get(user.getEmail());
            UserAndTimeDto userAndTimeDto = new UserAndTimeDto(user, times2);
            result.add(userAndTimeDto);
        }
        return result;
    }

    public List<User> findTutorsByTime(String time){
        return userRepository.findTutorsByTime(time);
    }
}