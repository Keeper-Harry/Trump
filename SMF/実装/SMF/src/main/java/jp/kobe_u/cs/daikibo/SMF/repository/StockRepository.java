package jp.kobe_u.cs.daikibo.SMF.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.kobe_u.cs.daikibo.SMF.entity.Stock;

@Repository
public interface StockRepository extends CrudRepository<Stock, Long>{
    Stock findByFid(Long fid);
}