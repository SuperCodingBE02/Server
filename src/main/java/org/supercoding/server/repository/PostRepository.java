package org.supercoding.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.supercoding.server.web.entity.PostEntity;

import java.util.*;
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    List<PostEntity> findByUser_Email(String email);
}
