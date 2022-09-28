package apptive.fruitable.controller;

import apptive.fruitable.domain.post.Post;
import apptive.fruitable.domain.post.PostDto;
import apptive.fruitable.repository.PostRepository;
import apptive.fruitable.service.PostService;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostServiceImpl postService;

    @Autowired
    private PostRepository postRepository;

    @GetMapping(value = "/new")
    public String newPost(Model model) {
        model.addAttribute("postDto", new PostDto());
        return "/post/list";
    }

    @PostMapping(value = "/new")
    public String newPost(@Valid PostDto postDto, BindingResult bindingResult,
                          Model model, @RequestParam("postImgFile")List<MultipartFile> postImgFileList) {

        if(bindingResult.hasErrors()) {
            return "/post/form";
        }

        if(postImgFileList.get(0).isEmpty() && postDto.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 이미지는 필수 입력 값입니다.");
            return "/post/form";
        }

        try {
            postService.savedPost(postDto, postImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "/post/form";
        }

        return "redirect:/";
    }

    /*
     * 게시글 목록
     */
    @GetMapping("/list")
    public String list(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("postList", postService.findBoardList(pageable));
        return "/post/list";
    }

    @RequestMapping(value="/write", method=RequestMethod.POST)
    public String write(
            HttpServletRequest req, @RequestParam("file") MultipartFile file,
            @RequestParam("vege")Integer vege,
            @RequestParam("title")String title,
            @RequestParam("userId")String userId,
            @RequestParam("content")String content,
            @RequestParam("contact")String contact,
            @RequestParam("price")Integer price,
            @RequestParam("endDate")LocalDateTime endDate) throws IllegalStateException, IOException {
        String PATH = req.getSession().getServletContext().getRealPath("/") + "resources/";
        if (!file.getOriginalFilename().isEmpty()) {
            file.transferTo(new File(PATH + file.getOriginalFilename()));
        }
        //postService.(new Post(0, userId, title, contents, file.getOriginalFilename()));
        return "board";
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
