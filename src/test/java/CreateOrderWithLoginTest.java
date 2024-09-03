import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.example.Api.Order.Order;
import org.example.Moderator.IngredientModerator;
import org.example.model.ingredient.Ingredient;
import org.example.model.order.OrderCreate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

@Slf4j
public class CreateOrderWithLoginTest extends BaseTest {
    public static final String SUCCESS = "success";
    public static final String ORDER = "order";
    public static final String MESSAGE = "message";
    public static final String TEXT_MESSAGE_INGREDIENT_IDS_MUST_BE_PROVIDED = "Ingredient ids must be provided";
    public static final String INGREDIENTS = "ingredients";
    private final Order order = new Order();
    private final IngredientModerator ingredientModerator = new IngredientModerator();
    private OrderCreate orderCreate;

    @Test
    @DisplayName("Создание заказа с авторизацией")
    public void createCorrectOrderWithLogin() {
        Map<String, String[]> ingredientsMap = ingredientModerator.getCorrectIngredients();
        Response response = order.createOrderWithLogin(ingredientsMap, accessToken);

        orderCreate = response.body().jsonPath().getObject(ORDER, OrderCreate.class);

        List<String> expected = new ArrayList<>(Arrays.asList(ingredientsMap.get(INGREDIENTS)));
        List<String> actual = getActual(orderCreate);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Создание заказа с авторизацией, без ингредиентов")
    public void createOrderWithoutIngredients() {
        Map<String, String[]> ingredientsMap = ingredientModerator.getEmptyIngredients();

        log.info("Список ингредиентов: {}", ingredientsMap);

        Response response = order.createOrderWithLogin(ingredientsMap, accessToken);
        log.info("Получен ответ от сервера: {}", response.body().asString());

        response.then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .and().body(SUCCESS, equalTo(false))
                .and().body(MESSAGE, equalTo(TEXT_MESSAGE_INGREDIENT_IDS_MUST_BE_PROVIDED));
    }


    @Test
    @DisplayName("Создание заказа с авторизацией, с некорректным хэшем ингредиентов")
    public void createOrderWithIncorrectIngredients() {
        Map<String, String[]> ingredientsMap = ingredientModerator.getIncorrectIngredients();
        log.info("Список ингредиентов: {}", ingredientsMap);

        Response response = order.createOrderWithLogin(ingredientsMap, accessToken);
        log.info("Получен ответ от сервера: {}", response.body().asString());

        response.then()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    private List<String> getActual(OrderCreate orderCreate) {
        List<String> actual = new ArrayList<>();
        for (Ingredient ingredient : orderCreate.getIngredients()) {
            actual.add(ingredient.get_id());
        }
        return actual;
    }
}