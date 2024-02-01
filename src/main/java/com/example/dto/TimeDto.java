package com.example.dto;

import com.example.domain.time.Time;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeDto {
    private Long id;
    private String time;
    private String user_email;

    public Time toEntity() {
        return Time.builder()
                .time(time)
                .user_email(user_email)
                .build();
    }
}
