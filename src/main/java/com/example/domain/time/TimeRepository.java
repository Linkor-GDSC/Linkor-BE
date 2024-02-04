package com.example.domain.time;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TimeRepository extends JpaRepository<Time, Long> {
    Time save(Time time);

    @Query("select t from Time t where t.user_email = ?1")
    List<Time> findTimesByUserEmail(String email);
}
