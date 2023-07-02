package apptive.fruitable.board.controller;

import apptive.fruitable.board.dto.comment.CommentDto;
import apptive.fruitable.board.service.inter.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
@Slf4j
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<Long> writeComment(@PathVariable Long postId,
                             @RequestPart(name = "comment") CommentDto.CommentRequestDto commentDto) {

        Long commentId = commentService.saveComment(postId, commentDto);
        return new ResponseEntity<>(commentId, HttpStatus.CREATED);
    }

    @PutMapping("/{postId}/update")
    public ResponseEntity<String> updateComment(@PathVariable Long postId,
                                                 @RequestPart(name = "comment") CommentDto.CommentUpdateRequestDto commentDto) {

        String content = commentService.update(commentDto);
        return new ResponseEntity<>(content, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/{commentId}")
    public ResponseEntity<Objects> deleteComment(@PathVariable Long postId,
                                                 @PathVariable Long commentId) {

        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
