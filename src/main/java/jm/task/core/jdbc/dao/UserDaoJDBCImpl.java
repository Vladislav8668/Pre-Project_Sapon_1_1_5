package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS Users (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(255) NOT NULL, " +
                "lastName VARCHAR(255) NOT NULL, " +
                "age INT NOT NULL, " +
                "PRIMARY KEY (id));";
        try (Connection connection = Util.getConnection();
             Statement createUsersTableStatement = connection.createStatement()
        ) {
            createUsersTableStatement.executeUpdate(createUsersTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String dropUsersTable = "DROP TABLE IF EXISTS Users;";
        try (Connection connection = Util.getConnection();
             Statement dropUsersTableStatement = connection.createStatement()
        ) {
            dropUsersTableStatement.executeUpdate(dropUsersTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "INSERT INTO Users (name, lastName, age) VALUES (?, ?, ?);";
        try (Connection connection = Util.getConnection();
             PreparedStatement saveUserStatement = connection.prepareStatement(saveUser)
        ) {
            saveUserStatement.setString(1, name);
            saveUserStatement.setString(2, lastName);
            saveUserStatement.setInt(3, age);
            saveUserStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String removeUserById = "DELETE FROM Users WHERE id = " + id;
        try (Connection connection = Util.getConnection();
             Statement removeUserByIdStatement = connection.createStatement()
        ) {
            removeUserByIdStatement.executeUpdate(removeUserById);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        String getAllUsers = "SELECT * FROM Users;";
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             Statement getAllUsersStatement = connection.createStatement();
             ResultSet set = getAllUsersStatement.executeQuery(getAllUsers)
        ) {
            if (set.next()) {
                do {
                    User user = new User();
                    user.setId(set.getLong("id"));
                    user.setName(set.getString("name"));
                    user.setLastName(set.getString("lastName"));
                    user.setAge(set.getByte("age"));
                    users.add(user);
                } while (set.next());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        String cleanUsersTable = "TRUNCATE TABLE Users;";
        try (Connection connection = Util.getConnection();
             Statement cleanUsersTableStatement = connection.createStatement()
        ) {
            cleanUsersTableStatement.executeUpdate(cleanUsersTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
