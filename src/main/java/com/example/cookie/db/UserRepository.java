package com.example.cookie.db;

import com.example.cookie.model.UserDto;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserRepository {

    private final List<UserDto> userList = new ArrayList<>();

    public Optional<UserDto> findById(String id){
        return userList
                .stream()
                .filter(it->{
                   return it.getId().equals(id);
                })
                .findFirst();
    }

    public Optional<UserDto> findByName(String name){
        return userList
                .stream()
                .filter(it->{
                    return it.getName().equals(name);
                    //name을 검색해서 매개변수(String name)과 비교
                })
                .findFirst();
    }


    @PostConstruct //빈 초기화 & 호출
    public void start(){
        userList.add(
           new UserDto( //랜덤아이디 생성
                   UUID.randomUUID().toString(),
                   "xen",
                   "1234"
             )
        );

        userList.add(
                new UserDto( //랜덤아이디 생성
                        UUID.randomUUID().toString(),
                        "jen",
                        "1234"
                )
        );

        userList.add(
                new UserDto( //랜덤아이디 생성
                        UUID.randomUUID().toString(),
                        "xena",
                        "1234"
                )
        );
    }


}
