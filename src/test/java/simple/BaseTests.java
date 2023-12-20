package simple;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTests {
    private static Playwright playwright;
    private static Browser browser;
    private BrowserContext context;
    protected Page page;

    @BeforeAll
    static void beforeAll() {
        playwright = Playwright.create();
        var options = new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500);
        browser = playwright.firefox().launch(options);
    }

    @BeforeEach
    void beforeEach() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void afterEach() {
        context.close();
    }

    @AfterAll
    static void afterAll() {
        playwright.close();
    }
}
