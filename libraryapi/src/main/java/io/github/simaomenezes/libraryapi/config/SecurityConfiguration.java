package io.github.simaomenezes.libraryapi.config;

import io.github.simaomenezes.libraryapi.security.CustomUserDetailsService;
import io.github.simaomenezes.libraryapi.security.JwtAuthenticationCustomFilter;
import io.github.simaomenezes.libraryapi.security.LoginSocialSuccessHandler;
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
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            LoginSocialSuccessHandler loginSocialSuccessHandler,
            JwtAuthenticationCustomFilter jwtAuthenticationCustomFilter) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .formLogin(configured->{
                    configured
                            .loginPage("/login");
                })
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/login/**").permitAll();
                    //authorize.requestMatchers("/users/**").permitAll();
                    authorize.requestMatchers(HttpMethod.POST, "/users/**").permitAll();

                    authorize.anyRequest().authenticated();
                })
                .oauth2Login(auth ->{
                    auth.loginPage("/login")
                            .successHandler(loginSocialSuccessHandler);
                })
                .oauth2ResourceServer(
                        oauth2ResourceServer->
                                oauth2ResourceServer
                                        .jwt(Customizer.withDefaults()))
                .addFilterAfter(jwtAuthenticationCustomFilter, BearerTokenAuthenticationFilter.class)
                .build();
    }

    //@Bean
    /*
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder(10);
        }
    */

    //@Bean
    /*
        public UserDetailsService userDetailsService(UserService userService){
            return new CustomUserDetailsService(userService);
        }
    */

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults(){
        return new GrantedAuthorityDefaults("");
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){
        var jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return converter;
    }
}
