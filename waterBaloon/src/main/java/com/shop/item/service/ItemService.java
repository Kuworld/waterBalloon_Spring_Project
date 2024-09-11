package com.shop.item.service;

import com.shop.item.entity.Item;
import com.shop.item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    
    public List<Item> searchItemsByName(String iName){
    	
    	return itemRepository.findByiNameContaining(iName);
    	
    }
    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }
    
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
    
    public List<Item> getItemsByCategoryAndSubCategory(Integer c1Cat, Integer c2Cat) {
        if (c2Cat == null) {
            return itemRepository.findByCategoryC1Cat(c1Cat);
        } else {
            return itemRepository.findByCategoryC1CatAndSubCategoryC2Cat(c1Cat, c2Cat);
        }
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }
    
    
//    public void saveItem(Item item) {
//    	item.setIRegdate(new Date());
//    	itemRepository.save(item);
//    }
    
    public List<Item> getItemsWithHighStock() {
        return itemRepository.findItemsWithHighStock();
    }
    public List<Item> getItemsWithHighStock2() {
        return itemRepository.findItemsWithStockBetween39And40();
    }
    public List<Item> getItemsWithHighStock3() {
        return itemRepository.findItemsWithStockBetween29And30();
    }
}
