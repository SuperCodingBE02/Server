package org.supercoding.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.supercoding.server.web.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
