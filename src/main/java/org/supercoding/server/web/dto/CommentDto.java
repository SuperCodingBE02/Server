package org.supercoding.server.web.dto;

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
    private PostEntity post;
    private String author;
    private String content;
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
