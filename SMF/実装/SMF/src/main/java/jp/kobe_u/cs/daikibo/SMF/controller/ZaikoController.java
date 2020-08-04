package jp.kobe_u.cs.daikibo.SMF.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ZaikoController {
    @GetMapping("/")
    String showIndex(){
        return "index";
    }

    @GetMapping("/manage")
    String showlist(Model model){
        // List<Tsubuyaki> list = ts.getAllTsubuyaki(); //全つぶやきを取得
        // model.addAttribute("tsubuyakiList", list);   //モデル属性にリストをセット
        model.addAttribute("zaikoForm", new ZaikoForm());  //空フォームをセット
        return "zaiko";
    }
    
}