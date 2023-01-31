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
import sub_steps.BidSubStep;
import sub_steps.ItemSubStep;
import sub_steps.RegistrationSubStep;
import sub_steps.SellerSubStep;

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

    @Given("^user has created an account with first name (.*) and last name (.*)$")
    public void userHasCreatedAnAccount(String firstName, String lastName) {
        registrationSubStep.createNewAccountWithRandomEmailAndPassword(firstName, lastName);
    }

    @And("^a user logs in with username and password$")
    public void loggedInWithUsernameAndPassword() {
        registrationSubStep.loginWithRandomEmailAndPassword();
        Serenity.setSessionVariable("token").to(registrationSubStep.getAccessToken());
        Serenity.setSessionVariable("userId").to(registrationSubStep.getUserId());
    }

    @And("^user adds an item (.*) to sell with photos, starting price, credit card info and their address$")
    public void userAddItemToSellWithPhotosStartingPriceAndFillCreditCardAndShippingForm(String itemName) {
        //sellerSubStep.postSubcategory("Shorts", "Woman", Serenity.sessionVariableCalled("token").toString());
        Address address = sellerSubStep.createAddress("SomeSt2", "Sarajevo", "71000", "State", "BiH");
        List<String> imagesUrl =sellerSubStep.addImages(itemName);
        CreateProductRequest productRequest = sellerSubStep.createProductRequest(itemName, "bla bla", imagesUrl,
                20, "2023-01-18T14:18:06.448Z", "2023-02-27T14:18:06.448Z",
                "Woman", address, Serenity.sessionVariableCalled("userId").toString());
        CreateCreditCardRequest cardRequest = sellerSubStep.createCreditCardInfo("Bla blas", "1234567543218888",
                "2025-01-17T14:18:06.448Z", "123");
        sellerSubStep.postProduct(productRequest, cardRequest, Serenity.sessionVariableCalled("token").toString());
        Serenity.setSessionVariable("productId").to(sellerSubStep.getIdOfProduct());
    }

    @And("^the item (.*) is successfully added and ready for auction$")
    public void itemIsSuccessfullyAddedAndReadyForAuction(String itemName) {
        itemSubStep.newItemIsCreated(Serenity.sessionVariableCalled("productId").toString(), itemName);
    }

    @When("^user finds (.*) item$")
    public void userFindWantedItem(String itemName) {
        Assert.assertTrue(itemSubStep.findWantedItem(itemName));
        Serenity.setSessionVariable("itemName").to(itemSubStep.getItemName(itemName));
    }

    @And("^user places the highest bid on the item$")
    public void userPlaceABidOnItem() {
        bidSubStep.placeABid(Serenity.sessionVariableCalled("itemName").toString(),  Serenity.sessionVariableCalled("token").toString(),
                Serenity.sessionVariableCalled("userId").toString());
    }

    @Then("^user receives success notification$")
    public void notificationWithSuccessMessageIsReceived() {
        Assert.assertTrue(bidSubStep.validateMessage());
    }

    @And("user is logged out")
    public void userIsLoggedOut() {
        registrationSubStep.getLogOut();
    }

}