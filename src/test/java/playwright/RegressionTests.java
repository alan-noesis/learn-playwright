package playwright;

import org.junit.jupiter.api.Test;

public class RegressionTests extends BaseTests {

    @Test
    public void CT_001() {
        var json = endpoint.createUser();
        var id = json.getString("id");
        var newJson = endpoint.activateUser(id);
        var status = newJson.getString("status");

        facebookPage.navigateTo();
        facebookPage.fillFirstName(json.getString("firstName"));
        facebookPage.fillLastName(json.getString("lastName"));
        facebookPage.fillPhone(json.getString("phone"));
        facebookPage.fillPassword(json.getString("password"));
        facebookPage.selectDay("25");
        facebookPage.selectMonth("8");
        facebookPage.selectYear("2000");
        facebookPage.checkMale();
        screenshot();
    }
}