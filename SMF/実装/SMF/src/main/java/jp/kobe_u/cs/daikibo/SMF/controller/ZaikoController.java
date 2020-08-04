package jp.kobe_u.cs.daikibo.SMF.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class ZaikoController {
    @GetMapping("/")
    String showIndex(){
        return "index";
    }
    
}