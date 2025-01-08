package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Alex", "Ivanov", (byte)30);
        userService.saveUser("Petr", "Gusev", (byte)20);
        userService.saveUser("Oleg", "Pavlov", (byte)41);
        userService.saveUser("Marina", "Petrova", (byte)55);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
