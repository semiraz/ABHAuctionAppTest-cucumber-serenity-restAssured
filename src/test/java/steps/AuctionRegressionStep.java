package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import sub_steps.RegistrationSubStep;
import sub_steps.UserSubStep;

public class AuctionRegressionStep {

    @Steps
    RegistrationSubStep registrationSubStep;

    @Steps
    UserSubStep userSubStep;

    @Given("^a user has created an account with first name (.*) and last name (.*)$")
    public void userHasCreatedAnAccountWithFirstNameLastName(String firstName, String lastName) {
        registrationSubStep.createNewAccountWithRandomEmailAndPassword(firstName, lastName);
        Serenity.setSessionVariable("email").to(registrationSubStep.getEmail());
    }

    @Given("^new user (.*) has created$")
    public void newUserHasCreated(String fullName) {
        registrationSubStep.verifyNewUserIsCreated(fullName);
    }

    @Given("^new user (.*) has already created$")
    public void newUserHasAlreadyCreated(String fullName) {
        userSubStep.getUsersInfo("53d669af-a8bd-4735-89dd-06c830b7f0c0");
    }

    @Given("^a user (.*) (.*) has created an account with (.*)$")
    public void userHasCreatedAnAccount(String firstName, String lastName, String email) {
        registrationSubStep.createNewAccountWithRandomEmailAndPassword(firstName, lastName, email);
    }

    @When("^user is on the login page and enters (.*) and (.*)$")
    public void userIsOnTheLoginPageAndEntersUsernameAndPassword(String username, String password) {
        registrationSubStep.login(username, password);
        Serenity.setSessionVariable("token").to(registrationSubStep.getAccessToken());
        Serenity.setSessionVariable("userId").to(registrationSubStep.getUserId());
    }

    @When("user is logged in")
    public void userIsLoggedIn() {
        registrationSubStep.login("lalo_tako@gmail.com", "Pass123*");
        Serenity.setSessionVariable("token").to(registrationSubStep.getAccessToken());
        Serenity.setSessionVariable("userId").to(registrationSubStep.getUserId());
        Serenity.setSessionVariable("username").to(registrationSubStep.getEmail());
    }

    @When("^user is on the login page entering their username and password$")
    public void userIsOnTheLoginPageEnteringUsernameAndPassword() {
        registrationSubStep.loginWithValidPassword(Serenity.sessionVariableCalled("email"));
        Serenity.setSessionVariable("token").to(registrationSubStep.getAccessToken());
        Serenity.setSessionVariable("userId").to(registrationSubStep.getUserId());
    }

    @Then("user is successfully logged in")
    public void userIsSuccessfullyLoggedIn() {
        registrationSubStep.userIsLoggedIn(200);
    }

    @Then("^user is not logged in with status (.*)$")
    public void userIsNotLoggedIn(String statusMsg) {
        registrationSubStep.getStatusMessage(statusMsg);
    }

    @And("^user receives a message (.*)$")
    public void userReceivesMessage(String message) {
        registrationSubStep.getMessage(message);
    }

    @When("user deactivate an account")
    public void userDeactivateAccount() {
        registrationSubStep.deactivateAccount(Serenity.sessionVariableCalled("token").toString());
    }

    @When("^user update (.*) information$")
    public void userUpdateInfo(String info) {
        userSubStep.updateUsersInformation(Serenity.sessionVariableCalled("userId").toString(),
                Serenity.sessionVariableCalled("token").toString(), info,
                Serenity.sessionVariableCalled("username").toString());
    }

    @Then("^updated (.*) information are saved$")
    public void updatedInfoAreSaved(String info) {
        userSubStep.usersInfoAreUpdated(Serenity.sessionVariableCalled("userId").toString(), info);
    }
}