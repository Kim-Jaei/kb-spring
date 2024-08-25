package org.scoula.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtProcessor { // Jwt 작업을 위한 helper 클래스
    static private final long TOKEN_VALID_MILISECOND = 1000L * 60 * 10; // 토큰 유효시간 단 10분이세요.

    // 개발 시 비밀키 직접 설정
    private String secretKey = "먹고싶은거:마라탕,대창,스지탕,크림새우,등촌샤브볶음밥,돈까스,치즈등갈비"; // 암호화될 문자열
    private Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)); // 지정된 서명 알고리즘에 맞게 비밀키 인코딩

    // private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); -- 운영 시에는 자동 생성 코드

    // JWT 생성
    public String generateToken(String subject){ // 사용자 id 기반으로 토큰 생성
        return Jwts.builder() // 빌더 패턴으로 생성
                .setSubject(subject) // JWT의 subject 클레임 설정 (username)
                .setIssuedAt(new Date()) // 토큰 발행 시간 클레임 설정 (현재 시간)
                .setExpiration(new Date(new Date().getTime() + TOKEN_VALID_MILISECOND)) // 만료 시간 클레임 설정 (현재 시간 + 유효 기간)
                .signWith(key) // 서명 생성할 때 사용할 비밀키
                .compact(); // 설정을 바탕으로 토큰 생성하고, 최종적으로 JWT 문자열 반환
    }

    // 예외 ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException
    // 토큰을 입력 받아 토큰에서 username을 추출하여 반환
    // 토큰 파싱 -> 서명 검증 -> username 추출
    public String getUsername(String token) {
        return Jwts.parserBuilder() // 토큰을 파싱하기 위한 파서 빌더 객체 생성
                .setSigningKey(key) // 서명 검증을 위한 비밀키 설정 (JWT 생성 시 서명에 사용된 것과 동일한 키)
                .build() // 생성한 파서 빌더 객체를 실제로 사용 가능한 파서 객체로 빌드 -> JWT를 파싱하고 서명 검증하는 역할
                .parseClaimsJws(token) // 입력받은 토큰을 파싱하여 서명 검증!! -> 성공 시 페이로드를 Claims 객체로 반환
                .getBody() // 변환한 Claims 객체에서 페이로드 부분 가져오기 (주된 데이터 영역으로, 여러 클레임 가져옴)
                .getSubject(); // 페이로드에서 subject 부분(username 같은 식별자) 가져옴 -> 최종 반환 값!
    }

    // JWT가 유효한지 검증
    // 현재는 기본적인 서명 검증만 수행 중
    public boolean validateToken(String token) {
        Jws<Claims> claims = Jwts.parserBuilder() // JWT를 파싱하고 서명을 검증할 수 있는 파서 빌더 객체 생성
                .setSigningKey(key) // 서명 검증할 비밀 키 설정
                .build() // 생성한 파서 빌더 객체를 실제로 사용 가능한 파서 객체로 빌드 -> JWT를 파싱하고 서명 검증하는 역할
                .parseClaimsJws(token); // 입력 받은 토큰 파싱 & 서명 검증 -> 성공 시 페이로드를 포함한 Jws<Claims> 객체 반환
        return true; // 검증 성공하면 true 반환 (여기서는 유효한지만 확인하는 수준)
    }
}
