package jp.kobe_u.cs.daikibo.SMF.controller;

// import java.text.ParseException;
// import java.text.SimpleDateFormat;
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
        List<Food> allFood = zs.getAllFood();
        List<Food> foods = zs.getStockFood();
        List<Stock> list = zs.getAllZaiko(); // 在庫一覧を取得

        model.addAttribute("allFoodList", allFood);
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

        return "read";
    }

    @GetMapping("/update/{sid}")
    String showupdate(@PathVariable Long sid, Model model) {
        Stock stock = zs.getStockBySid(sid);
        Food food = zs.getFood(stock.getFid());

        model.addAttribute("food", food);
        model.addAttribute("stock", stock); // モデル属性にリストをセット
        model.addAttribute("zaikoForm", new StockForm()); // 空フォームをセット

        return "update";
    }

    @GetMapping("/delete_config/{sid}")
    String deleteStock(@PathVariable Long sid, Model model) {
        Stock stock = zs.getStockBySid(sid);
        Food food = zs.getFood(stock.getFid());

        model.addAttribute("food", food);
        model.addAttribute("stock", stock); // モデル属性にリストをセット

        return "delete_config";
    }

    @GetMapping("/delete/{sid}")
    String deletedStock(@PathVariable Long sid, Model model) {
        Stock stock = zs.getStockBySid(sid);
        Food food = zs.getFood(stock.getFid());
        model.addAttribute("food", food);
        model.addAttribute("stock", stock); // モデル属性にリストをセット
        zs.deleteStocks(stock);
        return "/delete";
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
    String saveStocks(@ModelAttribute("stockForm") StockForm form, Model model) {
        Stock z = new Stock();
        Food f = new Food();
        f.setName(form.getName());
        f = zs.saveFoods(f);
        
        z.setName(form.getName());
        z.setFid(f.getFid());
        z.setAmount(form.getAmount());

        LocalDate localDate = LocalDate.parse(form.getExpirationDate(), DateTimeFormatter.ISO_DATE); // 文字列をLocalDateに
        Date date = Date.from(localDate.atStartOfDay(ZoneId.of("Asia/Tokyo")).toInstant()); // LocalDateをDateに
        z.setExpirationDate(date);
        zs.saveStocks(z);
        return "redirect:/manage";
    }

    @PostMapping("/updated/{sid}")
    String updateStocks(@PathVariable Long sid, @ModelAttribute("stockForm") StockForm form, Model model) {
        Stock z = new Stock();
        Food f = new Food();
        
        f.setName(form.getName());
        f = zs.saveFoods(f);
        
        z.setSid(sid);
        z.setName(form.getName());
        z.setFid(f.getFid());
        z.setAmount(form.getAmount());

        LocalDate localDate = LocalDate.parse(form.getExpirationDate(), DateTimeFormatter.ISO_DATE); // 文字列をLocalDateに
        Date date = Date.from(localDate.atStartOfDay(ZoneId.of("Asia/Tokyo")).toInstant()); // LocalDateをDateに
        z.setExpirationDate(date);
        zs.updateStocks(z);
        return "updated";
    }

}