package org.supercoding.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.supercoding.server.web.entity.CommentEntity;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
