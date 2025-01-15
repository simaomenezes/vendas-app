package io.github.simaomenezes.libraryapi.security;

import io.github.simaomenezes.libraryapi.model.User;
import io.github.simaomenezes.libraryapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        User user = userService.findByLogin(login);
        if(user == null){
            throw UserNameNotFound();
        }

        String passwordWithoutCript = authentication.getCredentials().toString();
        String passwordCript = user.getPassword();

        boolean passwordEqualOK = encoder.matches(passwordWithoutCript, passwordCript);
        if(passwordEqualOK){
            return new CustomAuthentication(user);
        }
        throw UserNameNotFound();
    }

    private UsernameNotFoundException UserNameNotFound() {
        throw new UsernameNotFoundException("User not exist!.");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
