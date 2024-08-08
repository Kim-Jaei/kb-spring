package org.scoula.test.controller;

import lombok.extern.log4j.Log4j;
import org.scoula.test.controller.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/test")
@Log4j
public class TestController {
    //GER + /basic
    @GetMapping("/basic")
    public String basic(){
        log.info("basic =====> TestController.basic");
        return "basic";
    }

    //POST + /basic
//    @PostMapping(value="/basic")
//    public String postBasic(){
//        log.info("basic =====> TestController.postBasic");
//        return "redirect:/test/basic";
//    }

    //POST + /basic
    @RequestMapping(value="/basic", method={RequestMethod.POST})
    public String basic2(){
        log.info("basic =====> TestController.basic2");
        return "/basic";
    }

    @GetMapping("/user")
    public String user(@RequestParam("name") String name, @RequestParam("age") int age){
        log.info("GET user =====> TestController.user");
        log.info("name: " + name);
        log.info("age: " + age);
        System.out.println("name: " + name + ", age: " + age);
        return "user";
    }

    @GetMapping("/userdto")
    public void userdto(UserDTO dto){
        log.info("GET user =====> TestController.userdto");
        log.info("dto: " + dto);
    }
}