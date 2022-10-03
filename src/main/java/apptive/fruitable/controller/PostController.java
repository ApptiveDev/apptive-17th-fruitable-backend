package apptive.fruitable.controller;

import apptive.fruitable.domain.post.Post;
import apptive.fruitable.service.PostServiceImpl;
import apptive.fruitable.web.dto.PostDto;
import apptive.fruitable.web.dto.PostSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostServiceImpl postService;

    @GetMapping(value = "/admin/post/new")
    public String postForm(Model model) {
        model.addAttribute("postDto", new PostDto());
        return "post/postForm";
    }

    @PostMapping(value = "/admin/post/new")
    public String postNew(@Valid PostDto postDto, BindingResult bindingResult, Model model,
                          @RequestParam("postImgFile")List<MultipartFile> postImgFileList) {

        if(bindingResult.hasFieldErrors()) return "post/postForm";
        if(postImgFileList.get(0).isEmpty() && postDto.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값입니다.");
            return "post/postForm";
        }

        try {
            postService.savedPost(postDto, postImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "post/postForm";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/admin/post/{postId}")
    public String postForm(@PathVariable("postId") Long postId, Model model) {

        try {
            PostDto postDto = postService
                    .getPostDetail(postId);
            model.addAttribute("postDto", postDto);
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 상품입니다.");
            model.addAttribute("postDto", new PostDto());
            return "post/postForm";
        }

        return "post/postForm";
    }

    @GetMapping(value = "/post/{postId}")
    public String postDetail(@PathVariable("postId")Long postId, Model model) {

        PostDto postDto = postService.getPostDetail(postId);
        model.addAttribute("post", postDto);

        return "post/postDetail";
    }

    @PostMapping(value = "/admin/post/{postId}")
    public String postUpdate(@Valid PostDto postDto, BindingResult bindingResult, Model model,
                             @RequestParam("postImgFile")List<MultipartFile> postImgFileList) {

        if(bindingResult.hasErrors()) return "post/postForm";
        if(postImgFileList.get(0).isEmpty() && postDto.getId() == null) {
            model.addAttribute("errorMessage", "첫번재 상품 이미지는 필수 입력값 입니다.");
            return "post/postForm";
        }

        try {
            postService.savedPost(postDto, postImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
            return "post/postForm";
        }

        return "redirect:/";
    }

   /* @GetMapping(value = {"/admin/posts", "/admin/posts/{page}"})
    public String postManage(PostSearchDto postSearchDto, @PathVariable("page")Optional<Integer> page, Model model) {

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
        Page<Post> posts = postService.getAdminPostPage(postSearchDto, pageable);

        model.addAttribute("posts", posts);
        model.addAttribute("postSearchDto", postSearchDto);
        model.addAttribute("maxPage", 5);

        return "post/postManage";
    }*/
}
