package org.supercoding.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.supercoding.server.repository.CommentRepository;
import org.supercoding.server.repository.PostRepository;
import org.supercoding.server.web.dto.CommentDto;
import org.supercoding.server.web.dto.CommonResponseDto;
import org.supercoding.server.web.entity.CommentEntity;
import org.supercoding.server.web.entity.PostEntity;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    public List<CommentDto> findAllComment() {
        List<CommentEntity> allCommentEntity = commentRepository.findAll();
        List<CommentDto> allCommentDto = new ArrayList<>();
        for(CommentEntity comment:allCommentEntity){
            allCommentDto.add(CommentDto.toCommentDto(comment));
        }
        return allCommentDto;
    }

    public CommonResponseDto addComment(CommentDto commentDto, Long postId) {

        PostEntity postEntity = postRepository.findById(postId).orElse(null);

        if(postEntity == null){
            CommonResponseDto addCommentResponse = new CommonResponseDto();
            addCommentResponse.setMessage("해당하는 게시물이 존재하지 않습니다.");
            return addCommentResponse;
        }
        commentDto.setPost(postEntity);

        CommentEntity commentEntity = CommentEntity.toCommentEntity(commentDto);
        CommentEntity savedCommentEntity = commentRepository.save(commentEntity);

        CommonResponseDto addCommentResponse = new CommonResponseDto();

        if(!savedCommentEntity.getAuthor().isEmpty()){
            addCommentResponse.setMessage("댓글이 성공적으로 작성되었습니다.");
        } else{
            addCommentResponse.setMessage("댓글 작성에 실패하였습니다.");
        }

        return addCommentResponse;
    }

    public CommonResponseDto editComment(String editContent, Long commentId) {
        CommentEntity targetComment = commentRepository.findById(commentId).orElse(null);

        if(targetComment == null){
            CommonResponseDto addCommentResponse = new CommonResponseDto();
            addCommentResponse.setMessage("해당하는 댓글이 존재하지 않습니다.");
            return addCommentResponse;
        }

        targetComment.setContent(editContent);

        commentRepository.save(targetComment);

        CommonResponseDto editCommentResponse = new CommonResponseDto();
        editCommentResponse.setMessage("댓글이 성공적으로 수정되었습니다.");
        return editCommentResponse;
    }
}
