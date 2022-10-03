package apptive.fruitable.controller;

import apptive.fruitable.web.dto.PostDto;
import apptive.fruitable.repository.PostRepository;
import apptive.fruitable.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostServiceImpl postService;

    @Autowired
    private PostRepository postRepository;

    /*
     * 게시글 작성/등록
     */
    @GetMapping(value = "/new")
    public String newPost(Model model) {
        model.addAttribute("postDto", new PostDto());
        return "post/postForm";
    }

    @PostMapping(value = "/new")
    public String newPost(@Valid PostDto postDto, BindingResult bindingResult,
                          Model model, @RequestParam("postImgFile")List<MultipartFile> postImgFileList) {

        if(bindingResult.hasErrors()) {
            return "post/postForm";
        }

        if(postImgFileList.get(0).isEmpty() && postDto.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 이미지는 필수 입력 값입니다.");
            return "/post/postForm";
        }

        try {
            postService.savedPost(postDto, postImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "/post/postForm";
        }

        //상품이 정상적으로 등록되면 리스트 페이지로 이동
        return "/post/list";
    }

    /*
     * 상품 수정 페이지로 진입
     */
    @GetMapping(value = "/{postId}")
    public String postDetail(@PathVariable("postId") Long postId, Model model) {

        try {
            PostDto postDto = postService.getPostDetail(postId);
            model.addAttribute("postDto", postDto);
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 상품입니다.");
            model.addAttribute("postDto", new PostDto());
            return "post/postForm";
        }

        return "post/postForm";
    }

    /*
     * 상품 수정 페이지
     */
    @PostMapping(value = "/{postId}")
    public String postUpDate(@Valid PostDto postDto, BindingResult bindingResult,
                             @RequestParam("postImgFile") List<MultipartFile> postImgFileList, Model model) {

        if(bindingResult.hasErrors()) {
            return "post/postForm";
        }

        if(postImgFileList.get(0).isEmpty() && postDto.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력값입니다.");
            return "post/postForm";
        }

        try {
            postService.savedPost(postDto, postImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
            return "post/postForm";
        }

        return "/post/list";
    }

    /*
     * 게시글 목록
     */
    @GetMapping("/list")
    public String list(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("postList", postService.findBoardList(pageable));
        return "/post/list";
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
