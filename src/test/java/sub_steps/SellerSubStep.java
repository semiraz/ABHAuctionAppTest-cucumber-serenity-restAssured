package sub_steps;

import company.*;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RunWith(SerenityRunner.class)
public class SellerSubStep {

    @Steps
    CommonSubSteps common;

    protected JsonPath js;
    protected String categoryId;
    protected String productName;

    @Step("Get category id")
    private String getCategoryId(String parentCategoryName) {
        common.sendGetRequest("/api/v1/categories");
        common.validateStatusCode(200);
        js = common.rawToJson();
        String nameC;
        for (int i=0; i<js.getInt("size()"); i++) {
            nameC = js.getString("[" +i+ "].name");
            if (nameC.contains(parentCategoryName)) {
                categoryId = js.getString("[" +i+ "].id");
                break;
            }
        }
        return categoryId;
    }

    @Step("Post a Subcategory")
    public void postSubcategory(String subCategoryName, String parentCategoryName, String token) {
        HashMap<String, String> category = new HashMap<>();
        category.put("name", subCategoryName);
        category.put("parentCategoryId", getCategoryId(parentCategoryName));
        common.sendPostRequestWithToken(category, "/api/v1/categories", token);
        common.validateStatusCode(200);
    }

    @Step("Create Credit Card Info")
    public CreateCreditCardRequest createCreditCardInfo(String fullName, String number, String expirationDate, String cvv) {
        return new CreateCreditCardRequest.CreateCreditCardRequestBuilder()
                .setHolderFullName(fullName).setNumber(number).setExpirationDate(expirationDate).setVerificationValue(cvv).build();
    }
    @Step("Create Address Info")
    public Address createAddress(String street, String city, String zipCode, String state, String country) {
        return new Address.AddressBuilder().setStreet(street).setCity(city).setZipCode(zipCode)
                .setState(state).setCountry(country).build();
    }

    @Step("Add images of item")
    public List<String> addImages(String image1, String image2, String image3) {
        List<String> images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        return images;
    }

    @Step("Create Product Request")
    public CreateProductRequest createProductRequest(String nameOfProduct, String description, List<String> imagesUrl, int startPrice, String creationDateTime,
                                     String expirationDateTime, String parentCategoryName, Address address, String userId) {
        return new CreateProductRequest.CreateProductRequestBuilder().setName(nameOfProduct)
                .setDescription(description).setImagesUrls(imagesUrl).setStartPrice(startPrice).setCategoryId(getCategoryId(parentCategoryName))
                .setSubcategoryId(getCategoryId(parentCategoryName))
                .setCreationDateTime(creationDateTime).setExpirationDateTime(expirationDateTime).setUserId(userId)
                .setAddress(address).build();
    }

    @Step("Post a Product")
    public void postProduct(CreateProductRequest productRequest, CreateCreditCardRequest cardInfo, String token) {
        CreateNewItem newItem = new CreateNewItem.CreateNewItemBuilder().setCreateProductRequest(productRequest)
                .setCreditCardRequest(cardInfo).build();
        common.sendPostRequestWithToken(newItem, "/api/v1/products", token);
        js = common.rawToJson();
        productName = js.getString("name");
        Assert.assertEquals(200, common.getStatusCode());
    }
    @Step("Get Id of Newly Created Product")
    public String getIdOfProduct() {
        return SerenityRest.lastResponse().jsonPath().getString("id");
    }
}