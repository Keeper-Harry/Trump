package jp.kobe_u.cs.daikibo.SMF.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.kobe_u.cs.daikibo.SMF.entity.Food;
import jp.kobe_u.cs.daikibo.SMF.entity.Stock;
import jp.kobe_u.cs.daikibo.SMF.repository.FoodRepository;
import jp.kobe_u.cs.daikibo.SMF.repository.StockRepository;

@Service
public class StockAdminService {
    @Autowired
    StockRepository zr;
    @Autowired
    FoodRepository fr;

    public List<Food> getStockFood() {
        Iterable<Stock> zaiko = zr.findAll();
        ArrayList<Long> fids = new ArrayList<>();
        ArrayList<Food> foods = new ArrayList<>();

        zaiko.forEach(z -> fids.add(z.getFid()));        
        fids.forEach(fid -> foods.add(fr.findById(fid)));
        return foods;
    }

    public List<Food> getAllFood() {
        Iterable<Food> food = fr.findAll();
        ArrayList<Food> list = new ArrayList<>();

        food.forEach(list::add);
        return list;
    }

    public List<Stock> getAllZaiko() {
        Iterable<Stock> zaiko = zr.findAll();
        ArrayList<Stock> list = new ArrayList<>();
        stocks.forEach(list::add);
        return list;
    }

    public List<Stock> getStockByFid(Long fid) {
        Iterable<Stock> stocks = zr.findByFid(fid);
        ArrayList<Stock> list = new ArrayList<>();
        stocks.forEach(list::add);
        return list;
    }

    public Stock getStockBySid(Long sid) {
        Stock stock = zr.findById(sid);
        return stock;
    }

    public Food getFood(Long fid){
        Food food = fr.findById(fid);
        return food;
    }

    public Stock saveStocks(Stock zaiko){
        return zr.save(zaiko);
    }

    public Food saveFoods(Food food){
        String name = food.getName();
        Iterable<Food> registered = fr.findByName(name);
        Iterator<Food> iter = registered.iterator();
        if(iter.hasNext())
            return iter.next();
        else{
            food.setCreatedAt(new Date());
            return fr.save(food);
        }
    }

    public void deleteStocks(Stock zaiko){
        zr.delete(zaiko);
    }
}