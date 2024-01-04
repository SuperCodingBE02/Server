package org.supercoding.server.web.controller;


import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.supercoding.server.web.dto.UserDTO;
import org.supercoding.server.service.RegisterService;
import org.supercoding.server.web.entity.UserEntity;


@AllArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class RegisterController {
    private RegisterService registerService;


    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserDTO userDTO){
        log.info("회원가입 GET 요청");
        if(registerService.existsByEmail(userDTO.getEmail())){
            log.info("이메일: " + userDTO.getEmail());
            return ResponseEntity.badRequest().body("Email already exists");
        }


        UserEntity newUserEntity = userDTO.toEntity();
        registerService.saveUser(newUserEntity);
        log.info("들어온 정보: " + newUserEntity);
        return ResponseEntity.ok("User signed up succesfully");
    }



}
