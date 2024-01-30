package com.example.dto;

import com.example.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {


    private String email;
    private String name;
    private String role;
    private String gender;
    private String locationsido;
    private String locationgu;
    private String tutoringMethod;
    private String introduction;


    public User toEntity(){
        return User.builder()
                .email(email)
                .name(name)
                .role(role)
                .gender(gender)
                .locationsido(locationsido)
                .locationgu(locationgu)
                .tutoringMethod(tutoringMethod)
                .introduction(introduction)
                .build();
    }


}