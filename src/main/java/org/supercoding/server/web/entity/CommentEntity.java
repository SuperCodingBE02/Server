package org.supercoding.server.web.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.supercoding.server.web.dto.CommentDto;

import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "comment_table")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "댓글 고유 id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    @Schema(description = "개시물 Entity")
    private PostEntity post;

    @Column(nullable = false)
    @Schema(description = "작성자", example = "홍길동")
    private String author;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    @Schema(description = "내용", example = "댓글 예시 1")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at", nullable = false)
    @Schema(description = "작성일")
    private Date createAt;

    @PrePersist
    protected void onCreate(){
        createAt = new Date();
    }

    public static CommentEntity toCommentEntity(CommentDto commentDto) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setPost(commentDto.getPost());
        commentEntity.setAuthor(commentDto.getAuthor());
        commentEntity.setContent(commentDto.getContent());
        commentEntity.setCreateAt(commentDto.getCreateAt());
        return commentEntity;
    }
}
