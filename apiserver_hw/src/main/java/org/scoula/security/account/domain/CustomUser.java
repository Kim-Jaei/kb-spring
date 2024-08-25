package org.scoula.security.account.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class CustomUser extends User { // Spring에 기본적으로 있는 User

    private MemberVO member; // 실질적인 사용자 데이터

    // 생성자 1
    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    // 생성자 2
    public CustomUser(MemberVO vo){
        super(vo.getUsername(), vo.getPassword(), vo.getAuthList());
        this.member = vo;
    }

}
