package org.example.Api.Order;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.Api.Api.Api;
import java.util.Map;

public class Order extends Api {
    private static final String ROOT = "/orders";
    private static final String ALL = "/all";
    public static final String HEADER_AUTHORIZATION = "Authorization";

    @Step("Создание заказа без авторизации")
    public Response createOrderWithoutLogin(Map<String, String[]> ingredients) {
        return specification()
                .body(ingredients)
                .post(ROOT);
    }

    @Step("Создание заказа с авотризацией")
    public Response createOrderWithLogin(Map<String, String[]> ingredients, String accessToken) {
        return specification()
                .header(HEADER_AUTHORIZATION, accessToken)
                .body(ingredients)
                .post(ROOT);
    }

    @Step("Получение всех заказов")
    public Response getAllOrders() {
        return specification()
                .get(ROOT + ALL);
    }

    @Step("Получение заказов конкретного пользователя с авторизацией")
    public Response getOrdersWithAuth(String accessToken) {
        return specification()
                .header(HEADER_AUTHORIZATION, accessToken)
                .get(ROOT);
    }

    @Step("Получение заказов конкретного пользователя без авторизации")
    public Response getOrdersWithoutAuth() {
        return specification()
                .get(ROOT);
    }
}