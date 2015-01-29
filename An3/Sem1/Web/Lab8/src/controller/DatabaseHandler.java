package controller;

import model.User;

import java.io.InputStream;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    private static Connection connection;

    public DatabaseHandler() {
        setConnection();
    }

    private static void setConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/profile_management", "root", "asusu36sg");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public User getUserIfValid(String username, String password) {
        User user = null;
        try {
            Statement statement = connection.createStatement();
            String query = String.format(
                    "SELECT * FROM user WHERE username='%s' AND password='%s'",
                    username,
                    password
            );
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String email = result.getString("email");
                String age = String.valueOf(result.getInt("age"));
                String hometown = result.getString("hometown");
                //String photo = result.getString("photo");

                user = new User(id, username, password, name, email, age, hometown);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User registerUser(User user) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format(
                    "INSERT INTO user (" +
                            "username, password, name, email, age, hometown)" +
                            "VALUES ('%s', '%s', '%s', '%s', '%s', '%s');",
                    user.getUsername(), user.getPassword(), user.getName(),
                    user.getEmail(), user.getAge(), user.getHometown()
            );
            int id = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            user.setId(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updateUser(User user) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format(
                    "UPDATE user SET username='%s', name='%s', password='%s', email='%s', age='%s', hometown='%s', photo='%s' WHERE id='%s'",
                    user.getUsername(), user.getName(), user.getPassword(), user.getEmail(), user.getAge(), user.getHometown(), user.getPhotoPath(),
                    user.getId()
            );
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsersByCriteria(String criteria, String searchedWord) {
        List<User> users = new ArrayList<User>();

        try {
            Statement statement = connection.createStatement();
            String query = String.format(
                    "SELECT * FROM user WHERE %s like '%%%s%%'",
                    criteria, searchedWord
            );
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                int id = result.getInt("id");
                String username = result.getString("username");
                String password = result.getString("password");
                String name = result.getString("name");
                String email = result.getString("email");
                String age = String.valueOf(result.getInt("age"));
                String hometown = result.getString("hometown");
                String photoPath = result.getString("photo");

                users.add(new User(id, username, password, name, email, age, hometown, photoPath));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User getUserById(String id) {
        User user = null;
        try {
            Statement statement = connection.createStatement();
            String query = String.format(
                    "SELECT * FROM user WHERE id='%s'", id);
            ResultSet result = statement.executeQuery(query);


            while (result.next()) {
                String username = result.getString("username");
                String password = result.getString("password");
                String name = result.getString("name");
                String email = result.getString("email");
                String age = String.valueOf(result.getInt("age"));
                String hometown = result.getString("hometown");
                //String photo = result.getString("photo");

                user = new User(Integer.parseInt(id), username, password, name, email, age, hometown);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
