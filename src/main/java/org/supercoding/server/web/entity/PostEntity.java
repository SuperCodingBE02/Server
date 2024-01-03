package org.supercoding.server.web.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
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
    @Column(name = "create_date", nullable = false)
    private Date createDate;
}
