package ru.job4j.dreamjob.services;

import ru.job4j.dreamjob.model.User;

import javax.servlet.http.HttpSession;

public final class UserNameService {

    private UserNameService() {
    }

    public static User getUserName(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setEmail("Гость");
            user.setName("Гость");
        }
        return user;
    }
}
