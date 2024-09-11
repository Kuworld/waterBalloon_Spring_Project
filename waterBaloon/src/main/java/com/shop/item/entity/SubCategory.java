package com.shop.item.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "subcategory")
@Getter
@Setter
public class SubCategory {
    @Id
    @Column(name = "c2_cat", nullable = false)
    private Integer c2Cat;

    @Column(name = "c2_name", length = 50)
    private String c2Name;

    @ManyToOne
    @JoinColumn(name = "c1_cat", nullable = false)
    @JsonIgnore
    private Category category;
}
