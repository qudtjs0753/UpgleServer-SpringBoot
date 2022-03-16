package com.kbs.upgle.service;

import com.kbs.upgle.constant.ErrorCode;
import com.kbs.upgle.domain.user.User;
import com.kbs.upgle.domain.user.UserRepository;
import com.kbs.upgle.dto.LogInReq;
import com.kbs.upgle.dto.SignUpReq;
import com.kbs.upgle.dto.SignUpRes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kbs.upgle.constant.ErrorCode.UNAUTHENTICATED_MEMBER;

/**
 * @Author: kbs
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignUpRes signUp(SignUpReq signUpReq){

        if(userRepository.findByEmail(signUpReq.getEmail()).isPresent()){
            throw new RuntimeException(ErrorCode.DUPLICATE_USER.getMessage());
        }else {
            User user = userRepository.save(User.builder()
                    .email(signUpReq.getEmail())
                    .nickname(signUpReq.getNickname())
                    .password(passwordEncoder.encode(signUpReq.getPassword()))
                    .build());
            return SignUpRes.from(user);
        }
    }



    public void checkEmail(String email) {

    }

//    public void logIn(LogInReq logInReq) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(logInReq.getEmail())
//                .orElseThrow(() -> new UsernameNotFoundException(UNAUTHENTICATED_MEMBER.getMessage()));
//        UsernamePasswordAuthenticationToken authenticationToken = LogInReq.();
//
//    }
}
