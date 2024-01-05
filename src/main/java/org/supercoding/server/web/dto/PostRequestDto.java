package org.supercoding.server.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.supercoding.server.web.entity.UserEntity;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class PostRequestDto {

    private UserEntity user;
    private String title;
    private String content;
    private Date createAt;



}
