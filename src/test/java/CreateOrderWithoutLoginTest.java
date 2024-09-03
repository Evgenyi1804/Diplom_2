import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.example.Api.Order.Order;
import org.example.Moderator.IngredientModerator;
import java.util.Map;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@Slf4j
public class CreateOrderWithoutLoginTest {
    public static final String SUCCESS = "success";
    public static final String ORDER = "order";
    public static final String MESSAGE = "message";
    public static final String TEXT_MESSAGE_INGREDIENT_IDS_MUST_BE_PROVIDED = "Ingredient ids must be provided";
    private final Order order = new Order();
    private final IngredientModerator ingredientModerator = new IngredientModerator();

    @Test
    @DisplayName("Создание заказа без авторизации")
    public void createCorrectOrderWithoutLogin() {
        Map<String, String[]> ingredientsMap = ingredientModerator.getCorrectIngredients();
        log.info("Список ингредиентов: {}", ingredientsMap);

        Response response = order.createOrderWithoutLogin(ingredientsMap);
        log.info("Получен ответ от сервера: {}", response.body().asString());

        response.then()
                .statusCode(HttpStatus.SC_OK)
                .and().body(SUCCESS, equalTo(true))
                .and().body(ORDER, notNullValue());
    }

    @Test
    @DisplayName("Создание заказа без авторизации, без ингредиентов")
    public void createOrderWithoutIngredients() {
        Map<String, String[]> ingredientsMap = ingredientModerator.getEmptyIngredients();
        log.info("Список ингредиентов: {}", ingredientsMap);

        Response response = order.createOrderWithoutLogin(ingredientsMap);
        log.info("Получен ответ от сервера: {}", response.body().asString());

        response.then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .and().body(SUCCESS, equalTo(false))
                .and().body(MESSAGE, equalTo(TEXT_MESSAGE_INGREDIENT_IDS_MUST_BE_PROVIDED));
    }

    @Test
    @DisplayName("Создание заказа ьез авторизации, с некорректным хэшем ингредиентов")
    public void createOrderWithIncorrectIngredients() {
        Map<String, String[]> ingredientsMap = ingredientModerator.getIncorrectIngredients();
        log.info("Список ингредиентов: {}", ingredientsMap);

        Response response = order.createOrderWithoutLogin(ingredientsMap);
        log.info("Получен ответ от сервера: {}", response.body().asString());

        response.then()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}