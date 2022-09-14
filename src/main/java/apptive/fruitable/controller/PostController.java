package apptive.fruitable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

@Controller
@RequestMapping(value = "/post/*")
public class PostController {

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public void list(Locale locale, Model model) {

    }
}
