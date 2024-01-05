package org.supercoding.server.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.supercoding.server.web.entity.UserEntity;

import java.util.Date;

@Getter
@Setter
@ToString
public class PostResponseDto {
    private Long id;
    private UserEntity user;
    private String title;
    private String content;
    private Date createAt;
}
