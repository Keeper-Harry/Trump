package jp.kobe_u.cs.daikibo.SMF.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.kobe_u.cs.daikibo.SMF.entity.Stock;
import jp.kobe_u.cs.daikibo.SMF.service.StockService;

@Controller
public class StockController {
    @Autowired
    StockService zs;

    @GetMapping("/")
    String showIndex(){
        return "index";
    }

    @GetMapping("/manage")
    String showlist(Model model){
        List<Stock> list = zs.getAllZaiko(); // 在庫一覧を取得
        model.addAttribute("zaikoList", list);   //モデル属性にリストをセット
        model.addAttribute("zaikoForm", new StockForm());  //空フォームをセット
        return "manage";
    }
    
    @PostMapping("/manage")
    String saveStocks(@ModelAttribute("stockForm") StockForm form, Model model){
        Stock z = new Stock();
        z.setName(form.getName());
        z.setAmount(form.getAmount());
        LocalDate localDate = LocalDate.parse(form.getExpirationDate(), DateTimeFormatter.ISO_DATE); // 文字列をLocalDateに
        Date date = Date.from(localDate.atStartOfDay(ZoneId.of("Asia/Tokyo")).toInstant()); // LocalDateをDateに
        z.setExpirationDate(date);
        zs.saveStocks(z);
        return "redirect:/manage";
    }
}