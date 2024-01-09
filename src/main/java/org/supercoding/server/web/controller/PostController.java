package org.supercoding.server.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.supercoding.server.service.PostService;
import org.supercoding.server.web.dto.CommentDto;
import org.supercoding.server.web.dto.CommonResponseDto;
import org.supercoding.server.web.dto.PostRequestDto;
import org.supercoding.server.web.dto.PostResponseDto;

import java.util.List;

@Tag(name = "게시글", description = "게시글 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
@Slf4j
public class PostController {
    private final PostService postService;


    @GetMapping("")
    @Operation(summary = "전체 게시글 조회")
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        log.info("GET 게시글 조회 요청이 들어왔습니다.");
        List<PostResponseDto> allPosts = postService.findAllPosts();
        log.info("GET 게시글 조회 응답 값 = " + allPosts);
        return ResponseEntity.ok().body(allPosts);
    }



    @GetMapping("/search")
    @Operation(summary = "게시글 검색")
    public ResponseEntity<List<PostResponseDto>> searchPostByEmail(@RequestParam String email) {
        //TODO 정렬
        //TODO 조건 늘려보기!
        log.info("GET 게시글 검색 요청이 들어왔습니다.");
        List<PostResponseDto> allPosts = postService.findByEmail(email);
        log.info("GET 게시글 검색 응답 값 = " + allPosts);
        return ResponseEntity.ok().body(allPosts);
    }

    @PostMapping("")
    @Operation(summary = "게시글 생성")
    public ResponseEntity<CommonResponseDto> addPost(
            @RequestBody PostRequestDto postRequestDto) {

        log.info("POST 게시글 생성 요청이 들어왔습니다. " + postRequestDto);
        return ResponseEntity.ok().body(postService.addPost(postRequestDto));
    }
    @PutMapping("/:{postId}")
    @Operation(summary = "게시글 수정")
    public ResponseEntity<CommonResponseDto> updatePost(
            @PathVariable Long postId,
            @Schema(description = "변경될 게시글 내용", example = "수정된 내용")
            @RequestBody PostRequestDto postRequestDto) {

        log.info("PUT 게시글 수정 요청이 들어왔습니다. " + postRequestDto);
        return ResponseEntity.ok().body(postService.updatePost(postId, postRequestDto));
    }

    @DeleteMapping("/{postId}")
    @Operation(summary = "게시글 삭제")
    public ResponseEntity<CommonResponseDto> deletePost(@PathVariable Long postId){

        return ResponseEntity.ok().body(postService.deletePost(postId));
    }
}
