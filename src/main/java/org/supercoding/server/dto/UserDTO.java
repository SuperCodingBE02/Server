package org.supercoding.server.dto;


import lombok.*;
import org.supercoding.server.web.entity.UserEntity;

@Data
public class UserDTO {
    private String email;
    private String password;

    public UserEntity toEntity(){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(getEmail());
        userEntity.setPassword(getPassword());
        return userEntity;
    }

}
