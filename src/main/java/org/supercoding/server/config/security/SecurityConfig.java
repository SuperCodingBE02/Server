package org.supercoding.server.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.supercoding.server.common.utils.TokenProvider;
import org.supercoding.server.config.security.CustomAuthenticationEntryPoint;
import org.supercoding.server.config.security.filter.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // csrf 비활
        http
                .csrf((auth) -> auth.disable());

        // formLogin 비활
        http
                .formLogin((auth) -> auth.disable());

        // basic인증 방식 비활
        http
                .httpBasic((auth) -> auth.disable());

        // 요청 인가
        http
                .authorizeHttpRequests((auth)->
                        auth
                                .requestMatchers("api/signup", "api/login","/v3/**", "/swagger-ui/**").permitAll()
                                .requestMatchers("/api/**").authenticated()
                                .requestMatchers("**exception**").permitAll()
                                .anyRequest().hasRole("ADMIN_ROLE")
                );

        // 세션 설정
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http
                .exceptionHandling((exception) -> exception
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint(tokenProvider)));

        http
                .exceptionHandling((exception) -> exception
                        .accessDeniedHandler(new CustomAccessDeniedHandler()));


        // jwt filter
        http
                .addFilterBefore(new JwtAuthorizationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
