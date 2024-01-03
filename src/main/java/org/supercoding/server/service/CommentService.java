package org.supercoding.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.supercoding.server.repository.CommentRepository;
import org.supercoding.server.web.dto.CommentDto;
import org.supercoding.server.web.entity.CommentEntity;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    public List<CommentDto> findAllComment() {
        List<CommentEntity> allCommentEntity = commentRepository.findAll();
        List<CommentDto> allCommentDto = new ArrayList<>();
        for(CommentEntity comment:allCommentEntity){
            allCommentDto.add(CommentDto.toCommentDto(comment));
        }
        return allCommentDto;
    }
}
