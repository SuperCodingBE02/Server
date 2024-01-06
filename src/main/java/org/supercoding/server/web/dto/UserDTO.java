package org.supercoding.server.web.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.supercoding.server.web.entity.UserEntity;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDTO {
    @Schema(description = "유저 이메일", example = "qwer1234@qwer.com")
    private String email;
    @Schema(description = "유저 비밀번호", example = "*****")
    private String password;



    public UserEntity toEntity(){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(getEmail());
        userEntity.setPassword(getPassword());
        return userEntity;

    }

}
