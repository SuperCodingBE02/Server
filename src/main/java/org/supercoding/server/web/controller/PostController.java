package org.supercoding.server.web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.supercoding.server.service.PostService;
import org.supercoding.server.web.dto.CommonResponseDto;
import org.supercoding.server.web.dto.PostRequestDto;
import org.supercoding.server.web.dto.PostResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @PostMapping("")
    public ResponseEntity<CommonResponseDto> addPost(@RequestBody PostRequestDto postRequestDto) {

        return ResponseEntity.ok().body(postService.addPost(postRequestDto));
    }

    //TODO 게시판 수정

    //TODO 게시판 삭제
}
