package org.scoula.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity // security로 사용할 거야!
@Log4j
@MapperScan(basePackages = {"org.scoula.security.account.mapper"})
@ComponentScan(basePackages = {"org.scoula.security"})
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 제일 중요
    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // 인증 관리를 담당하는 프레임워크
    // AuthenticationManager : 사용자의 인증을 처리하는 컴포넌트
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    // 다른 도메인에서 리소스를 요청할 수 있도록 허용하는 매커니즘
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); // url 패턴에 따라 cors 설정을 적용할 수 있음
        CorsConfiguration config = new CorsConfiguration(); // 실제로 cors 설정을 정의 : 어떤 도메인, 헤더, HTTP 메서드가 허용될지?
        config.setAllowCredentials(true); // 쿠키 같은 자격 증명을 포함한 요청을 허용할지
        config.addAllowedOriginPattern("*"); // 모든 도메인에서 오는 요청 허용
        config.addAllowedHeader("*"); // 모든 요청 헤더 허용
        config.addAllowedMethod("*"); // 모든 CRUD(HTTP 메서드) 허용
        source.registerCorsConfiguration("/**", config); // 앞서 정의한 CORS 설정을 애플리케이션의 모든 경로(/**)에 대해 적용
        return new CorsFilter(source); //  이 필터는 애플리케이션에 들어오는 모든 요청에 대해 CORS 정책을 적용
    }

    // 접근 제한 무시 경로 설정 - resource
    // 얘네는 검사하지 말아라~
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**", "/*", "/api/member/**");
    }

    // 문자셋 필터
    public CharacterEncodingFilter encodingFilter(){
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    // 제일 중요한 메서드
    // Security 설정을 커스터마이징하는 방법 중 하나
    // 필터의 순서를 조정하는 것 -> 특정 필터가 다른 필터보다 먼저 실행되어야 할 때 사용!
    // 특정 보안 작업이 다른 보안 작업보다 먼저 수행되도록 보장한대요.
    // 인코딩 -> CSRF 검사
    // 특정 url 경로에 대한 접근 권한 설정 & 폼 로그인 기능 활성화
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 한글 인코딩 필터 설정
        http.addFilterBefore(encodingFilter(), CsrfFilter.class);

        http.httpBasic().disable() // 기본 HTTP 인증 비활성화
                .csrf().disable() // CSRF 비활성화
                .formLogin().disable() // formLogin 비활성화 관련 필터 해제
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 세션 생성하지 말기
    }

    // AuthenticationManager 구성
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.info("configure .........................................");

        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());

    }
}
