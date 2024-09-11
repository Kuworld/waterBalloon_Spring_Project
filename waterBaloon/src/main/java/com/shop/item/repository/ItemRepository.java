package com.shop.item.repository;

import com.shop.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByCategoryC1Cat(Integer c1Cat);
    List<Item> findByCategoryC1CatAndSubCategoryC2Cat(Integer c1Cat, Integer c2Cat);

    @Query("SELECT i FROM Item i WHERE i.iStock = 60")
    List<Item> findItemsWithHighStock();

    @Query("SELECT i FROM Item i WHERE i.iStock = 40")
    List<Item> findItemsWithStockBetween39And40();

    @Query("SELECT i FROM Item i WHERE i.iStock = 50")
    List<Item> findItemsWithStockBetween29And30();
    
    List<Item> findByiNameContaining(String iName);
}
