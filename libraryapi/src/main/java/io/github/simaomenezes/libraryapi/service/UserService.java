package io.github.simaomenezes.libraryapi.service;

import io.github.simaomenezes.libraryapi.model.User;
import io.github.simaomenezes.libraryapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private PasswordEncoder encoder;

    public void Add(User user){
        var password = user.getPassword();
        user.setPassword(encoder.encode(password));
        repository.save(user);
    }

    public User findByLogin(String login){
        return repository.findByLogin(login);
    }
}
