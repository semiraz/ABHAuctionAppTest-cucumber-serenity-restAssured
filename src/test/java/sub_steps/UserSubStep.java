package sub_steps;

import company.*;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.junit.runner.RunWith;


@RunWith(CucumberWithSerenity.class)
public class UserSubStep {
    @Steps
    CommonSubSteps common;
    protected UpdateUser updateUser;
    protected UpdateUserRequest updateUserRequest;
    protected Address address;
    protected CreateCreditCardRequest updateCreditCardRequest;


    @Step("Update Users Personal Information & Credit Card Info")
    public void updateUsersInformation(String userId, String token, String info, String email) {
        updateUserRequest = new UpdateUserRequest.UpdateUserRequestBuilder().setFirstName(common.getFirstName())
                .setLastName(common.getLastName()).setEmail(email).build();
        if (info.equalsIgnoreCase("personal")) {
            updateUserRequest = new UpdateUserRequest.UpdateUserRequestBuilder().setFirstName(updateUserRequest.getFirstName()).setLastName(updateUserRequest.getLastName())
                    .setEmail(updateUserRequest.getEmail()).setPhoneNumber(common.getRandomNumbers(10)).setProfileImageUrl(common.getImageUrl()).build();
            updateUser = new UpdateUser.UpdateUserBuilder().setUpdateUserRequest(updateUserRequest).build();
        } else if (info.equalsIgnoreCase("address")) {
            address = new Address.AddressBuilder().setCity(common.getRandomString(5)).setCountry(common.getRandomString(6)).setStreet(common.getRandomString(7)).build();
            updateUserRequest = new UpdateUserRequest.UpdateUserRequestBuilder().setFirstName(updateUserRequest.getFirstName()).setLastName(updateUserRequest.getLastName())
                    .setEmail(updateUserRequest.getEmail()).setAddress(address).build();
            updateUser = new UpdateUser.UpdateUserBuilder().setUpdateUserRequest(updateUserRequest).build();
        } else if(info.equalsIgnoreCase("credit card")) {
            String fullName = updateUserRequest.getFirstName().concat(" ").concat(updateUserRequest.getLastName());
            updateCreditCardRequest = new CreateCreditCardRequest.CreateCreditCardRequestBuilder().setHolderFullName(fullName)
                    .setNumber(common.getRandomNumbers(16)).setVerificationValue(common.getRandomNumbers(3)).build();
            updateUserRequest = new UpdateUserRequest.UpdateUserRequestBuilder().setFirstName(updateUserRequest.getFirstName()).setLastName(updateUserRequest.getLastName())
                    .setEmail(updateUserRequest.getEmail()).build();
            updateUser = new UpdateUser.UpdateUserBuilder().setUpdateUserRequest(updateUserRequest).setUpdateCreditCardRequest(updateCreditCardRequest).build();
        }
        common.sendPutRequest(updateUser, "/api/v1/users/" + userId, token);
        common.validateStatusCode(200);
    }

    @Step("Validate if Users Info are updated")
    public void usersInfoAreUpdated(String userId, String info) {
        common.sendGetRequest("/api/v1/users/current/" + userId);
        common.validateStatusCode(200);
    }

    @Step("Get Users Info")
    public void getUsersInfo(String userId) {
        common.sendGetRequest("/api/v1/users/current/" + userId);
        common.verifyResponseBody("id", userId);
    }

}