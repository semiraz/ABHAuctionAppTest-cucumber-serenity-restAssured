package sub_steps;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import java.util.HashMap;

public class CommonSubSteps {
    protected String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    protected String lower = "abcdefghijklmnopqrstuvwxyz";
    protected String character = "!@$%&*";
    protected String number = "1234567890";
    protected Response response;

    @Step("Send Get Request")
    public void sendGetRequest(String path) {
        response = SerenityRest.given().when().get(path);
        response.prettyPrint();
    }

    @Step("Send Get Request With Query Params")
    public void sendGetRequestWithQueryParams(String path, HashMap<String, String> mapParams) {
        response = SerenityRest.given().queryParams(mapParams).when().get(path);
        response.prettyPrint();
    }
    @Step("Send Post Request")
    public void sendPostRequest(Object object, String path) {
        response = SerenityRest.given().contentType(ContentType.JSON)
                .body(object).log().all().when().post(path);
        response.prettyPrint();
    }

    @Step("Send Post Request")
    public void sendPostRequestNoBody(String path, String token) {
        response = SerenityRest.given().contentType(ContentType.JSON)
                .header("Authorization", ("Bearer " + token)).log().all().when().post(path);
        response.prettyPrint();
    }
    @Step("Send Post Request With Token")
    public void sendPostRequestWithToken(Object object, String path, String token) {
        response = SerenityRest.given().contentType(ContentType.JSON).header("Authorization", ("Bearer " + token))
                .body(object).log().all().when().post(path);
        response.prettyPrint();
    }
    @Step("Send Put Request")
    public void sendPutRequest(Object object, String path) {
        response = SerenityRest.given().contentType(ContentType.JSON)
                .body(object).log().all().when().put(path);
        response.prettyPrint();
    }

    @Step("Send Delete Request")
    public void sendDeleteRequest(String path) {
        response = SerenityRest.given().contentType(ContentType.JSON)
                .log().all().when().delete(path);
        response.prettyPrint();
    }

    @Step("Validate Status Code")
    public void validateStatusCode(int statusCode) {
        SerenityRest.restAssuredThat(response -> response.statusCode(statusCode));
        response.prettyPrint();
    }

    public JsonPath rawToJson() {
        return new JsonPath(response.asString());
    }

    @Step("Get Status Code")
    public int getStatusCode() {
        return response.getStatusCode();
    }

    @Step("Verify Response Body")
    public void verifyResponseBody(String key, String value) {
        SerenityRest.restAssuredThat(response -> response.body(key, Matchers.equalTo(value)));
    }

    @Step("Get response body")
    public Object getResponseBody() {
        return response.getBody().as(Object.class);
    }

    @Step("Get Random Email-Username")
    public String generateRandomEmail(int length, String name, String lastName) {
        String allowedChars = "1234567890" + "_-.";
        String email;
        String temp = RandomStringUtils.random(length, allowedChars);
        email = name + temp + lastName + "@gmail.com";
        return email;
    }

    @Step("Get Random Password")
    public String randomPassword(int lengthCharacter, int lengthUpper, int lengthLower, int lengthNumber) {
        //"^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"
        String c = RandomStringUtils.random(lengthCharacter, character);
        String u = RandomStringUtils.random(lengthUpper, upper);
        String l = RandomStringUtils.random(lengthLower, lower);
        String n = RandomStringUtils.random(lengthNumber, number);
        return c.concat(u).concat(l).concat(n);
    }
}