package gradle.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("profile")
public class ProfileController {
    @RequestMapping("name")
    public String name() {
        return "name";
    }
    @RequestMapping(path = "age", method = RequestMethod.POST)
    public String age(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "age";
    }
    @RequestMapping(path = "hello", method = RequestMethod.POST)
    public String hello(@RequestParam("age") String age, Model model) {
        model.addAttribute("age", age);
        return "hello";
    }
}
