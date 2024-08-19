package org.scoula.security.config;

import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity // security로 사용할 거야!
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 문자셋 필터
    public CharacterEncodingFilter encodingFilter(){
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return encodingFilter();
    }

    // 제일 중요한 메서드
    // Security 설정을 커스터마이징하는 방법 중 하나
    // 필터의 순서를 조정하는 것 -> 특정 필터가 다른 필터보다 먼저 실행되어야 할 때 사용!
    // 특정 보안 작업이 다른 보안 작업보다 먼저 수행되도록 보장한대요.
    // 인코딩 -> CSRF 검사
    //    @Override
    //    public void configure(HttpSecurity http) throws Exception {
    //        http.addFilterBefore(encodingFilter(), CsrfFilter.class);
    //    }



    // 특정 url 경로에 대한 접근 권한 설정 & 폼 로그인 기능 활성화
    @Override
    public void configure(HttpSecurity http) throws Exception {

    // http.addFilterBefore(encodingFilter(), CsrfFilter.class);

    // 경로별 접근 권한 설정
        http.authorizeRequests()
                .antMatchers("/security/all").permitAll()
                .antMatchers("/security/admin").access("hasRole('ROLE_ADMIN')") // 표현식
                .antMatchers("/security/member").access("hasRole('ROLE_MEMBER')");

        // 접근 권한이 없으면 로그인 페이지로 redirect
        http.formLogin()
                .loginPage("/security/login")
                .loginProcessingUrl("/security/login")
                .defaultSuccessUrl("/"); // form 기반 로그인 활성화, 나머지는 모두 디폴트
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        log.info("configure .........................................");
//
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("{noop}1234")
//                .roles("ADMIN","MEMBER"); // ROLE_ADMIN
//
//        auth.inMemoryAuthentication()
//                .withUser("member")
//                .password("{noop}1234")
//                .roles("MEMBER"); // ROLE_MEMBER
//    }
}
