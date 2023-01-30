package sub_steps;

import io.restassured.path.json.JsonPath;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ItemSubStep {

    @Steps
    CommonSubSteps common;
    protected JsonPath js;

    @Step("New Item is Created")
    public void newItemIsCreated(String idProduct, String itemName) {
        common.sendGetRequest("/api/v1/products/" + idProduct);
        common.validateStatusCode(200);
        common.verifyResponseBody("name", itemName);
    }

    @Step("Find Wanted Item")
    public String findWantedItemsId(String itemName) {
        common.sendGetRequest("/api/v1/products/search?criteria=new-arrivals");
        js = common.rawToJson();
        String findItem;
        String idProduct = "";
        for (int i=0; i<js.getInt("content.size()"); i++) {
            findItem = js.getString("content[" +i+ "].name");
            if (findItem.contains(itemName)) {
                idProduct = js.getString("content[" +i+ "].id");
                break;
            }
        }
        return idProduct;
    }

    @Step("Find Wanted Item")
    public boolean findWantedItem(String itemName) {
        common.sendGetRequest("/api/v1/products/search?criteria=new-arrivals");
        js = common.rawToJson();
        String findItem;
        String idProduct = "";
        for (int i=0; i<js.getInt("content.size()"); i++) {
            findItem = js.getString("content[" +i+ "].name");
            if (findItem.equalsIgnoreCase(itemName)) {
                idProduct = js.getString("content[" +i+ "].name");
                break;
            }
        }
        common.validateStatusCode(200);
        return idProduct != null;
    }

    @Step("Get Item Name")
    public String getItemName(String itemName) {
        js = common.rawToJson();
        String findItem;
        for (int i=0; i<js.getInt("content.size()"); i++) {
            findItem = js.getString("content[" +i+ "].name");
            if (findItem.equalsIgnoreCase(itemName)) {
                itemName = findItem;
                break;
            }
        }
        return itemName;
    }
}