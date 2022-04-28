package me.changwoo.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        // 강도 설정에 따른 Bruteforce attack 방지
        return new BCryptPasswordEncoder(5);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable();
        http.cors().and().csrf().disable();

        // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
//                .csrf().disable()
//
//                .exceptionHandling()
////                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
////                .accessDeniedHandler(jwtAccessDeniedHandler)
//
//                // h2-console 설정
//                .and()
//                .headers()
//                .frameOptions()
//                .sameOrigin()
//
//                // 세션을 사용하지 않기 때문에 STATELESS로 설정
//                .and()
//                .sessionManagement()
////                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//                .and()
//                .authorizeRequests() // HttpServletRequest를 사용하는 요청들에 대한 접근제한을 설정하겠다
//                .antMatchers("/auth/signUp").permitAll() // 괄호 안에 들어간 요청에 대해서는 인증없이 접근을 허용해주겠다
//                .antMatchers("/auth/login").permitAll()
////                .antMatchers("/api/signup").permitAll()
//                .anyRequest().authenticated(); // 나머지에 대해서는 모두 인증이 필요하다!

//                .and()
//                .apply(new JwtSecurityConfig(tokenProvider));
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addExposedHeader("access-token");
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedOriginPattern("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
