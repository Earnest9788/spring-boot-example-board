package com.example.board.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// https://stackoverflow.com/questions/40495244/too-many-redirects-with-own-login-form-spring-security

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .and()
            .formLogin()
            .loginPage("/pages/user/login")
                .and()
            .httpBasic();
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
