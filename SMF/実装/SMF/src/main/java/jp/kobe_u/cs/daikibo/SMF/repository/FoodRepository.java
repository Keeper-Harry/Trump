package jp.kobe_u.cs.daikibo.SMF.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.kobe_u.cs.daikibo.SMF.entity.Food;

@Repository
public interface FoodRepository extends CrudRepository<Food, Long>{
    Food findByName(String name);    
}