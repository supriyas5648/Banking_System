package service;

import dao.UserDAO;
import models.User;
import util.InputValidator;

public class AuthService {

    private UserDAO userDAO;

    public AuthService() {
        userDAO = new UserDAO();
    }

    public boolean register(
            String username,
            String password,
            String role) {

        if (!InputValidator.isValidUsername(
                username)) {

            System.out.println(
                    "Username must contain at least 4 characters."
            );

            return false;
        }

        if (!InputValidator.isValidPassword(
                password)) {

            System.out.println(
                    "Password must contain at least 4 characters."
            );

            return false;
        }

        User existingUser =
                userDAO.findUserByUsername(
                        username
                );

        if (existingUser != null) {

            System.out.println(
                    "Username already exists."
            );

            return false;
        }

        User user =
                new User(
                        0,
                        username,
                        password,
                        role
                );

        return userDAO.registerUser(user);
    }

    public User login(
            String username,
            String password) {

        if (!InputValidator.isValidUsername(
                username)) {

            System.out.println(
                    "Invalid Username"
            );

            return null;
        }

        if (!InputValidator.isValidPassword(
                password)) {

            System.out.println(
                    "Invalid Password"
            );

            return null;
        }

        return userDAO.login(
                username,
                password
        );
    }
}