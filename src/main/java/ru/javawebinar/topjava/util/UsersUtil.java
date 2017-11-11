package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public static final List<User> USERS = Arrays.asList(
            new User(null,"John", "john@mail.com", "johnpass", Role.ROLE_USER, Role.ROLE_ADMIN),
            new User(null,"Sarah", "sarah@mail.com", "sarahpass", Role.ROLE_USER),
            new User(null,"Alice", "alice@mail.com", "alicepass", Role.ROLE_USER)
    );
}
