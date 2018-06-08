import com.petstore.api.UserApiService;
import com.petstore.client.model.ModelApiResponse;
import com.petstore.client.model.User;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class UserApiTest {

    private UserApiService userApiService = new UserApiService();

    @Test
    public void shouldCreateNewUser()  {
        // Given
        User user = new User()
                .username("Ivan89")
                .email("ivan89@ukr.net");

        // When
        userApiService.createUser(user);

        User userByName = userApiService
                .getUserByName(user.getUsername())
                .as(User.class);

        // Then
        assertThat(userByName.getEmail(), equalTo(user.getEmail()));
    }

    @Test
    public void shouldDeleteUser(){
        // Given
        User user = new User()
                .username("Ivan89")
                .email("ivan89@ukr.net");

        // When
        userApiService.createUser(user);
        userApiService.deleteUser(user.getUsername());

        ModelApiResponse response = userApiService
                .getUserByName(user.getUsername())
                .as(ModelApiResponse.class);

        // Then
        assertThat(response.getCode(), equalTo(1));
        assertThat(response.getType(), equalTo("error"));
        assertThat(response.getMessage(), equalTo("User not found"));
    }
}
