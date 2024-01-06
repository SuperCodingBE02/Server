package org.supercoding.server.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import lombok.Data;
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
@Tag(name = "회원가입", description = "회원가입 api 입니다.")
public class RegisterController {
    private RegisterService registerService;

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDTO userDTO){
        log.info("회원가입 POST 요청");
        if(registerService.existsByEmail(userDTO.getEmail())){
            log.info("이메일: " + userDTO.getEmail());
            return ResponseEntity.badRequest().body("Email already exists");
        }

        registerService.saveUser(userDTO);
        log.info("들어온 정보: " + userDTO);
        return ResponseEntity.ok("User signed up succesfully");
    }



}
