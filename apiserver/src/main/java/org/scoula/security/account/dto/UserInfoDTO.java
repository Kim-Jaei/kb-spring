package org.scoula.security.account.dto;

import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.security.account.domain.MemberVO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO
{
    String username;
    String email;
    List<String> roles; // List<AuthVO>에서 변환
    public static UserInfoDTO of(MemberVO member) {
        return new UserInfoDTO
                (
                        member.getUsername(),
                        member.getEmail(),
                        member.getAuthList().stream()
                                .map(a -> a.getAuth())
                                .toList()
                );
    }
}
