package io.github.simaomenezes.libraryapi.security;

import io.github.simaomenezes.libraryapi.model.User;
import io.github.simaomenezes.libraryapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService service;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User u = service.findByLogin(login);

        if(u == null)
            throw new UsernameNotFoundException("User not found");

        return org.springframework.security.core.userdetails.User.builder()
                .username(u.getLogin())
                .password(u.getPassword())
                .roles(u.getRoles().toArray(new String[u.getRoles().size()]))
                .build();
    }
}
