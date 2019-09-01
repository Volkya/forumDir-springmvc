package com.store.freak.controller;

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

    @GetMapping("/insert-user")
    public String insertUser(@RequestParam String username, @RequestParam String email, @RequestParam String password) throws SQLException {
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/freakStore","root","1234");

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
