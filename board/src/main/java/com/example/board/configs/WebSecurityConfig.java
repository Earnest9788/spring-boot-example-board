package com.example.board.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

// https://stackoverflow.com/questions/40495244/too-many-redirects-with-own-login-form-spring-security
// https://velog.io/@jayjay28/2019-09-04-1109-%EC%9E%91%EC%84%B1%EB%90%A8

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors().and()
            .csrf().disable();

        http
            .authorizeRequests()
            .antMatchers("/**").permitAll();
        
        http
            .formLogin()
            .loginPage("/pages/user/login").and()
            .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}

// @Override
// protected void configure(HttpSecurity http) throws Exception {
//     http.authorizeRequests()
//         .antMatchers("/login*/**").permitAll()
//         .antMatchers("/logout/**").permitAll()
//         .antMatchers("/chatbot/**").permitAll()
//         .anyRequest().authenticated()
//     .and().logout()
//           .logoutUrl("/logout")
//           .logoutSuccessHandler(logoutSuccessHandler())
//     .and().csrf()
//           .disable()
//     .addFilter(jwtAuthenticationFilter())
//     .addFilter(jwtAuthorizationFilter())
//     .exceptionHandling()
//           .accessDeniedHandler(accessDeniedHandler())
//           .authenticationEntryPoint(authenticationEntryPoint())
// //        .and().sessionManagement()
// //              .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//     ;
// }
