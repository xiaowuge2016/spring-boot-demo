package com.lifujian.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lifujian
 * @date  2018年6月2日
 * @description Controller
 */
@RestController
public class WelcomeController {
    
    @RequestMapping("/")
    public String index(){
        return "hello, 世界！";
    }
}
 