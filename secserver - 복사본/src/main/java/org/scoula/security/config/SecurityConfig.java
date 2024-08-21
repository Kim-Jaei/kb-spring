package org.scoula.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

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

    http.addFilterBefore(encodingFilter(), CsrfFilter.class);

        // 역할별 접근 제한 설정 : RBAC
        http.authorizeRequests()
                .antMatchers("/security/all").permitAll()
                .antMatchers("/security/admin").access("hasRole('ROLE_ADMIN')") // 표현식
                .antMatchers("/security/member").access("hasRole('ROLE_MEMBER')");

        // 접근 권한이 없으면 로그인 페이지로 redirect
        http.formLogin()
                .loginPage("/security/login") // 로그인 get 요청 url -> jsp 뷰 정의
                .loginProcessingUrl("/security/login") // 로그인 form action url
                .defaultSuccessUrl("/"); // 로그인 성공하면 form 기반 로그인 활성화, 나머지는 모두 디폴트

        // 로그아웃 설정 POST로 요청! == CSRF 토큰 필요 == form을 써야함 not just <a>
        http.logout()
                .logoutUrl("/security/logout") // 호출 url
                .invalidateHttpSession(true) // 세션 invaildate
                .deleteCookies("remember-me", "JSESSION-ID") // 삭제할 쿠키 목록
                .logoutSuccessUrl("/security/logout"); // 로그아웃 성공 후 이동할 페이지
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.info("configure .........................................");

        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());

            //        auth.inMemoryAuthentication()
            //                .withUser("admin") // username
            //                // .password("{noop}1234") // 비밀번호
            //                .password("$2a$10$VJK.3K/W3PhSu53.FVm7WOEzFZPlGTw5.iiCZXgKTHPkhK419Jdz2")
            //                .roles("ADMIN","MEMBER"); // ROLE(자동으로 붙여줌)_ADMIN 역할 설정
            //
            //        auth.inMemoryAuthentication()
            //                .withUser("member")
            //                // .password("{noop}1234") // 비밀번호
            //                .password("$2a$10$VJK.3K/W3PhSu53.FVm7WOEzFZPlGTw5.iiCZXgKTHPkhK419Jdz2")
            //                .roles("MEMBER"); // ROLE_MEMBER

    }
}
