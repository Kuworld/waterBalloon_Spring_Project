package com.shop.item.repository;

import com.shop.item.entity.Category;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	@EntityGraph(attributePaths = "subCategories")
	Category findByC1Name(String c1Name);
	
	
}
