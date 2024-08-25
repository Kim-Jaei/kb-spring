package org.scoula.security.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.security.account.domain.MemberVO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO { // 로그인 성공 시 응답에 포함시킬 사용자 정보 [토큰, 유저 정보]
    String username; // 사용자 id
    String email; // 사용자 이메일
    List<String> roles; // 사용자의 역할, 여러개의 역할일 수도 있음

    // MemberVO 객체로 DTO 객체를 생성
    public static UserInfoDTO fromUser(MemberVO member) {
        return new UserInfoDTO(
                member.getUsername(),
                member.getEmail(),
                member.getAuthList().stream()
                        .map(a-> a.getAuth())
                        .toList() // AuthList에서 권한 정보를 추출해서 List<String>으로 변환
        );
    }
}
