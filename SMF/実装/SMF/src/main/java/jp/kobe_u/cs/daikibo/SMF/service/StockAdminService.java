package jp.kobe_u.cs.daikibo.SMF.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.kobe_u.cs.daikibo.SMF.entity.Stock;
import jp.kobe_u.cs.daikibo.SMF.repository.StockRepository;

@Service
public class StockService {
    @Autowired
    StockRepository zr;

    public List<Stock> getAllZaiko(){
        Iterable<Stock> zaiko = zr.findAll();
        ArrayList<Stock> list = new ArrayList<>();
        zaiko.forEach(list::add);
        return list;
    }

    public Stock saveStocks(Stock zaiko){
        return zr.save(zaiko);
    }
}