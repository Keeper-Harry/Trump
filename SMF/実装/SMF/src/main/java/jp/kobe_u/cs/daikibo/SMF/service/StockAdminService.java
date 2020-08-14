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

    
    public List<Food> getStockFood(){
        Iterable<Stock> zaiko = zr.findAll();
        ArrayList<Long> fids = new ArrayList<>();
        
        zaiko.forEach(z -> fids.add(z.getFid()));
        Iterable<Food> foods = fr.findAllById(fids);
        ArrayList<Food> list = new ArrayList<>();
        foods.forEach(list::add);
        return list;
    }

    public List<Stock> getAllZaiko(){
        Iterable<Stock>  zaiko = zr.findAll();
        ArrayList<Stock> list = new ArrayList<>();
        zaiko.forEach(list::add);
        return list;
    }

    
    public List<Stock> getZaiko(){
        ArrayList<Food> foods = (ArrayList<Food>) this.getStockFood();
        ArrayList<Stock> list = new ArrayList<>();
        foods.forEach(f -> list.add(zr.findByFid(f.getFid())) );
        return list;
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
}