package sub_steps;

import company.*;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
    public List<String> addImages(String itemName) {
        List<String> imagesSweater = new ArrayList<>();
        imagesSweater.add("https://media.gq.com/photos/634056f71784f0443e13b2d6/master/pass/sweaters.jpg");
        imagesSweater.add("https://media.gq.com/photos/632b3b6f25d02accbd22d3fd/master/pass/sweater.jpg");
        imagesSweater.add("https://i.ebayimg.com/images/g/od8AAOSw2elh0bGr/s-l400.jpg");
        Collections.shuffle(imagesSweater);

        List<String> imagesTrousers = new ArrayList<>();
        imagesTrousers.add("https://media.vogue.co.uk/photos/61f42bdf7f84078255af8cbc/2:3/w_2560%2Cc_limit/BW_British_Vogue_01_F10%2520copy.jpg");
        imagesTrousers.add("https://d3qyxwmfroew4h.cloudfront.net/media/catalog/product/cache/395f014b994aafeff4bad31a82fa3a56/m/a/margaret-howell-women-aw22-online-look-1032x1333-036_1_.jpg");
        imagesTrousers.add("https://b2c-media.maxmara.com/sys-master/m0/MM/2022/2/1786022606/002/s3details/1786022606002-x-catullo_thumbnail.jpg");
        Collections.shuffle(imagesTrousers);

        if (itemName.equalsIgnoreCase("Sweater")) {
            return imagesSweater;
        } else if (itemName.equalsIgnoreCase("Trousers")) {
            return imagesTrousers;
        }
        return null;
    }

    @Step("Create Product Request")
    public CreateProductRequest createProductRequest(String nameOfProduct, String description, List<String> imageURLs, int startPrice,
                                     String expirationDateTime, String parentCategoryName, Address address, String userId) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now date = " + now);
        return new CreateProductRequest.CreateProductRequestBuilder().setName(nameOfProduct)
                .setDescription(description).setImageURLs(imageURLs).setStartPrice(startPrice).setCategoryId(getCategoryId(parentCategoryName))
                .setSubcategoryId(getCategoryId(parentCategoryName))
                .setCreationDateTime(String.valueOf(now)).setExpirationDateTime(expirationDateTime).setUserId(userId)
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