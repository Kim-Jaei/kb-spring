package org.scoula.security.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResultDTO { // 로그인 성공 결과를 나타냄 [인증된 token, UserInfoDTO]
    String token;
    UserInfoDTO user;
}
