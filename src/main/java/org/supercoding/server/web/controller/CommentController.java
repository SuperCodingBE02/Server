package org.supercoding.server.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.supercoding.server.service.CommentService;
import org.supercoding.server.web.dto.CommentDto;
import org.supercoding.server.web.dto.CommonResponseDto;

import java.util.List;

@Tag(name = "댓글", description = "댓글 관련 api 입니다.")
@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @GetMapping("")
    @Operation(summary = "전체 댓글 조회")
    public ResponseEntity<List<CommentDto>> allComments() {
        log.info("GET 댓글 조회 요청이 들어왔습니다.");
        List<CommentDto> allComment = commentService.findAllComment();
        log.info("GET 댓글 조회 응답 값 = " + allComment);
        return ResponseEntity.ok().body(allComment);
    }

    @PostMapping("/{postId}")
    @Operation(summary = "댓글 작성")
    public ResponseEntity<CommonResponseDto> addComment(
            @Schema(description = "게시글 ID", example = "1")
            @PathVariable Long postId,
            @RequestBody CommentDto commentDto) {
        log.info("POST 댓글 작성 요청이 들어왔습니다. commentDto = " + commentDto);

        CommonResponseDto addCommentResult = commentService.addComment(commentDto, postId);
        log.info(("POST 댓글 작성 요청 결과 = " + addCommentResult));

        return ResponseEntity.ok().body(addCommentResult);
    }

    @PutMapping("/editContent/{commentId}")
    @Operation(summary = "댓글 수정")
    public ResponseEntity<CommonResponseDto> editComment(
            @PathVariable Long commentId,
            @Schema(description = "변경될 댓글 내용", example = "수정된 내용")
            @RequestParam String editContent){
        CommonResponseDto editCommentResult = commentService.editComment(editContent, commentId);

        return ResponseEntity.ok().body(editCommentResult);
    }
}
