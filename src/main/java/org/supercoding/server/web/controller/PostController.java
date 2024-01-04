package org.supercoding.server.web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    //TODO 게시판 삭제  @PathVariable , @RequestParam <<
    @PostMapping("/{postId}")
    public ResponseEntity<CommonResponseDto> deletePost(@PathVariable Long postId){

        return ResponseEntity.ok().body(postService.deletePost(postId));
    }
}
