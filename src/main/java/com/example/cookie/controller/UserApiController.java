package com.example.cookie.controller;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.UserDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {

    private final UserRepository userRepository;

    @GetMapping("/me")
    public UserDto me(  //Dto 반환 메서드
                        HttpServletRequest httpServletRequest,
                        @CookieValue(name = "authorization-Cookie", required = false)
                        //매핑값 지정  // true가 디폴트 -> 쿠키가 없으면 에러메세지
                        String authorizationCookie
    ) {
        log.info("authorization-Cookie:{}", authorizationCookie);

        var optionalUserDto = userRepository.findById(authorizationCookie);
        return optionalUserDto.get();

        //쿠키 내용 확인
/*        var cookies = httpServletRequest.getCookies();

        if(cookies != null){
             for(Cookie cookie: cookies){
                 log.info("key:{}, value:{}", cookie.getName(), cookie.getValue());
              }
        }*/
    }


    @GetMapping("/me2")
    public UserDto me2(
             HttpServletRequest httpServletRequest,
             @RequestHeader(name = "authorization", required = false)
             String authorizationHeader
    ) {
        log.info("authorizationHeader:{}", authorizationHeader);
        var optionalUserDto = userRepository.findById(authorizationHeader);
        return optionalUserDto.get();
    }
}