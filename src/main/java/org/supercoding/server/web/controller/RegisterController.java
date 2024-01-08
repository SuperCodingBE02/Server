package org.supercoding.server.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.supercoding.server.web.dto.CommonResponseDto;
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
    public ResponseEntity<CommonResponseDto> signup(@RequestBody UserDTO userDTO){
        log.info("회원가입 POST 요청");
        if(registerService.existsByEmail(userDTO.getEmail())){
            log.info("이메일: " + userDTO.getEmail());
            CommonResponseDto signupResult = new CommonResponseDto();
            signupResult.setMessage("동일한 이메일이 존재합니다.");
            return ResponseEntity.ok().body(signupResult);
        }

        registerService.saveUser(userDTO);
        log.info("들어온 정보: " + userDTO);
        CommonResponseDto signupResult = new CommonResponseDto();
        signupResult.setMessage("회원가입에 성공했습니다.");
        return ResponseEntity.ok().body(signupResult);
    }



}
