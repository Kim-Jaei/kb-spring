package org.scoula.security.account.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDTO {
    private String username;
    private String password;
    public static LoginDTO of(HttpServletRequest request) throws AuthenticationException { // body: json 인코딩
        ObjectMapper om = new ObjectMapper(); // jackson 객체
        try {
            return om.readValue(request.getInputStream(), LoginDTO.class); // getInputStream(): json 문자열 스트림 . 역직렬화 json -> 객체
        }catch (Exception e) {
            e.printStackTrace();
            throw new BadCredentialsException("username 또는 password가 없습니다.");
        }
    }
}
