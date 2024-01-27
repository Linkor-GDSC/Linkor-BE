//package com.example.login;
//
//import jakarta.persistence.EntityManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class MainConfig {
//    private DataSource dataSource;
//    private EntityManager em;
//
//    @Autowired
//    public MainConfig(DataSource dataSource, EntityManager em) {
//        this.dataSource = dataSource;
//        this.em = em;
//    }
//
//
//    @Bean
//    public UserRepository userRepository() { return new JpaRepository(em); }
//
//    @Bean
//    public UserServiceImpl userService(){ return new UserServiceImpl(userRepository()); }
//
//}
