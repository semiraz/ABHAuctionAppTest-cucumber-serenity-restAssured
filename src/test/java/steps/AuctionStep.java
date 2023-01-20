package steps;

import company.Address;
import company.CreateCreditCardRequest;
import company.CreateProductRequest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.List;

public class AuctionStep {

    @Steps
    RegistrationSubStep registrationSubStep;
    @Steps
    SellerSubStep sellerSubStep;
    @Steps
    ItemSubStep itemSubStep;
    @Steps
    BidSubStep bidSubStep;

    @Given("^User has created an account with first name (.*) and last name (.*)$")
    public void userHasCreatedAnAccount(String firstName, String lastName) {
        registrationSubStep.createNewUser(firstName, lastName);
    }

    @And("^Logged in with username and password$")
    public void loggedInWithUsernameAndPassword() {
        registrationSubStep.login();
        Serenity.setSessionVariable("token").to(registrationSubStep.getAccessToken());
        Serenity.setSessionVariable("userId").to(registrationSubStep.getUserId());
    }

    @When("^User add item (.*) to sell with photos, starting price and fill credit card and shipping form$")
    public void userAddItemToSellWithPhotosStartingPriceAndFillCreditCardAndShippingForm(String itemName) {
        sellerSubStep.postCategory("Shorts", "Woman", Serenity.sessionVariableCalled("token").toString());
        Address address = sellerSubStep.createAddress("SomeSt2", "Sarajevo", "71000", "State", "BiH");
        List<String> imagesUrl =sellerSubStep.addImages("https://images.app.goo.gl/jGhmwiFEiHWfHY1i9", "https://images.app.goo.gl/bt7mKCZGdQivVQkr7",
                "https://images.app.goo.gl/SwRw5jpJCaLanrwT8");
        CreateProductRequest productRequest = sellerSubStep.createProductRequest(itemName, "bla bla", imagesUrl,
                20, "2023-01-18T14:18:06.448Z", "2023-01-21T14:18:06.448Z",
                "Woman", address, Serenity.sessionVariableCalled("userId").toString());
        CreateCreditCardRequest cardRequest = sellerSubStep.createCreditCardInfo("Bla blas", "1234567543218888",
                "2025-01-17T14:18:06.448Z", "123");
        sellerSubStep.postProduct(productRequest, cardRequest, Serenity.sessionVariableCalled("token").toString());
        Serenity.setSessionVariable("productId").to(sellerSubStep.getIdOfProduct());
    }

    @Then("^Item (.*) is successfully added and ready for auction$")
    public void itemIsSuccessfullyAddedAndReadyForAuction(String itemName) {
        itemSubStep.newItemIsCreated(Serenity.sessionVariableCalled("productId").toString(), itemName);
    }

    @When("^User find wanted item (.*)$")
    public void userFindWantedItem(String itemName) {
        Assert.assertTrue(itemSubStep.findWantedItem(itemName));
        Serenity.setSessionVariable("itemName").to(itemSubStep.getItemName(itemName));
    }

    @And("^User place the highest bid on the item$")
    public void userPlaceABidOnItem() {
        bidSubStep.placeABid(Serenity.sessionVariableCalled("itemName").toString(),  Serenity.sessionVariableCalled("token").toString(),
                Serenity.sessionVariableCalled("userId").toString());
    }

    @Then("^Notification with successfully placed highest bid is received$")
    public void notificationWithSuccessMessageIsReceived() {
        Assert.assertTrue(bidSubStep.validateMessage());
    }

    @And("User is logged out")
    public void userIsLoggedOut() {
        registrationSubStep.getLogOut();
    }
}