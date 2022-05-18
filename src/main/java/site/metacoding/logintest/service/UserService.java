package site.metacoding.logintest.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.logintest.domain.User;
import site.metacoding.logintest.domain.UserRepository;

@RequiredArgsConstructor
@Service // IoC 등록
public class UserService {

    // DI
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void 회원가입(User user) {
        String rawPassword = user.getPassword(); 
        String encPassword = bCryptPasswordEncoder.encode(rawPassword); 
        user.setPassword(encPassword);
        userRepository.save(user);
    }
}