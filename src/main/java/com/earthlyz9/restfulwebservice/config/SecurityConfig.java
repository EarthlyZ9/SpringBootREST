package com.earthlyz9.restfulwebservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return web -> web.ignoring().requestMatchers(
        "/h2-console/**",
        "/v3/api-docs/**",
        "/swagger-ui/**",
        "/api/v1/login" // 임시
    );
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(authz -> authz.anyRequest().authenticated())
        .httpBasic(Customizer.withDefaults()).csrf().disable()
        .headers().frameOptions().disable()
    ;
    return http.build();
  }

  // 간단한 인메모리 아이디, 비밀번호 설정
  // withDefaultPasswordEncoder 는 deprecated (production 용으로는 따로 인코딩 된 비번 사용)
  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    UserDetails user = User.withDefaultPasswordEncoder()
        .username("kenneth")
        .password("test1234")
        .roles("USER")
        .build();
    return new InMemoryUserDetailsManager(user);
  }


}
