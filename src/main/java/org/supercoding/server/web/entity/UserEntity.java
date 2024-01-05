package org.supercoding.server.web.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_table")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "유저 고유 id")
    private Long id;

    @Column(unique = true)
    @Schema(description = "유저 이메일", example = "qwer1234@qwer.com")
    private String email;

    @Column
    @Schema(description = "유저 비밀번호", example = "*****")
    private String password;

    @OneToMany(mappedBy = "user")
    @Schema(description = "유저 작성 게시물")
    private List<PostEntity> posts;

    private String roles;

    public List<String> getRoleList() {
        if (!this.roles.isEmpty()) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public UserEntity(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
