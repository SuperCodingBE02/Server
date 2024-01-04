package org.supercoding.server.service;

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
            commonResponseDto.setMessage("게시물이 성공적으로 작성되었습니다.");
        }else{
            commonResponseDto.setMessage("게시물 작성에 실패 했습니다.");
        }

        return commonResponseDto;

    }
}