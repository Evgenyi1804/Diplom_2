import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.example.Api.UserApi.Userapi;
import org.example.Moderator.UserModerator;
import org.example.model.user.User;
import org.example.model.user.UserWithoutEmail;
import org.example.model.user.UserWithoutPassword;
import org.example.model.user.UserWithoutName;
import static org.hamcrest.CoreMatchers.equalTo;

@Slf4j
public class CreateNotUserTest {
    public static final String SUCCESS = "success";
    public static final String MESSAGE = "message";
    public static final String TEXT_MESSAGE = "Email, password and name are required fields";
    private final Userapi userapi = new Userapi();
    private final UserModerator userModerator = new UserModerator();

    @Test
    public void registerUserWithoutName() {
        UserWithoutName userWithoutName = userModerator.createUserWithoutName();

        ValidatableResponse response = userapi.registerUserWithoutName(userWithoutName);

        response.statusCode(HttpStatus.SC_FORBIDDEN)
                .and().body(SUCCESS, equalTo(false))
                .and().body(MESSAGE, equalTo(TEXT_MESSAGE));
    }

    @Test
    public void registerUserWithNameNull() {
        User user = userModerator.createUserWithNameNull();

        Response response = userapi.registerUser(user);

        response.then()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .and().body(SUCCESS, equalTo(false))
                .and().body(MESSAGE, equalTo(TEXT_MESSAGE));
    }

    @Test
    public void registerUserWithoutPassword() {
        UserWithoutPassword userWithoutPassword = userModerator.createUserWithoutPassword();

        ValidatableResponse response = userapi.registerUserWithoutPassword(userWithoutPassword);

        response.statusCode(HttpStatus.SC_FORBIDDEN)
                .and().body(SUCCESS, equalTo(false))
                .and().body(MESSAGE, equalTo(TEXT_MESSAGE));
    }

    @Test
    public void registerUserWithPasswordNull() {
        User user = userModerator.createUserWithPasswordNull();

        Response response = userapi.registerUser(user);

        response.then()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .and().body(SUCCESS, equalTo(false))
                .and().body(MESSAGE, equalTo(TEXT_MESSAGE));
    }

    @Test
    public void registerUserWithoutEmail() {
        UserWithoutEmail userWithoutEmail = userModerator.createUserWithoutEmail();

        ValidatableResponse response = userapi.registerUserWithoutEmail(userWithoutEmail);

        response.statusCode(HttpStatus.SC_FORBIDDEN)
                .and().body(SUCCESS, equalTo(false))
                .and().body(MESSAGE, equalTo(TEXT_MESSAGE));
    }

    @Test
    public void registerUserWithEmailNull() {
        User user = userModerator.createUserWithEmailNull();

        Response response = userapi.registerUser(user);

        response.then()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .and().body(SUCCESS, equalTo(false))
                .and().body(MESSAGE, equalTo(TEXT_MESSAGE));
    }
}
