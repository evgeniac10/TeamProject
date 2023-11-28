package com.example.wetro.user.service;

import com.example.wetro.user.dto.User;
import com.example.wetro.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SecurityCustomUserDetailService implements UserDetailsService {

    private final UserRepository userREpository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        return userREpository.findOneWithAuthoritiesByUserid(userid)
                .map(user -> createUser(userid, user))
                .orElseThrow(() -> new UsernameNotFoundException(userid + " -> 데이터베이스에서 찾을 수 없습니다."));
    }
    private org.springframework.security.core.userdetails.User createUser(String userid, User user) {
        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUserid(),
                user.getPassword(),
                grantedAuthorities);
    }
}
