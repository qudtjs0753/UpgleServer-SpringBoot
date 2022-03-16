package com.kbs.upgle.controller;

import com.kbs.upgle.dto.LogInReq;
import com.kbs.upgle.dto.SignUpReq;
import com.kbs.upgle.dto.SignUpRes;
import com.kbs.upgle.dto.TokenDto;
import com.kbs.upgle.security.jwt.JwtFilter;
import com.kbs.upgle.security.jwt.TokenProvider;
import com.kbs.upgle.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author: kbs
 */


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final AuthService authService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/check/email")
    public ResponseEntity<Void> checkEmail(@Valid @RequestBody String email){
        authService.checkEmail(email);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpRes> signUp(@Valid @RequestBody SignUpReq signUpReq) {
        return ResponseEntity.ok(authService.signUp(signUpReq));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> logIn(@Valid @RequestBody LogInReq loginReq){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
=        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }
}
