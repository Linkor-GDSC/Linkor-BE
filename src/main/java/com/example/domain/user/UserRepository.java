package com.example.domain.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    User save(User user);


    // ?1 : 첫 번째 위치 기반 파라미터에 해당하는 값 지정
    @Query("select u from User u where u.role = 'tutor' and u.email = ?1")
    List<User> findByEmail(String email);


    List<User> findAll();

    @Query("select u from User u where u.role = 'tutor'")
    List<User> findTutors(String role);

    @Query("select u from User u join Time t on u.email = t.user_email where u.role = 'tutor' and t.time = ?1")
    List<User> findTutorsByTime(String time);
}
