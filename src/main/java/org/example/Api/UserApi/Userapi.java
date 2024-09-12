package org.example.Api.UserApi;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.example.Api.Api.Api;
import org.example.model.user.User;
import org.example.model.user.UserWithoutEmail;
import org.example.model.user.UserWithoutName;
import org.example.model.user.UserWithoutPassword;
import java.util.Map;

public class Userapi extends Api {
    private static final String ROOT = "/auth";
    private static final String REGISTER = "/register";
    private static final String USER = "/user";
    private static final String LOGIN = "/login";
    public static final String HEADER_AUTHORIZATION = "Authorization";

    @Step("Регистрация пользователя")
    public Response registerUser(User user) {
        return specification()
                .body(user)
                .when()
                .post(ROOT + REGISTER);
    }

    @Step("Регистрация пользователя без  name")
    public ValidatableResponse registerUserWithoutName(UserWithoutName userWithoutName) {
        return specification()
                .body(userWithoutName)
                .post(ROOT + REGISTER)
                .then();
    }

    @Step("Регистрация пользователя без password")
    public ValidatableResponse registerUserWithoutPassword(UserWithoutPassword userWithoutPassword) {
        return specification()
                .body(userWithoutPassword)
                .post(ROOT + REGISTER)
                .then();
    }

    @Step("Регистрация пользователя без  email")
    public ValidatableResponse registerUserWithoutEmail(UserWithoutEmail userWithoutEmail) {
        return specification()
                .body(userWithoutEmail)
                .post(ROOT + REGISTER)
                .then();
    }

    @Step("Авторизация пользователя")
    public Response loginUser(User user) {
        return specification()
                .body(user)
                .post(ROOT + LOGIN);
    }

    @Step("Авторизация пользователя без  name")
    public ValidatableResponse loginUserWithoutName(UserWithoutName userWithoutName) {
        return specification()
                .body(userWithoutName)
                .post(ROOT + LOGIN)
                .then();
    }

    @Step("Авторизация пользователя без  password")
    public ValidatableResponse loginUserWithoutPassword(UserWithoutPassword userWithoutPassword) {
        return specification()
                .body(userWithoutPassword)
                .post(ROOT + LOGIN)
                .then();
    }

    @Step("Авторизация пользователя без  email")
    public ValidatableResponse loginUserWithoutEmail(UserWithoutEmail userWithoutEmail) {
        return specification()
                .body(userWithoutEmail)
                .post(ROOT + LOGIN)
                .then();
    }

    @Step("Получение информации о пользователе")
    public Response getUserInfo(String accessToken) {
        return specification()
                .header(HEADER_AUTHORIZATION, accessToken)
                .get(ROOT + USER);
    }


    @Step("Изменение пользователя c авторизацией")
    public Response updateUser(Map<String, String> updateData, String accessToken) {
        return specification()
                .header(HEADER_AUTHORIZATION, accessToken)
                .body(updateData)
                .patch(ROOT + USER);
    }

    @Step("Изменение пользователя без авторизации")
    public Response updateUserWithoutLogin(Map<String, String> updateData) {
        return specification()
                .body(updateData)
                .patch(ROOT + USER);
    }

    @Step("Удаление пользователя")
    public Response deleteUser(User user, String accessToken) {
        return specification()
                .header(HEADER_AUTHORIZATION, accessToken)
                .body(user)
                .delete(ROOT + USER);
    }
}
