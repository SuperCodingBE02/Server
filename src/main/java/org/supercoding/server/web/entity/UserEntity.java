package org.supercoding.server.web.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
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
}
