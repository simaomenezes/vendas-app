package io.github.simaomenezes.libraryapi.config;

import io.github.simaomenezes.libraryapi.security.CustomUserDetailsService;
import io.github.simaomenezes.libraryapi.security.OAuth2LoginSucessHandler;
import io.github.simaomenezes.libraryapi.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChainHttp(HttpSecurity http, OAuth2LoginSucessHandler oAuth2LoginSucessHandler) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .formLogin(configured->{
                    configured
                            .loginPage("/login")
                            .permitAll();
                })
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/auth2/**").permitAll();
                    authorize.requestMatchers("/login/**").permitAll();
                    authorize.requestMatchers(HttpMethod.POST, "/users/**").permitAll();

                    authorize.anyRequest().authenticated();
                })
                .oauth2Login(auth ->{
                    auth.loginPage("/login")
                            .successHandler(oAuth2LoginSucessHandler);
                })
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService){
        return new CustomUserDetailsService(userService);
    }
}
