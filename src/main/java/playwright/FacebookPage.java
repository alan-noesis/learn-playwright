package playwright;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class FacebookPage {
    private final Page page;

    private final Locator INPUT_FIRST_NAME;
    private final Locator INPUT_LAST_NAME;
    private final Locator INPUT_PHONE;
    private final Locator INPUT_PASSWORD;
    private final Locator SELECT_DAY;
    private final Locator SELECT_MONTH;
    private final Locator SELECT_YEAR;
    private final Locator CHECKBOX_MALE;

    public FacebookPage(Page page) {
        this.page = page;

        this.INPUT_FIRST_NAME = page.getByLabel("First name");
        this.INPUT_LAST_NAME = page.getByLabel("Last name");
        this.INPUT_PHONE = page.getByLabel("Mobile number or email");
        this.INPUT_PASSWORD = page.getByLabel("New password");
        this.SELECT_DAY = page.getByLabel("Day");
        this.SELECT_MONTH = page.getByLabel("Month");
        this.SELECT_YEAR = page.getByLabel("Year");
        this.CHECKBOX_MALE = page.getByLabel("Male", new Page.GetByLabelOptions().setExact(true));
    }

    public void navigateTo() {
        page.navigate("https://www.facebook.com/signup");
    }

    public void fillFirstName(String name) {
        INPUT_FIRST_NAME.fill(name);
    }

    public void fillLastName(String name) {
        INPUT_LAST_NAME.fill(name);
    }

    public void fillPhone(String phone) {
        INPUT_PHONE.fill(phone);
    }

    public void fillPassword(String password) {
        INPUT_PASSWORD.fill(password);
    }

    public void selectDay(String day) {
        SELECT_DAY.selectOption(day);
    }

    public void selectMonth(String month) {
        SELECT_MONTH.selectOption(month);
    }

    public void selectYear(String year) {
        SELECT_YEAR.selectOption(year);
    }

    public void checkMale() {
        CHECKBOX_MALE.check();
    }
}
