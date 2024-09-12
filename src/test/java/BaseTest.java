import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.example.Api.UserApi.Userapi;
import org.example.Moderator.UserModerator;
import org.example.model.user.User;

@Slf4j
public class BaseTest {
    public static final String ACCESS_TOKEN = "accessToken";
    protected final Userapi userapi = new Userapi();
    protected final UserModerator userModerator = new UserModerator();
    protected User user;
    protected String accessToken;
    protected Response responseCreateUser;


    @Before
    public void createUser() {
        user = userModerator.createUser();
        responseCreateUser = userapi.registerUser(user);
        accessToken = responseCreateUser.body().path(ACCESS_TOKEN);
    }

    @After
    public void deleteUser() {
        if (user != null) {
            userapi.deleteUser(user, accessToken);
        }
    }
}