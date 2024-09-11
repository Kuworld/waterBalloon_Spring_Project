package com.shop.item.repository;

import com.shop.item.entity.SubCategory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
	SubCategory findByC2Name(String c2Name);
	
	
	List<SubCategory> findByCategoryC1Cat(Integer c1Cat);
}

