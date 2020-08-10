package jp.kobe_u.cs.daikibo.SMF.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.kobe_u.cs.daikibo.SMF.entity.Zaiko;
import jp.kobe_u.cs.daikibo.SMF.service.ZaikoService;

@Controller
public class ZaikoController {
    @Autowired
    ZaikoService zs;

    @GetMapping("/")
    String showIndex(){
        return "index";
    }

    @GetMapping("/manage")
    String showlist(Model model){
        List<Zaiko> list = zs.getAllZaiko(); // 在庫一覧を取得
        model.addAttribute("zaikoList", list);   //モデル属性にリストをセット
        model.addAttribute("zaikoForm", new ZaikoForm());  //空フォームをセット
        return "zaiko";
    }
    
    @PostMapping("/manage")
    String saveStocks(@ModelAttribute("zaikoForm") ZaikoForm form, Model model){
        Zaiko z = new Zaiko();
        z.setName(form.getName());
        z.setAmount(form.getAmount());
        //z.setExpirationDate(form.getExpirationDate());
        zs.saveStocks(z);
        return "redirect:/manage";
    }
}