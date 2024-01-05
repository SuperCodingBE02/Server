package org.supercoding.server.service;


import lombok.Data;
import org.springframework.stereotype.Service;
import org.supercoding.server.repository.UserRepository;
import org.supercoding.server.web.entity.UserEntity;

import java.util.Optional;

@Service
@Data
public class RegisterService {
    private final UserRepository userRepository;

    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public void saveUser(UserEntity userEntity){
        userRepository.save(userEntity);
    }
}
