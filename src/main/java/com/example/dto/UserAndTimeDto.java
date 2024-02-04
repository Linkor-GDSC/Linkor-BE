package com.example.dto;

import com.example.domain.time.Time;
import com.example.domain.user.User;

import java.util.List;

public class UserAndTimeDto {
    private User user;
    private List<Time> times;

    public UserAndTimeDto(User user, List<Time> times) {
        this.user = user;
        this.times = times;
    }
}
