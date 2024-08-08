package org.scoula.ex03.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.scoula.dto.SampleDTO;
import org.scoula.dto.TodoDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller // 웹 요청을 처리하는 컨트롤러
@RequestMapping("/sample") // 컨트롤러가 처리할 기본 url
@Log4j // log 객체를 사용하여 로그 출력 가능
public class SampleController {

    // HTTP 요청을 특정 메서드에 매핑
    @RequestMapping("")
    public void basic(){
        log.info("basic.................");
    }

    // HTTP 요청을 특정 메서드에 매핑
    // value에 URL, method에 HTTP 메서드(get, post)
    @RequestMapping(value="/basic", method= {RequestMethod.GET, RequestMethod.POST})
    public void basicGet(){
        log.info("basic get.................");
    }

    // HTTP GET 요청을 특정 핸들러 메서드에 매핑
    // @RequestMapping(method = RequestMethod.GET)의 축약형
    @GetMapping("/basicOnlyGet")
    public void basicGet2(){
        log.info("basic get only get.................");
    }

    @GetMapping("/ex01")
    public String ex01(SampleDTO dto){
        log.info("" + dto);

        return "ex01"; // view(jsp 파일) 이름
    }

    @GetMapping("/ex02")
    // 웹 요청의 데이터를 자동으로 메서드 파라미터에 넣어줌
    // get의 쿼리 파라미터, post의 폼 데이터 받을 때 사용
    public String ex02(@RequestParam("name") String name, @RequestParam("age") int age){
        log.info("name: " + name);
        log.info("age: " + age);

        return "ex02";
    }

    @GetMapping("/ex02List")
    public String ex02List(
            @RequestParam("ids") ArrayList<String> ids){
        log.info("ids: " + ids);

        return "ex02List";
    }

    @GetMapping("/ex03")
    public String ex03(TodoDTO todo){
        log.info("todo: " + todo);
        return "ex03";
    }

    @GetMapping("/ex04")
    // 해당 객체를 모델에 추가하고 이름 지정할 수 있음
    public String ex03(SampleDTO dto, @ModelAttribute("page") int page){
        log.info("dto: " + dto);
        log.info("page: " + page);

        return "sample/ex04";
    }

    @GetMapping("/ex05")
    // void 타입 -> 요청 url을 jsp의 경로로 해석
    public void ex05(){
        log.info("/ex05............");
    }

    @GetMapping("/ex06")
    public String ex06(RedirectAttributes ra){
        log.info("/ex06.........");

        // ?name=AAA&age=10
        ra.addAttribute("name", "AAA");
        ra.addAttribute("age", 10);

        // 넘길 url
        return "redirect:/sample/ex06-2";
    }

    @GetMapping("/ex07")
    // @ResponseBody -> application/json 응답 타입
    // SampleDTO -> json 문자열
    public @ResponseBody SampleDTO ex07(){
        log.info("/ex07................");

        SampleDTO dto = new SampleDTO();
        dto.setAge(10);
        dto.setName("홍길동");

        return dto;
    }

    @GetMapping("/ex0701")
    public @ResponseBody SampleDTO ex0701(){
        SampleDTO dto = new SampleDTO();
        dto.setAge(10);
        dto.setName("아무개");

        return dto;
    }

    @GetMapping("/ex08")
    public ResponseEntity<String> ex08(){
        log.info("/ex08............");

        // {"name": "홍길동"}
        String msg = "{\"name\": \"홍길동\"}";

        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json;charset=UTF-8");

        // body, header, 상태코드
        return new ResponseEntity<>(msg, header, HttpStatus.OK);
    }

    @GetMapping("/ex0801")
    public ResponseEntity<String> ex0801(){
        String msg = "{\"name\": \"홍길동\"}";

        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json;charset=UTF-8");

        return new ResponseEntity<>(msg, header, HttpStatus.OK);
    }

    @GetMapping("/exUpload") // sample/exUpload
    public void exUpload(){
        log.info("/exUpload............");
    }

    // jsp의 action 경로와 일치해야 함
    // 해당 경로로 오는 post 요청을 처리하는 메서드 정의
    @PostMapping("/exUploadPost")
    // 변수명 중요! jsp의 input name과 동일
    public void exUploadPost(ArrayList<MultipartFile> files){

        for(MultipartFile file : files){
            log.info("------------------------------------");
            log.info("name:" + file.getOriginalFilename()); // 원래 파일명
            log.info("size:" + file.getSize()); // 파일 크기

        }
    }
    
}
