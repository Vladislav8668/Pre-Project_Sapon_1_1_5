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
        try (Statement createUsersTableStatement = Util.getConnection().createStatement()) {
            createUsersTableStatement.executeUpdate(createUsersTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String dropUsersTable = "DROP TABLE IF EXISTS Users;";
        try (Statement dropUsersTableStatement = Util.getConnection().createStatement()) {
            dropUsersTableStatement.executeUpdate(dropUsersTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "INSERT INTO Users (name, lastName, age) VALUES (?, ?, ?);";
        try (Connection connection = Util.getConnection()) {
            PreparedStatement saveUserStatement = connection.prepareStatement(saveUser);
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
        try (Statement removeUserByIdStatement = Util.getConnection().createStatement()) {
            removeUserByIdStatement.executeUpdate(removeUserById);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        String getAllUsers = "SELECT * FROM Users;";
        List<User> users = new ArrayList<>();
        try (Statement getAllUsersStatement = Util.getConnection().createStatement()) {
            ResultSet set = getAllUsersStatement.executeQuery(getAllUsers);
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
        try (Statement cleanUsersTableStatement = Util.getConnection().createStatement()) {
            cleanUsersTableStatement.executeUpdate(cleanUsersTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
