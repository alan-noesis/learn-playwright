package simple;

import org.junit.jupiter.api.Test;

public class RegressionTests extends BaseTests {

    @Test
    void CT_Base() {
        page.navigate("https://google.com");
        System.out.println(page.title());
    }

    // Generating tests:
    // gradle run --args="codegen https://www.facebook.com/signup"

    // Stack trace:
    // gradle run --args="show-trace evidences/traces/CT-001.zip"
}
