package com.example.domain.time;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRepository extends JpaRepository<Time, Long> {
    Time save(Time time);
}
