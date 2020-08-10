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
public class StockService {
    @Autowired
    StockRepository sr;
    @Autowired
    FoodRepository fr;

    public List<Stock> getAllStocks(){
        Iterable<Stock> stocks = sr.findAll();
        ArrayList<Stock> list = new ArrayList<>();
        stocks.forEach(list::add);
        return list;
    }

    public Stock addStock(Food food, String amount){}

    public Stock updateStock(Stock stock){}

    public void deleteStock(Long sid){}
}