package org.supercoding.server.web.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.supercoding.server.web.dto.PostRequestDto;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "post_table")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at", nullable = false)
    private Date createAt;

    public PostEntity(PostRequestDto postRequestDto) {
        this.user = postRequestDto.getUser();
        this.content = postRequestDto.getContent();
        this.title = postRequestDto.getTitle();
        this.createAt = postRequestDto.getCreateAt();
    }
}
