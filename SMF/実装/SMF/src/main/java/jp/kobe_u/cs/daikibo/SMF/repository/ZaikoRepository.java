package jp.kobe_u.cs.daikibo.SMF.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.kobe_u.cs.daikibo.SMF.entity.Zaiko;

@Repository
public interface ZaikoRepository extends CrudRepository<Zaiko, Long>{
    
}