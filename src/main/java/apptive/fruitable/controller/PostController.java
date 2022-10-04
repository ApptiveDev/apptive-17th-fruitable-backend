package apptive.fruitable.controller;

import apptive.fruitable.domain.post.PostFileVO;
import apptive.fruitable.service.PhotoService;
import apptive.fruitable.service.PostService;
import apptive.fruitable.dto.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private PostService postService;
    private PhotoService photoService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * postDtoList를 "board/list"에 postList로 전달
     * @param model
     * @return
     */
    @GetMapping("/")
    public String list(Model model) {

        List<PostDto> postDtoList = postService.getPostList();
        model.addAttribute("postList", postDtoList);
        return "board/list";
    }

    /*
     * 글쓰는 페이지로 이동
     */
    @GetMapping("/post")
    public String post() {
        return "board/post";
    }

    /**
     * Post로 받은 데이터를 데이터베이스에 추가
     *
     * @param postFileVO
     * @return 원래 화면
     */
    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public Long write(PostFileVO postFileVO) throws Exception {

        PostDto postDto = PostDto.builder()
                .userId(postFileVO.getUserId())
                .contact(postFileVO.getContact())
                .vege(postFileVO.getVege())
                .title(postFileVO.getTitle())
                .content(postFileVO.getContent())
                .price(postFileVO.getPrice())
                .endDate(postFileVO.getEndDate())
                .build();

        return postService.savePost(postDto, postFileVO.getFiles());
    }

    /**
     * 각 게시글 클릭시 /post/1 과 같이 get 요청, 해당 아이디의 데이터가 view로 전달되도록 함
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/post/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {

        PostDto postDto = postService.getPost(id);
        model.addAttribute("post", postDto);
        return "board/detail";
    }

    /**
     * id에 해당하는 게시글을 수정할 수 있음
     * @param id
     * @param model
     * @return put 형식으로 /post/edit/{id}로 서버에 요청 감
     */
    @GetMapping("/post/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {

        PostDto postDto = postService.getPost(id);
        model.addAttribute("post", postDto);
        return "board/edit";
    }

    /**
     * 서버에 put 요청이 오면, 데이터베이스에 변경된 데이터를 저장함
     * @param postDto
     * @return
     */
    @PutMapping("/post/edit/{id}")
    public String update(PostDto postDto) {

        //postService.savePost(postDto);
        return "redirect:/";
    }

    @DeleteMapping("/post/{id}")
    public String delete(@PathVariable("id") Long id) {

        postService.deletePost(id);
        return "redirect:/";
    }
}
