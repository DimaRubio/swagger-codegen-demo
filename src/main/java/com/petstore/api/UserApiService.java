package com.petstore.api;

import com.petstore.client.invoker.ApiClient;
import com.petstore.client.model.User;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

import static com.petstore.api.ApiClientUtils.supplier;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class UserApiService {

    public static final Function<Response, Response> RESPONSE_HANDLER = r -> {
        assertThat(r.statusCode(), equalTo(200));
        return r;
    };

    public static final Function<Response, Response> RESPONSE_RESPONSE_HANDLER = r -> r;

    private final ApiClient apiClient;

    public UserApiService() {
        this.apiClient = ApiClient
                .api(ApiClient.Config
                        .apiConfig()
                        .reqSpecSupplier(supplier()));
    }

    public Response getUserByName(String username) {

        Response response = apiClient.user()
                .getUserByName()
                .usernamePath(username)
                .execute(RESPONSE_RESPONSE_HANDLER);

        return response;
    }

    public void createUser(User user) {
        apiClient.user()
                .createUser()
                .body(user)
                .execute(RESPONSE_HANDLER);
    }

    public void deleteUser(String username) {
        apiClient.user()
                .deleteUser()
                .usernamePath(username)
                .execute(RESPONSE_HANDLER);
    }
}
