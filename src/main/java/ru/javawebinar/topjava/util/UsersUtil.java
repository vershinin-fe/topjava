package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public static final Integer JOHNS_ID = 101;
    public static final Integer SARAHS_ID = 102;
    public static final Integer ALICES_ID = 103;

    public static final List<User> USERS = Arrays.asList(
            new User(JOHNS_ID,"John", "john@mail.com", "johnpass", Role.ROLE_USER, Role.ROLE_ADMIN),
            new User(SARAHS_ID,"Sarah", "sarah@mail.com", "sarahpass", Role.ROLE_USER),
            new User(ALICES_ID,"Alice", "alice@mail.com", "alicepass", Role.ROLE_USER)
    );
}
