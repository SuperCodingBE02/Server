package org.supercoding.server.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.supercoding.server.service.CommentService;
import org.supercoding.server.web.dto.CommentDto;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @GetMapping("")
    public ResponseEntity<List<CommentDto>> allComments(){
        log.info("GET 댓글 조회 요청이 들어왔습니다.");
        List<CommentDto> allComment = commentService.findAllComment();
        log.info("GET 댓글 조회 응답 값 = " + allComment);
        return ResponseEntity.ok().body(allComment);
    }

}
