package playwright;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.nio.file.Paths;

public class BaseTests {
    private static Playwright playwright;
    private static Browser browser;
    private BrowserContext context;
    protected Page page;

    protected String testName;

    // PROJECT SCOPE
    protected FacebookPage facebookPage;
    protected Endpoint endpoint;

    @BeforeAll
    public static void beforeAll() {
        playwright = Playwright.create();
        var options = new BrowserType.LaunchOptions().setHeadless(true).setSlowMo(0);
        browser = playwright.firefox().launch(options);
    }

    @AfterAll
    public static void afterAll() {
        playwright.close();
    }

    @BeforeEach
    public void beforeEach() {
        context = browser.newContext();
        page = context.newPage();
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        // PROJECT SCOPE
        facebookPage = new FacebookPage(page);
        endpoint = new Endpoint(playwright);
    }

    @AfterEach
    public void afterEach() {
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));
        context.close();
    }

    protected void screenshot() {
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshot.png"))
                .setFullPage(true));
    }
}
