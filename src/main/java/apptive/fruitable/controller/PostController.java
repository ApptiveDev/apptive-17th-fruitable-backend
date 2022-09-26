package apptive.fruitable.controller;

import apptive.fruitable.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Date;

@Controller
public class PostController {

    @Autowired
    FileUploadService fileUploadService;

    @RequestMapping("/form")
    public String form() {
        return "redirect:";
    }

    @RequestMapping("/upload")
    public String upload(Model model,
                         @RequestParam("title") String title,
                         @RequestParam("content") String content,
                         @RequestParam("price") int price,
                         @RequestParam("img") MultipartHttpServletRequest file,
                         @RequestParam("endDate")Date date,
                         @RequestParam("contact") String contact) {

        String url = fileUploadService.restore(file);
        model.addAttribute("url", url);
        return "result";
    }

}
