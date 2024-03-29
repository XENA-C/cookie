package com.example.cookie.service;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.LoginRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final UserRepository userRepository;

    public void login(
            LoginRequest loginRequest,
            HttpServletResponse httpServletResponse
    ){
            var id = loginRequest.getId();
            var pw = loginRequest.getPassword();

        //로그인 id
        var optionalUser = userRepository.findByName(id);

        if(optionalUser.isPresent()){ //사용자정보 찾으면 Cookie 저장

                var userDto = optionalUser.get();

                if (userDto.getPassword().equals(pw)){ //패스워드가 동일하면
                    //cookie에 해당 정보 저장
                    var cookie = new Cookie("authorization-cookie", userDto.getId());
                    cookie.setDomain("localhost"); //특정 도메인에서만 쿠키 사용
                    cookie.setPath("/"); //특정 경로 설정
                //  cookie.setHttpOnly(true); //JavaScript 에서 쿠키정보를 읽을 수 없도록 보안처리/
                //  cookie.setSecure(true); // Https 에서만 사용(Http X)
                    cookie.setMaxAge(-1); //사용기한 (-1: 세션 유지될 동안)

                    httpServletResponse.addCookie(cookie); //컨트롤러에서 넘어온 httpResponse 쿠키 추가
                   // return userDto.getId();
                }

        }else{
            throw new RuntimeException("User Not Found");
        }
        //return null;
    }
}
