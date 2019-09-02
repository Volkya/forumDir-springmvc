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
    public String insertItem(@RequestParam String name, @RequestParam String urlpic, @RequestParam String description, @RequestParam int price, @RequestParam String category) throws SQLException {
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/freakStore", "root", "1234");

        PreparedStatement consulta =
                connection.prepareStatement("INSERT INTO products(name, description, urlpic, price, category) VALUES(?, ?, ?, ?, ?);");
        consulta.setString(1, name);
        consulta.setString(2, description);
        consulta.setString(3, urlpic);
        consulta.setInt(4, price);
        consulta.setString(5,category);

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
            String category = resultado.getString("category");

            Product x = new Product(id, name, description, urlpic, price, category);
            listProducts.add(x);
        }
        template.addAttribute("listProducts", listProducts);
        return "index";
    }




    @GetMapping("/detail/{id}")
    public String detail(Model template, @PathVariable int id) throws SQLException {

        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/freakStore","root","1234");

        PreparedStatement consulta =
                connection.prepareStatement("SELECT * FROM products WHERE id = ?;");

        consulta.setInt(1, id);

        ResultSet resultado = consulta.executeQuery();

        if ( resultado.next() ) {
            String name = resultado.getString("name");
            String urlpic = resultado.getString("urlpic");
            String description = resultado.getString("description");
            Integer price = resultado.getInt("price");
            String category = resultado.getString("category");

            template.addAttribute("name", name);
            template.addAttribute("urlpic", urlpic);
            template.addAttribute("description", description);
            template.addAttribute("price", price);
            template.addAttribute("category", category);
        }

        return "detailproduct";
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



    // Categories routes
    @GetMapping("/dress")
    public String dress(Model template) throws SQLException{
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/freakStore", "root", "1234");

    PreparedStatement consulta = connection.prepareStatement("SELECT * FROM products WHERE category = 'dress';");

        ResultSet resultado = consulta.executeQuery();

        ArrayList<Product> dressProducts = new ArrayList<Product>();

        while (resultado.next()) {
            int id = resultado.getInt("id");
            String name = resultado.getString("name");
            String description = resultado.getString("description");
            String urlpic = resultado.getString("urlpic");
            int price = resultado.getInt("price");
            String category = resultado.getString("category");

            Product x = new Product(id, name, description, urlpic, price, category);
            dressProducts.add(x);
        }
        template.addAttribute("dressProducts", dressProducts);
        return "dress";

    }


    @GetMapping("/edit/{id}")
    public String editar(Model template, @PathVariable int id) throws SQLException {

        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/freakStore","root","1234");

        PreparedStatement consulta =
                connection.prepareStatement("SELECT * FROM products WHERE id = ?;");

        consulta.setInt(1, id);

        ResultSet resultado = consulta.executeQuery();

        if ( resultado.next() ) {
            String name = resultado.getString("name");
            String description = resultado.getString("description");
            String urlpic = resultado.getString("urlpic");
            String price = resultado.getString("price");
            String category = resultado.getString("category");

            template.addAttribute("name", name);
            template.addAttribute("description", description);
            template.addAttribute("urlpic", urlpic);
            template.addAttribute("price", price);
            template.addAttribute("category", category);
        }

        return "editProduct";
    }



    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable int id, @RequestParam String name, @RequestParam String description, @RequestParam String urlpic, @RequestParam int price, @RequestParam String category) throws SQLException {
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/freakStore","root","1234");

        PreparedStatement consulta =
                connection.prepareStatement("UPDATE products SET name = ?, description = ?, urlpic = ?, price = ?, category = ? WHERE id = ?;");
        consulta.setString(1, name);
        consulta.setString(2, description);
        consulta.setString(3, urlpic);
        consulta.setInt(4, price);
        consulta.setString(5, category);
        consulta.setInt(6, id);

        consulta.executeUpdate();

        connection.close();
        return "redirect:/detail/" + id;
    }




    @GetMapping("/process-search")
    public String processSearch(Model template, @RequestParam String searchedWord) throws SQLException {

        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/freakStore","root","1234");

        PreparedStatement consulta =
                connection.prepareStatement("SELECT * FROM products WHERE name LIKE ?;");
        consulta.setString(1, "%" + searchedWord +  "%");

        ResultSet resultado = consulta.executeQuery();

        ArrayList<Product> listProducts = new ArrayList<Product>();

        while ( resultado.next() ) {
            int id = resultado.getInt("id");
            String name = resultado.getString("name");
            String description = resultado.getString("description");
            String urlpic = resultado.getString("urlpic");
            Integer price = resultado.getInt("price");
            String category = resultado.getString("category");


            Product x = new Product(id, name, description, urlpic, price, category);
            listProducts.add(x);
        }

        template.addAttribute("listProducts", listProducts);

        return "index";
    }


}
