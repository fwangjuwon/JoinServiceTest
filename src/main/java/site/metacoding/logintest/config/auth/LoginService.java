package site.metacoding.logintest.config.auth;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.logintest.domain.User;
import site.metacoding.logintest.domain.UserRepository;

@RequiredArgsConstructor
@Service // IoC 컨테이너 등록됨.
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOp = userRepository.findByUsername(username);
        if (userOp.isPresent()) {
            return new LoginUser(userOp.get());
        }
        return null;
    }

}