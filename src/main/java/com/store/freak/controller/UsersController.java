package com.store.freak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Controller
public class UsersController {

    @Autowired
    private Environment env;

    @GetMapping("/insert-user")
    public String insertUser(@RequestParam String username, @RequestParam String email, @RequestParam String password) throws SQLException {
        Connection connection;
        connection =
                DriverManager.getConnection(env.getProperty("spring.datasource.url"),env.getProperty("spring.datasource.username"),env.getProperty("spring.datasource.password"));

        PreparedStatement consulta =
                connection.prepareStatement("INSERT INTO users(username, email, password) VALUES(?, ?, ?);");
        consulta.setString(1, username);
        consulta.setString(2, email);
        consulta.setString(3, password);

        consulta.executeUpdate();

        connection.close();
        return "redirect:/";
    }

}
