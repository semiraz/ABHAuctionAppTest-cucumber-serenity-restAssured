package sub_steps;

import company.BidDetails;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.junit.runner.RunWith;

import java.util.HashMap;

@RunWith(SerenityRunner.class)
public class BidSubStep {
    @Steps
    CommonSubSteps common;
    @Steps
    ItemSubStep itemSubStep;
    BidDetails bidDetails;
    JsonPath js;

    private double getHighestBid(String itemName) {
        common.sendGetRequest("/api/v1/products/" + itemSubStep.findWantedItemsId(itemName));
        js = common.rawToJson();
//        if (js.getInt("size()") == 0) {
//            return js.getDouble("startPrice");
//        } else {
            String idProduct = itemSubStep.findWantedItemsId(itemName);
            common.sendGetRequest("/api/v1/product/" + idProduct + "/bids/highest");
            common.validateStatusCode(200);
            js = common.rawToJson();
            return (double) common.getResponseBody();
    }

    @Step("Place a Bid on the Item")
    public void placeABid(String itemName, String token, String userId) {
        double highestBid = getHighestBid(itemName) + 1;
        bidDetails = new BidDetails.BidDetailsBuilder().setPrice(highestBid)
                .setProductId(itemSubStep.findWantedItemsId(itemName)).setUserId(userId).build();
        common.sendPostRequestWithToken(bidDetails, "/api/v1/product/" + itemSubStep.findWantedItemsId(itemName) + "/bids", token);
        common.validateStatusCode(200);
    }
    @Step("Validate Message for the Highest Bid")
    public boolean validateMessage() {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", bidDetails.getUserId());
        params.put("productId", bidDetails.getProductId());
        common.sendGetRequestWithQueryParams("api/v1/notifications/search", params);
        js = common.rawToJson();
        return js.getString("type").equalsIgnoreCase("HIGHEST_BID_PLACED");
    }
}