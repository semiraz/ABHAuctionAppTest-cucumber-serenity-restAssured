package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import sub_steps.RegistrationSubStep;

public class AuctionLoginStep {

    @Steps
    RegistrationSubStep registrationSubStep;

    @Given("^User has created an account with first name (.*), last name (.*), email (.*) and password (.*)$")
    public void userHasCreatedAnAccountWithFirstNameLastNameEmailAndPassword(String firstName, String lastName, String email, String password) {
        registrationSubStep.createNewUserL(firstName, lastName, email, password);
    }
    @Given("^New user (.*) has created$")
    public void newUserHasCreated(String fullName) {
        Assert.assertEquals(registrationSubStep.verifyNewUserIsCreated(), fullName);
    }

    @When("^User is on the login page and enters (.*) and (.*)$")
    public void userIsOnTheLoginPageAndEntersUsernameAndPassword(String username, String password) {
        registrationSubStep.login(username, password);
        Serenity.setSessionVariable("token").to(registrationSubStep.getAccessToken());
    }

    @Then("User is successfully logged in")
    public void userIsSuccessfullyLoggedIn() {
        registrationSubStep.userIsLoggedIn(200);
    }

    @Then("^User is not logged in with status (.*)$")
    public void userIsNotLoggedIn(String statusMsg) {
        registrationSubStep.getStatusMessage(statusMsg);
    }

    @And("^User receives (.*)$")
    public void userReceivesMessage(String message) {
        registrationSubStep.getMessage(message);
    }

    @When("User deactivate account")
    public void userDeactivateAccount() {
        registrationSubStep.deactivateAccount(Serenity.sessionVariableCalled("token").toString());
    }
}