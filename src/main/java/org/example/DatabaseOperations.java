package org.example;

import java.sql.*;

public class DatabaseOperations {

    public static void main(String[] args) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");


            String url = "jdbc:mysql://localhost:3306/my_database"; // Замените на свои данные
            String user = "user"; // Замените на свое имя пользователя
            String password = "password"; // Замените на свой пароль
            Connection connection = DriverManager.getConnection(url, user, password);


            Statement statement = connection.createStatement();


            statement.execute("CREATE TABLE IF NOT EXISTS users (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), age INT, email VARCHAR(255))");

            // 5. Вставляем данные в таблицу users
            statement.execute("INSERT INTO users (name, age, email) VALUES ('John', 30, 'john@example.com')");
            statement.execute("INSERT INTO users (name, age, email) VALUES ('Alice', 25, 'alice@example.com')");
            statement.execute("INSERT INTO users (name, age, email) VALUES ('Bob', 35, 'bob@example.com')");


            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            System.out.println("Все записи из таблицы users:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Email: " + email);
            }


            statement.execute("DELETE FROM users WHERE name = 'Bob'");

            // 8. Выбираем все записи из таблицы users (после удаления)
            resultSet = statement.executeQuery("SELECT * FROM users");
            System.out.println("\nЗаписи из таблицы users после удаления:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Email: " + email);
            }


            connection.close();

        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }
}
