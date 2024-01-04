package org.supercoding.server.web.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.supercoding.server.web.dto.UserDTO;

@Entity
@Data
@Table(name = "user_table")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    public UserDTO toDTO(){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(getEmail());
        userDTO.setPassword(getPassword());
        return userDTO;
    }
}
