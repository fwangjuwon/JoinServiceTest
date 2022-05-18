package site.metacoding.logintest.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.logintest.domain.User;
import site.metacoding.logintest.domain.UserRepository;
import site.metacoding.logintest.web.dto.UpdateDto;

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
    
       // 회원 탈퇴하기
    @Transactional
    public void 회원탈퇴(Integer id) {
        userRepository.deleteById(id);
    }

       @Transactional
    public void 회원수정(Integer id, UpdateDto updateDto) {
        Optional<User> userOp = userRepository.findById(id); // 영속화 (디비 row를 영속성 컨텍스에 옮김)

        if (userOp.isPresent()) {
            // 영속화된 오브젝트 수정
            User userEntity = userOp.get();
            userEntity.setPassword(updateDto.getPassword());
            userEntity.setEmail(updateDto.getEmail());
            userEntity.setAddr(updateDto.getAddr());
        } else {
            throw new RuntimeException("아이디를 찾을 수 없습니다.");
        }
    } 
}