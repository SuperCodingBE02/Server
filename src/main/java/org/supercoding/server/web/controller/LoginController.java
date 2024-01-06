package org.supercoding.server.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.supercoding.server.common.utils.TokenProvider;
import org.supercoding.server.web.dto.UserDTO;
import org.supercoding.server.service.LoginService;
import org.supercoding.server.web.entity.UserEntity;


@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api")
@Tag(name = "로그인", description = "로그인 api 입니다.")
public class LoginController {
    private final LoginService loginService;
    private final TokenProvider tokenProvider;


    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        log.info("로그인 POST 요청");
        String email = userDTO.getEmail();
        String password = userDTO.getPassword();


        if (!loginService.existsByEmail(email)) {
            return ResponseEntity.badRequest().body("Email not found");
        }

        UserEntity userEntity = loginService.getUserByEmail(email);

        if (!userEntity.getPassword().equals(password)) {
            log.info("사용자가 입력한 일치하지 않는 비밀번호: " + password);
            return ResponseEntity.badRequest().body("Incorrect password");
        }
        String token = tokenProvider.generateJwtToken(userDTO);
        log.info("생성된 token = " + token);


        log.info("사용자가 입력한 비밀번호: " + password);
        return ResponseEntity.ok(token);
    }

    @Operation(summary = "로그아웃")
    @PostMapping("/logout")
    public String logout(){
        log.info("로그아웃 POST 요청");
        return "Logout successful";
    }
}
