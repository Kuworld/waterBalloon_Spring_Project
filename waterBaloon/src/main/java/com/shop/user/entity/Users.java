package com.shop.user.entity;

import com.shop.cart.entity.Cart;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "u_name", nullable = false, length = 30)
    private String name;

    @Column(name = "u_id", nullable = false, unique = true, length = 30)
    private String username;

    @Column(name = "u_pw", nullable = false, length = 100)
    private String password;

    @Column(name = "u_tel", length = 20)
    private String tel;

    @Column(name = "u_email", length = 100)
    private String email;

    @Column(name = "u_addr", nullable = false, length = 300)
    private String addr;

    @Column(name = "u_daddr", length = 300)
    private String daddr;

    @Column(name = "u_auth", columnDefinition = "number(1) default 0")
    private int auth = 0; // 0: 일반회원, 1: 관리자

    @Column(name = "u_out", columnDefinition = "number(1) default 0")
    private int out = 0; // 0: 정상회원, 1: 휴면회원 2:탈퇴회원

    @Column(name = "u_regdate")
    private LocalDateTime regdate;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

    public Cart getCart() {
        return cart;
    }
    
    @PrePersist
    protected void onCreate() {
        regdate = LocalDateTime.now();
    }
}
