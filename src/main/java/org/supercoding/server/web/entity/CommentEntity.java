package org.supercoding.server.web.entity;

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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    @Column(nullable = false)
    private String author;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at", nullable = false)
    private Date createAt;

    public static CommentEntity toCommentEntity(CommentDto commentDto) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setPost(commentDto.getPost());
        commentEntity.setAuthor(commentDto.getAuthor());
        commentEntity.setContent(commentDto.getContent());
        commentEntity.setCreateAt(commentDto.getCreateAt());
        return commentEntity;
    }
}
