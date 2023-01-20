package steps;

import company.User;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import org.junit.runner.RunWith;
import java.util.HashMap;
import java.util.Map;

@RunWith(SerenityRunner.class)
public class RegistrationSubStep {
    @Steps
    CommonSubSteps common;

    protected User user;
    protected String fullName;
    protected String emailRandom;
    protected String password;
    protected String username;
    protected JsonPath js;
    protected String idUser;

    @Step("Create a new user")
    public void createNewUser(String fName, String lName) {
        RestAssured.baseURI = "http://ec2-3-123-38-247.eu-central-1.compute.amazonaws.com:8080";
        emailRandom = common.generateRandomEmail(3, fName, lName);
        password =  common.randomPassword(2, 1, 2,3);

        user = new User.UserBuilder().setFirstName(fName).setLastName(lName)
                .setEmail(emailRandom).setPassword(password).setRole("1").build();
        common.sendPostRequest(user, "/api/v1/auth/register");
        js = common.rawToJson();
        idUser = js.get("id");
        username = js.get("email");
        String first = js.get("firstName");
        String last = js.get("lastName");
        fullName= first.concat( " " + last);
        Assert.assertEquals(200, common.getStatusCode());
        common.validateStatusCode(200);
    }

    @Step("Login")
    public void login() {
        Map<String, String> loginUser = new HashMap<>();
        loginUser.put("username", username);
        loginUser.put("password", password);
        common.sendPostRequest(loginUser, "/api/v1/auth/login");
        Assert.assertEquals(200, common.getStatusCode());
        common.verifyResponseBody("fullName", fullName);
    }
    @Step("Get User Id")
    public String getUserId() {
        return SerenityRest.lastResponse().jsonPath().getString("id");
    }
    @Step("Get Token")
    public String getAccessToken() {
        return SerenityRest.lastResponse().jsonPath().getString("accessToken");
    }

    @Step("Log Out")
    public void getLogOut() {
        common.sendGetRequest("/api/v1/auth/logout");
        common.validateStatusCode(200);
    }
}