package jp.kobe_u.cs.daikibo.SMF.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.kobe_u.cs.daikibo.SMF.entity.Zaiko;
import jp.kobe_u.cs.daikibo.SMF.repository.ZaikoRepository;

@Service
public class ZaikoService {
    @Autowired
    ZaikoRepository zr;

    public List<Zaiko> getAllZaiko(){
        Iterable<Zaiko> zaiko = zr.findAll();
        ArrayList<Zaiko> list = new ArrayList<>();
        zaiko.forEach(list::add);
        return list;
    }
}