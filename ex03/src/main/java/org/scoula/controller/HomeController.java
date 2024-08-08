package org.scoula.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("name", "홍길동");
        log.info("================> HomController /");
        return "index"; // 단순 문자열: View의 이름
    }
}