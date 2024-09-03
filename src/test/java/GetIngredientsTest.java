import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.example.Api.Api.Api;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class GetIngredientsTest {
    private final Api api = new Api();

    @Test
    @DisplayName("Получение списка ингредиентов")
    public void getAllIngredients() {
        Response response = api.getAllIngredients();
        log.info("Получен ответ от сервера: {}", response.body().asString());

        response.then().statusCode(HttpStatus.SC_OK)
                .and().body("success", equalTo(true));
    }
}
