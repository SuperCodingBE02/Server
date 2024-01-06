package org.supercoding.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.supercoding.server.repository.UserRepository;
import org.supercoding.server.web.dto.CustomUserDetailDto;
import org.supercoding.server.web.dto.UserDTO;
import org.supercoding.server.web.entity.UserEntity;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("일치하는 유저 정보가 존재하지 않습니다. email = " + email));

        UserDTO userDto = UserDTO.fromEntity(user);

        return new CustomUserDetailDto(userDto);
    }
}
