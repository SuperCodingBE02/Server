package org.supercoding.server.web.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.supercoding.server.web.dto.PostRequestDto;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "post_table")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "게시물 고유 id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Schema(description = "게시물 작성자")
    private UserEntity user;

    @Column(nullable = false)
    @Schema(description = "게시물 제목", example = "예시 게시물")
    private String title;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    @Schema(description = "게시물 내용", example = "예시 게시물 내용")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at", nullable = false)
    @Schema(description = "작성일")
    private Date createAt;


    @OneToMany(mappedBy = "post")
    @Schema(description = "게시물에 달린 댓글")
    private List<CommentEntity> comments;


    public PostEntity(PostRequestDto postRequestDto) {
        this.content = postRequestDto.getContent();
        this.title = postRequestDto.getTitle();
        this.createAt = postRequestDto.getCreateAt();
    }

    public PostEntity update(PostRequestDto postRequestDto){
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();

        return this;
    }

}
