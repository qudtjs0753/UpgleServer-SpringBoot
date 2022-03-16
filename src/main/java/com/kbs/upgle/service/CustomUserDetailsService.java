//package com.kbs.upgle.service;
//
//import com.kbs.upgle.domain.user.User;
//import com.kbs.upgle.domain.user.UserRepository;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
///**
// * @Author: kbs
// */
//
//
//@Component("userDetailsService")
//public class CustomUserDetailsService implements UserDetailsService {
//    private final UserRepository userRepository;
//
//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(final String email) {
//        return userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException(email + " -> 데이터베이스에서 찾을 수 없습니다."));
//    }
//}
