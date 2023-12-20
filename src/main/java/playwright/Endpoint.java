package playwright;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.json.JSONObject;

import java.util.HashMap;

public class Endpoint {

    private final Playwright playwright;

    public Endpoint(Playwright playwright) {
        this.playwright = playwright;
    }

    public JSONObject createUser() {
        var headers = new HashMap<String, String>();
        headers.put("content-type", "application/json");
        headers.put("Authorization", "Bearer " + "$token");

        var firstName = "Alan";
        var lastName = "Silva";
        var email = STR."teste.\{System.currentTimeMillis()}@teste.com";
        var password = "S7r0ngP4ssW0rd";
        var phone = "21999999999";
        var body = STR."""
            {
                "email": "\{email}",
                "firstName": "\{firstName}",
                "lastName": "\{lastName}",
                "password": "\{password}",
                "phone": "\{phone}"
            }
        """;

        var contextOptions = new APIRequest.NewContextOptions()
                .setBaseURL("http://127.0.0.1:8080")
                .setExtraHTTPHeaders(headers);

        var requestOptions = RequestOptions.create().setData(body);

        var requestContext = this.playwright.request().newContext(contextOptions);

        var response = requestContext.post("/users", requestOptions);

        if (response.ok()) {
            var responseBody = new JSONObject(response.text());
            requestContext.dispose();
            return responseBody;
        } else {
            var responseText = response.text();
            requestContext.dispose();
            throw new AssertionError(STR."Falhou ao criar o usuario: \{responseText}");
        }
    }

    public JSONObject activateUser(String id) {
        var headers = new HashMap<String, String>();
        headers.put("content-type", "application/json");
        headers.put("Authorization", "Bearer " + "$token");

        var contextOptions = new APIRequest.NewContextOptions()
                .setBaseURL("http://127.0.0.1:8080")
                .setExtraHTTPHeaders(headers);

        var requestOptions = RequestOptions.create();

        var requestContext = this.playwright.request().newContext(contextOptions);

        var response = requestContext.patch(STR."/users/activate/\{id}", requestOptions);

        if (response.ok()) {
            var responseBody = new JSONObject(response.text());
            requestContext.dispose();
            return responseBody;
        } else {
            var responseText = response.text();
            requestContext.dispose();
            throw new AssertionError(STR."Falhou ao ativar o usuario: \{responseText}");
        }
    }
}