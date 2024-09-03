import org.apache.http.HttpStatus;
import org.junit.Test;
import org.example.Api.UserApi.Userapi;
import org.example.Moderator.UserModerator;
import org.example.model.user.UserWithoutEmail;
import org.example.model.user.UserWithoutName;
import org.example.model.user.UserWithoutPassword;
import static org.hamcrest.CoreMatchers.equalTo;


public class LoginUserTest extends BaseTest {
    public static final String SUCCESS = "success";
    public static final String INCORRECT_EMAIL = "incorrectEmail@new.ru";
    public static final String INCORRECT_PASSWORD = "incorrect_password";
    private final UserModerator userModerator = new UserModerator();
    private final Userapi userapi = new Userapi();

    @Test
    public void loginCorrectUser() {
        userapi.loginUser(user)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .and().body(SUCCESS, equalTo(true));
    }

    @Test
    public void loginWithNameNull() {
        user.setName(null);

        userapi.loginUser(user)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .and().body(SUCCESS, equalTo(true));
    }

    @Test
    public void loginWithoutName() {
        UserWithoutName userWithoutName = new UserWithoutName(user.getEmail(), user.getPassword());

        userapi.loginUserWithoutName(userWithoutName)
                .statusCode(HttpStatus.SC_OK)
                .and().body(SUCCESS, equalTo(true));
    }

    @Test
    public void loginWithPasswordNull() {
        user.setPassword(null);

        userapi.loginUser(user)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and().body(SUCCESS, equalTo(false));
    }

    @Test
    public void loginWithPasswordIncorrect() {
        user.setPassword(INCORRECT_PASSWORD);

        userapi.loginUser(user)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and().body(SUCCESS, equalTo(false));
    }

    @Test
    public void loginWithoutPassword() {
        UserWithoutPassword userWithoutPassword = new UserWithoutPassword(user.getEmail(), user.getName());

        userapi.loginUserWithoutPassword(userWithoutPassword)
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and().body(SUCCESS, equalTo(false));
    }

    @Test
    public void loginWithEmailNull() {
        user.setEmail(null);

        userapi.loginUser(user)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and().body(SUCCESS, equalTo(false));
    }

    @Test
    public void loginWithEmailIncorrect() {
        user.setEmail(INCORRECT_EMAIL);

        userapi.loginUser(user)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and().body(SUCCESS, equalTo(false));
    }

    @Test
    public void loginWithoutEmail() {
        UserWithoutEmail userWithoutEmail = new UserWithoutEmail(user.getPassword(), user.getName());

        userapi.loginUserWithoutEmail(userWithoutEmail)
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and().body(SUCCESS, equalTo(false));
    }
}
