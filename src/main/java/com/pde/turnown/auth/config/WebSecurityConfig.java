package com.pde.turnown.auth.config;

import com.pde.turnown.auth.filter.CustomAuthenticationFilter;
import com.pde.turnown.auth.filter.JwtAuthorizationFilter;
import com.pde.turnown.auth.handler.CustomAuthFailureHandler;
import com.pde.turnown.auth.handler.CustomAuthSuccessHandler;
import com.pde.turnown.auth.handler.CustomAuthenticationProvider;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    /** 정적 자원에 대한 인증된 사용자의 접근을 설정하는 메소드 */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        //정적 리소스에대해서는 시큐리티 설정을 거치지 않게해주는 설정
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    /** Security filter chain 설정 메소드 */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)  //사이트 위조 방지//원래는 disable 하면 안된다.
                .addFilterBefore(jwtAuthorizationFilter(), BasicAuthenticationFilter.class) //토큰인증 후 사용자 인증정보 셋팅해줌
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(form -> form.disable())  //기본 로그인창 사용하지 않고 만든 로그인창 사용하는 설정
                .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class) //로그인 성공과 실패후 핸들러 등록
                .httpBasic(basic -> basic.disable()); // 기본 인증에 대한 인증철자로 진행하지 않고 지정해주는걸로 진행하는 설정

        return http.build();
    }

    /** 사용자 요청(request) 시 수행되는 메소드 */
    private JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(authenticationManager());
    }

    /** Authentization의 인증 메소드를 제공하는 매니저(= Provider의 인터페이스)를 반환하는 메소드 */
    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(customAuthenticationProvider());
    }

    /** 사용자의 id와 password를 DB와 비교하여 검증하는 핸들러 메소드 */
    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    /** 비밀번호를 암호화하는 인코더를 반환하는 메소드 */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /** 사용자의 인증 요청을 가로채서 로그인 로직을 수행하는 필터를 반환하는 메소드 */
    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
        customAuthenticationFilter.setFilterProcessesUrl("/login");
        customAuthenticationFilter.setAuthenticationSuccessHandler(customAuthLoginSuccessHandler());
        customAuthenticationFilter.setAuthenticationFailureHandler(customAuthLoginFailureHandler());
        customAuthenticationFilter.afterPropertiesSet();

        return customAuthenticationFilter;
    }

    /** 사용자 정보가 맞을 경우 (= 로그인 성공 시) 수행하는 핸들러를 반환하는 메소드 */
    private CustomAuthSuccessHandler customAuthLoginSuccessHandler() {
        return new CustomAuthSuccessHandler();
    }

    /** 사용자 정보가 맞지 않는 경우 (= 로그인 실패 시) 수행하는 핸들러를 반환하는 메소드 */
    private CustomAuthFailureHandler customAuthLoginFailureHandler() {
        return new CustomAuthFailureHandler();
    }

}