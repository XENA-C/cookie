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

    public String login(
            LoginRequest loginRequest,
            HttpServletResponse httpServletResponse
    ){
            var id = loginRequest.getId();
            var pw = loginRequest.getPassword();

            var optionalUser = userRepository.findByName(id);
            //이름을 찾아서 로그인 id 전달 ???

        if(optionalUser.isPresent()){
                var userDto = optionalUser.get();
                if (userDto.getPassword().equals(pw)){
//                    //cookie에 해당 정보 저장
//                    var cookie = new Cookie("authorization-cookie", userDto.getId());
//                    cookie.setDomain("localhost"); //쿠키는 특정 도메인에서만 사용
//                    cookie.setPath("/"); //특정 경로 설정
//                    cookie.setHttpOnly(true); //JavaScript 에서 쿠키정보를 읽을 수 없도록 보안처리
//                    cookie.setSecure(true); // Https 에서만 사용(Http X)
//                    cookie.setMaxAge(-1); //사용기한 (-1: 연결된 기간동안)
//                    httpServletResponse.addCookie(cookie);
                    //컨트롤러에서 넘어온 http리스폰스에 쿠키 추가

                    return userDto.getId();
                }

        }else{
            throw new RuntimeException("User Not Found");
        }

        return null;
    }

}
