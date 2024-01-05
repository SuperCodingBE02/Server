package org.supercoding.server.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.supercoding.server.repository.PostRepository;
import org.supercoding.server.web.dto.CommonResponseDto;
import org.supercoding.server.web.dto.PostRequestDto;
import org.supercoding.server.web.dto.PostResponseDto;
import org.supercoding.server.web.entity.PostEntity;
import org.supercoding.server.web.entity.UserEntity;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public CommonResponseDto addPost(PostRequestDto postRequestDto){
        UserEntity user = new UserEntity(1L, "김소은", "1234");
        postRequestDto.setUser(user);

        PostEntity postEntity = new PostEntity(postRequestDto);
        PostEntity savePostEntity = postRepository.save(postEntity);

        CommonResponseDto commonResponseDto = new CommonResponseDto();
        if(savePostEntity.getId()!=null) {
            commonResponseDto.setMessage("게시물이 성공적으로 작성 되었습니다.");
        }else{
            commonResponseDto.setMessage("게시물 작성에 실패 했습니다.");
        }

        return commonResponseDto;
    }


    //변경감지 JPA DirtyChecking
    @Transactional
    public CommonResponseDto updatePost(Long postId, PostRequestDto postRequestDto){
        CommonResponseDto commonResponseDto = new CommonResponseDto();

        PostEntity targetPost = postRepository.findById(postId).orElse(null);
        if(targetPost == null){
            commonResponseDto.setMessage("게시물이 존재하지 않습니다");
            return commonResponseDto;
        }
        targetPost.update(postRequestDto);
//        postRepository.save(targetPost);

        commonResponseDto.setMessage("게시물을 수정하였습니다");
        return commonResponseDto;
    }

    public CommonResponseDto deletePost(long postId){
        //1. 타겟 게시물 찾기
        PostEntity targetPost = postRepository.findById(postId).orElse(null);
        CommonResponseDto commonResponseDto = new CommonResponseDto();

        if(targetPost!=null){
            postRepository.deleteById(postId);
            commonResponseDto.setMessage("게시물을 삭제 하였습니다.");
        }else{
            commonResponseDto.setMessage("게시물이 존재하지 않습니다.");
        }

        return commonResponseDto;
    }

}


