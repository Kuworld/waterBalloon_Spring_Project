package com.shop.user.config;

import com.shop.user.entity.Users;
import com.shop.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserSetup implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("admin") == null) {
            Users admin = new Users();
            admin.setUsername("admin");
            admin.setName("Admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setAuth(1); 
            admin.setAddr("alal");// 관리자로 설정
            userRepository.save(admin);
        }
    }
}
