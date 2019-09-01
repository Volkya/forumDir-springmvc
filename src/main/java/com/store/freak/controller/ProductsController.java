package com.store.freak.controller;

import com.store.freak.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.util.ArrayList;

@Controller
public class ProductsController {

    @GetMapping("/insert-item")
    public String insertItem(@RequestParam String name, @RequestParam String urlpic, @RequestParam String description, @RequestParam int price) throws SQLException {
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/freakStore", "root", "1234");

        PreparedStatement consulta =
                connection.prepareStatement("INSERT INTO products(name, description, urlpic, price) VALUES(?, ?, ?, ?);");
        consulta.setString(1, name);
        consulta.setString(2, description);
        consulta.setString(3, urlpic);
        consulta.setInt(4, price);

        consulta.executeUpdate();

        connection.close();
        return "redirect:/";
    }


    @GetMapping("/")
    public String list(Model template) throws SQLException {

        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/freakStore", "root", "1234");

        PreparedStatement consulta =
                connection.prepareStatement("SELECT * FROM products;");

        ResultSet resultado = consulta.executeQuery();

        ArrayList<Product> listProducts = new ArrayList<Product>();

        while (resultado.next()) {
            int id = resultado.getInt("id");
            String name = resultado.getString("name");
            String description = resultado.getString("description");
            String urlpic = resultado.getString("urlpic");
            int price = resultado.getInt("price");

            Product x = new Product(id, name, description, urlpic, price);
            listProducts.add(x);
        }

        template.addAttribute("listProducts", listProducts);

        return "index";

    }



    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) throws SQLException {

        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/freakStore","root","1234");

        PreparedStatement consulta = connection.prepareStatement("DELETE FROM products WHERE id = ?;");
        consulta.setInt(1, id);

        consulta.executeUpdate();

        connection.close();

        return "redirect:/";
    }

}
