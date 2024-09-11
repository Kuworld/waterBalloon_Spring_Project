package com.shop.item.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category {
	@Id
    @Column(name = "c1_cat", nullable = false)
    private Integer c1Cat;

    @Column(name = "c1_name", length = 50)
    private String c1Name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("c2Name ASC")  // 정렬 순서를 명시
    private List<SubCategory> subCategories;
    
   
}
