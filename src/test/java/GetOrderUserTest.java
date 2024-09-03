import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.example.Api.Order.Order;
import org.example.Moderator.IngredientModerator;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrderUserTest extends BaseTest {
    private final IngredientModerator ingredientModerator = new IngredientModerator();
    private final Order order = new Order();

    @Test
    public void getOrdersWithAuth() {
        order.createOrderWithLogin(ingredientModerator.getCorrectIngredients(), accessToken);
        Response response = order.getOrdersWithAuth(accessToken);

        response.then()
                .statusCode(HttpStatus.SC_OK)
                .and().body("success", equalTo(true))
                .and().body("orders", notNullValue());
    }

    @Test
    public void getOrdersWithoutAuth() {
        Response response = order.getOrdersWithoutAuth();

        response.then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and().body("success", equalTo(false))
                .and().body("message", equalTo("You should be authorised"));
    }
}