package com.example.cookie.model;

import lombok.*;

@Builder
@Getter @Setter
@ToString
@NoArgsConstructor @AllArgsConstructor
public class UserDto {

    private String id; //서버에서 발행한 random id
    private String name;
    private String password;

}

