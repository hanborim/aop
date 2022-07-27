package com.example.aop.controller;

import com.example.aop.annotation.Decode;
import com.example.aop.annotation.Timer;
import com.example.aop.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping("/get/{id}")
    public String  get(@PathVariable Long id , @RequestParam String name)
    {
        System.out.println("get method");
        System.out.println("get method : " + id);
        System.out.println("get method" + name);
        //각메소드마다 로그 이렇게 만들꺼냐? 로그찍는거 따로 만들자 AOP
        return id +" "+ name;

    }

    @PostMapping("/post")
    public User post(@RequestBody User user)
    {
        System.out.printf("post method : "+ user);
        return user;
    }

    @Timer
    @DeleteMapping("/delete")
    public void delete() throws InterruptedException {
        //디비 로직 2초 걸림
        Thread.sleep(1000*2);
    }

    @Decode //디코드가 필요함 오노테이션 우리가 만든거
    @PutMapping("/put")
    public User put(@RequestBody User user)
    {
        System.out.println("put");
        System.out.println(user);;
        return user;
    }


}
