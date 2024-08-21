package org.scoula.security.account.mapper;

import org.scoula.security.account.domain.MemberVO;

// 한 사람의 여러 정보를 담는 매퍼
public interface UserDetailsMapper {
    public MemberVO get(String username);
}
