package org.supercoding.server.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.supercoding.server.repository.PostRepository;
import org.supercoding.server.repository.UserRepository;
import org.supercoding.server.web.dto.CommentDto;
import org.supercoding.server.web.dto.CommonResponseDto;
import org.supercoding.server.web.dto.PostRequestDto;
import org.supercoding.server.web.dto.PostResponseDto;
import org.supercoding.server.web.entity.CommentEntity;
import org.supercoding.server.web.entity.PostEntity;
import org.supercoding.server.web.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommonResponseDto addPost(PostRequestDto postRequestDto){
        log.info("[PostService] posrRequestDto = " + postRequestDto);

        UserEntity userData = userRepository.findById(postRequestDto.getUserId()).orElse(null);

        PostEntity postEntity = new PostEntity(postRequestDto);
        postEntity.setUser(userData);
        log.info("[PostService] 저장될 postEntity = " + postEntity);
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

    public List<PostResponseDto> findAllPosts() {
        List<PostEntity> allPostEntity = postRepository.findAll();
        List<PostResponseDto> allPostDto = new ArrayList<>();

        CommonResponseDto commonResponseDto = new CommonResponseDto();

        for(PostEntity post:allPostEntity){
            allPostDto.add(PostResponseDto.toPostResponseDto(post));
        }

        if(allPostEntity.isEmpty()){
            commonResponseDto.setMessage("게시물이 존재하지 않습니다.");
        }
        return allPostDto;

    }

    public List<PostResponseDto> findByEmail(String email) {
        List<PostEntity> targetPostEntity = postRepository.findByUser_Email(email);
        List<PostResponseDto> targetPostDto = new ArrayList<>();

        CommonResponseDto commonResponseDto = new CommonResponseDto();

        for(PostEntity post : targetPostEntity){
            targetPostDto.add(PostResponseDto.toPostResponseDto(post));
        }

        return targetPostDto;
    }
}


