package apptive.fruitable.controller;

import apptive.fruitable.domain.post.Post;
import apptive.fruitable.repository.PostRepository;
import apptive.fruitable.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    /*
     * 게시글 목록
     */
    @GetMapping("/list")
    public String list(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("postList", postService.findBoardList(pageable));
        return "/post/list";
    }

    /*
     * 게시글 상세 및 등록 폼 호출
     */
    @RequestMapping({"", "/"})
    public String post(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model) {
        model.addAttribute("post", postService.findBoardByIdx(idx));
        return "/post/form";
    }

    /*
     * 게시글 생성
     */
    public ResponseEntity<?> writePost(@RequestBody Post post) {
        postRepository.save(post);
        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

    /*
     * 게시글 수정
     */
    public ResponseEntity<?> rewritePost(@PathVariable("idx") Long idx, @RequestBody Post post) {
        Post updatePost = postRepository.getReferenceById(idx);
        updatePost.setContact(post.getContact());
        updatePost.setTitle(post.getTitle());
        updatePost.setPrice(post.getPrice());
        updatePost.setEndDate(post.getEndDate());

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    /*
     * 게시글 삭제
     */
    @DeleteMapping("/{idx}")
    public ResponseEntity<?> deletePost(@PathVariable("idx") Long idx) {
        postRepository.deleteById(idx);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
