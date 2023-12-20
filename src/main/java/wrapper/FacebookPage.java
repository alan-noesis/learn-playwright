package wrapper;

import org.noesis.Actions;
import org.noesis.By;

public class FacebookPage {
    private final Actions actions;

    private final By INPUT_FIRST_NAME = By.label("First name", "First name");
    private final By INPUT_LAST_NAME = By.label("Last name", "Last name");
    private final By INPUT_PHONE = By.label("Number", "Mobile number or email");
    private final By INPUT_PASSWORD = By.label("Password", "New password");
    private final By SELECT_DAY = By.label("Day", "Day");
    private final By SELECT_MONTH = By.label("Month", "Month");
    private final By SELECT_YEAR = By.label("Year", "Year");
    private final By CHECKBOX_MALE = By.xpath("Male", "//input[@type='radio' and @value='2']");

    public FacebookPage(Actions actions) {
        this.actions = actions;
    }

    public void navigateTo() {
        actions.navigate("https://www.facebook.com/signup");
    }

    public void fillFirstName(String name) {
        actions.fill(INPUT_FIRST_NAME, name);
    }

    public void fillLastName(String name) {
        actions.fill(INPUT_LAST_NAME, name);
    }

    public void fillPhone(String phone) {
        actions.fill(INPUT_PHONE, phone);
    }

    public void fillPassword(String password) {
        actions.fill(INPUT_PASSWORD, password);
    }

    public void selectDay(String day) {
        actions.select(SELECT_DAY, day);
    }

    public void selectMonth(String month) {
        actions.select(SELECT_MONTH, month);
    }

    public void selectYear(String year) {
        actions.select(SELECT_YEAR, year);
    }

    public void checkMale() {
        actions.check(CHECKBOX_MALE);
    }
}
