package com.example.dto;

import com.example.domain.time.Time;
import com.example.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserAndTimeDto {
    private User user;
    private List<Time> times;

    public UserAndTimeDto(User user, List<Time> times) {
        this.user = user;
        this.times = times;
    }

}
