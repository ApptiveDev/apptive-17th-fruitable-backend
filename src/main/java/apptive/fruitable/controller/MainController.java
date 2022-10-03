package apptive.fruitable.controller;

import apptive.fruitable.service.PostServiceImpl;
import apptive.fruitable.web.dto.MainPostDto;
import apptive.fruitable.web.dto.PostSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final PostServiceImpl postService;

    @GetMapping(value = "/")
    public String main(PostSearchDto postSearchDto, @PathVariable("page")Optional<Integer> page, Model model) {

        Pageable pageable = PageRequest.of(page.orElse(0), 8);
        //Page<MainPostDto> posts = postService.getMainPostPage(postSearchDto, pageable);
        //model.addAttribute("posts", posts);
        model.addAttribute("postSearchDto", postSearchDto);
        model.addAttribute("maxPage", 5);

        return "main";
    }
}
