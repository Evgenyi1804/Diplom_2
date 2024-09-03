import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.example.Api.UserApi.Userapi;
import org.example.Moderator.UserModerator;
import static org.hamcrest.CoreMatchers.equalTo;

@Slf4j
public class CreateUserTest extends BaseTest {
    public static final String SUCCESS = "success";
    public static final String MESSAGE = "message";
    public static final String TEXT_MESSAGE_EXISTS = "User already exists";
    private final Userapi userapi = new Userapi();
    private final UserModerator userModerator = new UserModerator();

    @Test
    @DisplayName("Регистрация валидного пользователя")
    public void registerUniqueUser() {
        user = userModerator.createUser();
        log.info("Попытка создания пользователя: {}", user);

        Response response = userapi.registerUser(user);
        log.info("Получен ответ от сервера: {}", response.body().asString());
        accessToken = response.body().path("accessToken");

        response.then()
                .statusCode(HttpStatus.SC_OK)
                .and().body(SUCCESS, equalTo(true));
    }

    @Test
    @DisplayName("Повторная регистрация уже имеющегося пользователя")
    public void registerUserWithExistingEmail() {
        user = userModerator.createUser();
        Response response = userapi.registerUser(user);
        accessToken = response.body().path("accessToken");
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .and().body(SUCCESS, equalTo(true));

        Response badResponse = userapi.registerUser(user);
        log.info("Попытка создания существующего пользователя: {}", user);
        log.info("Получен ответ от сервера: {}", badResponse.body().asString());

        badResponse.then()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .and().body(SUCCESS, equalTo(false))
                .and().body(MESSAGE, equalTo(TEXT_MESSAGE_EXISTS));
    }
}