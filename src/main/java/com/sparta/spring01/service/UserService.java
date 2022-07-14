package com.sparta.spring01.service;

import com.sparta.spring01.dto.SignupRequestDto;
import com.sparta.spring01.models.User;
import com.sparta.spring01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(SignupRequestDto requestDto) {

        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String checkpw = requestDto.getCheckpw();

        String usernamecheck = "^[a-zA-Z0-9._ -]{3,}$";  // 닉네임은 최소3자 이상, 알파벳 대소문자,숫자로 구성
        String passwordcheck = "^[a-zA-Z0-9._ -]{4,}$"; //  비밀번호는 최소4자 이상, 닉네임과 같은 값이 포함된 경우 회원가입에 실패

        boolean regex1 = Pattern.matches(usernamecheck, username);
        boolean regex2 = Pattern.matches(passwordcheck, password);

        if(!regex1){
            throw new IllegalArgumentException("아이디는 3글자이상, 알파벳 대소문자와 숫자로만 구성해주세요");
        }

        if(!regex2 || password.contains(username)){
            throw new IllegalArgumentException("비밀번호는 최소4자 이상, 닉네임과 같은 값이 포함될수 없습니다");
        }

        if(!password.equals(checkpw)){
            throw new IllegalArgumentException("패스워드가 다릅니다.");
        }

        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()){
            throw new IllegalArgumentException("중복된 사용자 ID가 존재합니다.");
        }
        // 패스워드 암호화
        password = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(username,password);
        userRepository.save(user);

    }
}
