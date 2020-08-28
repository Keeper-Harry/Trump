package jp.kobe_u.cs.daikibo.SMF.service;

import java.util.ArrayList;
import java.util.List;

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

        zaiko.forEach(z -> fids.add(z.getFid()));
        Iterable<Food> foods = fr.findAllById(fids);
        ArrayList<Food> list = new ArrayList<>();
        foods.forEach(list::add);
        return list;
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
        zaiko.forEach(list::add);
        return list;
    }

    public List<Stock> getStockByFid(Long fid) {
        Iterable<Stock> stocks = zr.findByFid(fid);
        ArrayList<Stock> list = new ArrayList<>();
        stocks.forEach(list::add);
        return list;
    }

    public Stock getStockBySid(Long sid) {
        Stock stock = zr.findBySid(sid);
        return stock;
    }

    public Food getFood(Long fid){
        Food food = fr.findByFid(fid);
        return food;
    }

    public Stock saveStocks(Stock zaiko){
        return zr.save(zaiko);
    }

    public Food saveFoods(Food food){
        String name = food.getName();
        if(fr.findByName(name)==null)
            return fr.save(food);
        else
            return fr.findByName(name);
    }

    public Stock updateStocks(Stock zaiko){
        return zr.save(zaiko);
    }

    public void deleteStocks(Stock zaiko){
        zr.delete(zaiko);
    }
}