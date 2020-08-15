package jp.kobe_u.cs.daikibo.SMF.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.kobe_u.cs.daikibo.SMF.entity.Food;
import jp.kobe_u.cs.daikibo.SMF.entity.Stock;
import jp.kobe_u.cs.daikibo.SMF.service.StockAdminService;

@Controller
public class StockAdminController {
    @Autowired
    StockAdminService zs;

    @GetMapping("/")
    String showIndex() {
        return "index";
    }

    @GetMapping("/manage")
    String showlist(Model model) {
        List<Food> foods = zs.getStockFood();
        List<Stock> list = zs.getAllZaiko(); // 在庫一覧を取得

        model.addAttribute("foodList", foods);
        model.addAttribute("zaikoList", list); // モデル属性にリストをセット
        model.addAttribute("zaikoForm", new StockForm()); // 空フォームをセット

        return "zaiko";
    }

    @GetMapping("/read/{fid}")
    String readStock(@PathVariable Long fid, Model model) {
        Food food = zs.getFood(fid);
        List<Stock> list = zs.getStockByFid(fid); // 在庫一覧を取得

        model.addAttribute("food", food);
        model.addAttribute("zaikoList", list); // モデル属性にリストをセット
        model.addAttribute("zaikoForm", new StockForm()); // 空フォームをセット

        return "read";
    }

    @GetMapping("/delete/{fid}/{sid}")
    String deleteStock(@PathVariable Long fid, @PathVariable Long sid, Model model) {
        Stock stock = zs.getStockBySid(sid);
        Food food = zs.getFood(fid);

        model.addAttribute("food", food);
        model.addAttribute("stock", stock); // モデル属性にリストをセット

        return "delete_config";
    }

    
    @GetMapping("/register")
    String showRegisterForm(Model model) {
        List<Food> foods = zs.getStockFood();
        List<Stock> list = zs.getAllZaiko(); // 在庫一覧を取得

        model.addAttribute("foodList", foods);
        model.addAttribute("zaikoList", list); // モデル属性にリストをセット
        model.addAttribute("zaikoForm", new StockForm()); // 空フォームをセット

        return "register";
    }

    @PostMapping("/manage")
    String saveStocks(@ModelAttribute("stockForm") StockForm form, Model model) throws ParseException {
        Stock z = new Stock();
        Food f = new Food();
        f.setName(form.getName());
        f = zs.saveFoods(f);

        z.setFid(f.getFid());
        z.setAmount(form.getAmount());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        // Date型変換
        String year = form.getYear();
        String month = form.getMonth();
        String date = form.getDate();

        Date expirationDate = sdf.parse(year+month+date);
        System.out.println(expirationDate);
        z.setExpirationDate(expirationDate);
        
        //z.setExpirationDate(form.getExpirationDate());
        zs.saveStocks(z);
        return "redirect:/manage";
    }
}