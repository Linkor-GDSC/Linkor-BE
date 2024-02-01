package com.example.domain.user;

import java.util.Optional;

import com.example.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    User save(User user);

    //Optional<User> findByUid(String uid);

    // ?1 : 첫 번째 위치 기반 파라미터에 해당하는 값 지정
    @Query("select u from User u where u.role = 'tutor' and u.email = ?1")
    List<User> findByEmail(String email);

    //@Query("select u.nickname from User u where u.uid = ?1")
    //String findNickName(String uid);

    List<User> findAll();

    @Query("select u from User u where u.role = 'tutor'")
    List<User> findTutors(String role);
}
