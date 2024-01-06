package org.supercoding.server.web.dto;


import lombok.*;
import org.apache.catalina.User;
import org.supercoding.server.web.entity.UserEntity;

@Data
public class UserDTO {
    private String email;
    private String password;
    private String role;

    public UserEntity toEntity(){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(getEmail());
        userEntity.setPassword(getPassword());
        userEntity.setRole(getRole());
        return userEntity;
    }

    public static UserDTO fromEntity(UserEntity userEntity){
        UserDTO userDto = new UserDTO();
        userDto.setEmail(userEntity.getEmail());
        userDto.setPassword(userEntity.getPassword());
        userDto.setRole(userEntity.getRole());
        return userDto;
    }

}
