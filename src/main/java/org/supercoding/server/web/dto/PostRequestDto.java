package org.supercoding.server.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    //TODO string으로 바꿔야할까?
    @Schema(description = "작성자", example = "홍길동")
    private UserEntity user;
    @Schema(description = "글 제목", example = "곱창마렵다")
    private String title;
    @Schema(description = "글 본문")
    private String content;
    @Schema(description = "작성일")
    private Date createAt;



}
