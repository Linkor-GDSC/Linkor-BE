package com.example.service;

import com.example.domain.time.TimeRepository;
import com.example.dto.TimeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class TimeServiceImpl {
    private final TimeRepository timeRepository;

    public void save(TimeDto timeDto){
        timeRepository.save(timeDto.toEntity());
    }
}
