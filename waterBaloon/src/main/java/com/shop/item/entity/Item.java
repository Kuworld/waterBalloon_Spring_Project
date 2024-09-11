package com.shop.item.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "items")
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_i_num_seq")
    @SequenceGenerator(name = "item_i_num_seq", sequenceName = "item_i_num_seq", allocationSize = 1)
    @Column(name = "i_num", nullable = false)
    private Long iNum;

    @ManyToOne
    @JoinColumn(name = "c1_cat", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "c2_cat", nullable = false)
    private SubCategory subCategory;

    @Column(name = "i_name", length = 50, nullable = false)
    private String iName;

    @Column(name = "i_price", nullable = false)
    private Integer iPrice;

    @Column(name = "i_maker", length = 100, nullable = false)
    private String iMaker;

    @Column(name = "i_img1", length = 1000)
    private String iImg1;

    @Column(name = "i_img2", length = 1000)
    private String iImg2;

    @Column(name = "i_img3", length = 1000)
    private String iImg3;

    @Lob
    @Column(name = "i_content")
    private String iContent;

    @Column(name = "i_content_det", length = 1000)
    private String iContentDet;

    @Column(name = "i_stock", nullable = false)
    private Integer iStock;

    @Column(name = "i_regdate" ,updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date iRegdate = new Date();

    @Column(name = "i_regstat", nullable = false)
    private Integer iRegstat;
}
