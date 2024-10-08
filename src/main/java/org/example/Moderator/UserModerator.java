package org.example.Moderator;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.model.user.User;
import org.example.model.user.UserWithoutEmail;
import org.example.model.user.UserWithoutName;
import org.example.model.user.UserWithoutPassword;

public class UserModerator {

    public User createUser() {
        return new User(createUniqueEmail(),createPassword(),createName());
    }

    public UserWithoutName createUserWithoutName() {
        return new UserWithoutName(createUniqueEmail(),createPassword());
    }

    public User createUserWithNameNull() {
        return new User(createUniqueEmail(),createPassword(),null);
    }

    public UserWithoutPassword createUserWithoutPassword() {
        return new UserWithoutPassword(createUniqueEmail(),createName());
    }

    public User createUserWithPasswordNull() {
        return new User(createUniqueEmail(),null,createName());
    }

    public UserWithoutEmail createUserWithoutEmail() {
        return new UserWithoutEmail(createName(),createPassword());
    }

    public User createUserWithEmailNull() {
        return new User(null,createPassword(),createName());
    }

    private String createUniqueEmail() {
        return String.format("%s@%s.ru", RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphabetic(3));
    }

    private String createPassword() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    private String createName() {
        return RandomStringUtils.randomAlphabetic(6);
    }
}