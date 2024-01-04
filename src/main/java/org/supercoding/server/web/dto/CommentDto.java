package org.supercoding.server.web.dto;

import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.supercoding.server.web.entity.CommentEntity;
import org.supercoding.server.web.entity.PostEntity;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CommentDto {
    @Schema(description = "연결된 게시물")
    private PostEntity post;
    @Schema(description = "작성자", example = "홍길동")
    private String author;
    @Schema(description = "내용", example = "댓글 예시")
    private String content;
    @Schema(description = "생성일")
    private Date createAt;

    public static CommentDto toCommentDto(CommentEntity commentEntity) {
        CommentDto commentDto = new CommentDto();
        commentDto.setPost(commentEntity.getPost());
        commentDto.setAuthor(commentEntity.getAuthor());
        commentDto.setContent(commentEntity.getContent());
        commentDto.setCreateAt(commentEntity.getCreateAt());
        return commentDto;
    }
}
