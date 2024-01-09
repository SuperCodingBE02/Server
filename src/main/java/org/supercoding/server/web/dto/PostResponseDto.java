package org.supercoding.server.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.supercoding.server.web.entity.CommentEntity;
import org.supercoding.server.web.entity.PostEntity;
import org.supercoding.server.web.entity.UserEntity;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PostResponseDto {
    @Schema(description = "고유번호")
    private Long id;
    @Schema(description = "작성자", example = "홍길동")
    private String userEmail;
    @Schema(description = "글 제목", example = "곱창마렵다")
    private String title;
    @Schema(description = "글 본문")
    private String content;
    @Schema(description = "작성일")
    private Date createAt;


    public static PostResponseDto toPostResponseDto(PostEntity postEntity) {
        PostResponseDto postResponseDto = new PostResponseDto();

        postResponseDto.setId(postEntity.getId());
        //JPA 영속성..
        postResponseDto.setUserEmail(postEntity.getUser().getEmail());
        postResponseDto.setTitle(postEntity.getTitle());
        postResponseDto.setContent(postEntity.getContent());
        postResponseDto.setCreateAt(postEntity.getCreateAt());

        return postResponseDto;
    }

}


