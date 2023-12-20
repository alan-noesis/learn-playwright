package simple;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

public class SimpleTests {
    private static Playwright playwright;
    private static Browser browser;
    private BrowserContext context;
    private Page page;

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

    @Test
    void CT_001() {
        page.navigate("https://google.com");
        System.out.println(page.title());
    }

    @Test
    void CT_002() {
        page.navigate("http://playwright.dev");
        System.out.println(page.title());
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
