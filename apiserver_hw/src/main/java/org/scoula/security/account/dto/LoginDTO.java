package org.scoula.security.account.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO { // username, password 프로퍼티 가짐
    private String username; // 사용자 id
    private String password; // 사용자 비번

    // HttpServletRequest -> LoginDTO 객체로 변환
    public static LoginDTO of(HttpServletRequest request) throws AuthenticationException {
        ObjectMapper om = new ObjectMapper(); // JSON -> Java 객체 변환
        try{
            return om.readValue(request.getInputStream(), LoginDTO.class); // 지정된 클래스 타입으로 변환 (클라이언트가 요청한 본문 데이터를 LoginDTO 클래스로)
        }catch (Exception e){
            e.printStackTrace();
            throw new BadCredentialsException("username or password doesn't exist.");
        }
    }
}
